package com.partJob.repository;

import com.partJob.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 学生查询接口
 * Created by tianfeng on 2017/6/13.
 */
public interface StudentRepository extends JpaRepository<Student,Long>{

}
