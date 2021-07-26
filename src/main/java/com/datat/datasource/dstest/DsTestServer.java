package com.datat.datasource.dstest;

import com.datat.common.DataSourceType;
import com.datat.datasource.entity.DataSourceEntity;
import lombok.extern.slf4j.Slf4j;

/**
 * @author mlhp1
 * @date 2021-07-26 17:44:25
 * @desc 数据源测试类
 */
@Slf4j
public class DsTestServer {

    /**
     * TestCorrectnessOfDs
     *
     * @param dataSourceEntity
     * @return string 测试结果
     * @desc 根据数据源配置信息，进行测试。若测试正确返回：“测试通过”，反则返回报错信息
     */
    public static String TestCorrectnessOfDs(DataSourceEntity dataSourceEntity) {
        String dsType = dataSourceEntity.getType();
        if (DataSourceType.MYSQL.equals(dsType)) {
            return MysqlTestServer.testDsDataOfMysql(dataSourceEntity);
        } else if (DataSourceType.MYSQL8.equals(dsType)) {
            return MysqlTestServer.testDsDataOfMysql(dataSourceEntity);
        } else if (DataSourceType.HIVE.equals(dsType)) {
            return HiveTestServer.testDsDataOfHive(dataSourceEntity);
        } else if (DataSourceType.SFTP.equals(dsType)) {
            return SftpTestServer.testDsDataOfSftp(dataSourceEntity);
        } else if (DataSourceType.OSS.equals(dsType)) {
            return OssTestServer.testDsDataOfOss(dataSourceEntity);
        } else if (DataSourceType.MONGODB.equals(dsType)) {
            return MongodbTestServer.testDsDataOfMongodb(dataSourceEntity);
        } else {
            log.error("没有找到该数据源类型");
            return "没有找到该数据源类型";
        }
    }
}
