import java.io.*;
import java.net.*;
import java.util.*;

public class QuizServer {

    private Map<Integer, String> questions;
    private Map<Integer, String> answers;

    public QuizServer() {
        questions = new HashMap<>();
        answers = new HashMap<>();

        // Add your quiz questions and answers here

        questions.put(1, "What is the capital of France?");
        answers.put(1, "Paris");

        questions.put(2, "Which planet is known as the Red Planet?");
        answers.put(2, "Mars");

        questions.put(3, "How many continents are there on Earth?");
        answers.put(3, "7");
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);

        while (true) {
            Socket clientSocket = serverSocket.accept();

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Start the quiz
            for (int questionNumber = 1; questionNumber <= 3; questionNumber++) {
                // Send the question to the client
                out.println("Question " + questionNumber + ": " + questions.get(questionNumber));

                // Receive the client's answer
                String answer = in.readLine();

                // Check if the answer is correct
                if (answer.equals(answers.get(questionNumber))) {
                    // The answer is correct
                    out.println("Correct!");
                } else {
                    // The answer is incorrect
                    out.println("Incorrect. The correct answer is: " + answers.get(questionNumber));
                }
            }

            // The quiz is finished
            out.println("Quiz finished!");

            // Close the socket
            clientSocket.close();
        }
    }

    public static void main(String[] args) throws IOException {
        QuizServer quizServer = new QuizServer();
        quizServer.start();
    }
}
