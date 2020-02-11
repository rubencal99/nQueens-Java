// Author: Ruben Calderon
// CruzID: 1698961
// Class: 12B/M
// Date: April 22, 2019
// Description: Prints out number of solutions to n-queens. Option: [-v] prints out every solution as the program finds them. 
// File Name: Queens.java

import java.util.Scanner;

public class Queens {

	public static void main(String[] args) {
		Scanner scan = null;

		String mode = "";
		int i = 0;
		
		//if there's two arguments, check to see if first argument is 
		//"verbose"
		//if not, exit
		if(args.length == 2){
			try{
				mode = args[0];
				i = Integer.parseInt(args[1]);
				if(!(mode.equals("-v"))){
				System.err.println("Usage: Queens [-v] number");
				System.err.println("Option: -v  verbose output, print all solutions");
                                System.exit(1);
				}		
			}
			catch (Exception e){
				System.err.println("Usage: Queens [-v] number");
				System.err.println("Option: -v  verbose output, print all solutions");
				System.exit(1);
			}
		}

		//if one argument, try to parse int
		//If can't, system exit
		else if(args.length == 1){
			try{
				i = Integer.parseInt(args[0]);
				}
			catch(Exception e){
				System.err.println("Usage: Queens [-v] number");
				System.err.println("Option: -v  verbose output, print all solutions");
			System.exit(1);
			}
		}
		else{
			System.err.println("Usage: Queens [-v] number");
                                System.err.println("Option: -v  verbose output, print all solutions");

			System.exit(1);
		}
		int j = i;
		int[][] B = new int[i+1][j+1];
		
		
		if(i > 1) {
			System.out.println(i + "-Queens has " + findSolutions(B, 1, mode) + " solutions");
		}
		else {
			System.out.println(i + "-Queens has " + findSolutions(B, 1, mode) + " solution");
		}
	}
	

//placeQueen
//Places queen on board, "attacks" each diagonal/vertical tile beneath it.
//Precondition: No queen already on row, B[i][j] must be equal to 0.
//Post: B[i][j] = 1. Sets first element in that row equal to j to keep track of placement of queens on the rows. Diagonal/verticals are decremented by 1.	
static void placeQueen(int[][] B, int i, int j) {
		B[i][j] = 1;
		B[i][0] = j;
		int left = j;
		int right = j;
		for(int k = i+1; k <= B.length-1; k++) {
			left--;
			right++;
			
			
			if(left <= 0) {
				left = 0;
			}
			else {
				B[k][left] -= 1;
			}
			
		
			if(right > B.length-1) {
				right = B.length-1;
			}
			else {
				B[k][right] -= 1;
			}
			
			
			B[k][j] -= 1;
		}
	}
	
//Remove Queen
//Removes the queen from B[i][j], resets the board for the next recursion on that row.
//Precondition: B[i][j] = 1, vertical/diagonal lines at less than 0. B[i][0] = j.
//Post: B[i][j] = 0. Vertical/diagonals incremented by 1. B[i][0] = 0;
static void removeQueen(int[][] B, int i, int j) {
		B[i][j] = 0;
		B[i][0] = 0;
		int left = j;
		int right = j;
		for(int k = i+1; k <= B.length-1; k++) {
			left--;
			right++;
			
			
			if(left <= 0) {
				left = 0;
			}
			else {
				B[k][left] += 1;
			}
			
			
			if(right > B.length-1) {
				right = B.length-1;
			}
			else {
				B[k][right] += 1;
			}
			
			
			B[k][j] += 1;
		}
	}

//Print Board
//Prints column B[i][0], which has the location of each queen placed on the board.
//Precondition: None
//Post: A comma-seperated list of the column number in which each respective queen is placed on the board.	
static void printBoard(int[][] B) {
		System.out.print("(");
		for(int i = 1; i < B.length-1; i++) {		
			System.out.print(B[i][0] + ", ");
		}
		System.out.println(B[B.length-1][0] + ")");
	}

//Find Solutions
//Implements recursive algorithm to iterate through the entire two-dimensional array (or "board") and returns the number of solutions to n-queens.
//Precondition: B[i][j] = 0 for all i, j. Option: mode = "-v" for printed solutions.
//Post: Number of solutions returned, board printed.	
static int findSolutions(int[][] B, int i, String mode) {
		int numSolutions = 0;
		if(i > B.length-1) {
			if(mode.equals("-v")) {
				printBoard(B);
			}
			return 1;
		}
		else {
			for(int j = 1; j <= B.length-1; j++) {
				if(B[i][j] == 0) {
					placeQueen(B, i, j);
					numSolutions += findSolutions(B, i+1, mode);		
					removeQueen(B, i, j);
				}
			}
		}
		return numSolutions;
	}

}

