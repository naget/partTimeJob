package com.partJob.service;

import com.partJob.model.Message;
import com.partJob.model.PtimeInfo;
import com.partJob.repository.InfoRecordRepository;
import com.partJob.repository.PtimeInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Created by t on 2017/3/26.
 */
@Service
public class PtimeInfoService {
    @Autowired
    PtimeInfoRepository ptimeInfoRepository;
    @Autowired
    InfoRecordRepository infoRecordRepository;
    public Page<PtimeInfo> getPtimeInfos(Integer page,Integer size,String kind,String location){
        Sort sort=new Sort(Sort.Direction.DESC,"id");
        if (kind.equals("")){
            if (location.equals("")){
                Page<PtimeInfo> ptimeInfoPage=ptimeInfoRepository.findAll(new PageRequest(page,size,sort));
                return ptimeInfoPage;
            }
            Page<PtimeInfo> ptimeInfoPage=ptimeInfoRepository.findByLocation(new PageRequest(page,size,sort),location);
            return ptimeInfoPage;

        }
        Page<PtimeInfo> ptimeInfoPage=ptimeInfoRepository.findByLocationAndKind(new PageRequest(page,size,sort),location,kind);
        return ptimeInfoPage;

    }
//    private void formatPtimeInfo(List<PtimeInfo> ptimeInfoList){
//        for (PtimeInfo ptimeInfo:ptimeInfoList){
//            if (ptimeInfo.getKind().equals("1"))ptimeInfo.setKind("家教");
//            if (ptimeInfo.getKind().equals("2"))ptimeInfo.setKind("发传单");
//            if (ptimeInfo.getState().equals("1"))ptimeInfo.setState("已完结");
//            if (ptimeInfo.getState().equals("0"))ptimeInfo.setState("未完结");
//            if (ptimeInfo.getLocation().equals("0"))ptimeInfo.setLocation("成都");
//        }
//    }
    public PtimeInfo getPtimeJobDetail(Long id){
        PtimeInfo ptimeInfo=ptimeInfoRepository.findById(id);
        return ptimeInfo;
    }
    public Message postPtimeInfo(PtimeInfo ptimeInfo){
        ptimeInfoRepository.save(ptimeInfo);
        return new Message(1,"发布成功");
    }

}
