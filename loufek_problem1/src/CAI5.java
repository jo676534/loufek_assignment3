import java.util.InputMismatchException;
import java.util.Scanner;
import java.security.SecureRandom;


public class CAI5 {
    public static void main(String[] args) {
        mathQuiz5 newQuiz = new mathQuiz5();
        newQuiz.quiz();
    }
}


class mathQuiz5 {

    Scanner in = new Scanner(System.in);
    public int correctAnswer, number1, number2, correctPercent, difficulty, quizType, currentOperator; //Current operator is used when randomly generating new quiz types
    public int[][] previousNumbersArray = new int[2][10];

    mathQuiz5() { //Constructor (We are getting no parameters from main)
        this.correctPercent = 0;
        this.difficulty = 0;
        this.quizType = 0;
        this.currentOperator = 0;
        this.number1 = 0;
        this.number2 = 0;
        this.correctPercent = 0;

        for ( int i =0 ; i<2 ; i++) {
            for (int j = 0 ; j<10 ; j++) previousNumbersArray[i][j] = -1;
        }
    }


    //PRIMARY METHOD FOR THIS APPLICATION
    protected void quiz () {
        int choice;
        System.out.println("Welcome to the mathematics practice application!");
        do {
            correctPercent = 0; //Reset the percentage for each new quiz
            System.out.println("New quiz started.");
            difficulty = readDifficulty(); //Prompt for difficulty
            quizType = readProblemType(); //Prompt for quiz type
            askTenQuestions(); //Ask 10 questions
            displayCompletionMessage(); //Shows results
            choice = newProblemSet(); //Prompts user if they want to go again
        } while (choice == 1);
    }


    private int readDifficulty() {
        int difficulty;
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


    private int readProblemType() {
        int theQuizType;
        do {
            try {
                System.out.println("\nFrom the following list, please choose which type of practice quiz you would like to take.");
                System.out.println("1. Addition\n2. Multiplication\n3. Subtraction\n4. Division\n5. Mixture of all types");
                theQuizType = in.nextInt();
                if (theQuizType >=1 && theQuizType <=5) return theQuizType;
                System.out.println("Invalid input");
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input, you need to enter a number. Try again.\n");
                in.next();
            }
        } while (true);
    }


    protected void askTenQuestions() {
        for (int i=0 ; i <10 ; i++) { //Repeats for each question
            getNumbers(i); //Automatically gets the correct operator
            askQuestion(i+1);
            if (isAnswerCorrect(readResponse()) == 1) {
                correctPercent += 10;
                displayCorrectResponse();
            }
            else displayIncorrectResponse();
        }

    }


    protected void getNumbers(int currentQuestionNumber) {
        //add redundancy to make sure we don't get two of the same question
        int duplicateChecker;
        do {
            duplicateChecker = 0;
            //Generate numbers
            number1 = generateQuestionArgument();
            number2 = generateQuestionArgument();

            //check for duplicates
            if (currentQuestionNumber != 0) {
                for (int i = 0 ; i<currentQuestionNumber ; i++) { //Wont look at current question or further questions to prevent conflict with previous quizzes
                    if ( (previousNumbersArray[0][i] == number1 && previousNumbersArray[1][i] == number2) || (previousNumbersArray[0][i] == number2 && previousNumbersArray[1][i] == number1) ) {
                        duplicateChecker++;
                    }
                }
            }

        }while(duplicateChecker != 0);
        //Stores numbers in the array to reference later for duplicates
        previousNumbersArray[0][currentQuestionNumber] = number1;
        previousNumbersArray[1][currentQuestionNumber] = number2;
        correctAnswer = calculateCorrectAnswer();
    }


    private int generateQuestionArgument() {
        int max;
        switch(difficulty) {
            case 1: max = 9;
                break;

            case 2: max = 99;
                break;

            case 3: max = 999;
                break;

            case 4:
            default: max = 9999;
        }
        return generateRandomNumber(0, max);
    }


    private int generateRandomNumber(int min, int max) {
        SecureRandom randomNum = new SecureRandom();
        return randomNum.nextInt(max-min+1) + min;
    }


    private int calculateCorrectAnswer() {
        if (quizType == 5){
            currentOperator = generateRandomNumber(1, 4);
        } else {
            currentOperator = quizType;
        }

        switch(currentOperator) {
            case 1:
                return number1 + number2;
            case 2:
                return number1 * number2;
            case 3:
                return number1 - number2;
            case 4:
                return number1 / number2;
            default:
                return 0;
        }

    }


    //askQuestion method that prints the problem to the screen
    private void askQuestion(int i) {
        switch(currentOperator) {
            case 1:
                System.out.println("Question " + i + ":");
                System.out.println("How much is " + number1 + " plus " + number2 + "?");
                break;
            case 2:
                System.out.println("Question " + i + ":");
                System.out.println("How much is " + number1 + " times " + number2 + "?");
                break;
            case 3:
                System.out.println("Question " + i + ":");
                System.out.println("How much is " + number1 + " subtracted by " + number2 + "?");
                break;
            case 4:
            default:
                System.out.println("Question " + i + ":");
                System.out.println("How much is " + number1 + " divided by " + number2 + "?\n(If your result is a fraction or decimal, please round down to the nearest whole number)");
        }
    }


    //readResponse method that reads the students answer
    int readResponse() {
        boolean validIn = false;
        int i = 0;
        int userResponse = 0;

        while(!validIn) {
            try {
                if (i!=0) askQuestion(currentOperator);
                i++;
                userResponse = in.nextInt();
                validIn = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input, you need to enter a number. Try again.");
                in.next();
            }
        }
        return userResponse;
    }


    //isAnswerCorrect method that checks if the student' answer is correct
    private int isAnswerCorrect(int userResponse) {
        if (userResponse == correctAnswer) return 1;
        else return 0;
    }


    //displayCorrectResponse that prints out the response when a student enters a correct answer
    private void displayCorrectResponse() {
        int x = generateRandomNumber(1, 4);
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
        System.out.println();
    }


    //displayIncorrectResponse methods that prints out the response when a student enters an incorrect answer
    private void displayIncorrectResponse() {
        int x = generateRandomNumber(1, 4);
        switch (x) {
            case 1:
                System.out.println("No. Make sure to check your work!");
                break;
            case 2:
                System.out.println("Wrong. Better luck next time.");
                break;
            case 3:
                System.out.println("Don’t give up! You'll get it next time!");
                break;
            case 4:
                System.out.println("No, but you've got the next one!");
                break;
        }
        System.out.println();
    }


    private void displayCompletionMessage() {
        System.out.println("You got " + correctPercent + "% correct.");
        if (correctPercent >= 75) System.out.println("Congratulations, you are ready to go to the next level!");
        else System.out.println("Please ask your teacher for extra help.");
    }


    private int newProblemSet() {
        int choice;
        do {
            try {
                System.out.println("\nWould you like to solve a new problem set?\n1.Yes\n2.No");
                choice = in.nextInt();

                if (choice == 1 || choice == 2) {
                    System.out.println();
                    return choice;
                }
                System.out.println("Invalid response");
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input, you need to enter a number. Try again.");
                in.next();
            }
        } while (true);
    }
}

