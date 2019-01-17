package com.example.javaweb.service;

import com.example.javaweb.dao.ParentUseServeMapper;
import com.example.javaweb.dao.ServeInfoMapper;
import com.example.javaweb.domain.ParentUseServe;
import com.example.javaweb.domain.ServeInfo;
import com.example.javaweb.repository.ParentUseServeRepository;
import com.example.javaweb.repository.ServerInfoRepositrory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 服务信息  Service
 * 处理全部服务项目信息相关的操作
 * CRUD
 */
@Service
public class ParentUseServeService {

    @Autowired
   private ParentUseServeMapper parentUseServeMapper;
    @Autowired
    private ParentUseServeRepository parentUseServeRepository;


    //获取指定老人的服务信息
    public List<Map<Object,Object>> getUse_serveInfo(Long parentId)
    {

        return parentUseServeMapper.getUse_serveInfo(parentId);
    }


    //获取指定老人的服务信息 评分
    public List<Map<Object,Object>> getUse_serveInfoForGrade(Long parentId)
    {

        return parentUseServeMapper.getUse_serveInfoForGrade(parentId);
    }

    //获取指定老人的定制服务信息
    public List<Map<Object,Object>> getUse_PersonalServeInfo(Long parentId,String ispersonal)
    {

        return parentUseServeMapper.getUse_PersonalServeInfo(parentId,ispersonal);
    }



    //保存评分信息
    @Transactional
    public List<Map<Object,Object>> saveParentUseInfo(ParentUseServe parentUseServe)
    {
        parentUseServeRepository.save(parentUseServe);
        return getUse_serveInfo(parentUseServe.getParentId());
    }
    //保存预定信息
    @Transactional
    public void saveParentOrderInfo(ParentUseServe parentUseServe)
    {
        parentUseServeRepository.save(parentUseServe);

    }


//获取指定人员和日期返回服务信息
    public List<Map<Object,Object>> getUseServeInfoByStartTimeAndParentId(Long parentId,String startTime)
    {
        return parentUseServeMapper.getUseServeInfoByStartTimeAndParentId(parentId,startTime);
    }

    //获取指定人员和日期返回定制服务信息
    public List<Map<Object,Object>> getPersonalUseServeInfoByStartTimeAndParentId(Long parentId,String startTime)
    {
        return parentUseServeMapper.getPersonalUseServeInfoByStartTimeAndParentId(parentId,startTime);
    }


    //getUseServeOrderInfoByStartTimeAndParentId

    //获取全部订单信息
    public List<Map<Object,Object>> getAllOrders()
    {
        return parentUseServeMapper.getAllOrders();
    }
    //获取日期返回定制服务信息
    public List<Map<Object,Object>> getUseServeOrderInfoByStartTimeAndParentId(String startTime)
    {
        return parentUseServeMapper.getUseServeOrderInfoByStartTimeAndParentId(startTime);
    }
//删除订单信息
@Transactional
    public void deleteOrdersById(Long Id)
    {
        parentUseServeRepository.deleteById(Id);
       // parentUseServeRepository.save(new ParentUseServe(12L,12L,"s","s","-1"));
    }


}
