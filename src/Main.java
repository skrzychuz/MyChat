import sun.plugin2.message.Message;
import sun.plugin2.message.Serializer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Main {


    public static void main(String[] args) {
        int port = 8885;
        try {
            ServerSocket serverSocket = new ServerSocket(port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("accept");

                Thread thread = new Thread(() -> {
                    try {
                        InputStream inputStream = clientSocket.getInputStream();
                        OutputStream outputStream = clientSocket.getOutputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                        String line;

                        while ((line = reader.readLine()) != null) {
                            System.out.println(line);
                            outputStream.write((line + "\n").getBytes());

                        }
                        clientSocket.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                thread.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}