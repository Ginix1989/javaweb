package com.example.javaweb.securityExp;

import com.example.javaweb.domain.ChildrenInfo;
import com.example.javaweb.domain.ParentInfo;
import com.example.javaweb.domain.VillageAdminStaff;
import com.example.javaweb.domain.VillageStaff;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

public class UserInfo implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String role;
    private String generalname;
    private Long userid;//父母Id

    private ParentInfo parentInfo;
    private ChildrenInfo childrenInfo;
    private VillageAdminStaff villageAdminStaff;
    private VillageStaff villageStaff;

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    public UserInfo(String username, String password, String role, String generalname, Long userid, boolean accountNonExpired, boolean accountNonLocked,
                    boolean credentialsNonExpired, boolean enabled) {

        this.username = username;
        this.password = password;
        this.role = role;
        this.userid = userid;
        this.generalname = generalname;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }

    public UserInfo(ParentInfo parentInfo, ChildrenInfo childrenInfo, VillageStaff villageStaff, VillageAdminStaff villageAdminStaff, String role, boolean accountNonExpired, boolean accountNonLocked,
                    boolean credentialsNonExpired, boolean enabled) {

        this.parentInfo = parentInfo;
        this.childrenInfo = childrenInfo;
        this.villageStaff = villageStaff;
        this.villageAdminStaff = villageAdminStaff;

        if (parentInfo!=null)
        {
            this.username = parentInfo.getUserName();
            this.password = parentInfo.getPassWord();
            this.userid =parentInfo.getParentId();
            this.generalname = parentInfo.getGeneralName();
        }
        if (childrenInfo!=null)
        {
            this.username = childrenInfo.getUserName();
            this.password = childrenInfo.getPassWord();
            this.userid = childrenInfo.getParentId();
            this.generalname = childrenInfo.getGeneralName();
        }
        if (villageStaff!=null)
        {
            this.username = villageStaff.getLoginName();
            this.password = villageStaff.getPassWord();
            this.generalname = villageStaff.getStaffName();
        }
        if (villageAdminStaff!=null)
        {
            this.username = villageAdminStaff.getLoginName();
            this.password = villageAdminStaff.getPassWord();
            this.generalname = villageAdminStaff.getAdminstaffName();
        }
        this.role = role;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }

    // 这是权限
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(role);
    }

    public String getGeneralname() {
        return generalname;
    }

    public Long getUserid() {
        return userid;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public  ParentInfo getParentInfo()
    {
        return  this.parentInfo;
    }

    public ChildrenInfo getChildrenInfo() {
        return childrenInfo;
    }

    public VillageAdminStaff getVillageAdminStaff() {
        return villageAdminStaff;
    }

    public VillageStaff getVillageStaff() {
        return villageStaff;
    }
    public  String getRole(){
        return  this.role;
    }
}
