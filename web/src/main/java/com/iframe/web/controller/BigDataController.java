package com.iframe.web.controller;

import com.iframe.common.annotations.CheckToken;
import com.iframe.common.utils.ResponseResult;
import com.iframe.common.utils.RetResponse;
import com.iframe.interfaces.dao.systemDao.AreaInfoDao;
import com.iframe.interfaces.dao.systemDao.HospitalGdDao;
import com.iframe.interfaces.dao.systemDao.HospitalWgDao;
import com.iframe.interfaces.model.systemModel.AreaInfoEntity;
import com.iframe.interfaces.model.systemModel.HospitalGdEntity;
import com.monitorjbl.xlsx.StreamingReader;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

@RestController
@RequestMapping("/iframe/bigData")
@Api(tags = "BigDataController", description = "读取大文件")
public class BigDataController {


    @Autowired
    private HospitalGdDao hospitalGdDao;

    @Autowired
    private AreaInfoDao areaInfoDao;

    @Autowired
    private HospitalWgDao hospitalWgDao;

//    @CheckToken(value = false)
//    @ApiOperation(value="寻找条目", notes="读取大文件")
//    @RequestMapping(value ="/getExcelData",method = RequestMethod.GET)
//    @ResponseBody
//    public ResponseResult getProvince(Integer levelType) throws Exception {
//        testFinalLoad();
//        return RetResponse.makeOKRsp(11);
//    }


