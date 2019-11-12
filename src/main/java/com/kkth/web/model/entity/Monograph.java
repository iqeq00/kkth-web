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
 * 专著
 * </p>
 *
 * @author lichee
 * @since 2019-11-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("monograph")
public class Monograph implements Serializable {

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
     * 专著编号
     */
    private String monographNumber;

    /**
     * 成果编号
     */
    private String resultNumber;

    /**
     * 书名
     */
    private String name;

    /**
     * 作者
     */
    private String author;

    /**
     * 出版时间
     */
    private String publishingDate;

    /**
     * 出版社
     */
    private String publishingHouse;

    /**
     * 出版地
     */
    private String publishingPlace;

    /**
     * 收集情况
     */
    private String collectionSituation;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 备注
     */
    private String remark;

    /**
     * 存储地址
     */
    private String url;


}
