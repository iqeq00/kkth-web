package com.kkth.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kkth.framework.enums.MenuTypeEnum;
import com.kkth.framework.enums.StatusEnum;
import com.kkth.web.model.entity.SysMenu;
import com.kkth.web.model.vo.MenuTreeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author lichee
 * @since 2019-07-31
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 获取用户权限菜单
     *
     * @param uid
     * @param statusType
     * @param menuTypes
     * @return
     */
    List<MenuTreeVo> getUserPermMenus(@Param("uid") Integer uid, @Param("statusType") StatusEnum statusType, @Param("menuTypes") List<MenuTypeEnum> menuTypes);
}
