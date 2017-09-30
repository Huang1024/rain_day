package com.hht;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by hht on 2017/8/30.
 */
public class Test {
    public static void main(String[] args) {
        ServerSocket serverSocket;
        Socket socket;
        try {
            serverSocket = new ServerSocket(9999);
            socket = serverSocket.accept();
            System.out.println("新增连接："+socket.getInetAddress()+":"+socket.getPort());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
