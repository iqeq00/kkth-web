package com.kkth.web.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 级别沿革
 * </p>
 *
 * @author lichee
 * @since 2019-11-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("level_evolution")
public class LevelEvolution implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 公园id
     */
    private Integer parkId;

    /**
     * 名称
     */
    private String name;

    /**
     * 时间
     */
    private String time;

    /**
     * 级别
     */
    private String level;

    /**
     * 主管单位
     */
    private String organizer;

    /**
     * 备注
     */
    private String remark;

    /**
     * 保护区编号
     */
    private String protectionNumber;


}
