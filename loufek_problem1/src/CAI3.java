import java.util.InputMismatchException;
import java.util.Scanner;
import java.security.SecureRandom;


public class CAI3 {

    public static void main(String[] args) {
        //runs by calling the quiz method
        int choice;
        System.out.println("Welcome to the multiplication practice application!");
        do {
            mathQuiz3 x = new mathQuiz3();
            x.quiz();
            choice = x.newProblemSet();
        } while (choice == 1);
    }

}


class mathQuiz3 {

    Scanner in = new Scanner(System.in);
    public int correctAnswer, userResponse, number1, number2, correctPercent;
    public int[][] previousNumbersArray = new int[2][10];

    mathQuiz3() {
        this.correctPercent = 0;
        for ( int i =0 ; i<2 ; i++) {
            for (int j = 0 ; j<10 ; j++) previousNumbersArray[i][j] = -1;
        }
    }

    void quiz () {
        System.out.println("New quiz started.");
        for (int i=0 ; i <10 ; i++) {
            getNumbers(i);
            askQuestion(i+1);
            readResponse();
            if (isAnswerCorrect() == 1) {
                correctPercent += 10;
                displayCorrectReponse();
            }
            else displayIncorrectResponse();
        }
        displayScore();
    }


    int newProblemSet() {
        int choice;
        do {
            try {
                System.out.println("\nWould you like to solve a new problem set?\n1.Yes\n2.No");
                choice = in.nextInt();

                if (choice == 1 || choice == 2) {
                    System.out.println("");
                    return choice;
                }
                System.out.println("Invalid response");
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input, you need to enter a number. Try again.");
                in.next();

            }
        } while (true) ;

    }

    void getNumbers(int k) {
        int duplicateChecker = 0;
        do {
            duplicateChecker = 0;
            //Generate numbers
            number1 = generateRandomNumbers(0, 9);
            number2 = generateRandomNumbers(0, 9);

            //check for duplicates
            if (k==0) ;
            else {
                for (int i = 0 ; i<k ; i++) {
                    if ( (previousNumbersArray[0][i] == number1 && previousNumbersArray[1][i] == number2) || (previousNumbersArray[0][i] == number2 && previousNumbersArray[1][i] == number1) ) {
                        duplicateChecker++;
                    }
                }
            }
        }while(duplicateChecker != 0);
        previousNumbersArray[0][k] = number1;
        previousNumbersArray[1][k] = number2;
        correctAnswer = number1 * number2;
    }


    int generateRandomNumbers(int min, int max) {
        SecureRandom randomNum = new SecureRandom();
        return randomNum.nextInt(max-min+1) + min;
    }


    //askQuestion method that prints the problem to the screen
    void askQuestion(int i) {
        System.out.println("Question " + i + ":");
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
    int isAnswerCorrect() {
        if (userResponse == correctAnswer) return 1;
        else return 0;
    }


    //displayCorrectResponse that prints out the response when a student enters a correct answer
    void displayCorrectReponse() {
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
        System.out.println("");
    }


    //displayIncorrectResponse methods that prints out the response when a student enters an incorrect answer
    void displayIncorrectResponse() {
        int x = generateRandomNumbers(1, 4);
        switch (x) {
            case 1:
                System.out.println("No. Make sure to check your work!");
                break;
            case 2:
                System.out.println("Wrong. Better luck next time.");
                break;
            case 3:
                System.out.println("Don’t give up!");
                break;
            case 4:
                System.out.println("No, but you've got the next one!");
                break;
        }
        System.out.println("");
    }

    void displayScore() {
        System.out.println("You got " + correctPercent + "% correct.");
        if (correctPercent >= 75) System.out.println("Congratulations, you are ready to go to the next level!");
        else System.out.println("Please ask your teacher for extra help.");
    }

}

