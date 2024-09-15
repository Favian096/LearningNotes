package org.packages;

import java.io.*;
import java.util.Arrays;

public class Demo_10 {
    public static void main(String[] args) throws Exception {
        {
//            编码和解码
            String name = "Colorful啊啊";
            byte[] bytes = name.getBytes("UTF-8");
            System.out.println("UTF-8编码:" + Arrays.toString(bytes));
            String open_code_GBK = new String(bytes, "GBK");
            String open_code = new String(bytes, "UTF-8");
            System.out.println("GBK 解码:" + open_code_GBK);
            System.out.println("UTF-8解码:" + open_code);
        }
        {
            System.out.println("-------------------------------");
//                           I/O流实现文件读写
            /*      创建字节输入流管道与源文件连通    */
            InputStream is = new FileInputStream("D:\\Java\\ITEMS\\course_ITheima\\Oop_Advance\\src\\main\\resources\\TestData.txt");
            /*  read() 方法读取一个字节返回(int)   读取完毕返回 -1
             *           只能用于读取数字和字母    */
            System.out.println("======使用单字节读取======");
            int i = 0, a = is.read();
            System.out.println((char) a);
            while (i != -1) {
                i = is.read();
                System.out.print((char) i);
            }


            System.out.println("\n======使用字节数组读取======");
            InputStream is2 = new FileInputStream("D:\\Java\\ITEMS\\course_ITheima\\Oop_Advance\\src\\main\\resources\\TestData2.txt");

            byte[] arr = new byte[3];
//            b 记录了每次读取到的字节数
            int b = is2.read(arr);
            System.out.println("读取到了" + b + "个字节" + Arrays.toString(arr) + "\t" + new String(arr));
            while ((b = is2.read(arr)) != -1) {
//                设置解码范围0~b
                System.out.print(new String(arr, 0, b));
            }
        }
        {
            System.out.println("\n========一次性全部读取============");
//            以上方法均会导致中文乱码, 由此可使用一次性读取全部字节的方法
            /*  1.建立一个与源文件一样大的字节数组(会导致内存溢出, 不建议)
             *   2.使用    .readAllBytes() */
            InputStream is3 = new FileInputStream("D:\\Java\\ITEMS\\course_ITheima\\Oop_Advance\\src\\main\\resources\\TestData3.txt");
            System.out.println(new String(is3.readAllBytes()));

        }
        {
//            数据写入
/*            FileOutputStream("路径") 创建字节输入流管道与源文件连通(覆盖写入), 不存在源文件则会自动创建
              FileOutputStream("路径", boolean append)   boolean为true时为追加写入  */
            /*  方法:
             *       write(int a)   写一个字节
             *       write(byte[] buffer)   写一个数组
             *       write(byte[] buffer, 起始索引, 写入字节个数)     */
            OutputStream os = new FileOutputStream("D:\\Java\\ITEMS\\course_ITheima\\Oop_Advance\\src\\main\\resources\\TestData4.txt");
            OutputStream os2 = new FileOutputStream("D:\\Java\\ITEMS\\course_ITheima\\Oop_Advance\\src\\main\\resources\\TestData5.txt");

            os.write(97);
            os.write('a');
            os.write('a');
            os.flush();

            os2.write(65);
            os2.write('A');
            byte[] arr = {'B', 66, 67, 68, 69, 70};
            os2.write(arr);
            os2.write('\n');
            /*  使用 .getBytes() 方法简略写法 */
            os2.write("\t!@#$%^&*(0)-+=/~TVa123啊啊".getBytes());
            /*  加入*/
            os2.write("\r\n".getBytes());
            os2.write(arr, 1, 3);

            os.close();
            os2.close();
            System.out.println("===============写入完成!==============");
            /*      注: 写入完成之后最好刷新数据流flush()  以此来保证数据写入成功(可继续使用)
             *          使用close() 来刷新并关闭数据流, 关闭后数据流不可再次使用(释放资源)*/


//            文件复制
            InputStream isTD5 = new FileInputStream("D:\\Java\\ITEMS\\course_ITheima\\Oop_Advance\\src\\main\\resources\\TestData5.txt");
            OutputStream isTD4 = new FileOutputStream("D:\\Java\\ITEMS\\course_ITheima\\Oop_Advance\\src\\main\\resources\\TestData4.txt");
            isTD4.write(isTD5.readAllBytes());
            isTD5.close();
            isTD4.close();
            System.out.println("===============复制完成!==============");

            /*  以上文件的读写如果出现错误, 将不会flush和close
                    由此引入了finally(不管怎么一定会执行, 除非jvm关闭)
                        不建议在finally添加return*/

            OutputStream os3 = null;
            try {
                os3 = new FileOutputStream("D:\\Java\\ITEMS\\course_ITheima\\Oop_Advance\\src\\main\\resources\\TestData6.txt");
                os3.write("JLY".getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("finally一定执行!");
                try {
                    if (os3 != null) {
                        os3.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            /*  以上方法依旧复杂, 由此再一次引入了↓(依旧不是很常用)*/
/*            try (定义I/O流) {
                处理方法
            } catch (Exception e) {
                ...
            }*/
//            运行完毕后会自动调用close(Autoclose()), 无需finally

        }
        {
            System.out.println("==================================");
//            字符输入流(直接读字符(非字节)), 不容易出现乱码
            Reader fr = new FileReader("D:\\Java\\ITEMS\\course_ITheima\\Oop_Advance\\src\\main\\resources\\TestData6.txt");

            /* read() 一次读取一个字符*/
            System.out.println((char) fr.read());

            /*  一次读取一个数组*/
            char[] arr = new char[2];
            int len = fr.read(arr);
            System.out.println(new String(arr, 0, len));

            /*  写入:
             *       FileWriter("路径")
             *       FileWriter("路径", boolean append) */
            /*  方法:
             *       write(int a)   写一个字符
             *       write(char[] buffer)   写一个字符数组
             *       write(char[] buffer, 起始索引, 写入字符个数)
             *       write(String str)   写一个字符串
             *       write(String str, 起始位置, 写入字符个数)  */

            Writer wr = new FileWriter("D:\\Java\\ITEMS\\course_ITheima\\Oop_Advance\\src\\main\\resources\\TestData6.txt", true);

            wr.write('\n');
            wr.write('A');
            wr.write("aaa啊啊啊啊");
            wr.flush();

            String str = "啊啊JLY啊啊";
            wr.write(str, 2, 3);

            wr.close();
            System.out.println("============字符写入完成===========");

        }
    }
}
