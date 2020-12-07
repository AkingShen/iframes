package com.iframe.web.controller;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.iframe.common.utils.JwtUtils;
import com.iframe.common.utils.RedisUtils;
import com.iframe.common.utils.ResponseResult;
import com.iframe.common.utils.RetResponse;
import com.iframe.interfaces.model.UserAccountEntity;
import com.iframe.interfaces.service.IUserAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.web.bind.annotation.*;




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
    public ResponseResult<String> login( @RequestBody UserAccountEntity entity){
       UserAccountEntity entityDb =  userAccountService.getByUserName(entity.getUserName());
       if(null == entityDb){
           return  RetResponse.makeErrRsp("用户不存在");
       }
       String token = userAccountService.checkUser(entity,entityDb);
       //权限鉴别
       return RetResponse.makeOKRsp(token);
    }

//    @ApiOperation(value="检验用户", notes="检验用户")
//    @RequestMapping(value ="/chece",method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseResult<String> chece( @RequestBody UserAccountEntity entity) throws Exception {
//        DecodedJWT jwt = JwtUtils.verifyToken(entity.getAreId(),entity.getUserId());
//        String s = JwtUtils.getAudience(entity.getAreId());
//        //权限鉴别
//        return RetResponse.makeOKRsp("ok");
//    }
}
