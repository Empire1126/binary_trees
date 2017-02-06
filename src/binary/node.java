package binary;



public class node implements LLnode
{
	node Link; //variable to link this node to others
	Object Val; //pay load of the node
	int count; // count to keep track of the occurrence of the word
	public node(){} // default constructor with null variables

	public node (Object v, node l) // main constructor for nodes with pay load and link to set
	{
		Val = v;
		Link = l;
		count = 1;
	}
	public void setval(Object v)// set the value of the node
	{
		Val = v;
	} 

	public void setlink(node l){Link = l;} // set the link of the node
	public node getlink(){if(Link == null){return null;}return Link;} // get the link
	public Object getval(){if(Val == null){return null;}return Val;} // get the value of the node
	public void countincrement(){count++;} // increment the number of times the word occurred
	public int getcount(){return count;}// get the count of words
	public void setcount(int newcount){count = newcount;} //this method is used to make list 4 work. It is used to switch the count
	//for two nodes in order so the many reference changes in a doubly linked lists don't happen.
}
