package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Integer id;
    String name;
    String no;
    private Integer gender;
    String phone;
    private Integer degree;
    private Integer clazzId;
    String clazzName;
    String idCard;
    private Integer isCollege;
    String address;
    String graduationDate;
    private Integer  violationCount;
    private Integer violationScore;
    LocalDateTime createTime;
    LocalDateTime updateTime;
}
