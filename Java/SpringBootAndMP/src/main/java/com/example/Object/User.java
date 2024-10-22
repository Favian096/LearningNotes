package com.example.Object;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User {
//    ID生成策略
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;

//    设置在数据库中的查询字段为pwd, 不参与查询
    @TableField(value = "pwd", select = false)
    private String password;
    private Integer age;
    private String tel;

//    添加数据库不存在但是要用到的即时字段
    @TableField(exist = false)
    private Integer Online;

}
