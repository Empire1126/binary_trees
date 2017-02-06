package binary;

public class test
{

public static void main(String[] args)
{
 String b = "lol";
 String v = "bob";
 node front = new node(v, null);
node selector = new node(b,null);
	System.out.println(selector.getval());
	selector = front;
	System.out.println(selector.getval());
	front.setval("nol");
	System.out.println(selector.getval());
}
}
