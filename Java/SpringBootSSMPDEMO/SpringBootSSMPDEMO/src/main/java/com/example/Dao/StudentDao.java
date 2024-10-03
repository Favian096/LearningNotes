package com.example.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.Obj.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentDao extends BaseMapper<Student> {
    IPage<Student> selectPage(int current, int pageSize);
//    直接继承BaseMapper并传入obj, 即可调用内置的增删改查方法(以select开头)
//    @Select("Select * from students where id = #{id}")
//    Student getById(int id);
}
