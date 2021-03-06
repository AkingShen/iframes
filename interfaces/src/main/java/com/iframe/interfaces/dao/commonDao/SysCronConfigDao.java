package com.iframe.interfaces.dao.commonDao;

import com.iframe.interfaces.model.commonModel.CronConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SysCronConfigDao extends JpaRepository<CronConfigEntity, Integer> {

    @Query(value ="SELECT u FROM CronConfigEntity u  where u.status = 1")
    CronConfigEntity getCron();

}
