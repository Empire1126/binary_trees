package binary;


public class totalhasher
{
	double uniquewordcount = 0; //this will keep track of the number of unique words in the list.
	double referncechangecount=0; // we will keep incrementing this every time we change a reference or a pointer to keep track of data
	double comparecount=0; // we will keep incrementing this everything we perform a test for equivalence of strings
	node selector; // node for incrementing the list
	node[] hashtable; // table for storing data	
	public totalhasher() // make the table for the words to be hashed and stored in
	{
	hashtable = new node[256]; //we need 256 hashes
		
	}
	public void add(String input) // here we will add a node to the hash table and set the word to end the end of the list that is in the hash
	//table if there is one on there. If the hashcode we are looking at does not contain multiple hashnodes then we will simply make a
	//new node that is equal to the hashcode index.
	{
		selector = null;
		int hashcode;//hashcode to access the table
		hashcode = gethashcode(input); // get the hash number
		comparecount++;
		if(input.equals("{}")) // here we test for if the input was a blank character and it is discarded
		{
			
			return;
		}
		comparecount++;
		if( hashtable[hashcode] == null) // if the node for the hashnumber is empty then simply store the hashnode in there
		{
			hashtable[hashcode] = new node(input, null); //make the new node with the data and set it equal to the correct array index
			uniquewordcount++;
			referncechangecount++;
			return;
		}
		else // if the hashcode isn't empty then we need to find the end of the node link in that hashcode
		{
			selector = hashtable[hashcode]; // set the selector to the hashcode we want to look at
			comparecount++;
			while (selector.getlink() != null) // as long as the selector is not at the end of the hashnode link
			{
				comparecount++;
				if(input.equals(selector.getval())) // if the word is already in the list then simply increment the count of said word
					// then return out of the add method
				{
					selector.countincrement();
					return;
				}
				selector = selector.getlink(); // go to the next hashnode
				
			}
			// if the item we are looking for is not on the list then we must put selector back at the front of the list
			// then we must have a new node with the data we want to add that links to the "front" of the list aka selector
			// then we have selector link to that new node.
			selector = hashtable[hashcode];
			referncechangecount++;
			node node = new node(input, selector); // when we are finally at the end make a new node with the string in it and
			//link it to the old front of the list hash list
			uniquewordcount++;
			referncechangecount++;
			hashtable[hashcode] = node; // have the new node be the front of the hash list
			referncechangecount++;
			return;
		}
		
		
	}
	public int gethashcode(String tobehashed) // here we will get the hashcode for the table to store the word
	{
		int hashcode = 0; // make a new hashcode number to store the finished code
		for(int i=0; i<tobehashed.length()-1; i++) // go through the word sent to you
		{
			hashcode += (int)(tobehashed.charAt(i)); // cast the next letter as an int and add it to the hascode
			
		}
		hashcode = hashcode % 256; // get the remainder of the hascode out of 256 and set that to the new hashcode
		return hashcode; // send back the code
	}
}
