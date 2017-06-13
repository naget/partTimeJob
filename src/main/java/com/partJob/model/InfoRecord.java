package com.partJob.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

/**
 * Created by t on 2017/3/26.
 */
@Entity
@Data
public class InfoRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long stuId;
    private Long ptimeInfoId;
    private String state;//完成状态
    private String review;
    private Date endTime;
    private Date reviewTime;
public InfoRecord(){}
    public InfoRecord(Long stuId, Long ptimeInfoId, String state) {
        this.stuId = stuId;
        this.ptimeInfoId = ptimeInfoId;
        this.state = state;
    }
}
