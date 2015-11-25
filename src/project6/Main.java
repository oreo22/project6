package project6;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
/*		System.out.println("For Console Interface, please type 'Console'");
		System.out.println("For Graphical Interface, please type 'Graphical'");
		System.out.print("Enter Interface Type: ");*/
		System.out.println("Please enter your guess: ");
		Scanner s = new Scanner(System.in);
		do{
            MasterMindConsole.answerGenerator();
			String input = s.nextLine();
            MasterMindTextInterface.creatingUserInput(input);
            /*			if(input.equals("Console") || input.equals("console")){
				MasterMindTextInterface.launch();
				break;
			}
			else if(input.equals("Graphical") || input.equals("graphical")){
				System.out.println("Graphics");
				break;
			}
			else{
				System.out.println("Please type in a proper input.\n");
				System.out.print("Enter Interface Type: ");
			}*/
		}while(true);

	}

}
