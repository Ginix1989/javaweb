package com.example.javaweb.dao;

import com.example.javaweb.domain.ChildrenInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.hibernate.annotations.Parameter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface
ParentUseServeMapper {
    //获取指定人员的预约服务信息
    @Select("select\n" +
            "            s.id,\n" +
            "            s.serveInfoId,\n" +
            "            s.parentId,\n" +
            "            t.serverItemInfo,\n" +
            "            s.orderdateTime,\n" +
            "            s.grade,\n" +
            "            s.note,\n" +
            "            case t.ispersonal\t \n" +
            "            when   '1' then '定制服务'\n" +
            "            when '0' then '小区服务'\n" +
            "            when '2' then '护理'\n" +
            "            end as type \n"+
            " from parent_use_serve s left join serve_info t  on  t.id=s.serveInfoId\n" +
            "where s.parentId = #{id} ")
    List<Map<Object,Object>> getUse_serveInfo(Long parentId);

    //获取指定人员的预约服务信息 用以评分
    @Select("select\n" +
            "            s.id,\n" +
            "            s.serveInfoId,\n" +
            "            s.parentId,\n" +
            "            t.serverItemInfo,\n" +
            "            s.orderdateTime,\n" +
            "            s.grade,\n" +
            "            s.note,\n" +
            "            case t.ispersonal\t \n" +
            "            when   '1' then '定制服务'\n" +
            "            when '0' then '小区服务'\n" +
            "            when '2' then '护理'\n" +
            "            end as type \n"+
            " from parent_use_serve s left join serve_info t  on  t.id=s.serveInfoId\n" +
            "where s.parentId = #{id} ")
    List<Map<Object,Object>> getUse_serveInfoForGrade(Long parentId);



    //获取全部订单信息
    @Select("select\n" +
            "            s.id,\n" +
            "            s.serveInfoId,\n" +
            "            s.parentId,\n" +
            "            t.serverItemInfo,\n" +
            "            s.orderdateTime,\n" +
            "            s.grade,\n" +
            "            s.note,\n" +
            "            case t.ispersonal\t \n" +
            "            when   '1' then '定制服务'\n" +
            "            when '0' then '小区服务'\n" +
            "            when '2' then '护理'\n" +
            "            end as type \n"+
            "            from parent_use_serve s left join serve_info t  on  t.id=s.serveInfoId")
    List<Map<Object,Object>> getAllOrders();

    //获取指定人员的定制服务信息
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
            "where s.parentId = #{id}  and t.ispersonal =#{ispersonal}")
    List<Map<Object,Object>> getUse_PersonalServeInfo(@Param("id")Long parentId,@Param("ispersonal")String ispersonal);

    //获取指定人员的预约服务信息 根据时间进行查询 （我的服务信息模块）
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
            "where s.parentId = #{id} and s.orderdateTime>= #{startTime} ")
    List<Map<Object,Object>> getUseServeInfoByStartTimeAndParentId(@Param("id") Long id, @Param("startTime") String startTime);


    //获取指定人员的预约定制服务服务信息 根据时间进行查询 （我的服务信息模块）
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
            "where s.parentId = #{id} and s.orderdateTime>= #{startTime}  and  t.ispersonal='1' ")
    List<Map<Object,Object>> getPersonalUseServeInfoByStartTimeAndParentId(@Param("id") Long id, @Param("startTime") String startTime);


    //获取全部订单信息 根据时间查询
    @Select("select\n" +
            "            s.id,\n" +
            "            s.serveInfoId,\n" +
            "            s.parentId,\n" +
            "            t.serverItemInfo,\n" +
            "            s.orderdateTime,\n" +
            "            s.grade,\n" +
            "            s.note,\n" +
            "            case t.ispersonal\t \n" +
            "            when   '1' then '定制服务'\n" +
            "            when '0' then '小区服务'\n" +
            "            when '2' then '护理'\n" +
            "            end as type \n"+
            " from parent_use_serve s left join serve_info t  on  t.id=s.serveInfoId\n" +
            "where s.orderdateTime>= #{startTime} ")
    List<Map<Object,Object>> getUseServeOrderInfoByStartTimeAndParentId(@Param("startTime") String startTime);




}
