package com.partJob.repository;

import com.partJob.model.PtimeInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by t on 2017/3/26.
 */
public interface PtimeInfoRepository extends JpaRepository<PtimeInfo,Long> {
    @Query("select p from PtimeInfo p where p.isEnd=0")
    Page<PtimeInfo> findAll(Pageable pageable);
    PtimeInfo findById(Long id);
    @Query("SELECT p from PtimeInfo p where p.isEnd=0 and p.location=?2")
    Page<PtimeInfo> findByLocation(Pageable pageable,String location);
    @Query("SELECT p from PtimeInfo p where p.isEnd=0 and p.kind=?2")
    Page<PtimeInfo> findByKind(Pageable pageable,String kind);
    @Query("SELECT p from PtimeInfo p where p.isEnd=0 and p.location=?2 and p.kind=?3")
    Page<PtimeInfo> findByLocationAndKind(Pageable pageable,String location,String kind);
}
