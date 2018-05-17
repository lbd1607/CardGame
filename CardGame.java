import java.util.Scanner;
import java.util.Random;
import java.io.*;
import java.util.ArrayList;

/**
 * Laura Davis CIS 260 903
 * This program uses the random class to generate a 
 * card game that requires user input to continue the game.
 * This program reads from a saved file called CardGameFile.txt
 * and creates an ArrayList from the lines in the file.
 * The array created includes the values for each suit of cards. The program
 * reads this file and creates an ArrayList. The random function reads
 * from the file in hex and then converts the value back into a string.
 * The for loop iterates five times to give the player five cards.
 * After each iteration, the card is deleted from the deck to ensure
 * that it cannot be drawn in the same hand. I moved the file read and ArrayList
 * creation to the outside of the while loop so the same cards cannot be
 * drawn in subsequent hands. I also added a method specifically for the main functions
 * of the program so I can avoid IllegalArgumentExceptions when the array runs out
 * of cards. The program also uses if statements to validate the
 * user's input and ensure that if the number of cards in the array drops below 5,
 * the program returns to the readFile method to recreate the array. 
 */

public class CardGame 
{
	public static void main(String[] args) throws IOException
	{
		readFile();
	}//end of main
	
	/**
	 * This method creates an ArrayList from a file and uses nested
	 * while, for, and if-else loops to deal a player a hand of cards
	 * while ensuring that no two same cards are given from the deck.
	 * When the ArrayList runs out of cards, the deck is "shuffled" and
	 * the ArrayList is recreated from the file to deal more cards until
	 * the user ends the game. This method is necessary so the program can
	 * return to the method in order to re-read the file and create a new
	 * ArrayList as many times as is necessary. 
	 * 
	 * @throws IOException
	 */
	public static void readFile() throws IOException
	{
		//declare variables
		String again = "y";
		
		Scanner keyboard = new Scanner(System.in);
		
		//Reads file into ArrayList. Creates cards array from .txt file
		File file = new File ("CardGameFile.txt");
		ArrayList<String> cards = new ArrayList<String>();
		Scanner inputFile = new Scanner(file);
		String line = inputFile.nextLine();
		
		while (again.equalsIgnoreCase("y"))
		{

				while (inputFile.hasNext()) 
				{
					cards.add(line);
					line = inputFile.nextLine();
				}

					for (int i = 0; i < 5; i++)
					{
			
						//Choose random line from list
						Random randStr = new Random();
						String randomString = cards.get(randStr.nextInt(cards.size()));
						System.out.println(randomString);
					
						//removes the card from circulation
						cards.remove(randomString);
					}
					
						if (cards.size() < 5)
						{
							System.out.println("\nWould you like another hand? Y/N"); 
							again = keyboard.nextLine();
							
							System.out.println("\nOops! Time to shuffle the cards.\n ");
							readFile();
						}
						else
						{
							System.out.println("\nWould you like another hand? Y/N");  
							again = keyboard.nextLine();	
						}
						
						//this has to be in its own if
						if (!again.equalsIgnoreCase("y"))
						{
							inputFile.close();
							System.exit(0);
						}
						
		}//end of main while loop
		
		inputFile.close();
	}//end of readFile()
	
}//end of class
