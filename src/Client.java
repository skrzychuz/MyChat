import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", 8885);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("connected");

        InputStream inputStream = null;
        try {
            inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            DataInputStream inputConsole = new DataInputStream(System.in);

            BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            String answer;
            while (true) {
                line = inputConsole.readLine();
                outputStream.write(line.getBytes());
                answer = input.readLine();
                System.out.println(answer);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

