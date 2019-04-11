package com.iframe.web.controller;


import com.iframe.common.annotations.CheckToken;
import com.iframe.common.utils.RedisUtils;
import com.iframe.common.utils.ResponseResult;
import com.iframe.common.utils.RetResponse;
import com.iframe.interfaces.model.testModel.SysDictEntity;
import com.iframe.interfaces.service.ISysDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/iframe")
@Api(tags = "BookController", description = "字典接口列表")
public class SysDictController {

    @Autowired
    ISysDictService sysDictService;

    @Autowired
    RedisUtils redisUtils;

    @CheckToken(value = true)
    @ApiOperation(value="查询字典", notes="根据label查询自典实体")
    @ApiImplicitParam(name = "SysDictEntity", value = "字典实体", required = false, dataType = "SysDictEntity")
    @RequestMapping(value ="/getDict",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult<SysDictEntity> getdict(HttpServletRequest request){
        SysDictEntity sysDictEntity = sysDictService.findByLable("paychannel1");
//        redisUtils.set("wxpay0",sysDictEntity,2);
        return RetResponse.makeOKRsp(sysDictEntity);
    }
}
