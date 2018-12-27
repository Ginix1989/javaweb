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

    //保存评分信息
    @Transactional
    public List<Map<Object,Object>> saveParentUseInfo(ParentUseServe parentUseServe)
    {
        parentUseServeRepository.save(parentUseServe);
        return getUse_serveInfo(parentUseServe.getParentId());
    }


}
