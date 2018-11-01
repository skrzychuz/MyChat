import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {

        MyMessage myMessage = new MyMessage(12, "terefere");
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
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());

                        while ((line = reader.readLine()) != null) {
                            if (line.equals("give me object")) {
                                objectOutputStream.writeObject(myMessage);
                            }
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