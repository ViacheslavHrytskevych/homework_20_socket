import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 8081);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
             BufferedReader keyboardReader = new BufferedReader (new InputStreamReader(System.in))) {
            System.out.println("Connected to the server");
            while (!socket.isClosed()) {
                String serverMessage = reader.readLine();
                if (serverMessage == null) {
                    break;
                }
                System.out.println("Server: " + serverMessage);
                System.out.println("Write a message: ");
                String clientMessage = keyboardReader.readLine();
                writer.println(clientMessage);
            }
            System.out.println("Game is over");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
