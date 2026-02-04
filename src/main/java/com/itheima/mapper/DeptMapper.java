package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface DeptMapper {
    //TODO 1,手动映射
//    @Results({
//            @Result(column = "create_time",property ="createTime"),
//            @Result(column = "update_time",property ="updateTime")
//    })

    //TODO 2,别名
//    @Select("select id, name, create_time createTime, update_time updateTime from dept order by update_time desc;")

    //TODO 3,罗峰命名
    @Select("select id, name, create_time, update_time from dept order by update_time desc;")
    List<Dept> findAll();

    @Update("update dept set update_time =#{updateTime},name=#{name} where id=#{id}")
    int update(Dept dept);

    @Delete("delete from dept where id=#{id}")
    void deleteById(Integer id);

    @Insert("insert into dept(name,create_time,update_time) values(#{name},#{createTime},#{updateTime})")
    void Insert(Dept dept);

    @Select("select id, name, create_time, update_time from dept where id=#{id}")
    Dept getById(Integer id);

    @Select("select count(*) from emp where dept_id =#{id}")
    int IsExistMapper(Integer id);
//    id查询部门数据
}












