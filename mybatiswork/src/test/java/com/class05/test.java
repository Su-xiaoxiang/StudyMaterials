package com.class05;

import com.class05.mapper.MybatisMapper;
import com.class05.pojo.ClassPojo;
import com.class05.pojo.student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @UserName 程序员_Suxiaoxiang
 * @date 2024/10/13 20:51
 * @Version 1.0
 */
public class test {
    private SqlSession sqlSession;
    @BeforeEach
    public void before() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //3.`SqlSession`对象，用于执行具体的SQL语句
        sqlSession = sqlSessionFactory.openSession();
    }
    @AfterEach
    public void after() {
        //5.关闭SqlSession对象
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 根据id查询班级信息以及班级中所有的学生信息
     */
//    @Test
//    public void selectClassById() {
//        MybatisMapper mapper = sqlSession.getMapper(MybatisMapper.class);
//        List<ClassPojo> classes = mapper.selectClassById(1);
//        System.out.println(classes);
//    }

    /**
     * 根据id查询学生信息以及学生所在的班级信息
     */
    @Test
    public void selectStudentById() {
        MybatisMapper mapper = sqlSession.getMapper(MybatisMapper.class);
        student student = mapper.selectStudentById(1);
        System.out.println("学生姓名："+student.getName());
        System.out.println("学生班级："+student.getClassPojo().getClass_name());
    }
    /**
     * .根据学生名字和年龄查询学生信息，使用if和where标签实现动态sql语句判断，判断条件：是否为null，如果为null,就不添加对应的条件语句
     */
    @Test
    public void selectStudentByIdAndAge() {
        MybatisMapper mapper = sqlSession.getMapper(MybatisMapper.class);
        student student = mapper.selectStudentByIdAndAge("张三", 18);
        System.out.println("学生姓名："+student.getName());
    }

    /**
     * 根据学生名字和年龄查询学生信息,如果名字不为空只根据名字进行查询；如果名字为空，年龄不为空只根据年龄进行查询；如果名字和年龄都为空则查询全部学生信息
     */
    @Test
    public void selectStudentByIdOrAge() {
        MybatisMapper mapper = sqlSession.getMapper(MybatisMapper.class);
        List<student> students = mapper.selectStudentByIdOrAge(null, null);
        for (student student : students) {
            System.out.println("学生姓名："+student.getName());
        }
    }
    /**
     * 插入一条学生信息，（4，赵六，21,1）------使用注解形式
     */
    @Test
    public void insertStudent() {
        MybatisMapper mapper = sqlSession.getMapper(MybatisMapper.class);
        student student = new student();
        student.setName("李四");
        student.setAge(20);
        student.setClass_id(1);
        int result = mapper.insertStudent(student);
        System.out.println("成功插入："+result+"条数据");
    }
    /**
     * 根据id查询学生表中id为2的学生信息------使用注解形式
     */
    @Test
    public void  updateStudent() {
        MybatisMapper mapper = sqlSession.getMapper(MybatisMapper.class);
        student student = new student();
        student.setName("赵六");// 4，赵六，21,1
        student.setAge(22);
        student.setClass_id(2);
        student.setId(2);
        int result = mapper.updateStudent(student);
        System.out.println("成功更新："+result+"条数据");
    }

    /**
     * 修改学生表中id为3的学生年龄为25
     */
    @Test
    public void updateStudentByid() {
        MybatisMapper mapper = sqlSession.getMapper(MybatisMapper.class);
        student student = new student();
        student.setAge(25);
        student.setId(3);
        int result = mapper.updateStudentByid(student);
        System.out.println("成功更新："+result+"条数据");
    }

    /**
     * 批量删除学生表中id在（2,3,4）范围内的所有学生
     */
    @Test
    public void deleteStudentById() {
        MybatisMapper mapper = sqlSession.getMapper(MybatisMapper.class);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
        list.add(4);
        int i = mapper.deleteStudentById(list);
        System.out.println("成功删除："+i+"条数据");
    }
}
