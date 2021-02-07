package com.iframe.web.controller;


import com.iframe.common.annotations.CheckToken;
import com.iframe.common.utils.ResponseResult;
import com.iframe.common.utils.RetResponse;
import com.iframe.interfaces.model.EquipmentInfoEntity;
import com.iframe.interfaces.model.dto.EquipmentInfoDto;
import com.iframe.interfaces.model.systemModel.AreaInfoEntity;
import com.iframe.interfaces.service.IAreaInfoService;
import com.iframe.interfaces.service.IEquipmentInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/iframe/areaInfo")
@Api(tags = "AreaInfoController", description = "地区列表")
public class AreaInfoController {

    @Autowired
    IAreaInfoService areaInfoService;


    @CheckToken(value = true)
    @ApiOperation(value="寻找条目", notes="寻找条目, Header : X-token 以及 X-userId")
    @RequestMapping(value ="/getProvince",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult  getProvince(Integer levelType){
        List<AreaInfoEntity> areaInfoEntities = areaInfoService.findByLevelType(levelType);
        return RetResponse.makeOKRsp(areaInfoEntities);
    }


    @CheckToken(value = true)
    @ApiOperation(value="寻找条目", notes="寻找条目, Header : X-token 以及 X-userId")
    @RequestMapping(value ="/getChildArea",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult  getChildArea(Integer parentId){
        List<AreaInfoEntity> areaInfoEntities = areaInfoService.findByParentId(parentId);
        return RetResponse.makeOKRsp(areaInfoEntities);
    }

    @CheckToken(value = true)
    @ApiOperation(value="寻找条目", notes="寻找条目, Header : X-token 以及 X-userId")
    @RequestMapping(value ="/getProvinceId",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult  getProvinceId(Integer cityId){
        Integer provinceId= areaInfoService.getProvinceId(cityId);
        return RetResponse.makeOKRsp(provinceId);
    }

    @CheckToken(value = true)
    @ApiOperation(value="根据ID寻找", notes="寻找条目, Header : X-token 以及 X-userId")
    @RequestMapping(value ="/getById",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult  getById(Integer cityId){
        AreaInfoEntity entity = areaInfoService.getById(cityId);
        return RetResponse.makeOKRsp(entity);
    }
}
