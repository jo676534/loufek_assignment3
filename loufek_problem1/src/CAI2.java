import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


public class CAI2 {

    public static void main(String[] args) {
        //runs by calling the quiz method
        mathQuiz2 x = new mathQuiz2();
        x.quiz();
    }

}


class mathQuiz2 {

    Scanner in = new Scanner(System.in);
    public int correctAnswer, userResponse, number1, number2, isCorrect;

    //quiz method that contains program logic
    protected void quiz() {
        number1 = generateRandomNumbers(0, 9);
        number2 = generateRandomNumbers(0, 9);
        correctAnswer = number1 * number2;
        while(true) {
            askQuestion();
            readResponse();
            isCorrect = isAnswerCorrect();
            if (isCorrect == 1) {
                displayCorrectReponse();
                break;
            }
            else displayIncorrectResponse();
        }

    }

    private int generateRandomNumbers(int min, int max) {
        //Random n = new Random();
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    //askQuestion method that prints the problem to the screen
    private void askQuestion() {
        System.out.println("How much is " + number1 + " times " + number2 + "?");
    }


    //readResponse method that reads the students answer
    void readResponse() {
        boolean validIn = false;
        int i = 0;

        while(!validIn) {
            try {
                if (i!=0) System.out.println("How much is " + number1 + " times " + number2 + "?");
                i++;
                userResponse = in.nextInt();
                validIn = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input, you need to enter a number. Try again.");
                in.next();
            }
        }


    }


    //isAnswerCorrect method that checks if the student' answer is correct
    private int isAnswerCorrect() {
        if (userResponse == correctAnswer) return 1;
        else return 0;
    }


    //displayCorrectResponse that prints out the response when a student enters a correct answer
    private void displayCorrectReponse() {
        int x = generateRandomNumbers(1, 4);
        switch (x) {
            case 1:
                System.out.println("Very good!");
                break;
            case 2:
                System.out.println("Excellent!");
                break;
            case 3:
                System.out.println("Nice Work!");
                break;
            case 4:
                System.out.println("Keep up the good work!");
                break;
        }
    }


    //displayIncorrectResponse methods that prints out the response when a student enters an incorrect answer
    private void displayIncorrectResponse() {
        int x = generateRandomNumbers(1, 4);
        switch (x) {
            case 1:
                System.out.println("No. Please try again.");
                break;
            case 2:
                System.out.println("Wrong. Try once more.");
                break;
            case 3:
                System.out.println("Don’t give up!");
                break;
            case 4:
                System.out.println("No. Keep trying.");
                break;
        }
    }

}

