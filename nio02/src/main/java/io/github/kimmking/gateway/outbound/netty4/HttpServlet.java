package io.github.kimmking.gateway.outbound.netty4;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServlet {

    public static void main(String[] args) throws Exception {
        final ServerSocket serverSocket = new ServerSocket(8801);

        try {
            final String body = "hello::" + serverSocket.getLocalPort();
            final Socket socket = serverSocket.accept();
            service(socket, body);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void service(Socket socket, String body) {
        try {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            printWriter.println("Content-Length:" + body.getBytes().length);
            printWriter.println();
            printWriter.write(body);
            printWriter.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
