package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {
    public static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    String str = in.readLine();
                    while (str != null &&!str.isEmpty()) {
                        System.out.println(str);
                        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                        if (str.contains("msg=Exit")) {
                            out.write("<h1>Server is close</h1>".getBytes());
                            server.close();
                            break;
                        } else if (str.contains("msg=Hello")) {
                            out.write("<h1>Hello</h1>".getBytes());
                            break;
                        } else {
                            out.write("<h1>What</h1>".getBytes());
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOG.error("Exception in serverSocket", e);
        }
    }
}
