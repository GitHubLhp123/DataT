package com.datat.datasource.Service;

import com.datat.datasource.entity.DataSourceEntity;
import com.datat.datasource.mapper.DataSourceMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: mlhp1
 * @date: 2021/7/26
 * @desc: 数据源信息Service类
 */
@Service
public class DataSourceService {
    @Autowired
    DataSourceMapping dataSource;

    public List<DataSourceEntity> getDsListByTeamName(String teamName, int pageIndex, int pageSize) {
        return dataSource.getDsListByTeamName(teamName, pageIndex, pageSize);
    }

    public void saveDataSource(DataSourceEntity dataSourceEntity) {
        dataSource.saveDataSource(dataSourceEntity);
    }
}
