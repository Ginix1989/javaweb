package com.example.javaweb.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * 小区员工表
 *
 */
@Entity
@Getter
@Setter
@Component
@Table(name = "village_staff")
public class VillageStaff {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  Long id;

    private String staffName;//员工姓名
    private String gender;//性别
    private String loginName;//登录名
    private String passWord;//密码

    protected VillageStaff(){}

    public  VillageStaff(String staffName,String gender ,String loginName,String passWord){

            this.staffName=staffName;
            this.gender = gender;
            this.loginName =loginName;
            this.passWord = passWord;

    }


}
