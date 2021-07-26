package com.datat.datasource.dstest;

import com.datat.datasource.entity.DataSourceEntity;
import com.datat.util.server.UtilsServer;
import lombok.extern.slf4j.Slf4j;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * @author mlhp1
 * @date 2021-07-26 17:44:25
 * @desc MYSQL数据源测试类
 */
@Slf4j
public class MysqlTestServer {

    public static String testDsDataOfMysql(DataSourceEntity dataSourceEntity) {
        String returnValue = "测试通过";
        String password = UtilsServer.decryptBasedDes(dataSourceEntity.getPassword());
        String userName = dataSourceEntity.getUser();
        String driver = "com.mysql.jdbc.Driver";
        //判断mysql版本
        if (dataSourceEntity.getVersion().startsWith("8")) {
            driver = "com.mysql.cj.jdbc.Driver";
        }
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (String url : dataSourceEntity.getUrl().split("\r\n")) {
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(url, userName, password);
            } catch (SQLException e) {
                log.error("测试失败:URL{},{}", url, e.getMessage());
                returnValue = "URL:" + url + ",错误信息:" + e.getMessage();
                break;
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return returnValue;
    }
}
