import java.util.ArrayList;

public class Algorithms {
	TextImport ti = new TextImport();

	public ArrayList<Integer> Naive_String_Matching(char[] T, String P) {
		int n = T.length;
		int m = P.length();

		int count = 0;
		ArrayList<Integer> output = new ArrayList<Integer>();
		for (int s = 0; s < n - m; s++) {
			for (int i = 0; i < m; i++) {
				if (P.charAt(i) == T[s + i]) {
					count++;
					if (count == m) {
						count = 0;
						output.add(s);
					}
				} else
					count = 0;
			}
		}
		return output;
	}

	public ArrayList<Integer> Finite_Automata_Matcher(char[] T, String P, String Alphabet) {
		ArrayList<Integer> output = new ArrayList<Integer>();
		int m = P.length();
		int n = T.length;
		int [][] tFunc;
		tFunc = Compute_Transition_Function(P, Alphabet);

		int q = 0;
		for (int i = 0; i < n; i++) {
			q = tFunc[q][T[i]];
			if (q == m) {
				output.add(i-m+1);
			}
		}
		return output;
	}

	public int [][] Compute_Transition_Function(String P, String Alphabet) {
		int m = P.length();
		int k;
		int [][] transitions = new int [m+1][255];
		for (int q = 0; q < m; q++) {
			for (int i = 0; i < Alphabet.length(); i++) {
				k = Math.min(m, q+1);
				while(P.substring(0, k).endsWith(P.substring(0, q) + Alphabet.charAt(i))){
					transitions [q][Alphabet.charAt(i)] = k;
					k = k-1;
				}
			}
		}
		return transitions;
	}

	public void printOutput(char[] T, ArrayList<Integer> output, String text) {
		int shiftCount = 0;
		int lineNumber = 0;
		int lineCount = ti.lineCount(text);
		int[] interval = new int[lineCount + 1];
		int[] occurrence = new int[lineCount + 1];

		// From the beginning of the line to the end of line, determines how many shifts are required for every line
		// Shift count stored in an array for each line
		for (int i = 0; i < T.length; i++) {
			if (T[i] != '\n') {
				shiftCount++;
			} else if (T[i] == '\n') {
				shiftCount++;
				interval[++lineNumber] = shiftCount;
			}
			if (i == T.length - 1)
				interval[++lineNumber] = shiftCount;
		}
		
		// Fills the occurrence array to determine line number and occurrence in
		// the corresponding line
		int j = 0;
		while (j < output.size()) {
			for (int i = 0; i < interval.length; i++) {
				if (output.get(j) >= interval[i] && output.get(j) < interval[i + 1]) {
					occurrence[i + 1]++;
				}
			}
			j++;
		}

		int count=0;
		// Prints the output
		for (int i = 0; i < occurrence.length; i++) {
			if (occurrence[i] != 0 && occurrence[i] == 1){
				System.out.println("Line " + i + ": " + occurrence[i] + " occurrence");
				count++;
			}
			else if (occurrence[i] != 0 && occurrence[i] > 1){
				System.out.println("Line " + i + ": " + occurrence[i] + " occurrences");
				count += occurrence[i];
			}
		}
		System.out.println("\nTotal occurrence is: " + count);
		
	}
}
