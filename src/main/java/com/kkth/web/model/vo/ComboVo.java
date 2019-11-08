package com.kkth.web.model.vo;

import com.kkth.framework.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 下拉框Vo
 * </p>
 *
 * @author lichee
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ComboVo extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String menuName;

}
