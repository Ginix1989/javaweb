package com.example.javaweb.domain;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * 小区管理员信息表
 */
@Entity
@Getter
@Setter
@Component
@Table(name="village_adminstaff")
public class VillageAdminStaff {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;


    private String adminstaffName;//员工姓名
    private String gender;//性别
    private String loginName;//登录名
    private String passWord;//密码

    protected  VillageAdminStaff(){}

    public  VillageAdminStaff(String adminstaffName,String gender ,String loginName,String passWord){

        this.adminstaffName=adminstaffName;
        this.gender = gender;
        this.loginName =loginName;
        this.passWord = passWord;

    }

}
