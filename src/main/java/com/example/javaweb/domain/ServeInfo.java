package com.example.javaweb.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 服务信息表
 */

@Entity
@Getter
@Setter
@Table(name = "serve_info")
public class ServeInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name = "serverItemInfo")
    private String serverItemInfo;

    @Column(name="ispersonal")
    private  String ispersonal="0"; //是否是个性化服务
    protected ServeInfo() {

    }




    @Override
    public String toString() {
        return "ServeInfo{" +
                "id=" + id +
                ", serverItemInfo='" + serverItemInfo + '\'' +
                '}';
    }

    public ServeInfo(String serverItemInfo) {
        this.serverItemInfo = serverItemInfo;
    }
    public ServeInfo(String serverItemInfo,String type) {
        this.serverItemInfo = serverItemInfo;
        this.ispersonal=type;
    }





}
