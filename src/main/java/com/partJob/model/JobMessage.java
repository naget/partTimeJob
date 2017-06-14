package com.partJob.model;

import lombok.Data;

import java.sql.Date;

/**
 * Created by tianfeng on 2017/6/14.
 */
@Data
public class JobMessage {
    private PtimeInfo ptimeInfo;
    private String reviewContent;
    private Date reviewTime;
    public JobMessage(){}
    public JobMessage(PtimeInfo ptimeInfo,String reviewContent,Date date){
        this.ptimeInfo=ptimeInfo;
        this.reviewContent=reviewContent;
        this.reviewTime=reviewTime;
    }
}
