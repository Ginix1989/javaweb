package com.example.javaweb.dao;

        import com.example.javaweb.domain.MenuInfo;
        import org.apache.ibatis.annotations.Mapper;
        import org.apache.ibatis.annotations.Param;
        import org.apache.ibatis.annotations.Select;
        import org.springframework.stereotype.Component;

        import java.util.List;
        import java.util.Map;

@Component
@Mapper
public interface MenuInfoMapper {
    @Select("select * from menu_info t where t.menuid in (#menuid) order by menuid asc")
    List<MenuInfo> getAllMenu_Back(String menuIds);


    @Select("SELECT * FROM menu_info WHERE menuid in (${ids}) order by menuid asc")
    public List<MenuInfo> getAllMenu(@Param("ids") String ids);

}
