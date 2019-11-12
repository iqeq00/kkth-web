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
 * 生物多样性
 * </p>
 *
 * @author lichee
 * @since 2019-11-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("biodiversity")
public class Biodiversity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 公园ID
     */
    private Integer parkId;

    /**
     * 保护区编号
     */
    private String protectionNumber;

    /**
     * 生物编号
     */
    private String biologicalNumber;

    /**
     * 中文名称
     */
    private String chineseName;

    /**
     * 拉丁名称
     */
    private String latinName;

    /**
     * 大类名称
     */
    private String mainCategory;

    /**
     * 生物分类
     */
    private String biologicalCategory;

    /**
     * 数量
     */
    private String quantity;

    /**
     * 保护级别
     */
    private String protectionLevel;

    /**
     * 分布
     */
    private String distributed;

    /**
     * 特征描述
     */
    private String featureDesc;

    /**
     * 照片
     */
    private String img;


}
