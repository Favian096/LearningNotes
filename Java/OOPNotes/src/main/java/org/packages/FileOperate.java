package org.packages;

import java.io.File;
import java.io.IOException;

public class FileOperate {
    public static void main(String[] args) throws IOException {
        {
            //                        File 文件操作
            /* File f = new File("文件/文件夹路径")     */
            File f = new File("D:\\Adobe\\Pr/ForzaHorizon4-video-110.mp4");
            /*  f.length() 返回文件的大小(字节数) */
            System.out.println(f.length());

            /*  file 支持相对路径和绝对路径
             *       相对路径一般是定位在模块中文件(File("模块名\\..."))    */

//            file 常用API
            /*   getAbsolutePath()   返回对象的绝对路径(字符串)
             *   getPath()         返回创建对象时的路径(字符串)
             *   getName()          返回对象的名称(带后缀)
             *   getLastModified()  返回对象的最后修改时间(毫秒值)
             *   isFile() 和 isDirectory()  判断是文件还是文件夹(boolean)
             *   exists()           判断文件(夹)是否存在
             *   createNewFile()    创建新的文件(空)
             *   mkdir()            创建一级目录(boolean)
             *   mkdirs()           创建多级目录(boolean)
             *   delete()           删除文件和空文件夹(非空不可删除)
             *   */
            System.out.println(f.getName());

            /*  遍历  :
             *       list   获取当前目录下的所有一级文件(夹)名, 到一个字符串数组中
             *       listFile   获取当前目录下的所有一级文件(夹)对象, 到一个对象数组中
             *          */
            System.out.println("---------------------");
            File d = new File("D:\\Adobe\\Pr");
            String[] name = d.list();
            for (String i : name) {
                System.out.println(i);
            }
            System.out.println("===================");
            File[] df = d.listFiles();
            for (File file : df) {
                System.out.println(file.getName());
            }
        }
        {
            System.out.println("***********************************************");
//            非规律化递归
            /*  文件查找功能(searchFile)  */
            searchFile(new File("D:/"), "TestData.txt");
            System.out.println("**********************************************");

        }

    }

    /**
     * @param dir      源目录
     * @param fileName 要查找的文件名
     */
    public static void searchFile(File dir, String fileName) {
        if (dir != null && dir.isDirectory()) {
            File[] df = dir.listFiles();
            if (df != null && df.length > 0) {
                for (File file : df) {
                    if (file.isFile() & file.getName().contains(fileName)) {
                        System.out.println("File target: " + file.getAbsoluteFile());
                    } else if (file.isDirectory()) {
                        searchFile(file, fileName);
                    }
                }
            }
        } else {
            System.out.println("NotDirectory or DirectoryIsNULL !");
        }
    }
}
