package listaccessing;

public class ShowEquals {
	
	public static void main(String[] args) {
		String s1 = new String("hello");
		String s2 = new String("hello");
		String s3 = new String("Bye");
		
		System.out.println("using == they are equal to each other?");
		System.out.println(s1 == s2); // are they the same object
		
		System.out.println("using .equals they are equal to each other?");
		System.out.println(s1.equals(s2));
		
		System.out.println("does hello.equal goodbye");
		System.out.println(s1.equals(s3));
	}

}
