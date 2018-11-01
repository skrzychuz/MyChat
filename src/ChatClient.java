import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient {

    public ChatClient() {
        System.out.println("Establishing connection. Please wait ...");
        DataInputStream console = null;
        OutputStream streamOut = null;
        BufferedReader reader = null;
        ObjectInputStream objectInputStream = null;
        try {
            Socket socket = new Socket("127.0.0.1", 8885);
            System.out.println("Connected: " + socket);
            console = new DataInputStream(System.in);
            streamOut = new DataOutputStream(socket.getOutputStream());
            InputStream inputStream = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            objectInputStream = new ObjectInputStream(socket.getInputStream());

        } catch (UnknownHostException uhe) {
            System.err.println("Host unknown: " + uhe.getMessage());
        } catch (IOException ioe) {
            System.err.println("Unexpected exception: " + ioe.getMessage());
        }
        String line = "";
        String answer = "";
        while (!line.equals(".bye")) {
            try {
                line = console.readLine();
                streamOut.write((line + "\n").getBytes());
                streamOut.flush();
                MyMessage myMessage = (MyMessage) objectInputStream.readObject();
                System.out.println(myMessage.getText());
//              answer = reader.readLine();
//                System.out.println(answer);

            } catch (IOException | ClassNotFoundException ioe) {
                System.out.println("Sending error: " + ioe.getMessage());
            }
        }
    }

    public static void main(String args[]) {
        ChatClient client = new ChatClient();

    }
}