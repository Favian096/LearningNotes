package org.packages;

import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpress {
    public static void main(String[] args) {
        {
            //        正则表达式(基本)
            /*          字符类
             *  [abc]               只能是a,b或c
             *  [^abc]              除了a,b或c之外的任何字符
             *  [a-zA-Z]            a到z或A到Z 包括头尾
             *  [a-d[m-p]]          a到d或m到p(相对于[a-dm-p])
             *  [a-z&&[def]]        a到z并且包括d,e或f
             *  [a-z&&[^bc]]        a到z并且除了b,c(相对于[ad-z])
             *  [a-z&&[^m-p]]       a到z并且除了m到p(相对于[a-lq-z])
             * */
            /*          预定义字符类(要加一个转义字符)
             *  .           表示任何字符
             *  \d          一个数字([0-9])
             *  \D          非数字([^0-9])
             *  \s          一个空白符([\t\n\0B\f\r])
             *  \S          非空白符([^\s])
             *  \w          字母、数字、下划线([0-9a-zA-Z_])
             *  \W          非单词字符([^w])
             * */
            /*          贪婪的量词
             *  X?         表X字符一次或没有
             *  X*         表X字符0次或n次
             *  X+         表X字符一次或多次
             *  X{n}       表X字符n次
             *  X{n, }     表X字符至少n次
             *  X{n, m}    表X字符至少n次, 最多m次(n到m次)
             * */
            /*  字符串的替换和分割可以使用正则表达式*/

            String str_0 = "a";
            String str_1 = "f";
            String str_2 = "147852";
            String str_3 = "13_strABC";
            String str_4 = "lylylyly";
            String str_5 = "  },\n";
            System.out.println(str_5.matches("  },\n"));
            System.out.println(str_0.matches("[abc]"));
            System.out.println(str_1.matches("[a-dg-q]]"));
            System.out.println(str_2.matches("\\d{6}"));
            System.out.println(str_3.matches("\\w*"));
            System.out.println(str_4.matches("(ly){4}"));
//            邮箱判定
            String em = "我的邮箱是-->1978699474@qq.com, ";
            String email = "\\w{1,30}@\\w{2,20}(\\.\\w{2,20}){1,2}";
            /*  创建爬取器*/
            Pattern pattern = Pattern.compile(email);
            Matcher matcher = pattern.matcher(em);
            while (matcher.find()) {
                String rs = matcher.group();
                System.out.println(rs);
            }
            System.out.println("------------------------");
        }

        {
//            数组工具类Arrays
            /*  Arrays.toString(arr)         返回数组内容(字符串类型)
             *  Arrays.sort(arr)             对数组排序(升序)
             *  Arrays.binarySearch(arr, 要找的数)     对要找的数进行二分查找返回索引(必须先排序)
             *                          (找不到会返回负数, 负数的绝对值是要插入该数字的位置+1)
             */

            int[] arr = {60, 72, 12, 48, 96, 24, 84, 36};
            System.out.println("原数组:" + Arrays.toString(arr));
            Arrays.sort(arr);
            System.out.println("升序后:" + Arrays.toString(arr));
            System.out.println("36在排序后的数组的:" + (Arrays.binarySearch(arr, 36) + 1) + "位置");

            /*   由于sort只能升序, 故引入了Comparator接口进行自定义排序(只支持对 引用类型 排序)*/
            Integer[] ages = {60, 72, 12, 48, 96, 24, 84, 36};
            Arrays.sort(ages, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    /*  =======****规则如下****======*/
                    /*  如果左边数据 大于 右边数据, 返回正整数(升序)
                     *  如果左边数据 小于 右边数据, 返回负整数(降序)
                     *  如果左边数据 等于 右边数据, 返回0
                     *  由此, 可以直接使用  o1-o2 表升序   o2-o1 表降序*/
//                    return o1 - o2;
                    return o2 - o1;
                }
            });
            System.out.println("Comparator自定义规则进行比较:" + Arrays.toString(ages));
            System.out.println("---------------------------");
//
//            二分查找
            int[] arr_num = {54, 36, 90, 9, 27, 18, 45, 63, 72, 81};
            int num = 63, i = (arr_num.length - 1) / 2, index = -1;
            Arrays.sort(arr_num);
            System.out.println("原数组排序后:" + Arrays.toString(arr_num));

            while (num != arr_num[i]) {
                if (num < arr_num[i]) {
                    i /= 2;
                } else if (num > arr_num[i]) {
                    i += i / 2;
                }
                index = i + 1;
            }

            System.out.println("二分查找63在第" + index + "个位置");
            System.out.println("---------------------------");
        }

    }
}
