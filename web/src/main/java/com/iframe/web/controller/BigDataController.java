package com.iframe.web.controller;

import com.iframe.common.annotations.CheckToken;
import com.iframe.common.utils.ResponseResult;
import com.iframe.common.utils.RetResponse;
import com.iframe.interfaces.dao.systemDao.HospitalGdDao;
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

    @CheckToken(value = false)
    @ApiOperation(value="寻找条目", notes="读取大文件")
    @RequestMapping(value ="/getExcelData",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getProvince(Integer levelType) throws Exception {
        testLoad();
        return RetResponse.makeOKRsp(11);
    }


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


    public  void testLoad() throws Exception{
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
        List<String> list = new ArrayList<>();
//        //遍历所有的行
        for (Row row : sheet) {
            System.out.println("开始遍历第" + row.getRowNum() + "行数据：");
            //遍历所有的列

            System.out.println("======"+row.getCell(8).getStringCellValue() + " ");
            String hosName = row.getCell(8).getStringCellValue();
            String city = row.getCell(13).getStringCellValue();
            String alias = "";

            if( row.getCell(9) == null || row.getCell(9).equals(" ")){
                list = hospitalGdDao.findByHospitalNameLike(city,hosName);
            }else{
                String hosAlias = row.getCell(9).getStringCellValue();
                list = hospitalGdDao.findByHospitalNameLikeOrHospitalAliasLike(city,hosName,hosAlias);
            }
            if(list.size() > 0){
                res1.add(hosName);
            }
            System.out.println("======当前命中数:"+res1.size()+ " ");
        }
        System.out.println("============================结束时间：" + new Date()+"==================================");
    }


    public void testClean() throws FileNotFoundException {

            System.out.println("============================开始时间：" + new Date()+"==================================");
//           String path = File.separator +"usr"+File.separator+"java"+File.separator+"外购医院表.xlsx";
            String path ="D:\\hospitalBuy\\外购医院表.xlsx";
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
         Workbook wk = StreamingReader.builder().rowCacheSize(100).bufferSize(8192).open(ins);
         System.out.println("============================开始第二步解析：" + new Date()+"==================================");
         Sheet sheet = wk.getSheetAt(0);
         List<String>  res1 = new ArrayList<>();
         List<HospitalGdEntity> list = new ArrayList<>();
         for (Row row : sheet) {
             System.out.println("开始遍历第" + row.getRowNum() + "行数据：");
             //遍历所有的列
             System.out.println("======"+row.getCell(0).getStringCellValue() + " ");

             //先去除名字里的省市区
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
             hos.setShortName(hosName);


//            hospitalGdDao.save(hos);
             list.add(hos);

             System.out.println("====当前简称===：" +  hosName);
         }
         System.out.println("====开始导入==============================");
         hospitalGdDao.saveAll(list);
     }



}

