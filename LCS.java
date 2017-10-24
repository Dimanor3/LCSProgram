// Bijan Razavi.

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
	  Qsort qS = new Qsort ();

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

	// This function completes the quick sort algorithm
	// I could've written this into the main class, but I felt
	// that it'd be too much clutter so I decided to make a
	// function for it.

    ArrayList<Float> quickSort (ArrayList<Float> unorderedList, int pivot, int lessThan, int greaterThan) {
	    if (lessThan > greaterThan) {
            unorderedList = partition(unorderedList, pivot, lessThan, greaterThan);

            lessThan = Math.round (unorderedList.remove (unorderedList.size () - 1));
            greaterThan = Math.round (unorderedList.remove (unorderedList.size () - 1));

            if (lessThan > 0) {
                quickSort(unorderedList, lessThan, -1, 0);
            }

            quickSort(unorderedList, unorderedList.size() - 1, pivot, pivot + 1);
        }

        return unorderedList;
    }

	// Pivot is the pivot index.
    // lessThan is the list of numbers that are less than the pivot.
    // greaterThan are the list of numbers greater than the pivot.
	public ArrayList<Float> partition (ArrayList<Float> unorderedList, int pivot, int lessThan, int greaterThan) {
       System.out.println ("Starting Lessthan: " + lessThan + " Greaththan: " + greaterThan + " Pivot: " + pivot);

	   if (pivot == lessThan + 1 || greaterThan >= pivot || pivot < 0) {
           unorderedList.add ((float) greaterThan);
           unorderedList.add ((float) lessThan);

           return unorderedList;
       }

	   float temp = 0;

	   while (greaterThan < pivot) {
           System.out.println ("In loop Lessthan: " + lessThan + " Greaththan: " + greaterThan + " Pivot: " + pivot);

		   if (unorderedList.get (pivot) >= unorderedList.get (greaterThan)) {
			   lessThan++;

			   temp = unorderedList.get (lessThan);
			   unorderedList.set (lessThan, unorderedList.get (greaterThan));
			   unorderedList.set (greaterThan, temp);
		   }

		   greaterThan++;
	   }

       System.out.println ("Lessthan: " + lessThan + " Greaththan: " + greaterThan + " Pivot: " + pivot);
       temp = unorderedList.get (lessThan + 1);
	   unorderedList.set (lessThan + 1, unorderedList.get (pivot));
	   unorderedList.set (pivot, temp);

	   unorderedList.add ((float) greaterThan);
       unorderedList.add ((float) lessThan);

	   return unorderedList;
	}
}