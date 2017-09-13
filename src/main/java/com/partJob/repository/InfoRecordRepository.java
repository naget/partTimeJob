package com.partJob.repository;

import com.partJob.model.InfoRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * 信息记录查询接口
 * Created by t on 2017/3/26.
 */
public interface InfoRecordRepository extends JpaRepository<InfoRecord,Long> {
    @Query("SELECT i from InfoRecord i where i.stuId=?1 and i.ptimeInfoId=?2")
    InfoRecord findByStuIdAndPtimeInfoId(Long id,Long pId);
    @Query("SELECT i from InfoRecord i where i.ptimeInfoId=?1")
    Page<InfoRecord> findByJobId(Long id, Pageable pageable);
    Page<InfoRecord> findByStuIdAndState(Long stuId,String state,Pageable pageable);
}
