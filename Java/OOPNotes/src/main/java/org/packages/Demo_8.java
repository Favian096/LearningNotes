package org.packages;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo_8 {
    //    创建日志对象LOGGER, 选择日志技术(导入)
    public static final Logger LOGGER = (Logger) LoggerFactory.getLogger("Demo_8.class");

    public static void main(String[] args) {
        {
            //         日志技术
            try {
                LOGGER.debug("Demo_8 main方法开始执行.");
                LOGGER.info("第二行, 创建变量.");
                LOGGER.error("error");
                LOGGER.warn("waring");
                LOGGER.info("info");
                LOGGER.debug("debug");
                LOGGER.trace("trace");

//        使用占位符输出日志消息
                String name = "itheima";
                Integer age = 20;
                LOGGER.info("用户：{},{}", name, age);
            } catch (Exception e) {
                System.out.println("出现异常!");
                LOGGER.error("异常" + e);
            }

//            日志级别:
/*
            TRACE < DEBUG < INFO < WARN < ERROR
                    * 默认级别为debug
*/

        }
    }
}

