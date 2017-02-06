package binary;

public class binarynode implements treenode
{
	/* here is the binary tree node we will implement a similar approach to this as the linked list node but instead we will have two links to
	get and set from, one will be the greater node and one will be the lesser node, the lesser node will be anything that .compare determines the be
 	lexiconically lesser in string value than the current value of this node, similarly the greater link will be anything 
	lexiconically greater in string value to this node. 
	We will implement the ability to set and get both of these links and we will provides the same functionality as the previous linked list 
	node as we will have counts, values, and set counts ,set vals, and return counts and values*/
	binarynode leftchild; //variable to lesser string value of this node
	binarynode rightchild; // variable to link to greater string value
	Object Val; //payload of the node
	int count; // count of the node
	public binarynode()
	{
		
	} // default constructor with null variables

	public binarynode(Object v, binarynode l,binarynode g ) // main constructor for nodes with payload 
	//and two links to set as greater and a lesser
	{
		Val = v;
		leftchild = l;
		rightchild = g;
		count = 1;
	}
	public void setval(Object v)// set the value of the node
	{
		Val = v;
	} 

	public void setLC(binarynode l)  // set the lesser link of the node
	{
		leftchild = l;
	}
	public void setRC(binarynode g) //set the greater link of this node
	{
		rightchild = g;
	}
	public binarynode getLC() // get the lesser string connected to this binary node
	{
		if(leftchild == null){return null;}
		return leftchild;
	} 
	public binarynode getRC() //get the greater string linked to this node 
	{
		if(rightchild==null){return null;}
		return rightchild;
	} 
	public Object getval()// get the value of the node
	{
		if(Val == null)
		{return null;}
		return Val;
	} 
	public void countincrement() // increment the number of times the word occurred
	{
		count++;
	} 
	public int getcount() // get the count of words
	{
		return count;
	}
	public void setcount(int newcount)
	{
		count = newcount;
	} //this method is used to make list 4 work. It is used to switch the count
	//for two nodes in order so the many reference changes in a doubly linked lists don't happen.

}
