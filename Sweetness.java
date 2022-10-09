import java.util.ArrayList;
import java.util.Scanner;

public class Sweetness {
	
	
	private static int[] getIndices(int sizeOfInput, int sweetness, int[]input) {

		boolean[][]dp = new boolean[sizeOfInput+1][sweetness+1];
		
		for(int i = 0; i<=sizeOfInput; i++) {
			dp[i][0]= true;
		}
		
		for(int i = 1; i<=sizeOfInput; i++) {
			for(int j = 1; j<=sweetness; j++) {
				
				if(input[i-1]<=j) {
					dp[i][j] = dp[i-1][j-input[i-1]] || dp[i-1][j];
				}
				else {
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		
		
		int row = sizeOfInput;
		int col = sweetness;
		
		if(!dp[row][col]) {
			System.out.println("No such subset exists");
			return null;
		}
		
		else{
			System.out.println("There exists a subset whose indices from the input are below.");
		
			ArrayList<Integer> list = new ArrayList<>();
		
			while(row > 0 && col > 0) {
				if(dp[row-1][col]) {
					row--;
					continue;
				}
				else {
					list.add(row-1);
					col = col - input[row-1];
				}
			}
			int n = list.size();
			int[] result = new int[n];
			for(int i = 0; i<n; i++) {
				result[i] = list.get(i);
			}
			return result;
		}
	}
	
	
	
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		
		System.out.println("Enter size of Input");
		//Taking size of input
		int sizeOfInput =  scn.nextInt();
		
		// create array of input size
		int[]input = new int[sizeOfInput];
		
		//Input integer at every index of input array, all are positive as no pie with negative sweetness exists
		for(int i = 0; i<sizeOfInput; i++) {
			System.out.print("Enter integers in array");
			input[i] = scn.nextInt();
		}
		
		//Enter target sum of whose you want to obtain subsets
		System.out.println("Enter target sum");
		int sweetness = scn.nextInt();
		scn.close();
		
		// No subset with negative sum subset exists.
		if(sweetness<0) {
			System.out.println("No such subset exists.");
		}
		
		//There is no input and targetSum is greater than 0
		if(sizeOfInput == 0 && sweetness > 0) {
			System.out.println("No subset can form the targetSum");
		}
		int sum = 0;
		for(int i = 0; i<input.length; i++) {
			sum+=input[i];
		}
		if(sum>=sweetness) {
			int[]result = getIndices(sizeOfInput,sweetness, input);
			if(result!=null) {
				for(int i = 0; i<result.length; i++) {
					System.out.println(result[i]);
					}
			}
		}
		else {
			System.out.println("Not possible for this input");
		}
		
	}
	
}
