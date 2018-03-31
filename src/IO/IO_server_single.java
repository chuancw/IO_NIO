package IO;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * BIO特点
 * 2初阻塞点
 * 一个服务端为一个客户端服务
 */
public class IO_server_single {

    public static void main(String[] args)  throws Exception {

        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("服务端启动, 端口号为：12345");

        while(true){
            // 获取socket套接字
            Socket socket = serverSocket.accept();  //阻塞点
            System.out.println("有新客户端加上来了");
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];

            //循环读取数据
            while(true){

                int data = inputStream.read(bytes);  //阻塞点
                if(data != -1){

                    String readStr = new String(bytes, 0, data, "GBK");
                    System.out.println(readStr);

                }
                else{
                    break;
                }
            }


        }

    }
}
