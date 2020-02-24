import java.util.InputMismatchException;
import java.util.Scanner;
import java.security.SecureRandom;

//Program to help an elementary school student learn multiplication
//use a secure random object to make two positive one-digit integers
//then prompt the user with a question
//then input an answer and repeat until they get it right
//use a seperate method to generate the question


public class CAI4 {
    public static void main(String[] args) {
        mathQuiz4 newQuiz = new mathQuiz4();
        newQuiz.quiz();
    }
}


class mathQuiz4 {

    Scanner in = new Scanner(System.in);
    public int correctAnswer, userResponse, number1, number2, correctPercent, difficulty;
    public int[][] previousNumbersArray = new int[2][10];

    mathQuiz4() { //Constructor (We are getting no parameters from main)
        this.correctPercent = 0;
        this.difficulty = 0;
        this.userResponse = 0;
        this.number1 = 0;
        this.number2 = 0;
        this.correctPercent = 0;

        for ( int i =0 ; i<2 ; i++) {
            for (int j = 0 ; j<10 ; j++) previousNumbersArray[i][j] = -1;
        }
    }

    protected void quiz () {
        int choice;
        System.out.println("Welcome to the multiplication practice application!");
        do {
            correctPercent = 0; //Reset the percentage for each new quiz
            System.out.println("New quiz started.");
            difficulty = chooseDifficulty(); //Prompt for difficulty
            askQuestions(); //Ask 10 questions
            displayScore(); //Shows results
            choice = newProblemSet(); //Prompts user if they want to go again
        } while (choice == 1);
    }

    protected void askQuestions() {
        for (int i=0 ; i <10 ; i++) { //Repeats for each question
            getNumbers(i);
            askQuestion(i+1);
            readResponse();
            if (isAnswerCorrect() == 1) {
                correctPercent += 10;
                displayCorrectReponse();
            }
            else displayIncorrectResponse();
        }
    }

    private int chooseDifficulty() {
        int difficulty = 0;
        do {
            try {
                System.out.println("Please choose what difficulty (1-4) you would like to practice at.");
                difficulty = in.nextInt();
                if (difficulty >= 1 && difficulty <= 4) return difficulty;
                System.out.println("Invalid entry");
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input, you need to enter a number. Try again.\n");
                in.next();
            }

        } while (true);
    }


    private int newProblemSet() {
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


    protected void getNumbers(int k) {
        //add redundancy to make sure we don't get two of the same question
        int duplicateChecker = 0;
        int max = difficultyMax();
        do {
            duplicateChecker = 0;
            //Generate numbers
            number1 = generateRandomNumbers(0, max);
            number2 = generateRandomNumbers(0, max);

            //check for duplicates
            if (k==0) ;
            else {
                for (int i = 0 ; i<k ; i++) { //Wont look at current question or further questions to prevent conflict with previous quizzes
                    if ( (previousNumbersArray[0][i] == number1 && previousNumbersArray[1][i] == number2) || (previousNumbersArray[0][i] == number2 && previousNumbersArray[1][i] == number1) ) {
                        duplicateChecker++;
                    }
                }
            }
        }while(duplicateChecker != 0);
        //Stores numbers in the array to reference later
        previousNumbersArray[0][k] = number1;
        previousNumbersArray[1][k] = number2;
        correctAnswer = number1 * number2;
    }

    private int difficultyMax() {
        int max = 9999;
        for (int i = 4 ; i > difficulty ; i-- ) {
            max /= 10;
        }
        return max;
    }


    private int generateRandomNumbers(int min, int max) {
        SecureRandom randomNum = new SecureRandom();
        return randomNum.nextInt(max-min+1) + min;
    }


    //askQuestion method that prints the problem to the screen
    private void askQuestion(int i) {
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
        System.out.println("");
    }


    //displayIncorrectResponse methods that prints out the response when a student enters an incorrect answer
    private void displayIncorrectResponse() {
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

    private void displayScore() {
        System.out.println("You got " + correctPercent + "% correct.");
        if (correctPercent >= 75) System.out.println("Congratulations, you are ready to go to the next level!");
        else System.out.println("Please ask your teacher for extra help.");
    }

}

