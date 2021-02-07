package com.iframe.web.controller;


import com.alibaba.fastjson.JSON;
import com.iframe.common.annotations.CheckToken;
import com.iframe.common.utils.PageResult;
import com.iframe.common.utils.ResponseResult;
import com.iframe.common.utils.RetResponse;
import com.iframe.interfaces.model.EquipmentInfoEntity;
import com.iframe.interfaces.model.UserAccountEntity;
import com.iframe.interfaces.model.dto.EquipmentInfoDto;
import com.iframe.interfaces.model.dto.LoginDto;
import com.iframe.interfaces.model.systemModel.SystemRoleInfoEntity;
import com.iframe.interfaces.model.vo.LoginVo;
import com.iframe.interfaces.service.IEquipmentInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/iframe/equipmentInfo")
@Api(tags = "EquipmentInfoController", description = "消防设备列表")
public class EquipmentInfoController {

    @Autowired
    IEquipmentInfoService equipmentInfoService;


    @ApiOperation(value="添加消防设备", notes="添加消防设备")
    @RequestMapping(value ="/add",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<String> login(@RequestBody EquipmentInfoDto equipmentInfoDto) throws Exception {
        if(equipmentInfoService.addEquipmentInfo(equipmentInfoDto));
        return RetResponse.makeOKRsp("添加成功");
    }


    @CheckToken(value = true)
    @ApiOperation(value="获取菜单表格", notes="获取菜单表格, Header : X-token 以及 X-userId")
    @RequestMapping(value ="/getDataTable",method = RequestMethod.GET)
    @ResponseBody
    public String  getDataTable(String userId){
        PageResult<EquipmentInfoEntity> data = equipmentInfoService.findByPage(userId);
        return JSON.toJSONString(data);
    }

    @CheckToken(value = true)
    @ApiOperation(value="寻找条目", notes="寻找条目, Header : X-token 以及 X-userId")
    @RequestMapping(value ="/getEquById",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult  getEquById(Integer id){
       EquipmentInfoEntity equipmentInfoEntity = equipmentInfoService.getEquById(id);
        return RetResponse.makeOKRsp(equipmentInfoEntity);
    }



    @CheckToken(value = true)
    @ApiOperation(value="删除记录", notes="删除记录, Header : X-token 以及 X-userId")
    @RequestMapping(value ="/deleteEqu",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult  deleteEqu(Integer id){
        boolean isSuccess = equipmentInfoService.deleteById(id);
        return RetResponse.makeOKRsp(isSuccess);
    }
}
