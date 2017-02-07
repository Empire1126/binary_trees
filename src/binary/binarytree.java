package binary;

public class binarytree
{
	int counter = 0; //the counter for printing the binary tree
	double uniquewordcount = 0; //this will keep track of unique words in the list
	double uniquewords = 0; // total words of the list
	double referncechangecount=0; // we will keep incrementing this every time we change a reference or a pointer to keep track of efficiency
	double comparecount=0; // we will keep incrementing this everything we perform a test for equivalence on strings
	binarynode previous; // trailing node pointer to add nodes to the list
	binarynode selector; // current node pointer to traverse the list
	binarynode root; // the top of the list and the first reference point of the list
public binarytree()
{
	root = null; //default constructor, with no node added
}
public binarytree(binarynode f)
{
root = f;	//if we pass the constructor a node then add it to the list
}
public void add(String input)
{
	/*here we will add a string called input to the binary tree, we will search the entire tree checking if the string is greater than or less
	than every node we come across and advancing via the left child or right child based on that comparison. Then if we find the 
	same string as the one we are adding we will just increment the count of the word in the tree and return to the caller. 
	If our selector binarynode ends up null then we have fallen off the tree as there are no more leaves to look at and the string is not 
	present in the tree. If that is the case then we must add the string input as a new binary node to the tree by linking it to the
	last leaf we were at in the tree.
	*/
	if(input == "{}"){return;} // if the string was formated to be empty then simply return we don't want to add an empty string to the tree
	selector = root; // start at the top of the list
	previous = selector; // start previous at the same place as the selector node
	comparecount++;
	referncechangecount++;
	referncechangecount++;
	if(root == null) // if the list is empty
	{
		root = new binarynode(input,null,null); // make a new node and set it equal to the root
		uniquewordcount++;
		referncechangecount++;
		System.out.println((String)root.getval() +" was added with a count of "+ root.getcount());
		return; // return to the caller
		
	}
	comparecount++;
	while(selector != null) // here we will keep searching the nonempty list until we fall off the list or we find the word we are looking for
	{
		comparecount++;
		if(input.compareTo((String) selector.getval()) == 0) // if we find the word we are looking for
		{
			selector.countincrement(); // then increment the count
			System.out.println((String)selector.getval() +" was added with a count of "+ selector.getcount());
			return; // return to the caller
		}
		
		else if(input.compareTo((String) selector.getval()) < 0) // if the string to input to the tree is less than the 
			//word in the current node
			
		{
			previous = selector;//then we must go down the list by setting previous to the selector and having selector traverse to the 
			//left of the current node
			selector = selector.getLC();
			referncechangecount++;
			referncechangecount++;
		}
		else if(input.compareTo((String) selector.getval()) > 0) // if the string to input is greater then we must traverse to the 
			//right of the current node
		{
			previous = selector; // set previous to the current node
			selector = selector.getRC(); // set the current node to the right child of the current node
			referncechangecount++;
			referncechangecount++;
		}
		comparecount++;
		comparecount++;
	}
	//if we traverse the whole list and we do not find the string we are looking for
	binarynode leaf = new binarynode(input, null,null); // we must create a new node with the string in its value slot
	comparecount++;
	referncechangecount++;
	if(input.compareTo((String) previous.getval()) < 0) // then we must test to see if the new node should be linked in the 
		//right child or left child, if the string is less than the last node set the link of the previous node's left child to the new node
	{
		previous.setLC(leaf);
		referncechangecount++;
		System.out.println((String)previous.getLC().getval() +" was added with a count of "+ previous.getLC().getcount());
		
	}
	else // if the new node is not less than the previous node it must be greater so set it equal to the right child
		
	{
		previous.setRC(leaf);
		referncechangecount++;
		System.out.println((String)previous.getRC().getval() +" was added with a count of "+ previous.getRC().getcount());
	}
	
	uniquewordcount++;
	
}
public void delete(String input) // here we will delete a node from the tree, reducing its count if the count is > 1 and removing the mode all
//together when the count is reduced below 1
{
	selector = root; // reset the selector node
	previous= selector; // reset the previous node
	
	while(selector != null &&input.compareTo(((String)selector.getval()))!=0) // while we haven't found the node to remove from yet and haven't
		//fallen off of the tree
	{
		
		if(input.compareTo((String) selector.getval()) < 0) // keep testing for if the string to remove is less than or greater than the current
			//node we are at and traversing the list based on that comparison 
		{
			previous = selector;
			selector = selector.getLC();
		}
		else
		{
			previous = selector;
			selector = selector.getRC();
					
		}
		
	}
	//if we found the 
	if(selector == null) // if we reach the end of the list let he user know the node doesn't exist
	{
		System.out.println(" the string you whished to delete is not in the tree ");
		return;
	}	
	if(selector.getcount() == 1) // if the correct node has 1 count left we will find the max node to the left of the current node that equaled 
		// the string to delete and then we will switch the max value we find with the current node and remove the reference to the max value 
		//in the left side of the tree.
	{
		binarynode temp = selector;
		previous = selector;
		String maxleft =  (String)selector.getval();
		while (selector.getRC() != null)
		{
			previous = selector;
			selector = selector.getRC();
		}
		maxleft = (String) selector.getval();
		temp.setval(maxleft);
		previous.setRC(null);
		System.out.println(" the word "+input+" was deleted from the tree ");
		return;
	}
	else // if the found word doesn't have a count of 1  simply decrement the count
	{
		selector.setcount(selector.getcount()-1);
		System.out.println(" the word " + input+ " has had its count decreased to "+ selector.getcount());
		return;
	}
	
}
public void search(String input) //this method will search the binary tree for the string given and print out the node contaning the same string
//if it is found
{
selector= root;
previous = selector;
while(selector != null &&input.compareTo(((String)selector.getval()))!=0) // while we haven't found the word or fallen off the list

	{
	 
	   if(input.compareTo((String)selector.getval()) < 0)  //while searching the list compare the input to the traversed nodes and modify the
		   //traversal path based on those results.
	   {
		   previous = selector;
		   selector = selector.getLC();
		   
	   }
	   else 
	   {
		   previous = selector;
		   selector = selector.getRC();
	   }
	  
	   
	}
	if(selector == null) // if if we fell off the tree then the word isnt in the list
	{
	System.out.println(" the string you whished to find is not in the tree ");
	return;
	}
	//otherwise print our the found string and its count
	System.out.println(" the word you wanted was " + (String)selector.getval()+ " with a count of " + selector.getcount());
	return;
	
}
public void print(binarynode binode)
/*here we will recursively print the contents of the tree by recursively calling print with the next left node of the tree, 
when we hit the end of the tree we will return to the last node that called the null node and print its contents 
then we will call print for the same nodes right child if it exists and while we keep doing this we
will be keeping track of the non null nodes we visited to figure out what the total number of words in the list were, we will also stop at 
100 printed nodes*/ 

{
	if(counter== 100){return;}
	if(binode == null ||counter == 100){return;} // as long as we are not at the end of the list and we haven't printed 100 nodes
	else
	{
	if(counter == 100){return;}	
	print(binode.getLC());
	System.out.println(binode.getval()+ "  with a count of    " + binode.getcount()); // print the current nodes contents
	
	counter++; // increment the counter
	print(binode.getRC()); // call print on the the next right child
	} // call print to go the the next left child
	
	
	
	
}
public void totalwordsfind(binarynode binode)
{
	//here we will increment recursively through the entire tree to find all of the unique words of said tree and we will increment unique count
	//every time we find a new node that is not null
	
	if(binode == null){return;} // if we are not at the end of the list
	else{uniquewords++;totalwordsfind(binode.getLC());} // increment the unique word count and go to the next left child of the current node
	
	totalwordsfind(binode.getRC()); // go to the next right child of the current node
	
}
}
