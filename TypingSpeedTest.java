import java.util.Scanner;
import java.util.Random;

public class TypingSpeedTest {
    
    private static final String[] sentences = {
        "The quick brown fox jumps over the lazy dog.",
        "Practice makes perfect.",
        "Coding is fun and improves problem-solving skills.",
        "Java is a powerful programming language.",
        "Keep pushing your limits and achieve excellence."
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean retry = true;

        while (retry) {
            String sentence = sentences[random.nextInt(sentences.length)];
            System.out.println("\nType the following sentence as fast and accurately as possible:");
            System.out.println(">> " + sentence);
            System.out.print("\nStart typing: ");
            
            long startTime = System.currentTimeMillis(); 
            String userInput = scanner.nextLine();
            long endTime = System.currentTimeMillis(); 

            
            double timeTaken = (endTime - startTime) / 1000.0;
            int wordCount = sentence.split("\\s+").length;

            
            double wpm = (wordCount / timeTaken) * 60;

            
            int errors = calculateErrors(sentence, userInput);
            double accuracy = ((double) (sentence.length() - errors) / sentence.length()) * 100;

            
            System.out.printf("\nResults:\nTyping Speed: %.2f WPM\n", wpm);
            System.out.printf("Accuracy: %.2f%%\n", accuracy);
            System.out.println("Errors Made: " + errors);

            
            displayMotivationalMessage(wpm, accuracy);

            
            System.out.print("\nDo you want to retry? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();
            retry = response.equals("yes");
        }

        System.out.println("\nThank you for using the Typing Speed Test! Keep practicing!");
        scanner.close();
    }

    
    private static int calculateErrors(String original, String userInput) {
        int errors = 0;
        int minLength = Math.min(original.length(), userInput.length());

        for (int i = 0; i < minLength; i++) {
            if (original.charAt(i) != userInput.charAt(i)) {
                errors++;
            }
        }
       
        errors += Math.abs(original.length() - userInput.length());
        return errors;
    }

    
    private static void displayMotivationalMessage(double wpm, double accuracy) {
        if (accuracy >= 95 && wpm >= 60) {
            System.out.println("🏆 Amazing! You're a typing pro! Keep up the great work!");
        } else if (accuracy >= 90 && wpm >= 40) {
            System.out.println("🔥 Great job! You're getting better. Keep practicing!");
        } else if (accuracy >= 80) {
            System.out.println("👍 Good effort! Work on speed and accuracy for better results.");
        } else {
            System.out.println("💡 Keep practicing! Focus on accuracy first, and speed will follow.");
        }
    }
}
