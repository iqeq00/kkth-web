package com.kkth.web.model.vo;

import com.kkth.framework.enums.MenuTypeEnum;
import com.kkth.framework.enums.StatusEnum;
import com.kkth.framework.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author lichee
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MenuVo extends BaseModel {

    private static final long serialVersionUID = 1L;

    private Integer parentId;

    private String menuName;

    private String path;

    private String router;

    private MenuTypeEnum menuType;

    private String icon;

    private String alias;

    private StatusEnum status;

    private List<String> resourceIds;

}