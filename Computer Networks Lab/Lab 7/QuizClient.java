import java.io.*;
import java.net.*;

public class QuizClient {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 12345);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        // Start the quiz
        while (true) {
            // Receive the question from the server
            String question = in.readLine();

            // Print the question to the console
            System.out.println(question);

            // Get the user's answer
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String answer = reader.readLine();

            // Send the answer to the server
            out.println(answer);

            // Receive the result from the server
            String result = in.readLine();

            // Display the result to the user
            System.out.println(result);

            // Check if the quiz is finished
            if (result.equals("Quiz finished!")) {
                break;
            }
        }

        // Close the socket
        socket.close();
    }
}
