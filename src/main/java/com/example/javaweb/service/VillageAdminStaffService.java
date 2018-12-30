package com.example.javaweb.service;

import com.example.javaweb.dao.VillageAdminStaffMapper;
import com.example.javaweb.dao.VillageStaffMapper;
import com.example.javaweb.domain.VillageAdminStaff;
import com.example.javaweb.domain.VillageStaff;
import com.example.javaweb.repository.VillageAdminStaffRepository;
import com.example.javaweb.repository.VillageStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 员工信息 CRUD 服务
 */
@Service
public class VillageAdminStaffService {
    @Autowired
    private VillageAdminStaffRepository villageAdminStaffRepository;
    @Autowired
    private VillageAdminStaffMapper villageAdminStaffMapper;


    /**
     * 获取全部管理员信息
     *
     * @return
     */
    public List<VillageAdminStaff> getAllVillageAdminStaff() {

        List<VillageAdminStaff> villageAdminStaffList = villageAdminStaffMapper.getAllVillageAdminStaff();
        return villageAdminStaffList;

    }

    /**
     * 根据id删除villageStaff
     * @param villageAdminStaffId
     * @return 管理员信息列表
     */
    @Transactional
    public List<VillageAdminStaff> deleteVillageStaffById(Long villageAdminStaffId) {

        villageAdminStaffRepository.deleteById(villageAdminStaffId);
        return getAllVillageAdminStaff();
    }

    /**
     * 保存员管理员信息
     * @param villageAdminStaff
     * @return
     */
    public  List<VillageAdminStaff> saveVillageStaffInfo(VillageAdminStaff villageAdminStaff)
    {
        villageAdminStaffRepository.save(villageAdminStaff);
        return  getAllVillageAdminStaff();
    }

    /**
     * 根据姓名返回管理员登陆信息
     * @param loginname
     * @return
     */
    public VillageAdminStaff getVillageAdminStaffByLoginName(String loginname)
    {
        return  villageAdminStaffMapper.getVillageAdminStaffByLoginName(loginname);
    }
}
