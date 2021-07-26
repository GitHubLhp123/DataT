package com.datat.datasource.dstest;


import com.datat.datasource.entity.DataSourceEntity;
import com.datat.util.server.UtilsServer;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * @author mlhp1
 * @date 2021-07-26 17:44:25
 * @desc HIVE数据源测试类
 */
@Slf4j
public class HiveTestServer {

    public static String testDsDataOfHive(DataSourceEntity dataSourceEntity) {
        String returnValue = "测试通过";
        String password = UtilsServer.decryptBasedDes(dataSourceEntity.getPassword());
        String userName = dataSourceEntity.getUser();
        try {
            // 此Class 位于 hive-jdbc的jar包下
            Class.forName("org.apache.hive.jdbc.HiveDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (String url : dataSourceEntity.getUrl().split("\r\n")) {
            Connection conn = null;
            Statement stmt = null;
            try {
                if (url.length() > 10) {
                    url = url.trim();
                    conn = DriverManager.getConnection(url, userName, password);
                    stmt = conn.createStatement();
                    //要进行输出结果才能判断是否发生错误
                    System.out.println(stmt.executeQuery("select version()").getFetchSize());
                }
            } catch (Exception e) {
                log.error("测试失败:URL{},{}", url, e.getMessage());
                returnValue = "URL:" + url + ",错误信息:" + e.getMessage();
                break;
            } finally {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return returnValue;
    }
}
