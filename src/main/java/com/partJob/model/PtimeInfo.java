package com.partJob.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 兼职信息各个信息
 * Created by t on 2017/3/26.
 */
@Entity
@Data
public class PtimeInfo {
    public PtimeInfo(String groupName,Long pubStuId, String title, String kind, String phone, String text, String reward,int number,int needNumber,String location,String locationDetail,String extra) {
        this.groupName=groupName;//发布单位
        this.pubStuId = pubStuId;//发布者学号
        this.title = title;//兼职内容
        this.kind = kind;//兼职种类
        this.phone = phone;//电话号
        this.text = text;//兼职详述
        this.reward = reward;//薪酬
        this.number = number;
        this.needNumber=needNumber;
        this.location=location;//工做区域
        this.locationDetail=locationDetail;//详细地址
        this.extra=extra;
        this.isEnd=0;
    }
    public PtimeInfo(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String groupName;
    private Long pubStuId;
    private String title;
    private String kind;//类型
    private String phone;
    private String text;
    private String location;
    private String reward;
    private Integer number;//需要人数
    private Integer needNumber;//还需人数
    private String locationDetail;//详细地址
    private String extra;
    private Integer isEnd;

}
