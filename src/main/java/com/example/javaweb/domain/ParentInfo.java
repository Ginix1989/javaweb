package com.example.javaweb.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 父母表
 */
@Entity
@Getter
@Setter
@Table(name = "parent_info")
public class ParentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parentId;

    private String userName;
    private String passWord;
    private String generalName;
    private String gender;
    // 身份证
    private String identityCard;
    private String phoneNum;
    private String addressInfo;
    private String childrenName=null;
    // 是否审批
    private String isAccess="0";
    //预定内容
    @JoinColumn(name = "parentUseServe_parentId")
    @ManyToOne
    private ParentUseServe parentUseServe=null;

    /**
     * Generate
     */
    protected ParentInfo() {
    }

    public ParentInfo(String userName, String generalName, String gender, String identityCard, String phoneNum, String addressInfo) {
        this.userName = userName;
        this.generalName = generalName;
        this.gender = gender;
        this.identityCard = identityCard;
        this.phoneNum = phoneNum;
        this.addressInfo = addressInfo;
    }

    @Override
    public String toString() {
        return "ParentInfo{" +
                "parentId=" + parentId +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", generalName='" + generalName + '\'' +
                ", gender='" + gender + '\'' +
                ", identityCard='" + identityCard + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", AddressInfo='" + addressInfo + '\'' +
                ", childernName='" + childrenName + '\'' +
                ", isAccess='" + isAccess + '\'' +
                ", parentUseServe=" + parentUseServe +
                '}';
    }
}
