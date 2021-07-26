package com.datat.datasource.dstest;

import com.datat.datasource.entity.DataSourceEntity;
import com.datat.util.server.UtilsServer;
import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

@Slf4j
/**
 * @author mlhp1
 * @date 2021-07-26 17:44:25
 * @desc SFTP数据源测试类
 */
public class SftpTestServer {
    public static String testDsDataOfSftp(DataSourceEntity dataSourceEntity) {
        String returnValue = "测试通过";
        ChannelSftp nChannelSftp = null;
        Session nSShSession = null;
        Channel channel = null;
        try {
            String password = (UtilsServer.decryptBasedDes(dataSourceEntity.getPassword()));
            String username = dataSourceEntity.getUser();
            String host = dataSourceEntity.getIp();
            int port = Integer.parseInt(dataSourceEntity.getPort());
            JSch nJSch = new JSch();
            // 3.获取session
            nSShSession = nJSch.getSession(username, host, port);
            // 4.设置密码
            nSShSession.setPassword(password);
            // 5.实例化Properties
            Properties nSSHConfig = new Properties();
            // 6.设置配置信息
            nSSHConfig.put("StrictHostKeyChecking", "no");
            // 7.session中设置配置信息
            nSShSession.setConfig(nSSHConfig);
            // 8.session连接
            nSShSession.connect();
            // 9.打开sftp通道
            channel = nSShSession.openChannel("sftp");
            // 10.开始连接
            channel.connect();
            nChannelSftp = (ChannelSftp) channel;
        } catch (Exception e) {
            log.error("测试失败:{}", e.getMessage());
            returnValue = "错误信息:" + e.getMessage();
        } finally {
            try {
                if (channel != null) {
                    channel.disconnect();
                }
                if (nSShSession != null) {
                    nSShSession.disconnect();
                }
                if (nChannelSftp != null) {
                    nChannelSftp.disconnect();
                }
            } catch (Exception e) {
                returnValue = "错误信息:" + e.getMessage();
            }
        }
        return returnValue;
    }
}
