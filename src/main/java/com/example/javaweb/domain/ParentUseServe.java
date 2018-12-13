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
    private Long serveInfoId;
    private String dateTime;

}
