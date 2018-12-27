package com.example.javaweb.dao;

import com.example.javaweb.domain.ChildrenInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface ParentUseServeMapper {
    //获取指定人员的预约服务信息
    @Select("select\n" +
            "\n" +
            "s.id,\n" +
            "s.serveInfoId,\n"+
            "s.parentId,\n"+
            "t.serverItemInfo,\n" +
            "s.orderdateTime,\n" +
            "s.grade,\n" +
            "s.note\n" +
            " from parent_use_serve s left join serve_info t  on  t.id=s.serveInfoId\n" +
            "where s.parentId = #{id} ")
    List<Map<Object,Object>> getUse_serveInfo(Long parentId);
}
