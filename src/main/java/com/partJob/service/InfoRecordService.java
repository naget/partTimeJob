package com.partJob.service;

import com.partJob.model.InfoRecord;
import com.partJob.model.Message;
import com.partJob.model.PtimeInfo;
import com.partJob.repository.InfoRecordRepository;
import com.partJob.repository.PtimeInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;

/**
 * Created by t on 2017/3/26.
 */
@Service
public class InfoRecordService {
    @Autowired
    InfoRecordRepository infoRecordRepository;
    @Autowired
    PtimeInfoRepository ptimeInfoRepository;
    public Message getJob(String stuId,String jobId){
        InfoRecord infoRecord=infoRecordRepository.findByStuIdAndPtimeInfoId(Long.valueOf(stuId),Long.valueOf(jobId));
        if (infoRecord!=null){
            return new Message(0,"请勿重复申请");
        }else {
            PtimeInfo ptimeInfo=ptimeInfoRepository.findById(Long.valueOf(jobId));
            ptimeInfo.setNumber(ptimeInfo.getNumber()-1);
            if (ptimeInfo.getNumber()<=0)ptimeInfo.setIsEnd(1);
            ptimeInfoRepository.save(ptimeInfo);
            InfoRecord infoRecord1=new InfoRecord(Long.valueOf(stuId),Long.valueOf(jobId),"0");
            //state 0表示未完成 1表示已完成
            infoRecordRepository.save(infoRecord1);
            return new Message(1,"成功申请");
        }
    }
    public Message reviewJob(String stuId,String jobId,String review){
        InfoRecord infoRecord = infoRecordRepository.findByStuIdAndPtimeInfoId(Long.valueOf(stuId),Long.valueOf(jobId));
        if (infoRecord==null)return new Message(0,"您还没有进行此兼职，不能进行评价哦！");
        else{
            infoRecord.setReview(review);
            infoRecord.setReviewTime(new Date(System.currentTimeMillis()));
            infoRecordRepository.save(infoRecord);
            return new Message(1,"评价成功！");
        }
    }
    public Page<InfoRecord> showReviewInfo(String jobId,String page,String size){
        Sort sort=new Sort(Sort.Direction.DESC,"id");
        Page<InfoRecord> infoRecordPage=infoRecordRepository.findByJobId(Long.valueOf(jobId),new PageRequest(Integer.valueOf(page),Integer.valueOf(size),sort));
        return infoRecordPage;

    }
}