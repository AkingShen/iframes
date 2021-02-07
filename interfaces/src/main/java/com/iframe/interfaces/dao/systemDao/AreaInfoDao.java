package com.iframe.interfaces.dao.systemDao;

import com.iframe.interfaces.model.systemModel.AreaInfoEntity;
import com.iframe.interfaces.model.systemModel.SystemRoleInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AreaInfoDao  extends JpaRepository<AreaInfoEntity,Integer> {

    List<AreaInfoEntity> findByLevelType(Integer LevelType);

    List<AreaInfoEntity> findByParentId(Integer parentId);
}
