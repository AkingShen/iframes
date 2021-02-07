package com.iframe.interfaces.dao.systemDao;

import com.iframe.interfaces.model.systemModel.DistrictsInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;



public interface DistrictInfoDao extends JpaRepository<DistrictsInfoEntity,Integer> {

    DistrictsInfoEntity getById(Integer id);

}
