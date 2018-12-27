package com.example.javaweb.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * 订阅服务表
 */
@Entity
@Getter
@Setter
@Table(name = "parent_use_serve")
public class ParentUseServe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    父母id
    private Long parentId;
//    服务ID
@Column(name = "serveInfoId")
    private Long serveInfoId;
    //预约时间
    private String orderdateTime;
    //备注
    private String note ;
//评分
    private String grade ;

}
