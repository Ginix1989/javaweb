package com.example.javaweb.service;

import com.example.javaweb.dao.MenuInfoMapper;
import com.example.javaweb.dao.ParentInfoMapper;
import com.example.javaweb.domain.MenuInfo;
import com.example.javaweb.domain.ParentInfo;
import com.example.javaweb.repository.MenuInfoRepository;
import com.example.javaweb.repository.ParentInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ParentInfoService {
    @Autowired
    ParentInfoMapper parentInfoMapper;
    @Autowired
    ParentInfoRepository parentInfoRepository;

    /**
     * 获取全部父母信息
     *
     * @return
     */
    public List<ParentInfo> getAllParentInfo() {

        return parentInfoMapper.getAllParentInfo();

    }

    /**
     * 根据id 删除父母信息
     *
     * @param parentId
     * @return
     */
    @Transactional
    public List<ParentInfo> deleteParentInfoById(Long parentId) {

        parentInfoRepository.deleteById(parentId);

        return getAllParentInfo();
    }

    /**
     * 保存父母信息
     *
     * @param parentInfo
     * @return
     */
    @Transactional
    public List<ParentInfo> saveParentByEntity(ParentInfo parentInfo) {

        parentInfoRepository.save(parentInfo);
        return getAllParentInfo();
    }

    //根据登录名查找用户  登陆
    public ParentInfo getParentInfoByParentName(String parentName) {
        return parentInfoMapper.getParentInfoByParentName(parentName);
    }

    //根据Id 查找 父母信息
    public ParentInfo getParentInfoByParentId(Long parentId) {
        return parentInfoRepository.findById(parentId).get();
    }

    @Transactional
    //保存父母信息
    public Map<String, String> saveParent(ParentInfo parentInfo) {


        parentInfoRepository.save(parentInfo);

        Map<String, String> msg = new HashMap<String, String>();
        msg.put("msg", "保存成功");


        return msg;

    }
}
