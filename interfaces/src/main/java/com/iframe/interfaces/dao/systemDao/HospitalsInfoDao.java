package com.iframe.interfaces.dao.systemDao;

import com.iframe.interfaces.model.systemModel.HospitalsInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalsInfoDao  extends JpaRepository<HospitalsInfoEntity,Integer> {
}
