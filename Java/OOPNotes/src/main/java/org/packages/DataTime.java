package org.packages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class DataTime {
    public static void main(String[] args) throws ParseException {
        {
            //        时间和日期(详见文档)
            /*      Date类(系统此刻日期和时间)       */
            Date d = new Date();
            System.out.println("返回系统此刻时间" + d);
//            获取时间毫秒值
            System.out.println(d.getTime());
            System.out.println(System.currentTimeMillis());

            System.out.println("----------时间毫秒值转日期对象-----------");
            long time_1 = System.currentTimeMillis();
            time_1 += (60 * 60 * 1000);
            d.setTime(time_1);
            System.out.println("一小时后的时间:" + d);

//            SimpleDateFormat类(格式化毫秒时间, 字符串解析为时间)
//             构造器
/*          SimpleDateFormat();
            SimpleDateFormat(指定格式);                          年   月 日   时 分 秒 星期 上下午*/
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss EEE a");
//            转为字符串
            String a = sdf.format(d);
            System.out.println("格式化后的时间:" + a);
//            字符串格式化方法(parse, 需要设置抛出异常)
            String strDay = "2019年09月07日 18:00:00";
            SimpleDateFormat sdf_strDay = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
            Date start = sdf_strDay.parse(strDay);
            System.out.println("字符串转化为时间:" + sdf.format(start));

            String new_strDay = "2023年03月23日 00:00:00";
            Date end = sdf_strDay.parse(new_strDay);
            long J = end.getTime() - start.getTime();
            Date L = new Date(J);
            System.out.println("计算时间间隔:" + sdf.format(L));
//            (时间前后比较可以用 date对象.before  date对象.after   比较)

//            Calendar(不可创建对象, 日历)
            /*  获取对象 */
            Calendar cal = Calendar.getInstance();
            System.out.println(cal);

            /*  获取日期字段信息(一旦修改整个会变)    */
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int minutes = cal.get(Calendar.MINUTE);
            System.out.println("字段信息:" + year + "\t" + month + "\t" + day + "\t" + hour + "\t" + minutes);
            /*      日历的增删改减......*/
            cal.add(Calendar.DAY_OF_MONTH, 1);
            System.out.println(cal.get(Calendar.DAY_OF_MONTH));

//            新API(常用)
            /*  LocalDate 不含具体时间的日期
                LocalTime 不含日期的时间
                LocalDateTime   具体时间的日期和时间
                Instant         代表时间戳(当前时刻)
                DateTimeformatter  用于时间格式化和解析
                Duration  计算 时间间隔
                period      计算 日期 间隔*/
            System.out.println("---------------------");
            LocalDateTime nowDate = LocalDateTime.now();
            System.out.println(nowDate);

            /*  直接使用 now.get******()  就可以实现时间获取*/
            System.out.println(nowDate.getYear());
//            获取当前纳秒
            System.out.println(nowDate.getNano());

            Instant instant = Instant.now();
            System.out.println(instant.atZone(ZoneId.systemDefault()));
//            灵活格式化
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日 hh:mm:ss EEE a");
            System.out.println("正向格式化:" + nowDate.format(dtf));
            System.out.println("反向格式化:" + dtf.format(nowDate));

//            字符串解析(注意ofpattern字母大小写)
            DateTimeFormatter dtf_2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
            LocalDateTime beg_time = LocalDateTime.parse("2019年09月07日 18:00:00", dtf_2);
            LocalDateTime end_time = LocalDateTime.parse("2023年03月23日 00:00:00", dtf_2);
            System.out.println(beg_time + " ~ " + end_time);

//            时间差(第二个参数减第一个)
            Duration duration = Duration.between(beg_time, end_time);
            System.out.println(duration.toDays());
            System.out.println(duration.toHours());
            System.out.println(duration.toMinutes());

//            chronounit 工具(比较相差时间)
            LocalDateTime bir = LocalDateTime.of(2003, 03,
                    23, 00, 00, 00);
            System.out.println("相差的年数:" + ChronoUnit.YEARS.between(bir, nowDate));
        }
        {
//            8 种基本类型的引用类型(包装类型)
            /*  byte        Byte
             *  short       Short
             *  int         Integer
             *  long        Long
             *  char        Character
             *  float       Float
             *  double      Double
             *  boolean     Boolean
             * 基本数据类型的数值和变量可以直接赋值给包装类型的变量(自动装箱)
             * 包装类型的的数值和变量可以直接赋值给基本数据类型(自动拆箱)*/

            /*  区别: 包的容错率更高, 可以为null
             *        可以将基本类型的数据转化为字符串类型
             *        也可以将字符串类型转化为基本数据类型(字符串要是相应的数字)*/

            int a = 123;
            Integer b = 456;
            a = b;
            b = a;
            String str_0 = b.toString();
            String str_1 = "789";
            int c = Integer.valueOf(str_1);
            System.out.println(c);


        }

    }
}
