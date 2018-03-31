package IO;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IO_server_multiThread {

    public static void main(String[] args) throws Exception {

        // 线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();
        //ExecutorService threadPool = Executors.newFixedThreadPool(100);
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("服务端口已开启，等待连接");

        while(true){

            Socket socket = serverSocket.accept(); //客户端连接
            System.out.println("有客户端连接上来了");

            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try{
                        InputStream inputStream = socket.getInputStream();
                        byte[] bytes = new byte[1024];

                        while(true){

                            int readData = inputStream.read(bytes);
                            if(readData != -1){
                                String info = new String(bytes, 0, readData,"GBK");
                                System.out.println(info);
                            }
                            else{
                                break;
                            }

                        }
                    }
                    catch(Exception e){

                       e.printStackTrace();
                    }
                }
            });

        }

    }
}
