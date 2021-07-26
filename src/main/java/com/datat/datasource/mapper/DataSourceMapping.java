package com.datat.datasource.mapper;

import com.datat.datasource.entity.DataSourceEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: mlhp1
 * @date: 2021/7/26
 * @desc: 数据源信息Mapper类
 */
@Mapper
public interface DataSourceMapping {
    /**
     * getDsListByTeamName
     *
     * @param teamName
     * @param pageIndex
     * @param pageSize
     * @return List<DataSourceEntity>
     * @desc 根据团队名查询数据源列表
     */
    @Select("select * from dataSource where teamName=#{teamName} limit #{pageIndex},#{pageSize}")
    List<DataSourceEntity> getDsListByTeamName(@Param("teamName") String teamName, @Param("pageIndex") int pageIndex,
                                               @Param("pageSize") int pageSize);

    /**
     * saveDataSource
     *
     * @param dataSourceVo
     * @desc 保存数据源信息到数据库中
     */
    @Insert("insert into dataSource\n" +
            "(id,dsName,dsSchema,state,user,password,url,type,teamName,ip,port,authSchema) \n" +
            "values\n" +
            "(#{vo.getId},#{vo.getDsName},#{vo.getDsSchema},#{vo.getState},#{vo.getUser},#{vo.getPassword},#{vo.getUrl},#{\n" +
            "vo.getType},#{vo.getTeamName},#{vo.getIp},#{vo.getPort},#{vo.getAuthSchema},#{vo.getVersion});")
    void saveDataSource(@Param("dataSourceEntity") DataSourceEntity dataSourceVo);
}
