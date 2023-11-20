import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Подключен к серверу.");

            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            Thread readThread = new Thread(() -> {
                while (true) {
                    if (in.hasNext()) {
                        String serverMessage = in.nextLine();
                        System.out.println("\n" + "Сервер: " + serverMessage);
                        System.out.print("Клиент: ");
                    }
                }
            });
            readThread.start();

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Клиент: ");
                String clientMessage = scanner.nextLine();
                out.println(clientMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}