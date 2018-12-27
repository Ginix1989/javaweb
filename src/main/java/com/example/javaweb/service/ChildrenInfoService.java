package com.example.javaweb.service;

import com.example.javaweb.dao.ChildrenInfoMapper;

import com.example.javaweb.domain.ChildrenInfo;

import com.example.javaweb.repository.ChildrenInfoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ChildrenInfoService {
    @Autowired
    ChildrenInfoMapper childrenInfoMapper;
    @Autowired
    ChildrenInfoRepository childrenInfoRepository;

    /**
     * 获取全部子女信息
     *
     * @return
     */
    public List<ChildrenInfo> getAllChildrenInfo() {

        return childrenInfoMapper.getAllChildrenInfo();

    }

    /**
     * 根据id 删除子女信息
     *
     * @param childrenId
     * @return
     */
    @Transactional
    public List<ChildrenInfo> deleteChildrenInfoById(Long childrenId) {

        childrenInfoRepository.deleteById(childrenId);

        return getAllChildrenInfo();
    }

    /**
     * 保存子女信息
     *
     * @param childrenInfo
     * @return
     */
    @Transactional
    public List<ChildrenInfo> saveChildrenByEntity(ChildrenInfo childrenInfo) {

        childrenInfoRepository.save(childrenInfo);
        return getAllChildrenInfo();
    }

}
