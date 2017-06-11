package com.partJob.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by tianfeng on 2017/6/10.
 */
@Data
@Entity
public class Student {
    public Student(Long id, Long stuId, String name, String profession, String location, String phone, String kind, String reword, String workTime, String extra) {
        this.id = id;
        this.stuId = stuId;
        this.name = name;
        this.profession = profession;
        this.location = location;
        this.phone = phone;
        this.kind = kind;
        this.reword = reword;
        this.workTime = workTime;
        this.extra = extra;
    }
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long stuId;
    private String name;
    private String profession;
    private String location;
    private String phone;
    private String kind;
    private String reword;
    private String workTime;
    private String extra;
}
