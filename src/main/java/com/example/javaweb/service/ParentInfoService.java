package com.example.javaweb.service;

import com.example.javaweb.dao.MenuInfoMapper;
import com.example.javaweb.dao.ParentInfoMapper;
import com.example.javaweb.domain.MenuInfo;
import com.example.javaweb.domain.ParentInfo;
import com.example.javaweb.repository.MenuInfoRepository;
import com.example.javaweb.repository.ParentInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ParentInfoService {
    @Autowired
    ParentInfoMapper parentInfoMapper;
    @Autowired
    ParentInfoRepository parentInfoRepository;

    /**
     * 获取全部父母信息
     * @return
     */
    public List<ParentInfo> getAllParentInfo()
    {

        return parentInfoMapper.getAllParentInfo();

    }

    /**
     * 根据id 删除父母信息
     * @param parentId
     * @return
     */
    @Transactional
    public List<ParentInfo> deleteParentInfoById(Long parentId)
    {

        parentInfoRepository.deleteById(parentId);

         return getAllParentInfo();
    }

    /**
     * 保存父母信息
     * @param parentInfo
     * @return
     */
    @Transactional
    public  List<ParentInfo> saveParentByEntity(ParentInfo parentInfo)
    {

        parentInfoRepository.save(parentInfo);
        return getAllParentInfo();
    }

}
