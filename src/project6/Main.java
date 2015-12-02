package project6;
import com.sun.org.apache.xpath.internal.SourceTree;

import project6.Pegs.Peg;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		boolean playAgain=true;
		boolean gameplayed=false;
		MasterMindConsole.answerGenerator();
		Scanner s = new Scanner(System.in);
		
			System.out.println("Welcome to Mastermind!");
			System.out.println("For Console Interface, please type 'Console'");
			System.out.println("For Graphical Interface, please type 'Graphical'");
			System.out.print("Enter Interface Type: ");
			String consoleType=s.nextLine();
			if(consoleType.equals("Console") || consoleType.equals("console")){
				do{
					MasterMindTextInterface.launch();
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
				System.out.println("Incorrect input. Please try again.");
			}

	}

}
