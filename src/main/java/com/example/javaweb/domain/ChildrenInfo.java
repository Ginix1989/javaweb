package com.example.javaweb.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "children_info")
public class ChildrenInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long childrenId;

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
}
