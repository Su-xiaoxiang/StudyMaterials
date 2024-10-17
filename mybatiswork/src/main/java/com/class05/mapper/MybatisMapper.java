package com.class05.mapper;

import com.class05.pojo.ClassPojo;
import com.class05.pojo.student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;
/**
 * @UserName 程序员_Suxiaoxiang
 * @date 2024/10/13 20:56
 * @Version 1.0
 */
public interface MybatisMapper {
    /**
     * 根据id查询班级信息以及班级中所有的学生信息
     * @Username 程序员-Su_xiaoxiang
     * @date 2024/10/13 20:59
     */

    List<ClassPojo> selectClassById(@Param("id") Integer id);
    student selectStudentById  (@Param("id") Integer id);
    student selectStudentByIdAndAge(@Param("name") String name,@Param("age") Integer age);
    List<student> selectStudentByIdOrAge(@Param("name") String name,@Param("age") Integer age);
    //插入一条学生信息，（4，赵六，21,1）------使用注解形式
    @Insert("insert into student(name,age,class_id) values(#{name},#{age},#{class_id})")
    int insertStudent(student student);
    //根据id查询学生表中id为2的学生信息------使用注解形式
    @Update("update student set name=#{name},age=#{age},class_id=#{class_id} where id=#{id}")
    int updateStudent(student student);
    int updateStudentByid(student student);
    int deleteStudentById(@Param("ids") List<Integer> id);
}
