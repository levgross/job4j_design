package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String line = in.readLine();
                    if (line != null && !line.isEmpty()) {
                        String[] array = line.split(" ");
                        String msg = "";
                        for (String str : array) {
                            if (str.startsWith("/?msg=")) {
                                msg = str.substring(6);
                                break;
                            }
                        }
                        if ("Exit".equals(msg)) {
                            server.close();
                        } else {
                            out.write(msg.getBytes());
                        }
                    }
                    out.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("Exception in main method", e);
        }
    }
}
