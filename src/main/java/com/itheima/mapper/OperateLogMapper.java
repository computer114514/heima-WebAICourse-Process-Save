package com.itheima.mapper;

import com.itheima.pojo.OperateLog;
import com.itheima.pojo.PageResult;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OperateLogMapper {

    //插入日志数据
    @Insert("insert into operate_log (operate_emp_id, operate_time, class_name, method_name, method_params, return_value, cost_time) " +
            "values (#{operateEmpId}, #{operateTime}, #{className}, #{methodName}, #{methodParams}, #{returnValue}, #{costTime});")
    public void insert(OperateLog log);

    @Select("select o.id,o.operate_emp_id,e.name operateEmpName, operate_time, class_name, method_name, method_params, return_value,cost_time\n" +
            "from operate_log o left join emp e on e.id=o.id;")
    List<OperateLog> queryOperateLog();

    @Select("select count(*) from operate_log")
    Long queryOperateLogTotal();
}
