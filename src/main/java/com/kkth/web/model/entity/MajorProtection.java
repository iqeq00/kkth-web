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
 * 重点保护对象
 * </p>
 *
 * @author lichee
 * @since 2019-11-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("major_protection")
public class MajorProtection implements Serializable {

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
     * 保护对象编号
     */
    private String protectionObjectNumber;

    /**
     * 名称
     */
    private String name;

    /**
     * 保护对象类型
     */
    private String protectionObjectCategory;

    /**
     * 保护级别
     */
    private String protectionLevel;

    /**
     * 地质年代
     */
    private String geologicalAge;

    /**
     * 保护措施
     */
    private String safeguard;

    /**
     * 开发利用情况
     */
    private String develop;

    /**
     * 长度
     */
    private String lengths;

    /**
     * 面积
     */
    private String acreage;

    /**
     * 是否有实物标本
     */
    private String specimen;

    /**
     * 特征描述
     */
    private String featureDesc;

    /**
     * 照片
     */
    private String img;


}
