package com.example.javaweb.service;

import com.example.javaweb.dao.VillageStaffMapper;
import com.example.javaweb.domain.VillageStaff;
import com.example.javaweb.repository.VillageStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 员工信息 CRUD 服务
 */
@Service
public class VillageStaffService {
    @Autowired
    private VillageStaffRepository villageStaffRepository;
    @Autowired
    private VillageStaffMapper villageStaffMapper;


    /**
     * 获取全部员工信息
     *
     * @return
     */
    public List<VillageStaff> getAllVillageStaff() {

        List<VillageStaff> villageStaffList = villageStaffMapper.getAllVillageStaff();
        return villageStaffList;

    }

    /**
     * 根据id删除villageStaff
     * @param VillageStaffId
     * @return 员工信息列表
     */
    @Transactional
    public List<VillageStaff> deleteVillageStaffById(Long VillageStaffId) {

        villageStaffRepository.deleteById(VillageStaffId);
        return getAllVillageStaff();
    }

    /**
     * 保存员工信息
     * @param villageStaff
     * @return
     */
    public  List<VillageStaff> saveVillageStaffInfo(VillageStaff villageStaff)
    {
        villageStaffRepository.save(villageStaff);
        return  getAllVillageStaff();
    }
}
