package binary;



public interface treenode
{
	public void setval(Object v); //set value of the node

	public void  setLC(binarynode l); // set the link
	public void setRC(binarynode g); // set the greaterlink
	public binarynode getRC();//get the greater link
	public binarynode getLC();//get the lesser link
	public Object getval(); // get the value of the node
}
