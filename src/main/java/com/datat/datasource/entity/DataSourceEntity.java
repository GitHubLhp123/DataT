package com.datat.datasource.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author mlhp1
 * @date 2021-07-26 17:44:25
 * @desc 数据源类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DataSourceEntity {
    /**
     * 主键
     */
    private String id;
    /**
     * 数据源名称
     */
    private String dsName;
    /**
     * 库名或库名列表（使用逗号切割）
     */
    private String dsSchema;
    /**
     * 状态 1：有效，0：失效
     */
    private String state;
    /**
     * 用户名
     */
    private String user;
    /**
     * 密码
     */
    private String password;
    /**
     * 数据源链接url
     */
    private String url;
    /**
     * 数据源类型
     */
    private String type;
    /**
     * 团队名
     */
    private String teamName;
    /**
     * ip
     */
    private String ip;
    /**
     * 端口
     */
    private String port;
    /**
     * 认证库
     */
    private String authSchema;
    /**
     * 版本
     */
    private String version;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 上次操作时间
     */
    private Date updateTime;
}
