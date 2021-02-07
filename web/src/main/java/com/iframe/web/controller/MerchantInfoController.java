package com.iframe.web.controller;


import com.alibaba.fastjson.JSON;
import com.iframe.common.annotations.CheckToken;
import com.iframe.common.utils.PageResult;
import com.iframe.common.utils.ResponseResult;
import com.iframe.common.utils.RetResponse;
import com.iframe.interfaces.model.EquipmentInfoEntity;
import com.iframe.interfaces.model.MerchantInfoEntity;
import com.iframe.interfaces.model.dto.EquipmentInfoDto;
import com.iframe.interfaces.model.dto.MerchantInfoDto;
import com.iframe.interfaces.service.IMerchantInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/iframe/merchantInfo")
@Api(tags = "MerchantInfoController", description = "商户信息管理")
public class MerchantInfoController {


    @Autowired
    IMerchantInfoService merchantInfoService;


    @CheckToken(value = true)
    @ApiOperation(value="获取菜单表格", notes="获取菜单表格, Header : X-token 以及 X-userId")
    @RequestMapping(value ="/getDataTable",method = RequestMethod.GET)
    @ResponseBody
    public String  getDataTable(String userId){
        PageResult<MerchantInfoEntity> data = merchantInfoService.findByPage(userId);
        return JSON.toJSONString(data);
    }


    @ApiOperation(value="添加商户信息", notes="添加商户信息")
    @RequestMapping(value ="/add",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<String> login(@RequestBody MerchantInfoDto merchantInfoDto) throws Exception {
        if(merchantInfoService.addMerchantInfo(merchantInfoDto));
        return RetResponse.makeOKRsp("添加成功");
    }

    @CheckToken(value = true)
    @ApiOperation(value="寻找条目", notes="寻找条目, Header : X-token 以及 X-userId")
    @RequestMapping(value ="/getMerchantById",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult  getEquById(Integer id){
        MerchantInfoEntity merchantInfoEntity = merchantInfoService.getMerchanrById(id);
        return RetResponse.makeOKRsp(merchantInfoEntity);
    }
}
