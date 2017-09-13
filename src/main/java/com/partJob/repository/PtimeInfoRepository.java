package com.partJob.repository;

import com.partJob.model.PtimeInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 兼职信息搜寻接口
 * Created by t on 2017/3/26.
 */
public interface PtimeInfoRepository extends JpaRepository<PtimeInfo,Long> {
    @Query("select p from PtimeInfo p where p.isEnd=0")
    Page<PtimeInfo> findAll(Pageable pageable);
    @Query("SELECT p from PtimeInfo p where p.isEnd=0 and p.id=?1")
    PtimeInfo findById(Long id);
    @Query("SELECT p from PtimeInfo p where p.isEnd=0 and p.location=?1")
    Page<PtimeInfo> findByLocation(Pageable pageable,String location);
    @Query("SELECT p from PtimeInfo p where p.isEnd=0 and p.kind=?1")//?2改为了?1 ,看来pageable不占位置哇
    Page<PtimeInfo> findByKind(Pageable pageable,String kind);
    @Query("SELECT p from PtimeInfo p where p.isEnd=0 and p.location=?2 and p.kind=?3")
    Page<PtimeInfo> findByLocationAndKind(Pageable pageable,String location,String kind);
    @Query("SELECT p from PtimeInfo p where p.isEnd=0 and p.extra like concat('%',:content,'%') or p.groupName like concat('%',:content,'%') or p.kind like concat('%',:content,'%') or p.reward like concat('%',:content,'%') or p.title like concat('%',:content,'%')or p.location like concat('%',:content,'%')")
    Page<PtimeInfo> findByPersional(@Param("content")String content,Pageable pageable);
    @Query("SELECT p from PtimeInfo p where p.isEnd=0 and p.kind=:kind and p.extra like concat('%',:content,'%') or p.groupName like concat('%',:content,'%') or p.kind like concat('%',:content,'%') or p.reward like concat('%',:content,'%') or p.title like concat('%',:content,'%') or p.location like concat('%',:content,'%') ")
    Page<PtimeInfo> findByIndexAndKind(@Param("content")String content,@Param("kind") String kind,Pageable pageable);
    @Query("SELECT p from PtimeInfo p where p.isEnd=0 and p.location=:location and p.extra like concat('%',:content,'%') or p.groupName like concat('%',:content,'%') or p.kind like concat('%',:content,'%') or p.reward like concat('%',:content,'%') or p.title like concat('%',:content,'%')or p.location like concat('%',:content,'%')")
    Page<PtimeInfo> findByIndexAndLocation(@Param("content")String content,@Param("location") String location,Pageable pageable);
    @Query("SELECT p from PtimeInfo p where p.isEnd=0 and p.location=:location and p.kind=:kind and p.extra like concat('%',:content,'%') or p.groupName like concat('%',:content,'%') or p.kind like concat('%',:content,'%') or p.reward like concat('%',:content,'%') or p.title like concat('%',:content,'%')or p.location like concat('%',:content,'%')")
    Page<PtimeInfo> findByIndexAndLocationAndKind(@Param("content")String content,@Param("location") String location,@Param("kind")String kind, Pageable pageable);
}
