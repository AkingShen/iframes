package com.iframe.web.controller;


import com.alibaba.fastjson.JSONArray;
import com.iframe.common.httpUtils.HttpUtil;
import com.iframe.common.utils.RedisUtils;
import com.iframe.common.utils.ResponseResult;
import com.iframe.common.utils.RetResponse;
import com.iframe.interfaces.dao.systemDao.DistrictInfoDao;
import com.iframe.interfaces.model.UserAccountEntity;
import com.iframe.interfaces.model.dto.LoginDto;
import com.iframe.interfaces.model.systemModel.DistrictsInfoEntity;
import com.iframe.interfaces.model.vo.JsonRootBean;
import com.iframe.interfaces.model.vo.LoginVo;
import com.iframe.interfaces.service.IUserAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.net.www.http.HttpClient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


@RestController
@RequestMapping("/iframe")
@Api(tags = "LoginController", description = "登录接口列表")
public class UserAccountController {


    @Autowired
    RedisUtils redisUtils;

    @Autowired
    IUserAccountService userAccountService;

    @Autowired
    DistrictInfoDao districtInfoDao;

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
    public ResponseResult<LoginVo> login( @RequestBody LoginDto loginDto) throws Exception {
       UserAccountEntity entityDb =  userAccountService.getByUserName(loginDto.getUserName());
       if(null == entityDb){
           return  RetResponse.makeErrRsp("用户不存在");
       }
       LoginVo loginVo = userAccountService.checkUser(loginDto,entityDb);
       if(loginVo == null){
           return RetResponse.makeErrRsp("登陆失败");
       }
       return RetResponse.makeOKRsp(loginVo);
    }

    @ApiOperation(value="地图关键字接口搜索", notes="地图关键字接口搜索")
    @RequestMapping(value ="/search",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<String> search( @RequestBody LoginDto loginDto) throws Exception {
        String res = "";
//        String url = "https://restapi.amap.com/v3/place/text?keywords=北京大学&city=beijing&output=xml&offset=20&page=1&key=<用户的key>&extensions=all";
        String url  = "https://restapi.amap.com/v3/place/text";
        String params = "city=610113&citylimit=true&offset=500&types=090000&page=2&key=c3a741cea743555498d279fc1c40623b&extensions=all&output=JSON";
        DistrictsInfoEntity die = districtInfoDao.getById(1);
//        String  str = HttpUtil.doGetRequest(url,params);
//        JsonRootBean jsonRootBean =  JSONArray.parseObject(str, JsonRootBean.class);
        return RetResponse.makeOKRsp("finish_Work");
    }

}
