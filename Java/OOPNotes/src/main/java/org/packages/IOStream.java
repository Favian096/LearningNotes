package org.packages;

import java.io.*;

public class IOStream {
    public static void main(String[] args) throws IOException {
//        I/O流(2)
        {
            System.out.println("------------------------------------------");
//            缓冲流(高效流, 高级流)
            /*  缓冲流自带缓冲区, 可以提高原始字节流, 字符流读写数据的性能  */
            /*  IO流体系
                   字节流 InputStream     FileInputstream     BufferedInputStream(字节输入缓冲流8kb)
                         OutputStream    FileOutputStream    BufferedOutputStream(字节输出缓冲流8kb)
                                                              ObjectInputStream(对象字节输入流)
                                                              ObjectOutputStream(对象字节输出流)
                                ...

             *     字符流 Reader        FIleReader        BufferedReader(字符输入缓冲流)
                          Writer        FileWriter        BufferedWriter(字符输出缓冲流)
                                                          InputStreamReader(字符输入转换流)
                                                          OutputStreamWriter(字符输出转换流)
                               ...
                                 */


            /*          字节缓冲流(方法相同)         */
            /*从字节流的继承关系来看(resource中图片),
                    可以通过由FileInputStream创建文件对象,
                    然后再由BufferedInputStream进行包装, 赋值给InputStream*/

            FileOutputStream fis = new FileOutputStream("D:\\Java\\ITEMS\\course_ITheima\\" +
                    "Oop_Advance\\src\\main\\resources\\TestData7.txt", true);
            OutputStream bis = new BufferedOutputStream(fis);
            bis.write("字节缓冲流和字符缓冲流的使用".getBytes());
            bis.write("\r\n".getBytes());
            bis.write("字节缓冲流使用BufferedOutputStream进行了字符串的写入\n".getBytes());
            bis.close();

            /*          字符缓冲流(多了按行读取数据的功能readLIne, 和newLine()换行     )       */
            /*  包装方法同上  */
            Reader r = new FileReader("D:\\Java\\ITEMS\\course_ITheima\\Oop_Advance\\src\\main\\resources\\TestData7.txt");
            BufferedReader br = new BufferedReader(r);
//            按行读取(同read)
            System.out.println(br.readLine());

            r.close();
            br.close();

            System.out.println("-------------------------------------");
        }
        {
//            转换流
            /*
             *   将字节流(按照编码)转换为字符流  */
            /*属于字符流下:
             *  字符输入转换流 InputStreamReader
             *  字符输出转换流 OutputStreamWriter */
            /*  常用构造器   InputStreamReader(file, 编码类型)*/

            InputStream is = new FileInputStream("D:\\Java\\ITEMS\\course_ITheima\\Oop_Advance\\src\\main\\resources\\TestData8(ANSI).txt");

            /*      使用构造器转化过程       */
            Reader isr = new InputStreamReader(is, "GBK");

            BufferedReader br = new BufferedReader(isr);
            String str;
            while ((str = br.readLine()) != null) {
                System.out.println(str);
            }
            System.out.println("-----------------------");

        }
        {
//            打印流(高效写入文件)
            /*  方便高效的写数据到文件中(打印什么就是什么)   */
            /*   字节PrintStream()
             *   字符PrintWriter()  */
            /*  括号内部可以直接写文件的位置 */
            FileOutputStream os = new FileOutputStream("D:\\Java\\ITEMS\\course_ITheima\\Oop_Advance\\src\\main\\resources\\TestData7.txt", true);
            PrintStream ps = new PrintStream(os);
            ps.print("\n--------使用字节打印流直接打印数据----------\n");
            os.close();
            ps.close();
        }

    }
}

















