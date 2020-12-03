package com.iframe.web.controller;

import com.iframe.common.utils.RedisUtils;
import com.iframe.common.utils.ResponseResult;
import com.iframe.common.utils.RetResponse;
import com.iframe.interfaces.model.UserAccountEntity;
import com.iframe.interfaces.service.IUserAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;



@RestController
@RequestMapping("/iframe")
@Api(tags = "LoginController", description = "登录接口列表")
public class UserAccountController {


    @Autowired
    RedisUtils redisUtils;

    @Autowired
    IUserAccountService userAccountService;

    @ApiOperation(value="注册用户", notes="注册用户")
    @RequestMapping(value ="/register",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<String> register(@RequestBody UserAccountEntity entity){
        String res = "申请失败";
        boolean isSuccess = userAccountService.addUserAccount(entity);
        if(isSuccess){
            res = "注册成功";
        }
        return RetResponse.makeOKRsp(res);
    }

    @ApiOperation(value="用户登录", notes="用户登录")
    @RequestMapping(value ="/login",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<String> login(@RequestBody UserAccountEntity entity){
        return RetResponse.makeOKRsp("ok");
    }
}
