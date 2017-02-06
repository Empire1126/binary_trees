package binary;
/*
 * Luke Pinkney, EECS 2500, Dr Thomas
 * binary tree program using previous hashing list program. 
 *12/5/16
 *as before the string called "thefileinput" is the file path for the program to read a txt file from
  */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class filereader
{
	//This program will use the hashing lists from the listing of txt program written earlier
	// it will also use a new list called the binarylist, this is a binary tree list composed of binary nodes
	// the nodes have two links that are supposed to link to something lexiconcically larger and smaller
	// This allowed for a binary search to be done on the list in order quickly find if something is one the list
	// The program will read whatever file path My professor puts into the string "thefileinput" and parse it once, this is to get the time to parse the list
	// as to subtract that time from the total time to build the lists (totalhasher, singlehasher, and binary)
	// the times and data including comparisons, reference changes, size of the lists will logged for analysis
	static String teststring = "\\}--{][()!@#$%^&*\',.\":;<>/?"; // string to hold all possible punctuation
	static singlehasher hashlistsmall = new singlehasher();
	static totalhasher hashlist = new totalhasher();// this is the hashing list that will be used to create a hash list for the text
	static double  firstpasssdeltatime; // parsing time found by the first pass method
	static binarytree binarytree = new binarytree();// binary tree to be used to sort data
	public static void main(String[] args)
	{ // Here we will get the text file and parse it to be
	// THE NEXT STRING IS USED FOR DETERMINING THE FILE PATH OF ALL THE SCANNERS YOU MUST PUT THE FILE PATH HERE
	String thefileinput = "C:/Users/Luke/Documents/hamlet.txt";
	firstpass(thefileinput);
	System.out.println("time for first pass was " +firstpasssdeltatime +" many seconds "); // display the time to parse the file
	System.out.println("Welcome Dr Thomas the file you enetered into the string thefileinput will be proccessed shortly ");
	runhasher(thefileinput);
	runbinary(thefileinput);
	
	
	
	
	}
	public static void runbinary(String thefileinput)
	{
		//here we will continually add words to the tree until no words exist in the file selected. 
		//We will then print the first 100 words of the tree
		// as well as display the time to complete and the reference changes made and the comparisons made
		// we will first use a try catch block for the file path and format the text so that we eliminate all trailing and leading punctuation
		// then we will send the word to the binary tree to add to the tree
		String Textinput= ""; // the string to be used to input to the builders
		Scanner fileinput = null; // initialize the scanner to read the file
		
		try
		{
			fileinput = new Scanner(new File(thefileinput)); //get the file via scanner to be read
		} 
		catch (FileNotFoundException e) // catch the exception of invalid file type
		{
			
			System.out.println("file not found"); // catch and display the error of a invalid file path
		} 
		double startime = (double)(System.currentTimeMillis()/1000.0);//get the start time

		while(fileinput.hasNext()) // test to see if the file has more words
		{
			Textinput = fileinput.next(); // get the next word to be added
			Textinput = formatstring(Textinput); // formated said word
			
			binarytree.add(Textinput); // add the word to the tree
			
			
		}
		double endtime = (double)(System.currentTimeMillis()/1000.0);	// get the endtime in seconds
		double deltatime = (endtime-startime); // the time it took to build the unsorted list
		System.out.println(" the binarytree completed in building in "+ (deltatime-firstpasssdeltatime)+ " many seconds " 
				+  " the tree mande " + binarytree.comparecount + " many comparisons and " + binarytree.referncechangecount 
				+ " many reference changes" );  // display the stats for the reference changes and comparisons
		fileinput.close(); // close the scanner
		launchtree(); // print the tree
	}
	public static void launchtree()
	//this method will print the first  words of the list an their count and find the total words of the tree
	{
		System.out.println(" The next list is the binary tree and contains the following"+ "\n" + "---------------------------------------------");
		
		System.out.println("The next  words in the list are "); // prompt
		binarytree.selector = binarytree.root; // reset the selector binode
		binarytree.counter = 0; // reset counter 
		
		binarytree.print(binarytree.selector); // print the first  nodes of the list
		 System.out.println(" the count is " + binarytree.counter); // test code
		binarytree.selector = binarytree.root; // reset the selector
		binarytree.totalwordsfind(binarytree.selector); // find the total words of the tree
		
		System.out.println("the total words in the list are " + binarytree.uniquewords); // print the number of total words
		
	}
	public static void runhasher(String thefileinput) // this method will read the words from a 
	//file document and send them to be formated then it will add them to the hash list and run the method to format the input
	// before adding it to the list and run the print method to print the finished list + the metadata about the list.
	{
		double wordcount= 0; // the number of words we found
		String Textinput= ""; // the string to be used to input to the builders
		Scanner fileinput = null; // initialize the scanner to read the file
		
		try
		{
			fileinput = new Scanner(new File(thefileinput)); //get the file via scanner to be read
		} 
		catch (FileNotFoundException e) // catch the exception of invalid file type
		{
			
			System.out.println("file not found"); // catch and display the error of a invalid file path
		} 
			
		double startime = (double)(System.currentTimeMillis()/1000.0);//get the start time
		
			while(fileinput.hasNext()) // test to see if the file has more words
			{
				Textinput = fileinput.next(); // get the next word to be added
				Textinput = formatstring(Textinput); // formated said word
				if(Textinput.equals("{}")){wordcount--;} // if the character was blank we do not need to increment the word count
				hashlist.add(Textinput); // add the word to the list
				
				wordcount++; // increment the word count to keep track of the words sent to be added
			}
			
		double endtime = (double)(System.currentTimeMillis()/1000.0);	// get the end time in seconds
		double deltatime = (endtime-startime); // the time it took to build the unsorted list
		System.out.println(" the hashlist completed in building in "+ (deltatime-firstpasssdeltatime)+ " many seconds " 
		+ " the amount of words we found was "+ wordcount +" the number of unqique words was "+hashlist.uniquewordcount + " the list mande " 
				+ hashlist.comparecount + " many comparisons and " + hashlist.referncechangecount 
		+ " many reference changes" );  // display the stats for the reference changes and comparisons
		
		System.out.println("-------------------------------------------------------------------------------------"); 
		
		fileinput.close(); // close the scanner
		launchhasher(); // launch hasher for part two of the program
		
		// This is the dividing line that signifies that we are now doing the second list of part 2 of the program
		wordcount= 0; // the number of words we found
		Textinput= ""; // the string to be used to input to the builders
		fileinput = null; // initialize the scanner to read the file
		
		try
		{
			fileinput = new Scanner(new File(thefileinput)); //get the file via scanner to be read
		} 
		catch (FileNotFoundException e) // catch the exception of invalid file type
		{
			
			System.out.println("file not found"); // catch and display the error of a invalid file path
		} 
			
		startime = (double)(System.currentTimeMillis()/1000.0);//get the startime
		
			while(fileinput.hasNext()) // test to see if the file has more words
			{
				Textinput = fileinput.next(); // get the next word to be added
				Textinput = formatstring(Textinput); // formated said word
				if(Textinput.equals("{}")){wordcount--;} // if the character was blank we do not need to increment the word count
				
				hashlistsmall.add(Textinput); // add the word to the second list
				wordcount++; // increment the word count to keep track of the words sent to be added
			}
			
		endtime = (double)(System.currentTimeMillis()/1000.0);	// get the endtime in seconds
		deltatime = (endtime-startime); // the time it took to build the unsorted list
		System.out.println(" the small hashlist completed in building in "+ (deltatime-firstpasssdeltatime)+ " many seconds " 
		+ " the amount of words we found was "+ wordcount +" the number of unqique words was "+hashlistsmall.uniquewordcount + " the list mande " + hashlistsmall.comparecount + " many comparisons and " + hashlistsmall.referncechangecount 
		+ " many reference changes" );  // display the stats for the reference changes and comparisons 
		
		System.out.println("-------------------------------------------------------------------------------------"); 
		
		fileinput.close(); // close the scanner
		launchhasher2(); // launch hasher for part two of the program (small list)
		
	}
	public static void launchhasher() // This method will print out all of the hashed words of the hash list
	//it does this by incrementally going throw the list and printing the pay loads of the nodes in some index of the hash array
	// then it will go to the next index until done.
	{
		int counter = 0;// this will make sure we only print out the first 100 word of the list
		for ( int i = 0; i<=10&&counter != 100; i++) // increment through the hash array
		{
			hashlist.selector = hashlist.hashtable[i]; // set the selector to the next hash array index point
			System.out.println(" the hashcode of " + i + " contains the node values of "); // print out the hash code index
			// we are at
			while(hashlist.selector != null&&counter!=100) // while there is something left to print in the hash array index i
			{
				//print out the data of said hash array node list with the count of the word
				System.out.println( " "+hashlist.selector.getval() + "                               " + hashlist.selector.getcount());
				counter++; // this will increment the count and when we reach 100 the for loop will fail and we will only print out the first 100 of the list
				hashlist.selector = hashlist.selector.getlink();// go to the next node of the hash
			}
			if(counter == 100){return;} // stops the endless dividing lines i found
			System.out.println("-------------------------------------------------------------------------------------"); 
			// dividing line for the purpose of showing which when a hash index ends
		}
		
	}
	public static void launchhasher2() // This method will print out all of the hashed words of the small hash list
	//it does this by incrementally going throw the list and printing the pay loads of the nodes in some index of the hash array
	// then it will go to the next index until done. it will also determine if the hash is empty and if it is it will skip that list
	{
		int counter = 0; // this will make sure we only print out the first 100 word of the list
		for ( int i = 0; i<=255&&counter!=100; i++) // increment through the hash array
		{
			
			hashlistsmall.selector = hashlistsmall.hashtable[i]; // set the selector to the next hash array index point
			if(hashlistsmall.selector == null && counter!= 100){continue;} // if the list we are looking at doesn't contain any words we will skip
			// this interation
			System.out.println(" the hashcode of " + i + " contains the node values of "); // print out the hash code index
			// we are at
			while(hashlistsmall.selector != null&& counter!= 100) // while there is something left to print in the hash array index i
			{
				//print out the data of said hash array node list with the count of the word
				System.out.println( " "+hashlistsmall.selector.getval() + "                    " + hashlistsmall.selector.getcount());
				counter++;// this will increment the count and when we reach 100 the for loop will fail and we will only print out the first 100 of the list
				hashlistsmall.selector = hashlistsmall.selector.getlink(); // go to the next node of the hash
			}
			if(counter == 100){return;}// stops the endless dividing lines i found
			System.out.println("-------------------------------------------------------------------------------------"); 
			// dividing line for the purpose of showing which when a hash index ends
		}
		
	}
	public static void firstpass(String thefileinput) // this method will do a pass through the list to determine how long it takes to parse the list.
	{
		double firstpassstartime = (double)(System.currentTimeMillis()/1000.0); // get the start time 
		String Textinput = "";//placeholder string to parse the list with
		Scanner fileinput = null; // make the scanner
		try
		{
			fileinput = new Scanner(new File(thefileinput)); //create the file to be parsed
		} 
		catch (FileNotFoundException e) // catch the exception for a invalid file path
		{
			
			System.out.print("file not found"); // display the error to user
		} 
		while(fileinput.hasNext()) // see if the file has a next word
				{
				Textinput = fileinput.next();// set that next word to the placeholder string
				
				Textinput= Textinput.trim();//trim whitespace
				Textinput = Textinput.toLowerCase(); // set the next word to lower case
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
						// we then proccess the string as an array and just keep adding the words to the dummy string starting from the char that isn't the last char
						//as we just determined that that char is not what we want to keep
					{
						for(int p =0 ; p<Textinput.length()-1; p++) // process the string from the second to last char
						{
							adder += Textinput.charAt(p); // keep adding the chars that we want to the new dummy word
							
						}
						Textinput = adder; // set the input equal to the dummy word
						
					}
					
					
				}
				
				}
		double   firstpassendtime = (double)(System.currentTimeMillis()/1000.0); // get the end time
		firstpasssdeltatime =  (double)(firstpassendtime- firstpassstartime); // calculate the time to process the list
		System.out.println("the time to proccess the list was "+  firstpasssdeltatime); // display the time it took to parse the list
		fileinput.close();
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
