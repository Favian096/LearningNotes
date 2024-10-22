package com.example.Dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.Object.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserDaoTests {
    @Autowired
    UserDao userDao;

    @Test
    void selectAll() {
        System.out.println(userDao.selectList(null));
    }

    @Test
    void saveTest() {
        User user = new User();
        user.setName("啊啊");
        user.setPassword("000000");
        user.setTel("151515155");
        user.setAge(21);
        userDao.insert(user);
    }

    @Test
    void updateTest() {
        User user = new User();

//      由于简单的更新需要考虑全部字段的修改,
//        由此提出了setId的 ID 后面添加了一个L 即可实现修改某一字段而其他字段不变

        user.setId(4L);
        user.setPassword("111111");
        userDao.updateById(user);
    }

    @Test
    void getByPage() {
//        实现分页功能需要配置拦截器(在Config->MPConfig.java)

        IPage<User> page = new Page<>(2, 2);

        System.out.println(userDao.selectPage(page, null).getRecords());
    }


    @Test
    void getAllByQueryWrapper() {
//        由queryWrapper设置限制条件

//        基本格式
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.lambda().lt(User::getAge, "16");


//        通用格式
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        lt 小于   gt 大于, 可添加or(), 进行或者


        Integer age1 = 16;
        Integer age2 = 3;
//        下为并且关系------------小于16-------------------------大于3(即between)
        lambdaQueryWrapper.lt(age1 != null, User::getAge, age1)
                .gt(age2 != null, User::getAge, age2);


//        设置字段
        lambdaQueryWrapper.select(User::getId, User::getName);

        System.out.println(userDao.selectList(lambdaQueryWrapper));
    }

    @Test
    void deleteBatchIdsTest() {
//            传入list, 多个删除
    }

}
