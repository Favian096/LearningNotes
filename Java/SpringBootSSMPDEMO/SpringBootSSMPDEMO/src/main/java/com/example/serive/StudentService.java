package com.example.serive;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.Dao.StudentDao;
import com.example.Obj.Student;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

//定义为业务层Bean
//@Service
//@RestController
//@RequestMapping(value = "/student")
public class StudentService implements StudentDao {
    //此controller基于实现Dao进行@Override
    @Autowired
    StudentDao studentDao;

    @Override
    public int insert(Student entity) {
        return studentDao.insert(entity);
    }

    @Override
    public int updateById(Student entity) {
        return studentDao.updateById(entity);
    }


    @Override
    public int deleteById(Serializable id) {
        return studentDao.deleteById(id);
    }

    @Override
    public Student selectById(Serializable id) {
        return studentDao.selectById(id);
    }

    @Override
    public List<Student> selectList(Wrapper<Student> queryWrapper) {
        return studentDao.selectList(null);
    }


    @Override
    public IPage<Student> selectPage(int current, int pageSize) {
        IPage page = new Page(current, pageSize);
        return studentDao.selectPage(page, null);
    }

    //    ---------------------------------------------------------------------------------
    @Override
    public int deleteByMap(Map<String, Object> columnMap) {
        return 0;
    }

    @Override
    public int delete(Wrapper<Student> queryWrapper) {
        return 0;
    }

    @Override
    public int deleteBatchIds(Collection<? extends Serializable> idList) {
        return 0;
    }

    @Override
    public int update(Student entity, Wrapper<Student> updateWrapper) {
        return 0;
    }

    @Override
    public List<Student> selectBatchIds(Collection<? extends Serializable> idList) {
        return null;
    }

    @Override
    public List<Student> selectByMap(Map<String, Object> columnMap) {
        return null;
    }

    @Override
    public Student selectOne(Wrapper<Student> queryWrapper) {
        return null;
    }


    @Override
    public Integer selectCount(Wrapper<Student> queryWrapper) {
        return null;
    }


    @Override
    public List<Map<String, Object>> selectMaps(Wrapper<Student> queryWrapper) {
        return null;
    }

    @Override
    public List<Object> selectObjs(Wrapper<Student> queryWrapper) {
        return null;
    }

    @Override
    public <E extends IPage<Student>> E selectPage(E page, Wrapper<Student> queryWrapper) {
        return null;
    }


    @Override
    public <E extends IPage<Map<String, Object>>> E selectMapsPage(E page, Wrapper<Student> queryWrapper) {
        return null;
    }
}
