package com.example.serive;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.Dao.StudentDao;
import com.example.Dao.StudentDaoFromMP;
import com.example.Obj.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceFromMP extends ServiceImpl<StudentDao, Student> implements StudentDaoFromMP {
    //                    直接继承ServiceImpl<原Mapper, 原Obj> 并实现业务层接口
//                    即可实现业务层方法(CRUD), 无需再次写studentController和StudentDao的接口
//                    如果方法不够可自行接口和service中写并实现即可

    @Autowired
    StudentDao studentDao;

    @Override
    public IPage<Student> getPage(int current, int pageSize) {
        return studentDao.selectPage(new Page<Student>(current, pageSize), null);
    }
}


