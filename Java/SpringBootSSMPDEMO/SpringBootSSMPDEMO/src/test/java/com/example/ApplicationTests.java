package com.example;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.Dao.StudentDao;
import com.example.Obj.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

    @Autowired
    StudentDao studentDao;

    @Test
    void contextLoads() {
        System.out.println(studentDao.selectById(1)
        );
    }

    @Test
    void saveTest() {
        Student stu = new Student();
        stu.setName("啊啊");
        stu.setGender('女');
        stu.setClassId(1);
        stu.setScore(100);
        studentDao.insert(stu);
    }

    @Test
    void updateTest() {
        Student stu = new Student();
        stu.setId(133);
        stu.setName("渡渡");
        stu.setGender('女');
        stu.setClassId(1);
        stu.setScore(100);
        studentDao.updateById(stu);
    }

    @Test
    void deleteTest() {
//        studentDao.deleteById(132);
    }

    @Test
    void getPageTest() {
//        测试分页->由于分页需要sql设置limit, mybatis实现需要使用拦截器
//        故我们在cfg中设置MPConfig返回拦截器
        IPage page = new Page(2, 4);
//                            当前页      页面条数
        studentDao.selectPage(page, null);
//        查询后返回的数据为page对象(queryWrapper为数据条件)
        System.out.println("-----------------------------------------------------------");
        System.out.println(page.getPages());
        System.out.println(page.getCurrent());
        System.out.println(page.getRecords());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
        System.out.println("-----------------------------------------------------------");
    }


    //    加入queryWrapper条件查询
    @Test
    void getPageWithQueryWrapper() {
        IPage page = new Page(1, 5);
        QueryWrapper<Student> queryWrapper = new QueryWrapper<Student>();
        queryWrapper.like("gender", "M");
        System.out.println(studentDao.selectPage(page, queryWrapper).getRecords());
    }


    //    标准的格式
    @Test
    void getListWithLambdaQueryWrapper() {
        String classId = "1";
        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper<Student>();
//                                  只有在条件不为空时才会执行筛选(即Strings.isNoEmpty(classId))
        lambdaQueryWrapper.like(classId != null, Student::getClassId, classId);
        System.out.println(studentDao.selectList(lambdaQueryWrapper).toString());
    }

}
