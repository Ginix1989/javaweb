package com.example.javaweb.service;

import com.example.javaweb.dao.ServeInfoMapper;
import com.example.javaweb.domain.ServeInfo;
import com.example.javaweb.repository.ServerInfoRepositrory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/***
 * 服务信息  Service
 * 处理全部服务项目信息相关的操作
 * CRUD
 */
@Service
public class ServeInfoService {

    @Autowired
   private  ServerInfoRepositrory serverInfoRepositrory;
    @Autowired
    private ServeInfoMapper  serveInfoMapper;

    //获取全部服务项目信息
    public List<ServeInfo> getAllServe()
    {
        List<ServeInfo> list = serveInfoMapper.getAllServerInfo();

        return list;
    }

    /***
     *  DeleteServeItemInfo and return  refresh View
     *  Begain Transactional
     * @return
     */
    @Transactional
    public List<ServeInfo> deleteServeInfo(Long serveItemId)
    {
        serverInfoRepositrory.deleteById(serveItemId);

        return getAllServe();
    }

}
