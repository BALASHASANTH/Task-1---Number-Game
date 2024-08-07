import java.util.Random;
import java.util.Scanner;

class NumberGuessingGame {

    private static final int MAX_ATTEMPTS = 10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int score = 0;
        boolean playAgain = true;

        while (playAgain) {
            boolean roundWon = playGame(scanner);
            if (roundWon) {
                score++;
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String response = scanner.next().trim().toLowerCase();
            if (!response.equals("yes")) {
                playAgain = false;
            }
        }

        System.out.println("Your final score is: " + score);
        scanner.close();
    }

    private static boolean playGame(Scanner scanner) {
        Random random = new Random();
        int secretNumber = random.nextInt(100) + 1;
        int attemptsLeft = MAX_ATTEMPTS;

        System.out.println("Guess the number between 1 and 100.");

        while (attemptsLeft > 0) {
            System.out.printf("You have %d attempts left. Enter your guess: ", attemptsLeft);
            int guess = getValidGuess(scanner);

            if (guess == secretNumber) {
                System.out.printf("Congratulations! You've guessed the number %d correctly.%n", secretNumber);
                return true;
            } else if (guess < secretNumber) {
                System.out.println("Too low.");
            } else {
                System.out.println("Too high.");
            }

            attemptsLeft--;
        }

        System.out.printf("Sorry, you're out of attempts. The correct number was %d.%n", secretNumber);
        return false;
    }

    private static int getValidGuess(Scanner scanner) {
        while (true) {
            try {
                int guess = scanner.nextInt();
                if (guess < 1 || guess > 100) {
                    System.out.println("Please enter a number between 1 and 100.");
                } else {
                    return guess;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            }
        }
    }
}