    @CheckToken(value = false)
    @ApiOperation(value="清洗数据", notes="清洗数据")
    @RequestMapping(value ="/cleanExcel",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult cleanExcel(Integer levelType) throws Exception {
        testClean();
        return RetResponse.makeOKRsp(11);
    }

    @CheckToken(value = false)
    @ApiOperation(value="清洗高德数据", notes="清洗数据")
    @RequestMapping(value ="/cleanGDExcel",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult cleanGDExcel(Integer levelType) throws Exception {
        testGdClean();
        return RetResponse.makeOKRsp(11);
    }



    @CheckToken(value = false)
    @ApiOperation(value="提取高德字段以及地区码", notes="清洗数据")
    @RequestMapping(value ="/getLocation",method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public ResponseResult getLocation(Integer levelType) throws Exception {
        testFinalLoad();
        return RetResponse.makeOKRsp(11);
    }


    public  void testLoad() throws Exception{
//        System.out.println("============================开始时间：" + new Date()+"==================================");
//        String path = File.separator +"usr"+File.separator+"java"+File.separator+"外购医院表.xlsx";
////        String path ="D:\\hospitalBuy\\外购医院表.xlsx";
//        FileInputStream in = new FileInputStream(path);
//        Workbook wk = StreamingReader.builder()
//                .rowCacheSize(100)  //缓存到内存中的行数，默认是10
//                .bufferSize(4096)  //读取资源时，缓存到内存的字节大小，默认是1024
//                .open(in);  //打开资源，必须，可以是InputStream或者是File，注意：只能打开XLSX格式的文件
//        Sheet sheet = wk.getSheetAt(0);
//        List<String>  res1 = new ArrayList<>();
//        List<String> list = new ArrayList<>();
////        //遍历所有的行
//        for (Row row : sheet) {
//            System.out.println("开始遍历第" + row.getRowNum() + "行数据：");
//            //遍历所有的列
//
//            System.out.println("======"+row.getCell(8).getStringCellValue() + " ");
//            String hosName = row.getCell(8).getStringCellValue();
//            String city = row.getCell(13).getStringCellValue();
//            String alias = "";
//
//            if( row.getCell(9) == null || row.getCell(9).equals(" ")){
//                list = hospitalGdDao.findByHospitalNameLike(city,hosName);
//            }else{
//                String hosAlias = row.getCell(9).getStringCellValue();
//                list = hospitalGdDao.findByHospitalNameLikeOrHospitalAliasLike(city,hosName,hosAlias);
//            }
//            if(list.size() > 0){
//                res1.add(hosName);
//            }
//            System.out.println("======当前命中数:"+res1.size()+ " ");
//        }
//        System.out.println("============================结束时间：" + new Date()+"==================================");
    }


    public void testClean() throws FileNotFoundException {

            System.out.println("============================开始时间：" + new Date()+"==================================");
           String path = File.separator +"usr"+File.separator+"java"+File.separator+"外购医院表.xlsx";
//            String path ="D:\\hospitalBuy\\外购医院表.xlsx";
            FileInputStream ins = new FileInputStream(path);
            Workbook wk = StreamingReader.builder().rowCacheSize(100).bufferSize(4096).open(ins);
            Sheet sheet = wk.getSheetAt(0);
            List<String>  res1 = new ArrayList<>();
            List<HospitalGdEntity> list = new ArrayList<>();
        for (Row row : sheet) {
            System.out.println("开始遍历第" + row.getRowNum() + "行数据：");
            //遍历所有的列
            System.out.println("======"+row.getCell(8).getStringCellValue() + " ");

            //先去除名字里的省市区
            String hosName = row.getCell(8).getStringCellValue()
                    .replace("省","")
                    .replace("市","")
                    .replace("区","")
                    .replace("自治区","")
                    .replace("县","")
                    .replace("盟","")
                    .replace("旗","")
                    .replace("自治州","")
                    .replace("新疆生产建设兵团","");
            String province = row.getCell(11).getStringCellValue().replace("省","").replace("自治区","");
            String city = row.getCell(12).getStringCellValue().replace("市","").replace("自治州","").replace("盟","").replace("县","");
            String district = row.getCell(13).getStringCellValue().replace("区","").replace("县","").replace("旗","");
            hosName = hosName.replace(province,"").replace(city,"").replace(district,"");


            HospitalGdEntity hos = new HospitalGdEntity();
            hos.setHospitalName(row.getCell(8).getStringCellValue());
            if (row.getCell(9) == null || row.getCell(9).equals(" ")) {
                hos.setHospitalAlias("");
            } else {
                hos.setHospitalAlias(row.getCell(9).getStringCellValue());
            }
            hos.setAmpCode(hosName);
            hos.setProvince(row.getCell(11).getStringCellValue());
            hos.setCity(row.getCell(12).getStringCellValue());
            hos.setDistrict(row.getCell(13).getStringCellValue());
            hos.setAdress(row.getCell(14).getStringCellValue());
            hos.setType(row.getCell(15).getStringCellValue());
            hos.setLevel(row.getCell(16).getStringCellValue());
//            hospitalGdDao.save(hos);
            list.add(hos);

            System.out.println("====当前简称===：" +  hosName);
        }
        hospitalGdDao.saveAll(list);
    }



     public void testGdClean() throws FileNotFoundException {
         System.out.println("============================开始时间：" + new Date()+"==================================");
         String path = File.separator +"usr"+File.separator+"java"+File.separator+"gdsj.xlsx";
//         String path ="D:\\hospitalBuy\\gdsj.xlsx";
         FileInputStream ins = new FileInputStream(path);
         System.out.println("============================开始第一步解析：" + new Date()+"==================================");
         Workbook wk = StreamingReader.builder().rowCacheSize(100).bufferSize(4096).open(ins);
         System.out.println("============================开始第二步解析：" + new Date()+"==================================");
         Sheet sheet = wk.getSheetAt(0);
         List<String>  res1 = new ArrayList<>();
         List<HospitalGdEntity> list = new ArrayList<>();
         for (Row row : sheet) {
             System.out.println("开始遍历第" + row.getRowNum() + "行数据：");
             //遍历所有的列
             System.out.println("======"+row.getCell(0).getStringCellValue() + " ");

//             先去除名字里的省市区
             String hosName = row.getCell(0).getStringCellValue()
                     .replace("省","")
                     .replace("市","")
                     .replace("区","")
                     .replace("自治区","")
                     .replace("县","")
                     .replace("盟","")
                     .replace("旗","")
                     .replace("自治州","")
                     .replace("新疆生产建设兵团","");
             String province = row.getCell(6).getStringCellValue().replace("省","").replace("自治区","");
             String city = row.getCell(8).getStringCellValue().replace("市","").replace("自治州","").replace("盟","").replace("县","");
             String district = row.getCell(10).getStringCellValue().replace("区","").replace("县","").replace("旗","");
             hosName = hosName.replace(province,"").replace(city,"").replace(district,"");


             HospitalGdEntity hos = new HospitalGdEntity();
             hos.setHospitalName(row.getCell(0).getStringCellValue());
             hos.setHospitalAlias(row.getCell(1).getStringCellValue());
             hos.setAmpCode(row.getCell(2).getStringCellValue());
             hos.setAdCode(row.getCell(5).getStringCellValue());
             hos.setProvince(row.getCell(6).getStringCellValue());
             hos.setProvinceCode(row.getCell(7).getStringCellValue());
             hos.setCity(row.getCell(8).getStringCellValue());
             hos.setCityCode(row.getCell(9).getStringCellValue());
             hos.setDistrict(row.getCell(10).getStringCellValue());
             hos.setDistrictCode(row.getCell(11).getStringCellValue());
             if (row.getCell(12) == null || row.getCell(12).equals(" ")) {
                 hos.setAdress("");
             } else {
                 hos.setAdress(row.getCell(12).getStringCellValue());
             }

             hos.setLocationX(row.getCell(13).getStringCellValue());
             hos.setLocationY(row.getCell(14).getStringCellValue());
             hos.setLocationY(row.getCell(14).getStringCellValue());
//             hos.setShortName(hosName);


//            hospitalGdDao.save(hos);
             list.add(hos);

             System.out.println("====当前简称===：");
         }
         System.out.println("====开始导入==============================");
         hospitalGdDao.saveAll(list);
     }



    @Transactional
    public  void  testFinalLoad() throws Exception{
        System.out.println("============================开始时间：" + new Date()+"==================================");
        String path = File.separator +"usr"+File.separator+"java"+File.separator+"外购医院表.xlsx";
//        String path ="D:\\hospitalBuy\\外购医院表.xlsx";
        FileInputStream in = new FileInputStream(path);
        Workbook wk = StreamingReader.builder()
                .rowCacheSize(100)  //缓存到内存中的行数，默认是10
                .bufferSize(4096)  //读取资源时，缓存到内存的字节大小，默认是1024
                .open(in);  //打开资源，必须，可以是InputStream或者是File，注意：只能打开XLSX格式的文件
        Sheet sheet = wk.getSheetAt(0);
        List<String>  res1 = new ArrayList<>();
        List<HospitalGdEntity> list = new ArrayList<>();
        List<String> idlist =  new ArrayList<>();
//        //遍历所有的行
        for (Row row : sheet) {
            System.out.println("===================开始遍历第" + row.getRowNum() + "行数据=====================");
            //遍历所有的列

            System.out.println("======"+row.getCell(8).getStringCellValue() + " ");
            String hosName = row.getCell(9).getStringCellValue();
            String city = row.getCell(12).getStringCellValue();
            String distict = row.getCell(13).getStringCellValue();
            System.out.println("====市==>"+city + " ");
            System.out.println("====区==>"+distict+ " ");
            System.out.println("====简称==>"+hosName + " ");

            list = hospitalGdDao.findByDistrictAndHospitalNameAndCity(distict,hosName,city);
            if(list.size() > 0){
                res1.add(hosName);
                idlist.add(row.getCell(17).getStringCellValue());
                System.out.println("需要补充经纬度的记录序号: " + row.getCell(17).getStringCellValue());
                Integer id = Integer.valueOf(row.getCell(17).getStringCellValue().replace("\"",""));
                hospitalWgDao.saveDoto(list.get(0).getLocationX(),list.get(0).getLocationY(),id);
            }
            System.out.println("======当前命中数:"+res1.size()+ " ");

//            return  "ok";

        }

        System.out.println("============================结束时间：" + new Date()+"==================================");
//        return  "ol";
    }


    public  void getLocation() throws Exception{
       //遍历医院
        int id = 1;
        List<HospitalGdEntity> wgList = new ArrayList<>();
        for(int i = 310153; i<324255;i++){
            //寻找单条医院
            HospitalGdEntity wg  = hospitalGdDao.getOne(i);
            //获取省市区地区码;
            AreaInfoEntity  areaInfoEntity =  areaInfoDao.getCityAndProvince(wg.getCity());
            String cityCode = "";
            String provinceCode = "";
            String distictCode = "";
            if(areaInfoEntity != null) {
                 cityCode = String.valueOf(areaInfoEntity.getId());
                 if(!cityCode.equals("") && cityCode != null  &&!cityCode.equals("null")){
                    wg.setCityCode(cityCode);
                 }
                 provinceCode = String.valueOf(areaInfoEntity.getParentId());
                if(!provinceCode.equals("") && provinceCode != null &&!provinceCode.equals("null")){
                    wg.setProvinceCode(provinceCode);
                 }
                distictCode = String.valueOf(areaInfoDao.getDistinctId(wg.getDistrict(), Integer.valueOf(cityCode)));
                if(!distictCode.equals("") && distictCode != null && !distictCode.equals("null")){
                    wg.setDistrictCode(distictCode);
                }
            }
            String level = "";
            switch (wg.getLevel()){
                case "一级甲等":
                    level = "一级医院";
                    wg.setLevel(level);
                    break;
                case "一级丙等":
                    level = "一级医院";
                    wg.setLevel(level);
                    break;
                case "一级乙等":
                    level = "一级医院";
                    wg.setLevel(level);
                    break;
                case "一级未定等":
                    level = "一级医院";
                    wg.setLevel(level);
                    break;
                case "三级丙等":
                    level = "三级医院";
                    wg.setLevel(level);
                    break;
                case "三级乙等":
                    level = "三级乙等";
                    wg.setLevel(level);
                    break;
                case "三级合格":
                    level = "三级医院";
                    wg.setLevel(level);
                    break;
                case "三级未定等":
                    level = "三级医院";
                    wg.setLevel(level);
                    break;
                case "三级甲等":
                    level = "三级甲等";
                    wg.setLevel(level);
                    break;
                case "不适用":
                    level = "未评级";
                    wg.setLevel(level);
                    break;
                case "二级丙等":
                    level = "二级医院";
                    wg.setLevel(level);
                    break;
                case "二级乙等":
                    level = "二级乙等";
                    wg.setLevel(level);
                    break;
                case "二级甲等":
                    level = "二级甲等";
                    wg.setLevel(level);
                    break;
                case "二级未定等":
                    level = "二级医院";
                    wg.setLevel(level);
                    break;
                case "未分级":
                    level = "未评级";
                    wg.setLevel(level);
                    break;
                case "未知":
                    level = "未评级";
                    wg.setLevel(level);
                    break;
                case "机构等级":
                    level = "机构等级";
                    wg.setLevel(level);
                    break;
            }
            hospitalGdDao.save(wg);
            wgList.add(wg);
            System.out.println("=======第"+wg.getId()+"条===========" + wg.getHospitalName() +"=======导入完成=====");
        }
//        System.out.println("======开始入库=====");
//        hospitalGdDao.saveAll(wgList);
    }


}

