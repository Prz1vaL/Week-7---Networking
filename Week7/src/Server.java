import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Server {
    static boolean done;
public static void main(String[] args) {
    connectToServer();
}
public static void connectToServer() {
    //Try to connect to the Server on the designated port e.g. 12345. A successful socket returns a socket.
    try(ServerSocket serverSocket = new ServerSocket(12345)) {
        Socket connectionSocket = serverSocket.accept();

        //Creating Input & Output Streams for the Connection
        InputStream inputToServer = connectionSocket.getInputStream();
        OutputStream outputFromServer = connectionSocket.getOutputStream();

        Scanner scanner = new Scanner(inputToServer, StandardCharsets.UTF_8);
        PrintWriter serverPrintOut = new PrintWriter(new OutputStreamWriter(outputFromServer, StandardCharsets.UTF_8), true);
        serverPrintOut.println("Hi This is a exercise module of CS5001 - Week 7");
        // Server take input from the client and echo it back,
        //Until boolean done = false run the server,boolean = true Should be placed in a loop that listens for a terminator text.

        while(!done && scanner.hasNextLine()) {
            String line = scanner.nextLine();
            serverPrintOut.println("Echo from apm30 Server : 12345 - " +line);
            if (line.toLowerCase().trim().equals("week")) {
                done = true;
            }
        }
    }
    catch (IOException e) {
        e.printStackTrace();
    }
    }
}
