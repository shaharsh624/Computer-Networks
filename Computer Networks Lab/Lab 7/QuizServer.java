import java.io.*;
import java.net.*;

public class QuizServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Quiz Server is running and listening on port 12345...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                // Send welcome message and instructions
                out.println("Welcome to the Online Quiz!");
                out.println("Answer the following questions. Type 'q' to quit.");
                out.println();

                int score = 0;
                int questionNumber = 1;

                while (questionNumber <= 3) {
                    // Implement quiz questions here
                    String question = getQuestion(questionNumber);
                    out.println("Question " + questionNumber + ": " + question);
                    String answer = in.readLine();
                    if (answer.equalsIgnoreCase(getAnswer(questionNumber))) {
                        score++;
                        out.println("Correct!");
                    } else {
                        out.println("Incorrect. The correct answer is: " + getAnswer(questionNumber));
                    }
                    out.println();
                    questionNumber++;
                }

                out.println("Quiz finished! Your score: " + score + " out of 3.");
                clientSocket.close();
                System.out.println("Client disconnected.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getQuestion(int questionNumber) {
        // Replace with your quiz questions
        switch (questionNumber) {
            case 1:
                return "What is the capital of France?";
            case 2:
                return "Which planet is known as the Red Planet?";
            case 3:
                return "How many continents are there on Earth?";
            default:
                return "Question not found.";
        }
    }

    private static String getAnswer(int questionNumber) {
        // Replace with the correct answers
        switch (questionNumber) {
            case 1:
                return "Paris";
            case 2:
                return "Mars";
            case 3:
                return "7";
            default:
                return "Answer not found.";
        }
    }
}
