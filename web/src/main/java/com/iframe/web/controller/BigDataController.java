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


    public  void testLoad() throws Exception{
        System.out.println("============================开始时间：" + new Date()+"==================================");
        String path = File.separator +"usr"+File.separator+"java"+File.separator+"外购医院表.xlsx";
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
//        FileInputStream ins = new FileInputStream("D:\\hospitalBuy\\gdName.xlsx");
//        Workbook wks = StreamingReader.builder()
//                .rowCacheSize(100)  //缓存到内存中的行数，默认是10
//                .bufferSize(4096)  //读取资源时，缓存到内存的字节大小，默认是1024
//                .open(ins);  //打开资源，必须，可以是InputStream或者是File，注意：只能打开XLSX格式的文件
//        Sheet sheets = wks.getSheetAt(0);
//        List<String> res2 = new ArrayList<>();
//        //遍历所有的行
//        for (Row row : sheets) {
//            System.out.println("开始遍历第" + row.getRowNum() + "行数据：");
//            //遍历所有的列
//
//            System.out.print("======"+row.getCell(0).getStringCellValue() + " ");
//
//            String hosName = row.getCell(0).getStringCellValue();
//            res2.add(hosName);
//
//        }
//        List<String> result  = new ArrayList<>();
//       for(int i = 0;i<res2.size();i++){
//           for(int j = 0;j<res1.size();j++){
//               if(res1.get(j).contains(res2.get(i))){
//                   result.add(res1.get(j));
//               }
//           }
//       }


    }


}

