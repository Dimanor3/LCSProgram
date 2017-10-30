/*
	Bijan Razavi
	Elizabeth Thomas
	Gyovanni Boston-Crompton
*/

/*
	README file
	---------------------------

	Program Design Description
	--------------------------

	For this we had 3 classes, all which were imported from the insertion sort homework.
	With these 3 classes, we went ahead and modified them to both include findLCS as well
    as fix any issues that the previous assignment had and add some new functionality.

	Breakdown of Algorithm
	----------------------

	The longest common subsequence algorithm compares a table of characters, implemented as a
	two dimensional array, and searches for character matches in the horizontal and vertical 
	character intersection. It then traverses the table, moving either left or up depending on 
	the table entry. When the characters match, the traversal may move diagonally. Matching characters
	get added to the longest common subsequence (in our implementation this is an array of
	characters LCS).

	Our Compiler
	------------

	We wrote our program in java, so our compiler was javac.

	Our Platform
	------------

	The platform we used to write this program in was an Intel i5 processor
	running windows 10.

	What works and Fails
	--------------------

	Our program properly reads any number of files, places them into an ArrayList,
	checks the amount of time it takes for the program to run, runs a working findLCS 
    function on the ArrayList, and outputs all the required information.

    Some issues we ran into was an odd duplication issue. Whenever we used two files,
    one of which was empty and another of which had content, the program reads the
    first file into the ArrayList twice. This issue was solved by clearing the unorderedlist
    in between reads (within the readFile class.)
    
    An issue with this program is that it could be made even more space efficient. We did not
    attempt to do so, but future modifications could improve on this factor.

	Data Structure Design Description
	---------------------------------

	The data structure we choose to use was an arraylist, this is because arraylists are
	scalable unlike their array counterparts. This is very convenient for this project
	since the list of numbers we could be given can end up being any size.
	
	The data structure used to implement the LCS table for the algorithm was a two dimensional
	array of characters.
*/

import java.io.*;

import java.util.*;

public class LCS {
	public static void main (String[] args) {
		String lcsListString = "";

		// Takes the DNA Strands to have LCS ran on it.
		ArrayList<String> lcsList = new ArrayList<String> ();

        ArrayList<String> finalizedLCS = new ArrayList<String> ();

		// Gets access to the readfile class.
		readfile rF = new readfile ();

		// Gets access to the writefile class.
		writefile wF = new writefile ();

		// Gets access to the LCS class.
		LCS lcsAccess = new LCS ();

		// Attempts to open selected file(s).
		for (int i = 0; i < args.length; i++) {
            // Opens selected files.
			rF.openFile (args[i]);
            
			// Reads selected file into lcsList.
			lcsList.addAll (rF.readFile ());
            
            rF.clearFile ();

    		// Closes selected file.
	    	rF.closeFile ();
		}

		// Gets the starting time of findLCS.
		long start = System.nanoTime ();

		for (int i = 0; i < lcsList.size (); i += 2) {
            if (lcsList.size () != i + 1)
        		finalizedLCS.add (findLCS (lcsList.get (i + 1), lcsList.get (i)));
        }

		// Gets the ending time of findLCS.
		long end = System.nanoTime ();

		// Gets the total time that findLCS ran.
		long totalTime = end - start;

		// Creates answer.txt to be written to.
		wF.createFile ();

        // Designs the layout for the output file.
        for (int i = 0; i < lcsList.size (); i += 2) {
            if (lcsList.size () != i + 1) {
                lcsListString += "-----------------------------------------------------" + System.lineSeparator ();
                lcsListString += "The DNA strands:" + System.lineSeparator ();
                lcsListString += "\t" + lcsList.get (i) + System.lineSeparator ();
                lcsListString += "\t" + lcsList.get (i + 1) + System.lineSeparator ();
                lcsListString += "LCS is " + finalizedLCS.get (i / 2) + System.lineSeparator ();
                lcsListString += "LCS length is " + (finalizedLCS.get (i / 2).length () - 1) + "." + System.lineSeparator ();
                lcsListString += "-----------------------------------------------------" + System.lineSeparator ();
            }
        }

        if (lcsList.size () == 0) {
            lcsListString += "The input file is empty, please try again!" + System.lineSeparator ();
        }

        lcsListString += System.lineSeparator ();
        lcsListString += "Running time: " + totalTime + " nano seconds" + System.lineSeparator ();
        lcsListString += "-----------------------------------------------------" + System.lineSeparator ();

		wF.addOrderedList (lcsListString);
	}

	/**
	* This method will find the strings that are most common from the array of strings.
	* @param String str1: This will hold the first string.
	* @param String str2: This will hold the second string.
	* @return String (lcs): This will return the short string of the lcs.
	*/
	public static String findLCS(String str1, String str2) {
		// Gets the length size of each string.
		int str1Length = str1.length ();
		int str2Length = str2.length ();

		// Builds a table out of the string sizes.
		int[][] table = new int[str1Length + 1][str2Length + 1];

		/*
			Bottom up, compare characters in each
			element of the table and fill the table
			with integers accordingly.
		*/
		for (int row = 0; row <= str1Length; row++) {
			for (int column = 0; column <= str2Length; column++) {
				if (row == 0 || column == 0)
					table[row][column] = 0;
				else if (str1.charAt (row-1) == str2.charAt (column-1))
					table[row][column] = table[row-1][column-1] + 1;
				else
					table[row][column] = Math.max (table[row-1][column], table[row][column-1]);
			}
		}

		// Current index, begin at far right corner of the table.
		int find = table[str1Length][str2Length];

		// Array of the characters in the LCS.
		char lcs[] = new char[find + 1];

		/*
			Move through the table and store the
			characters in the LCS output array
			from the table.
		*/
		int i = str1Length, j = str2Length;

		/*
			Traverses through each possible location check,
			and determines whether the characters at indices
			i - 1 and j - 1 are the same, if they are it
			increments the according LCS position, if not it
			traverses left or up in the table depending on
			which indice holds the greater value.
		*/
		while (i > 0 && j > 0) {
			if (str1.charAt (i-1) == str2.charAt (j-1)) {
				lcs[find-1] = str1.charAt (i-1);

				i--;
				j--;
				find--;
			} else if (table[i-1][j] > table[i][j-1]) {
				i--;
			} else {
				j--;
			}
		}

		/*
			Return the array of chars as the LCS
			in String format.
		*/
		return new String (lcs);
	}
}
