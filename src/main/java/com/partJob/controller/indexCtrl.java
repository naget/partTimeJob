package com.partJob.controller;

import com.partJob.model.*;
import com.partJob.repository.StudentRepository;
import com.partJob.service.InfoRecordService;
import com.partJob.service.PtimeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by t on 2017/3/26.
 */
@Controller
public class indexCtrl {
    @Autowired
    PtimeInfoService ptimeInfoService;
    @Autowired
    InfoRecordService infoRecordService;
    @Autowired
    StudentRepository studentRepository;
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
    @RequestMapping("/getJobDetail")
    @ResponseBody
    public PtimeInfo getJobDetail(@RequestParam String jobId){
        return ptimeInfoService.getPtimeJobDetail(Long.valueOf(jobId));
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
        Long pubStuId=201531060664L;//获得登陆者stuid
        PtimeInfo ptimeInfo=new PtimeInfo(groupName,Long.valueOf(pubStuId),title,kind,phone,text,reward,Integer.valueOf(number),Integer.valueOf(number),location,locationDetail,extra);
        return ptimeInfoService.postPtimeInfo(ptimeInfo);
    }
    @RequestMapping(value = "/pushMyInfo",method = RequestMethod.POST)
    @ResponseBody
    public Message pushMyInfo(
                              @RequestParam(value = "location")String location,
                              @RequestParam(value = "phone")String phone,
                              @RequestParam(value = "kind")String kind,
                              @RequestParam(value = "reward")String reward,
                              @RequestParam(value = "workTime")String workTime,
                              @RequestParam(value ="extra" )String extra){
           Long userId=1L;//得到用户ID
        Student student=studentRepository.findOne(userId);
        if (null==student)return new Message(0,"用户不存在");

            student.setExtra(extra);
            student.setLocation(location);
            student.setPhone(phone);
            student.setKind(kind);
            student.setReward(reward);
            student.setWorkTime(workTime);
            studentRepository.save(student);
            return new Message(1,"更新成功");

    }

    @RequestMapping("/getJob/{jobId}")
    @ResponseBody
    public Message getJob(Long stuId, @PathVariable String jobId){
                                stuId=1L;
        return infoRecordService.getJob(stuId,jobId);
    }
    @RequestMapping("/reviewJob")
    @ResponseBody
    public Message reviewJob(@RequestParam String jobId,@RequestParam String review){
        Long stuId=1L;
        return infoRecordService.reviewJob(stuId,Long.valueOf(jobId),review);
    }
    @RequestMapping("/getStateJobs")
    @ResponseBody
    public List<JobMessage> getStateJobs(String state){
        Long userId=1L;
        return infoRecordService.getStateJob(userId,state);
    }
    @RequestMapping("/changeMyInfo")
    @ResponseBody
    public ModelAndView changeMyInfo(ModelAndView model){
        Long id=1L;//获取用户id
        Student myInfo = studentRepository.findOne(id);
        model.addObject("myInfo",myInfo);
        model.setViewName("release-resume");
        return model;
    }
    @RequestMapping("/getReviewInfo")
    @ResponseBody
    public Page<InfoRecord> getReviewInfo(String jobId,String page,String size){
        return infoRecordService.showReviewInfo(jobId, page, size);
    }
    @RequestMapping("/getMyInfo")
    @ResponseBody
    public ModelAndView getMyInfo(ModelAndView modelAndView){
        Long id=1L;
        Student myInfo= infoRecordService.getStudentInfo(id);
        List<JobMessage> doingList=infoRecordService.getStateJob(id,"0");
        List<JobMessage> doneList=infoRecordService.getStateJob(id,"1");
        modelAndView.addObject("myInfo",myInfo);
        modelAndView.addObject("doingList",doingList);
        modelAndView.addObject("doneList",doneList);
        modelAndView.setViewName("my-work");
        return modelAndView;
    }

}
