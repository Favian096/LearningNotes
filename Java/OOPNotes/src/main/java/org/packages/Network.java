package org.packages;

import java.io.IOException;
import java.net.InetAddress;

public class Network {
    public static void main(String[] args) throws IOException {
        {
//            基础网络通信

//            1.获取本地网络地址对象
            InetAddress ip_local = InetAddress.getLocalHost();
            System.out.println(ip_local);
            System.out.println(ip_local.getHostAddress());

//            2.获取其他ip对象
            InetAddress ip_Bing = InetAddress.getByName("bing.com");
            System.out.println(ip_Bing.getHostName());
            System.out.println(ip_Bing.getHostAddress());

//            3.获取公网IP对象
            InetAddress ip_Bing2 = InetAddress.getByName("204.79.197.200");
            System.out.println(ip_Bing2.getHostName());

//            4. PING 判断是否连通
            System.out.println(ip_Bing.isReachable(5000));

        }
        {
//            动态代理
            /*  无侵入式的给代码增加功能  */
            /*
                    使用 proxy 实现代理(实现接口的类)
                    public static Object newProxyInstance(ClassLoader loader, Class<?> interface, invocationHandler h)
             *
                    (强转对象)Proxy.newProxyInstance(
                    ProxyUtil.class.getClassLoader,
                    new class[]{要代理的类名.class},
                    new invocationHandler(){
                        @overrider
                        public Object invoke(Object proxy, Method method, Object[] args){
                                               代理的对象      要运行的方法    运行方法要传递的实参
                            ...
                        }
                    });
                    */
        }
        {
//          反射
            /*  获取class对象
             *       1. Class.forName("全类名");   即: 包名.类名
             *       2. 类名.class;
             *       3. 类对象.getClass();     */


        }

    }
}
