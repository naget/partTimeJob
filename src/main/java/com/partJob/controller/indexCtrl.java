package com.partJob.controller;

import com.partJob.model.InfoRecord;
import com.partJob.model.Message;
import com.partJob.model.PtimeInfo;
import com.partJob.service.InfoRecordService;
import com.partJob.service.PtimeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by t on 2017/3/26.
 */
@Controller
public class indexCtrl {
    @Autowired
    PtimeInfoService ptimeInfoService;
    @Autowired
    InfoRecordService infoRecordService;
    @RequestMapping("/showPtimeJobs")
    @ResponseBody
    public Page<PtimeInfo> showPtimeJobs(@RequestParam(value = "page",defaultValue = "0")String page,
                                         @RequestParam(value = "size",defaultValue = "10")String size,
                                         @RequestParam(value = "kind",defaultValue = "")String kind,
                                         @RequestParam(value = "location",defaultValue = "")String location,
                                         @RequestParam(value = "index",defaultValue = "")String index){
        return ptimeInfoService.getPtimeInfos(Integer.valueOf(page),Integer.valueOf(size),kind,location,index);
    }
    @RequestMapping("/getPtimeJobDetail/{id}")
    @ResponseBody
    public ModelAndView getPtimeJobDetail(@PathVariable(value = "id")String id, ModelAndView model){
        PtimeInfo ptimeInfo= ptimeInfoService.getPtimeJobDetail(Long.valueOf(id));
        model.setViewName("work-details");
        model.addObject("job",ptimeInfo);
        return model;
    }
    @RequestMapping(value = "/pushWanted",method = RequestMethod.POST)
    @ResponseBody
    public Message pushWanted(
                            @RequestParam(value = "groupName")String groupName,
                           @RequestParam(value = "locationDetail")String locationDetail,
                           @RequestParam(value = "title")String title,
                           @RequestParam(value = "kind")String kind,
                           @RequestParam(value = "phone")String phone,
                           @RequestParam(value = "text")String text,
                           @RequestParam(value = "reward")String reward,
                           @RequestParam(value = "number")String number,
                           @RequestParam(value = "location")String location,
                            @RequestParam(value = "extra")String extra){
        Long pubStuId=2015310606654L;//获得登陆者stuid
        PtimeInfo ptimeInfo=new PtimeInfo(groupName,Long.valueOf(pubStuId),title,kind,phone,text,reward,Integer.valueOf(number),Integer.valueOf(number),location,locationDetail,extra);
        return ptimeInfoService.postPtimeInfo(ptimeInfo);
    }
    @RequestMapping("/getJob")
    @ResponseBody
    public Message getJob(String stuId, String jobId){
        return infoRecordService.getJob(stuId,jobId);
    }
    @RequestMapping("/reviewJob")
    @ResponseBody
    public Message reviewJob(String stuId,String jobId,String review){
        return infoRecordService.reviewJob(stuId,jobId,review);
    }
    @RequestMapping("/getReviewInfo")
    @ResponseBody
    public Page<InfoRecord> getReviewInfo(String jobId,String page,String size){
        return infoRecordService.showReviewInfo(jobId, page, size);
    }

}
