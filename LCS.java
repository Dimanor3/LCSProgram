/* 	
	Bijan Razavi
	Elizabeth Thomas
	Gyovanni Boston-Crompton
*/

/*
	README file by Bijan Razavi
	---------------------------

	Program Design Description
	--------------------------

	For this I had 3 classes, all which were imported from the insertion sort homework.
	With these 3 classes, I went ahead and modified them to both include quicksort
	as well as fix any issues that the previous assignment had and add some new functionality.

	Breakdown of Algorithm
	----------------------

	The quicksort algorithm requires you to pick a pivot and look for all numbers
	less than the pivot. These numbers are set to the left of the array, and the
	greater numbers are set to the right of the array. The pivot is placed in the
	center. Quicksort then goes ahead and does the same for the left side of the array
	and the right side of the array until the whole thing is sorted.

	My Compiler
	-----------

	I wrote my program in java, so my compiler was javac.

	My Platform
	-----------

	The platform I used to write this program in is an Intel i5 processor
	running windows 10.

	What works and Fails
	--------------------

	My program properly reads any number of files, places them into an ArrayList,
	checks the amount of time it takes for the program to run, and outputs all the
	required information.

	The part this program currently fails in is the quicksort itself. Unfortunately,
	I wasn't able to get this sorting algorithm to run properly as it currently sets
	every position as the pivot and does this multiple times. This was more than likely
	because I don't have a proper quit functionality in place.

	Data Structure Design Description
	---------------------------------

	The data structure I choose to use was an arraylist, this is because arraylists are
	scalable unlike their array counterparts. This is very convenient for this project
	since the list of numbers we could be given can end up being any size.
*/

import java.io.*;

import java.util.*;

public class LCS {
	public static void main (String[] args) {
		String orderedListString = "";

		// Takes the unorderedList to be ordered.
		ArrayList<Float> orderedList = new ArrayList<Float> ();

		ArrayList<Float> tempList = new ArrayList<Float> ();

		ArrayList<Integer> listSize = new ArrayList<Integer> ();

		int totalListSize = 0;

		// Gets access to the readfile class.
		readfile rF = new readfile ();

		// Gets access to the writefile class.
		writefile wF = new writefile ();

		// Gets access to the Qsort class.
		LCS qS = new LCS ();

		// Attempts to open selected file(s).
		for (int i = 0; i < args.length; i++) {
			rF.openFile(args[i]);

			tempList = rF.readFile ();

			listSize.add (tempList.size ());

			totalListSize += tempList.size ();

			// Reads selected file.
			orderedList.addAll(rF.readFile());

			tempList.clear ();
		}

		// Closes selected file.
		rF.closeFile ();

		// Gets the starting time of Quick Sort.
		double start = System.currentTimeMillis();

		// Quick Sort.
		orderedList = qS.quickSort (orderedList, orderedList.size () - 1, -1, 0);

		// Gets the ending time of Quick Sort.
		double end = System.currentTimeMillis();

		// Gets the total time that Quick Sort ran.
		double totalTime = end - start;

		// Creates answer.txt to be written to.
		wF.createFile ();

		// Fills orderedListString with all the information it needs to output.
		orderedListString = "Sorting result:" + System.lineSeparator();
		orderedListString += orderedList.toString ().replaceAll ("[\\[\\]]", "").replaceAll (",", ";") + System.lineSeparator () + System.lineSeparator();
		orderedListString += "Performance" + System.lineSeparator () + "analysis: Input file\t\tSorting Time (in milliseconds)" + System.lineSeparator();
		orderedListString += "Size " + args[0] + ":\t" + listSize.get (0) + "\t\t" + totalTime + System.lineSeparator ();

		// Appends the name and size of every input file other than first input file.
		for (int i = 1; i < args.length; i++) {
			orderedListString += "Size " + args[i] + ":\t" + listSize.get (i) + System.lineSeparator ();
		}

		orderedListString += "Total size of all lists: " + totalListSize;

		wF.addOrderedList (orderedListString);
	}

	public static char[] findLCS(String str1, String str2) {
		int str1Length = str1.length();
		int str2Length = str2.length();
		int[][] table = new int[str1Length + 1][str2Length + 1];

		//bottom up, compare characters in X and Y
		//fill the table with integers accordingly
		for (int row = 0; row <= str1Length; row++) {
			for (int column = 0; column <= str2Length; column++) {
				if (row == 0 || column == 0)
					table[row][column] = 0;
				else if (str1.charAt(row-1) == str2.charAt(column-1))
					table[row][column] = table[row-1][column-1] + 1;
				else
					table[row][column] = Math.max(table[row-1][column], table[row][column-1]);
			}
		}

		int find = table[str1Length][str2Length]; //current index, begin at far right corner of table

		char lcs[] = new char[find + 1]; //array of the characters in the lcs

		//move through the table, store chars in the lcs output array from the table
		int i = str1Length, j = str2Length;
		
		while (i > 0 && j > 0) {
			//if characters at indices are the same, corresponds to the diagonal movement in the table
			if (str1.charAt(i-1) == str2.charAt(j-1)) {
				lcs[find-1] = str1.charAt(i-1);

				i--;
				j--;
				find--;
			}

			//if not the same, traverse left or up accordingly
			else if (table[i-1][j] > table[i][j-1])
				i--;

			else
				j--;
		}

		return lcs; //return the array of chars as the lcs

		System.out.println("------------------------------------------------------------------");
		System.out.println("The LCS of DNA sequences: \n" + str1 + "\n" + str2 + "\n is: ");
		for (int k = 0; k < lcs.length - 1; k++) {
			System.out.print(lcs[k]);
		}
		
		System.out.println("\n The length is " + table[str1Length][str2Length]);
		System.out.println("------------------------------------------------------------------");
	}
}
