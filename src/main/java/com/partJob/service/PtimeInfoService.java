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
 * 兼职信息服务类
 * Created by t on 2017/3/26.
 */
@Service
public class PtimeInfoService {
    @Autowired
    PtimeInfoRepository ptimeInfoRepository;
    @Autowired
    InfoRecordRepository infoRecordRepository;

    /**
     * 获取兼职信息
     * @param page
     * @param size
     * @param kind
     * @param location
     * @param index
     * @return
     */
    public Page<PtimeInfo> getPtimeInfos(Integer page,Integer size,String kind,String location,String index){
        Sort sort=new Sort(Sort.Direction.DESC,"id");
        if (kind.equals("")){
            if (location.equals("")){
                if (index.equals("")){
                  return ptimeInfoRepository.findAll(new PageRequest(page,size,sort));

                }else {
                    return ptimeInfoRepository.findByPersional(index,new PageRequest(page,size,sort));
                }
            }else {
                if (index.equals("")){
                    return ptimeInfoRepository.findByLocation(new PageRequest(page,size,sort),location);
                }else {
                    return ptimeInfoRepository.findByIndexAndLocation(index,location,new PageRequest(page,size,sort));
                }
            }
        }else {
            if (location.equals(""))
            {
                if (index.equals("")){
                    return ptimeInfoRepository.findByKind(new PageRequest(page,size),kind);
                }else {
                    return ptimeInfoRepository.findByIndexAndKind(index,kind,new PageRequest(page,size,sort));
                }

            }else {
                if (index.equals("")){
                    return ptimeInfoRepository.findByLocationAndKind(new PageRequest(page,size,sort),location,kind);
                }else {
                    return ptimeInfoRepository.findByIndexAndLocationAndKind(index,location,kind,new PageRequest(page,size,sort));
                }
            }
        }

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

    /**
     * 获取兼职信息细节
     * @param id
     * @return
     */
    public PtimeInfo getPtimeJobDetail(Long id){
        PtimeInfo ptimeInfo=ptimeInfoRepository.findById(id);
        return ptimeInfo;
    }

    /**
     * 发布兼职信息服务
     * @param ptimeInfo
     * @return
     */
    public Message postPtimeInfo(PtimeInfo ptimeInfo){
        ptimeInfoRepository.save(ptimeInfo);
        return new Message(1,"发布成功");
    }

}
