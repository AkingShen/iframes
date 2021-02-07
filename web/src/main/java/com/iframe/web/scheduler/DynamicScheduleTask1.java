package com.iframe.web.scheduler;

import com.alibaba.fastjson.JSONArray;
import com.iframe.common.httpUtils.HttpUtil;
import com.iframe.interfaces.dao.commonDao.SysCronConfigDao;
import com.iframe.interfaces.dao.systemDao.DistrictInfoDao;
import com.iframe.interfaces.dao.systemDao.HospitalsInfoDao;
import com.iframe.interfaces.model.systemModel.DistrictsInfoEntity;
import com.iframe.interfaces.model.systemModel.HospitalsInfoEntity;
import com.iframe.interfaces.model.vo.JsonRootBean;
import com.iframe.interfaces.model.vo.Pois;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Component
@Configuration
@EnableScheduling
public class DynamicScheduleTask1 implements SchedulingConfigurer {

   @Autowired
   SysCronConfigDao sysCronConfigDao;

   @Autowired
   DistrictInfoDao districtInfoDao;

   @Autowired
    HospitalsInfoDao hospitalsInfoDao;


   Map<String,Integer> concurrentHashMap = new ConcurrentHashMap<String,Integer>();



    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> doExcute(),
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1 从数据库获取执行周期
                    String cronExpress = sysCronConfigDao.getCron().getCronExpress();
                    //2.2 合法性校验.
                    if (StringUtils.isEmpty(cronExpress)) {
                        // Omitted Code ..
                    }
                    //2.3 返回执行周期(Date)
                    return new CronTrigger(cronExpress).nextExecutionTime(triggerContext);
                }
        );

    }

    public  void getAdCode(){
        Integer code = 0;
        Integer nums = 0;
        int currentNums =  0;
        if(!concurrentHashMap.containsKey("currentCode")){
            concurrentHashMap.put("currentCode",code);
            concurrentHashMap.put("currentNums",currentNums);
            concurrentHashMap.put("nums",nums);
        }else{
            code = concurrentHashMap.get("currentCode");
            currentNums =  concurrentHashMap.get("currentNums");
            nums =  concurrentHashMap.get("nums");
            if(currentNums == nums){
                code = code +1;
                concurrentHashMap.put("currentCode",code);
                concurrentHashMap.put("nums",0);
                concurrentHashMap.put("currentNums",0);
            }else{
                currentNums = currentNums + 1;
                concurrentHashMap.put("currentNums",currentNums);
            }
        }

    }

    public boolean  checkAdcode(DistrictsInfoEntity die){
        String adcode = die.getAdcode();
        String name = die.getName();
        String regx  = adcode.substring(2,6);
        System.out.println(regx);
        if(adcode.substring(2,6).equals("0000")){
            return false;
        }else if (adcode.substring(4,6).equals("00")){
            return false;
        }else if(adcode.substring(4,6).equals("01") && name.contains("市辖区")){
            return false;
        }
        return true;
    }


    public void  insertDb(JsonRootBean jsonRootBean){
        List<Pois>  pois = new ArrayList<>();
        List<HospitalsInfoEntity>  hosList = new ArrayList<>();
        if(jsonRootBean != null ){
            if(Integer.valueOf(jsonRootBean.getCount()) != 0){
                if(jsonRootBean.getPois().size() >0){
                    pois = jsonRootBean.getPois();
                    for(Pois po : pois){
                        HospitalsInfoEntity hos = new HospitalsInfoEntity();
                        hos.setHospitalName(po.getName());
                        if(po.getAlias() != null){
                            hos.setHospitalAlias(po.getAlias());
                        }
                        hos.setAmpCode(po.getTypecode());
                        if(po.getType() != null){
                            String[] s = po.getType().split(";");
                            hos.setType(s[1]);
                            hos.setLevel(s[2]);
                        }
                        hos.setAdCode(po.getAdcode());
                        hos.setProvince(po.getPname());
                        hos.setProvinceCode(po.getPcode());
                        hos.setCity(po.getCityname());
                        hos.setCityCode(po.getCitycode());
                        hos.setDistrict(po.getAdname());
                        hos.setDistrictCode(po.getAdcode());
                        hos.setAdress(po.getAddress());
                        String[] location = po.getLocation().split(",");
                        hos.setLocationX(location[0]);
                        hos.setLocationY(location[1]);
                        hos.setCreateTime(new Date());
                        hosList.add(hos);
                        hospitalsInfoDao.saveAll(hosList);
                    }
                }
            }
        }
    }

    @Transactional
    public void doExcute(){
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("<=======================================>  开始执行任务 : "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) +"<======================================>");
        System.out.println("<=======================================>  执行前 : currentCode ："+ concurrentHashMap.get("currentCode")  +"currentNums :"+concurrentHashMap.get("currentNums")+"nums :"+concurrentHashMap.get("nums")+"<======================================>");
        getAdCode();
        Integer code = concurrentHashMap.get("currentCode");
        DistrictsInfoEntity die =  districtInfoDao.getById(code);

        Integer currentNums = concurrentHashMap.get("currentNums");

        //判断是否是县级市
        if(!checkAdcode(die)){
            concurrentHashMap.put("currentNums",0);
            concurrentHashMap.put("nums",0);
            System.out.println("地级市不予获取" + die.getAdcode() +" :"+ die.getName());
            System.out.println("<=======================================>  结束任务 : "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) +"<======================================>");
            return;
        }
        if(code > 1768){
            System.out.println("已经完成");
            return;
        }

        String url  = "https://restapi.amap.com/v3/place/text";
        String params = "citylimit=true&key=c3a741cea743555498d279fc1c40623b&extensions=all&output=JSON&offset=50";
        params= params+"&types=" + URLEncoder.encode("090200|090100");
        params = params + "&city="+die.getAdcode();
        if(currentNums == 0){
            params = params + "&page=1";
            concurrentHashMap.put("currentNums",1);
        }else{
            params = params + "&page="+currentNums;
        }

        System.out.println( "================currentNums :" +currentNums+"<===================>");
        System.out.println( "================adCode :" +die.getAdcode()+"<===================>");
        System.out.println("<=======================================>  高德数据  pull start  <======================================>");
        System.out.println("<=======================================>  参数准备就绪 :  adcode : "+params +"<======================================>");
        String  str = HttpUtil.doGetRequest(url,params);
        JsonRootBean jsonRootBean =  JSONArray.parseObject(str, JsonRootBean.class);
        //插入DB
        insertDb(jsonRootBean);
        int count= Integer.valueOf(jsonRootBean.getCount());
        int nums = (count/50);
        if(count%50 != 0){
            nums= nums +1;
        }
        concurrentHashMap.put("nums",nums);
        System.out.println("<=======================================>  高德数据  pull over <======================================>");
        System.out.println("<=======================================>  本次请求结束 :  响应参数: "+str +"<======================================>");
        System.out.println("<=======================================>  结束任务 : "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) +"<======================================>");
    }


}
