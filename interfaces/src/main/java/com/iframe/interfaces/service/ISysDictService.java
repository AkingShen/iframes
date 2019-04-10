package com.iframe.interfaces.service;

import com.iframe.interfaces.model.testModel.SysDictEntity;
import org.springframework.stereotype.Component;

@Component
public interface ISysDictService {
    SysDictEntity findByLable(String Label);
}
