package com.itheima.mapper;

import com.itheima.pojo.ClazzDataKV;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    List<Student> studentPageSearch(StudentQueryParam studentQueryParam);

    Long getStudentCountData(StudentQueryParam studentQueryParam);

    void deleteById(int[] ids);

    @Insert("insert into student" +
            "    (name,no,gender,phone,id_card,is_college,address,degree,graduation_date,clazz_id,violation_count,violation_score,create_time,update_time)" +
            "values (#{name},#{no},#{gender},#{phone},#{idCard},#{isCollege},#{address},#{degree},#{graduationDate},#{clazzId},#{violationCount},#{violationScore},#{createTime},#{updateTime})")
    void addStudent(Student student);

    @Select("select * from student where id=#{id}")
    Student queryById(Integer id);

    void update(Student student);

    @Update("update student set violation_count=violation_count+1,violation_score=violation_score+#{score}," +
            "update_time=NOW() where " +
            "id=#{id}")
    void punishBadKid(Integer id,Integer score);

    @Select("select (case degree\n" +
            "        when 1 then '初中'\n" +
            "        when 2 then '高中'\n" +
            "        when 3 then '大专'\n" +
            "        when 4 then '本科'\n" +
            "        when 5 then '硕士'\n" +
            "        when 6 then '博士'\n" +
            "        else '其他' end\n" +
            "    )name,count(*)value from student group by degree;")
    List<Map<String, Object>> getStudentDegreeData();

    @Select("    select (case clazz_id\n" +
            "        when 1 then 'JavaEE就业167期'\n" +
            "        when 2 then '前端就业90期'\n" +
            "        when 3 then 'C++就业114期'\n" +
            "        when 4 then 'JavaEE就业166期'\n" +
            "        when 5 then '大数据就业58期'\n" +
            "        else '其他' end\n" +
            "    )clazz from student group by clazz_id order by clazz_id  ;")
    String[] getClazzList();

    @Select("select count(*) num from student group by clazz_id;")
    Integer[] getDataList();
}
