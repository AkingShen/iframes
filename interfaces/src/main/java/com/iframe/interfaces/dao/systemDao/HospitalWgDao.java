package com.iframe.interfaces.dao.systemDao;

import com.iframe.interfaces.model.systemModel.HospitalGdEntity;
import com.iframe.interfaces.model.systemModel.HospitalWgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HospitalWgDao extends JpaRepository<HospitalWgEntity,Integer> {

      @Query(value = "select h.hospitalName from HospitalGdEntity h  where h.district = ?1  and h.hospitalName like %?2%  or  h.hospitalAlias like  %?3%  ")
      List<String> findByHospitalNameLikeOrHospitalAliasLike(String district, String hospitalName, String aiysas);


      @Query(value = "select h.hospitalName from HospitalGdEntity h  where h.district = ?1  and h.hospitalName like %?2% ")
      List<String> findByHospitalNameLike(String district, String hospitalName);



    @Query(value = "select h.hospitalName from HospitalGdEntity h  where h.district =?1  and h.shortName =?2  and  h.city =?3  ")
    List<String> findByDistrictAndHospitalNameAndCity(String district, String hospitalName, String city);


    @Query(value = "select h.hospitalName from HospitalGdEntity h  where h.district = ?1  and h.hospitalName like %?2% ")
    List<String> findByHospitalName(String district, String hospitalName);


    @Modifying
    @Query(value = "update HospitalWgEntity h set h.locationX = ?1 ,h.locationY = ?2 where h.id = ?3")
    void saveDoto(String locationX, String locationY,Integer id);
}
