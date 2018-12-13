package com.example.javaweb.domain;

import com.sun.xml.internal.bind.v2.model.core.ID;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * 菜单表
 */
@Entity
@Getter
@Setter
@Component
@Table(name = "menu_info")
public class MenuInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //导库时使用
    private Long menuId;
            
   @Column(name = "parentId",columnDefinition = "int default  0")
    private  Long parentId;
    @Column(name = "menuName")
    private  String menuName;
    @Column(name = "menuClass")
    private String menuClass;
    private  String menuUrl="#";

    public MenuInfo(Long parentId,String menuName, String menuClass) {
        this.parentId=  parentId;
        this.menuName = menuName;
        this.menuClass = menuClass;
    }

    protected MenuInfo() {

    }
}
