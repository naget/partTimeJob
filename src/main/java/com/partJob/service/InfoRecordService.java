package com.partJob.service;

import com.partJob.model.*;
import com.partJob.repository.InfoRecordRepository;
import com.partJob.repository.PtimeInfoRepository;
import com.partJob.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * 兼职领取信息记录服务类
 * Created by t on 2017/3/26.
 */
@Service
public class InfoRecordService {
    @Autowired
    InfoRecordRepository infoRecordRepository;
    @Autowired
    PtimeInfoRepository ptimeInfoRepository;
    @Autowired
    StudentRepository studentRepository;

    /**
     * 通过学生id兼职id查询兼职记录信息
     * @param stuId
     * @param jobId
     * @return
     */
    public Message getJob(Long stuId,String jobId){
        InfoRecord infoRecord=infoRecordRepository.findByStuIdAndPtimeInfoId(stuId,Long.valueOf(jobId));
        if (infoRecord!=null){
            return new Message(0,"请勿重复申请");
        }else {
            PtimeInfo ptimeInfo=ptimeInfoRepository.findById(Long.valueOf(jobId));
            ptimeInfo.setNeedNumber(ptimeInfo.getNeedNumber()-1);
            if (ptimeInfo.getNumber()<=0)ptimeInfo.setIsEnd(1);
            ptimeInfoRepository.save(ptimeInfo);
            InfoRecord infoRecord1=new InfoRecord(Long.valueOf(stuId),Long.valueOf(jobId),"0");
            //state 0表示未完成 1表示已完成
            infoRecordRepository.save(infoRecord1);
            return new Message(1,"成功申请");
        }
    }

    /**
     * 评论兼职
     * @param stuId
     * @param jobId
     * @param review
     * @return
     */
    public Message reviewJob(Long stuId,Long jobId,String review){
        InfoRecord infoRecord = infoRecordRepository.findByStuIdAndPtimeInfoId(stuId,jobId);
        if (infoRecord==null)return new Message(0,"您还没有进行此兼职，不能进行评价哦！");
        else{
            infoRecord.setState("1");
            infoRecord.setReview(review);
            infoRecord.setReviewTime(new Date(System.currentTimeMillis()));
            infoRecordRepository.save(infoRecord);
            return new Message(1,"评价成功！");
        }
    }

    /**
     * 展示评论信息
     * @param jobId
     * @param page
     * @param size
     * @return
     */
    public Page<InfoRecord> showReviewInfo(String jobId,String page,String size){
        Sort sort=new Sort(Sort.Direction.DESC,"id");
        Page<InfoRecord> infoRecordPage=infoRecordRepository.findByJobId(Long.valueOf(jobId),new PageRequest(Integer.valueOf(page),Integer.valueOf(size),sort));
        return infoRecordPage;

    }

    /**
     * 得到某种状态的兼职
     * @param userId
     * @param state
     * @return
     */
    public List<JobMessage> getStateJob(Long userId,String state){
        List<InfoRecord> infoRecords = infoRecordRepository.findByStuIdAndState(userId,state,new PageRequest(0,10)).getContent();
        List<JobMessage> jobMessages = new ArrayList<JobMessage>();
        for (InfoRecord infoRecord:infoRecords){
            jobMessages.add(new JobMessage(ptimeInfoRepository.findOne(infoRecord.getPtimeInfoId()),null==infoRecord.getReview()?"":infoRecord.getReview(),null==infoRecord.getReviewTime()?new Date(System.currentTimeMillis()):infoRecord.getReviewTime()));
        }
        return jobMessages;
    }

    /**
     * 得到学生信息
     * @param id
     * @return
     */
    public Student getStudentInfo(Long id){
        return studentRepository.findOne(id);
    }
}
