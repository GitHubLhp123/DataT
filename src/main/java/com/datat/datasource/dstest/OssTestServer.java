package com.datat.datasource.dstest;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.datat.datasource.entity.DataSourceEntity;
import com.datat.util.server.UtilsServer;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author mlhp1
 * @date 2021-07-26 17:44:25
 * @desc OSS数据源测试类
 */
@Slf4j
public class OssTestServer {
    public static String testDsDataOfOss(DataSourceEntity dataSourceEntity) {
        String returnValue = "测试通过";
        OSS ossClient = null;
        try {
            // url
            String endpoint = dataSourceEntity.getUrl();
            // 密码
            String accessKeySecret = new String(UtilsServer.decryptBasedDes(dataSourceEntity.getPassword()));
            // 账号
            String accessKeyId = dataSourceEntity.getUser();
            // 链接oss客户端
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            String[] bucketList = dataSourceEntity.getDsSchema().split(",");
            /**
             * 对桶列表，依次进行：求文件数操作
             * 若没有这个桶或无操作该桶权限，则报错
             */
            for (String bucketName : bucketList) {
                ObjectListing objectListing = ossClient.listObjects(bucketName);
                List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
                System.out.println(objectListing.getBucketName());
            }
        } catch (Exception e) {
            log.error("测试失败:{}", e.getMessage());
            returnValue = "错误信息:" + e.getMessage();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return returnValue;
    }
}
