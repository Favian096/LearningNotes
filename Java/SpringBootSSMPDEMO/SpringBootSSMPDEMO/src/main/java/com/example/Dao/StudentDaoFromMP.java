package com.example.Dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.Obj.Student;
import org.apache.ibatis.annotations.Mapper;

//直接继承IService实现业务层的接口
@Mapper
public interface StudentDaoFromMP extends IService<Student> {
    IPage<Student> getPage(int current, int pageSize);
}
