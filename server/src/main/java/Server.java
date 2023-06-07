import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Server {

    public static void main(String[] args) {

        System.out.println("Server is started. Waiting for client connection...");

        try (ServerSocket serverSocket = new ServerSocket(8081);
             Socket clientSocket = serverSocket.accept();
             BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)) {
            System.out.println("Client connected");
            writer.println("Hello!");
            String clientGreeting = reader.readLine().toLowerCase();
            if (checkLetters(clientGreeting)) {
                writer.println("Що таке паляниця?");
                String clientResponse = reader.readLine();
                if (clientResponse.equalsIgnoreCase("пиріжечок")) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String currentDateTime = LocalDateTime.now().format(formatter) + " До побачення!";
                    writer.println(currentDateTime);
                } else {
                    clientSocket.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean checkLetters(String text) {
        for (char c : text.toCharArray()) {
            if (c == 'э' || c == 'ы' || c == 'ъ' || c == 'ё') {
                return true;
            }
        }
        return false;
    }
}

