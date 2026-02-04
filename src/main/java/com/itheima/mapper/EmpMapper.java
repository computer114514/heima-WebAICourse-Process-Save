package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {
    @Select(" select * from emp")
    List<Emp> queryAllEmp();

    //统计性别人数
    @MapKey("name")
     List<Map<String, Object>> countEmpGenderData();

//TODO 原始查询
//    @Select("select count(*) from emp e left join dept d on e.dept_id = d.id;")
//    public Long count();//总记录数字
//    @Select("select e.* ,d.name deptName from emp e left join dept d on e.dept_id = d.id order by update_time desc " +
//            "limit #{start},#{pageSize};")
//public List<Emp> list(Integer start,Integer pageSize);

//    @Select("select e.* ,d.name deptName from emp e left join dept d on e.dept_id = d.id order by update_time desc ")
    //万万不可加分号

//    public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);//查询方法

    public List<Emp> list(EmpQueryParam empQueryParam);//查询方法

    @Options(useGeneratedKeys = true,keyProperty="id")
    //主键返回，emp本来没有主键，在数据库表插入后返回主键到emp的id上
    @Insert("    insert into emp (username,name, gender, phone, job, salary, image, entry_date, dept_id, create_time, " +
            "update_time)\n" +
            "    values(#{username},#{name},#{gender},#{phone},#{job},#{salary},\n" +
            "           #{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    void deleteByIds(List<Integer> ids);

    Emp getById(Integer id);

    void updateById(Emp emp);
    //插入方法
    @MapKey("pos")
    List<Map<String,Object>> countEmpJobData();

    @Select("select * from emp where username=#{username} and password=#{password}")
    Emp selectEmpByUsernameAndPassword(Emp emp);
}





















