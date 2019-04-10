package com.iframe.interfaces.dao.testDao;

import com.iframe.interfaces.model.testModel.SysDictEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysDictDao extends JpaRepository<SysDictEntity,Integer> {

    /**
     * 通过标签获取字典对象
     * @param lable
     * @return
     */
    SysDictEntity findByLable(String lable);

}
