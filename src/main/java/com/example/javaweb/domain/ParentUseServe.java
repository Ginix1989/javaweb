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

    protected  ParentUseServe(){};
    public ParentUseServe(Long parentId, Long serveInfoId, String orderdateTime, String note, String grade) {
        this.parentId = parentId;
        this.serveInfoId = serveInfoId;
        this.orderdateTime = orderdateTime;
        this.note = note;
        this.grade = grade;
    }

    //评分
    private String grade ;

    @Override
    public String toString() {
        return "ParentUseServe{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", serveInfoId=" + serveInfoId +
                ", orderdateTime='" + orderdateTime + '\'' +
                ", note='" + note + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}
