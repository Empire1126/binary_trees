package binary;

import java.util.Scanner;

public class console_interface
{
	static String teststring = "\\}--{][()!@#$%^&*\',.\":;<>/? "; // string to hold all possible punctuation
	static singlehasher hashlistsmall = new singlehasher();
	static totalhasher hashlist = new totalhasher();// this is the hashing list that will be used to create a hash list for the text
	static binarytree binarytree = new binarytree();// binary tree to be used to sort data
	public static void main(String[] args) // here is the console interface for the binary tree
	{
		Scanner txtin= new Scanner(System.in); // we will use a scanner to detect user input
		System.out.println("welcome to my binary tree string console interface!");
		String in= ""; // dummy string to store input from user
		while(true) // keep the user console interface going until the user specifies otherwise
			//the predefined commands will determine what action the system will take to modify the tree.
		{
			System.out.println("\n please input a command or type \"help\" for options ");
			in = txtin.next();
			if (in.equalsIgnoreCase("help"))
			{
				System.out.println("The following commands are allowed for the tree " + "\n" +"insert- will insert a word to the list and print it with the count "
			+ "\n"+ "delete- which will delete one count of the word from the list,when all counts of a word are deleted then the word will be deleted from the list "
						+ "\n" + "search- which will search for and print the desired string as well as its count" + "\n" + "print- which will print the entire tree"
			+ "out as well as the nodes counts " + "\n" + "census-which will print the number of unique words of the list out"+"\n" + "reset- which will "
						+" reset the binary tree to a null value"+"\n"+ "please note that whenever you add a word or delete a word or even search for a word"
			+ "the program will format your String you input and trim any leading or trailing white space and then remove any punctuation from the string, also all word"
						+" are set to lower case" + "\n"+ "if you whish to end the program simply type end");
				
			}
			else if(in.equalsIgnoreCase("insert")){System.out.print(" please input a single string to be added to the tree \n ");in = txtin.next();insert(in);continue;}
			else if(in.equalsIgnoreCase("delete")){System.out.print(" please input a single string to be deleted to the tree \n ");in = txtin.next();delete(in);continue;}
			else if(in.equalsIgnoreCase("search")){System.out.print(" please input a single string to be searched for in the tree \n ");in = txtin.nextLine();search(in);continue;}
			else if(in.equalsIgnoreCase("print")){printall();continue;}
			else if(in.equalsIgnoreCase("census")){census();continue;}
			else if(in.equalsIgnoreCase("reset")){reset();continue;}
			else if(in.equalsIgnoreCase("end")){System.exit(0);}
			else{System.out.println(" unknown command please type a valid command- type help to display commands accepted ");continue;}
		}
			
			
			
			
		}

	
	//all of the below methods simply format the string input to the tree and apply the appropriate command drawn from the binary tree itself
	public static void insert(String input)
	{
		input = formatstring(input);
		if(input == "{}")
			{
			System.out.println("you just tried to delete a string from the tree that only contained special characters and the tree and ignored the command");
			return;
			}
		binarytree.add(input);
		return;
	}
	public static void delete(String input)
	{	
		input = formatstring(input);
		if(input == "{}")
			{
			System.out.println("you just tried to delete a string from the tree that only contained special characters and the tree and ignored the command");
			return;
			}
		binarytree.delete(input);
		return;
		
	}
	public static void search(String input)
	{
		input = formatstring(input);
		if(input == "{}")
			{
			System.out.println("you just tried to search for a string from the tree that only contained special characters "
					+ "and the tree and ignored the command");
			return;
			}
		binarytree.search(input);
	}
	
	public static void printall()
	{
		binarytree.counter = 0;
		binarytree.selector = binarytree.root;
		binarytree.previous = binarytree.selector;
		binarytree.print(binarytree.selector);
		
	}
	public static void census()
	{
		binarytree.selector = binarytree.root;
		binarytree.totalwordsfind(binarytree.selector);
		System.out.println(" the total words in the list are " + binarytree.uniquewords);
	}
	//this method will simply reset the binary tree to an empty tree.
	public static void reset()
	{
		
		binarytree = new binarytree();
		System.out.print(" the tree was reset \n ");
	}
	public static String formatstring(String Textinput) 
	// this method takes a string value and trims it of whitespace then it tests the beginning and end of each string to see if it contains
	// some of the characters that we do not want ( the global string "teststring" contains all of the characters we do not want)
	// if it detects any characters at the beginning or end of the word then it will trim off either the leading or trailing 
	// characters that the punctuation we didn't want was detected in depending on whether it was in the front of back.
	{
		Textinput = Textinput.trim(); // remove whitespace from the text
		
		while(Textinput.length() !=0 &&(teststring.contains(""+Textinput.charAt(0)) || 
				teststring.contains(""+Textinput.charAt((Textinput.length()-1))) ))
			// here we are going to trim the leading and trailing punctuation of a word
				// we will test to see if the testerstring of possible punctuation chars contains the char at zero of the word
				//if this is true we must now find out if it is at the beginning or the end
			{
			String adder = ""; // dummy string so that we can add chars to it and set the textinput equal to it later
			if(teststring.contains(""+Textinput.charAt(0))) // see if the char we found to be punctuation is at the begging of the list
			{
				for(int p = 1; p<Textinput.length(); p++) // if it is then start one space to the right and keep incrementing until you reach the end of the list
					
				{
					adder += Textinput.charAt(p); // get the current char and add it to the dummy variable
					
				}
				Textinput = adder; // here we set the new input equal to the dummy trimmed word
			}
			adder = ""; // reset the dummy string
			if(Textinput.length() !=0 &&teststring.contains(""+Textinput.charAt((Textinput.length()-1)))) //here we test if the offending char is at the end of the word.
				// here we do the opposite and start at the end of the list
				// we then process the string as an array and just keep adding the words to the dummy string starting from the char that isn't the last char
				//as we just determined that that char is not what we want to keep
			{
				for(int p =0 ; p<Textinput.length()-1; p++) // process the string from the second to last char
				{
					adder += Textinput.charAt(p); // keep adding the chars that we want to the new dummy word
					
				}
				Textinput = adder; // set the input equal to the dummy word
			}
			
			}
		if(Textinput.length() == 0) // check to see if we got rid of all of the words
		{
			Textinput ="{}"; //this will signal the listbuilders that the word we are sending is a blank word so ignore it
			
		}
		Textinput = Textinput.toLowerCase();// set the characters to lower case
		return Textinput; // return the formated text
		
		}
	
}
