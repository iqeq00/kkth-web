package com.kkth.web.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 菜单资源关系表
 * </p>
 *
 * @author lichee
 * @since 2019-08-14
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("sys_menu_resource")
public class SysMenuResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 菜单ID
     */
    private Integer menuId;

    /**
     * 资源ID
     */
    private String resourceId;

    public SysMenuResource(String resourceId) {
        this.resourceId = resourceId;
    }

    public SysMenuResource(Integer menuId, String resourceId) {
        this.menuId = menuId;
        this.resourceId = resourceId;
    }

    public SysMenuResource(Integer id, Integer menuId, String resourceId) {
        this.id = id;
        this.menuId = menuId;
        this.resourceId = resourceId;
    }

    public static final String ID = "id";

    public static final String MENU_ID = "menu_id";

    public static final String RESOURCE_ID = "resource_id";

}
