import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        try{
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Сервер запущен");

            Socket client = serverSocket.accept();
            System.out.println("Клиент подключен");

            Scanner in = new Scanner(client.getInputStream());
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);

            Thread readThread = new Thread(() -> {
                while (true) {
                    if (in.hasNext()) {
                        String clientMessage = in.nextLine();
                        System.out.println("\n" + "Клиент: " + clientMessage);
                        System.out.print("Сервер: ");
                    }
                }
            });
            readThread.start();

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Сервер: ");
                String serverMessage = scanner.nextLine();
                out.println(serverMessage);
            }
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }
}
