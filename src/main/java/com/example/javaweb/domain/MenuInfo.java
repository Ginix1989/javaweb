package com.example.javaweb.domain;

import com.sun.xml.internal.bind.v2.model.core.ID;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Component
//@Table(name = "menuInfo")
public class MenuInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   @Column(name = "parentId",columnDefinition = "int default  0")
    private  Long parentId;
    @Column(name = "menuName")
    private  String menuName;
    @Column(name = "menuClass")
    private String menuClass;

    public MenuInfo(Long parentId,String menuName, String menuClass) {
        this.parentId=  parentId;
        this.menuName = menuName;
        this.menuClass = menuClass;
    }

    protected MenuInfo() {

    }
}
