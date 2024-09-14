import java.util.Scanner;
import java.util.Random;

public class Main {

    private static int highScore = 0;
    private static int allScore=0;
    public static String playerName ;
    public static boolean startGame(Scanner scanner )
    {
        Random random = new Random();
        if(highScore==0) {
            System.out.print("Enter your name: ");
            playerName = scanner.next();
        }
        int score = 0;
        int successiveCorrect = 0, successiveWrong = 0;
        int difficultyLevel = 1;
        for (int questionCount=1;questionCount<=20;questionCount++){
            int correctAnswer=generateQuestion(random ,difficultyLevel);
            int userAnswer;
            System.out.println("your answer : ");
            userAnswer = scanner.nextInt();

            if(userAnswer==correctAnswer){
                System.out.println("Correct!");
                score++;
                successiveCorrect++;
                successiveWrong = 0;

                if(successiveCorrect==3){
                    if(difficultyLevel==3){
                        if(highScore<score)
                        {
                            highScore=score;
                            System.out.println("New high score! Congratulations!"+highScore);
                            allScore+=score;
                        }
                        System.out.println("you win!!\nyou reach the third level");
                        feedBack(allScore);
                        return false;

                    }

                    difficultyLevel++;
                    successiveCorrect=0;
                    System.out.println("Difficulty increased!");

                }
            }
            else{
                System.out.println("Wrong answer !");
                successiveWrong++;
                successiveCorrect = 0;
                if(highScore<score)
                {
                    highScore=score;
                    allScore+=score;
                    score=0;
                }
                if(successiveWrong==3){
                    System.out.println("game over . you've made 3 successive wrong answer ");
                    feedBack(allScore);
                    return false;
                }
            }

        }
        feedBack(allScore);
        return true;

    }

    public static void feedBack(int score)
    {
        if(score<3)
            System.out.println("please ask your teacher for extra help");
        else if(score <5)
            System.out.println("you are doing well practice more");
        else if (score <10)
            System.out.println("you are excellent ");
        else
            System.out.println("you are expert");

        System.out.println("high score :" + highScore);
        System.out.println("all score you have made :"+allScore);
    }

    public static int generateQuestion(Random random , int difficulty)
    {
        int factor1 = random.nextInt(10 * difficulty) + 1;
        int factor2 = random.nextInt(10 * difficulty) + 1;
        System.out.println("What is " + factor1 + " * " + factor2 + "?");
        return factor1 * factor2;
    }


    public static void showHelp() {
        System.out.println("\n--- Help ---");
        System.out.println("This game helps you practice multiplication.");
        System.out.println("You will be asked up to 20 questions each time.");
        System.out.println("After 3 consecutive correct answers, the difficulty increases.");
        System.out.println("If you get 3 wrong answers in a row, the game ends.");
    }

    public static void showHighScore() {
        System.out.println("\n--- High Score ---");
        System.out.println("The current high score is: " + highScore + ".");
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("choose from the following: \n 1. start game.\n" +
                    "2. help.\n" +
                    "3. show high score.\n" +
                    "4. exit. \n " +
                    "choose an option (1-4): ");
            int number = scanner.nextInt();
            switch (number) {
                case 1:
                    flag=startGame(scanner);
                    break;

                case 2:
                    showHelp();
                    break;

                case 3:
                    showHighScore();
                    break;

                case 4:
                    flag = false;
                    System.out.println("You entered 5 and exit the game");
                    break;

                default:
                    System.out.println("Invalid number. try again");
            }

        }

        scanner.close();
    }
}