package com.iframe.web.controller;


import com.alibaba.fastjson.JSON;
import com.iframe.common.annotations.CheckToken;
import com.iframe.common.utils.PageResult;
import com.iframe.common.utils.ResponseResult;
import com.iframe.common.utils.RetResponse;
import com.iframe.interfaces.dao.systemDao.SystemRoleInfoDao;
import com.iframe.interfaces.model.UserAccountEntity;
import com.iframe.interfaces.model.dto.CommonDto;
import com.iframe.interfaces.model.systemModel.SystemMenuEntity;
import com.iframe.interfaces.model.systemModel.SystemRoleInfoEntity;
import com.iframe.interfaces.model.vo.RoleInfoVo;
import com.iframe.interfaces.service.ISystemRoleInfoService;
import com.iframe.interfaces.service.IUserAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/iframe/index")
@Api(tags = "IndexController", description = "权限接口")
public class IndexController {


    @Autowired
    ISystemRoleInfoService systemRoleInfoService;

    @Autowired
    IUserAccountService userAccountService;


    @Autowired
    SystemRoleInfoDao systemRoleInfoDao;


    @CheckToken(value = true)
    @ApiOperation(value="获取菜单列表", notes="获取菜单列表, Header : X-token 以及 X-userId")
    @RequestMapping(value ="/getMenuList",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<RoleInfoVo> getMenuList(@RequestBody CommonDto dto){
        //根据Id获取用户
        UserAccountEntity userAccountEntity = userAccountService.getByUserId(dto.getUserId());
        RoleInfoVo roleInfo = systemRoleInfoService.findByRoleName(userAccountEntity.getRole());
        return RetResponse.makeOKRsp(roleInfo);
    }

    @CheckToken(value = true)
    @ApiOperation(value="获取菜单表格", notes="获取菜单表格, Header : X-token 以及 X-userId")
    @RequestMapping(value ="/getDataTable",method = RequestMethod.GET)
    @ResponseBody
    public String  getDataTable(String userId){
        //根据Id获取用户
        UserAccountEntity userAccountEntity = userAccountService.getByUserId(userId);
        List<SystemRoleInfoEntity> users = systemRoleInfoDao.findAll();
        Page<String> page = null;

        PageResult<SystemRoleInfoEntity> data = new PageResult<SystemRoleInfoEntity>();
        data.setData(users);
        data.setDraw(1);
        data.setRecordsTotal(Integer.valueOf(users.size()));
        data.setRecordsFiltered(Integer.valueOf(users.size()));
        String listJsonString = JSON.toJSONString(data);
        return listJsonString;
    }
}
