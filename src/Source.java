import java.util.ArrayList;
import java.util.Scanner;

public class Source {
	
	public static void main(String args[])
	{
	   Algorithms al = new Algorithms();
	   TextImport ti = new TextImport();
		
	   String Alphabet = "abcdefghijklmnopqrstuvwxyz0123456789";
	   
	   System.out.println("Type a word to search: ");
	   Scanner s = new Scanner(System.in);
	   String P = s.nextLine().toLowerCase();
	    
	   char [] T;
	   ArrayList<Integer> output = new ArrayList<Integer>();
	   String text = "C:\\Users\\Sercan\\Desktop\\Hamlet.txt"; // Change this directory to search in a different text file
	   T = ti.constructList(text);
	    
	   long startTime1 = System.nanoTime();
	   al.Naive_String_Matching(T, P);
	   long endTime1 = System.nanoTime() - startTime1;
	   
	   long startTime2 = System.nanoTime();
	   output = al.Finite_Automata_Matcher(T, P, Alphabet);
	   long endTime2 = System.nanoTime() - startTime2;
	  
	   
	   al.printOutput(T, output, text);
	   System.out.println("\nNaive String Matching: " + endTime1/1000000.0 + "ms");
	   System.out.println("Finite Automata Matcher: " + endTime2/1000000.0 + "ms");
	   

	}
}
	







