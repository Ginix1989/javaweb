package com.example.javaweb.securityExp;

import com.example.javaweb.domain.ParentInfo;
import com.example.javaweb.domain.VillageAdminStaff;
import com.example.javaweb.service.ParentInfoService;
import com.example.javaweb.service.VillageAdminStaffService;
import javafx.scene.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    VillageAdminStaffService villageAdminStaffService;//管理管理员登陆
    @Autowired
    ParentInfoService parentInfoService;//父母登陆

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return  null;
    }
    public UserDetails loadUserByUsername(String username,String author)throws UsernameNotFoundException {


        switch (author)
        {
            case "parent":
                ParentInfo parentInfo = parentInfoService.getParentInfoByParentName(username);

                if (parentInfo!=null)
                {
                    //假设返回的用户信息如下;
                    UserInfo userInfo = new UserInfo(parentInfo.getUserName(), parentInfo.getPassWord(), "ROLE_PARENT", parentInfo.getGeneralName(),parentInfo.getParentId().toString(),true, true, true, true);
                    return  userInfo;
                }
                else
                {
                    return  null;
                }

            case "children":
                break;
            case "admin":
               VillageAdminStaff villageAdminStaff=  villageAdminStaffService.getVillageAdminStaffByLoginName(username);

               if (villageAdminStaff!=null)
               {
                   //假设返回的用户信息如下;
                   UserInfo userInfo = new UserInfo(villageAdminStaff.getAdminstaffName(), villageAdminStaff.getPassWord(), "ROLE_ADMIN", villageAdminStaff.getAdminstaffName(),villageAdminStaff.getId().toString(),true, true, true, true);
                   return  userInfo;
               }
               else
               {
                   return  null;
               }


            case "staff":
                break;

            default:
                return  null;
        }

        return null;
    }

}
