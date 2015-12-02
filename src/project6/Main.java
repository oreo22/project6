package project6;
import com.sun.org.apache.xpath.internal.SourceTree;

import project6.Pegs.Peg;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		boolean playAgain=true;
		boolean correctInput = true;
		MasterMindConsole.answerGenerator();
		Scanner s = new Scanner(System.in);
			System.out.println("Welcome to Mastermind!");
			do{
			correctInput = true;
			System.out.println("For Console Interface, please type 'Console'");
			System.out.println("For Graphical Interface, please type 'Graphical'");
			System.out.print("Enter Interface Type: ");
			String consoleType=s.nextLine();
			if(consoleType.equals("Console") || consoleType.equals("console")){
				do{
                    System.out.println("To guess against the AI, please type '1'"); //Human Vs. AI
                    System.out.println("To make the AI guess, please type '2'"); //AI Vs. Human
                    System.out.println("Choose the Playing Mode");
                    String modeType=s.nextLine();
                    if(modeType.equals("1")){
                        MasterMindTextInterface.HumanvsComputerlaunch();
                    }
                    else if(modeType.equals("2")){
                        MasterMindTextInterface.aiVsHuman();
                    }
                    else{
                        System.out.println("Incorrect input. Please try again.\n");
                        correctInput = false;
                    }
					System.out.println("Play Again? (Y/N)");
					String play = s.next();
					if (play.equals("N")) {
						playAgain = false;
						System.out.println("Thanks for playing!");
					}
				}while(playAgain == true);
			}
			else if(consoleType.equals("Graphical") || consoleType.equals("graphical")){
				MastermindFX.launch(MastermindFX.class, args);
			}
			else{
				System.out.println("Incorrect input. Please try again.\n");
				correctInput = false;
			}
		}while(correctInput == false);

	}

}
