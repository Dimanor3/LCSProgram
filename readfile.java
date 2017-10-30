/*
	Bijan Razavi
	Elizabeth Thomas
	Gyovanni Boston-Crompton
*/

import java.io.*;
import java.util.*;

/*
	This class is used to get access to the
	DNA strands provided by a user provided
	file.

	Once the DNA strands are read it is, within
	this class, turned into a usable String arraylist
	by main.

	Afterwards it returns the arraylist to main.
*/
public class readfile {
	// This scanner will be used to open, read and close the choosen file.
	private Scanner read;

	// This arraylist holds the DNA strands.
	private ArrayList<String> dnaLCS = new ArrayList<String> ();

    /*
	    This function opens the file.
	*/
	public void openFile (String fileName) {
		try {
			read = new Scanner (new File (fileName));
		} catch (Exception e) {
			System.out.println ("Could not find file\n");
		}
	}

	/*
	    This function reads the file and turns the DNA Strands list into a
	    String arraylist for main, afterwards, it returns the arraylist.
	*/
	public ArrayList<String> readFile () {
		while (read.hasNext ()) {
			dnaLCS.add (read.next ().replaceAll ("[;]", ""));
		}

		return dnaLCS;
	}

	// This function closes the file.
	public void closeFile () {
		read.close ();
	}
}
