package com.iframe.interfaces.dao.systemDao;

import com.iframe.interfaces.model.systemModel.HospitalGdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HospitalGdDao extends JpaRepository<HospitalGdEntity,Integer> {

      @Query(value = "select h.hospitalName from HospitalGdEntity h  where h.district = ?1  and h.hospitalName like %?2%  or  h.hospitalAlias like  %?3%  ")
      List<String> findByHospitalNameLikeOrHospitalAliasLike(String district,String hospitalName, String aiysas);


      @Query(value = "select h.hospitalName from HospitalGdEntity h  where h.district = ?1  and h.hospitalName like %?2% ")
      List<String> findByHospitalNameLike(String district,String hospitalName);
}
