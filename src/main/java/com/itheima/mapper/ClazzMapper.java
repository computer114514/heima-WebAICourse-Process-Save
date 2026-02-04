package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface ClazzMapper {
    @Insert("insert into clazz (name,room,begin_date,end_date,master_id,subject,create_time,update_time) values " +
            "(#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject},#{createTime},#{updateTime})")
    void addClazz(Clazz clazz);
//    @Select("select clazz.id, clazz.nam
//    e, room, begin_date, end_date, master_id, subject, clazz.create_time, clazz.update_time,emp.name masterName  from clazz,emp where clazz.master_id=emp.id;\n")

    List<Clazz> getClazz(ClazzQueryParam clazzQueryParam);

    @Select("select count(*) from clazz,emp where clazz.master_id=emp.id;")
    Long getClazzCount();

    @Select("select * from clazz where id=#{id}")
    Clazz queryById(Integer id);

    void update(Clazz newClazz);

    @Delete("Delete from clazz where id=#{id}")
    void deleteById(Integer id);

    @Select("select count(*) from student where clazz_id=#{id}")
    int IsExistStudent(Integer id);

    @Select("select * from clazz")
    List<Clazz> queryAllClass();
}
