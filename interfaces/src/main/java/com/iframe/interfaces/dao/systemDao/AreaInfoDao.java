package com.iframe.interfaces.dao.systemDao;

import com.iframe.interfaces.model.systemModel.AreaInfoEntity;
import com.iframe.interfaces.model.systemModel.SystemRoleInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AreaInfoDao  extends JpaRepository<AreaInfoEntity,Integer> {

    List<AreaInfoEntity> findByLevelType(Integer LevelType);

    List<AreaInfoEntity> findByParentId(Integer parentId);


    @Query("select u.id   from AreaInfoEntity  u where name = ?1 and u.levelType = 3 and u.parentId = ?2 ")
    Integer getDistinctId(String districtName,Integer cityId);

    @Query("select u from AreaInfoEntity  u where u.levelType = 2 and name = ?1")
    AreaInfoEntity getCityAndProvince(String cityName);
}
