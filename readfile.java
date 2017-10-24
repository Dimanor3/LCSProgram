// Bijan Razavi.

import java.io.*;
import java.util.*;

/*
   This class is used to get access to the
   unordered list of numbers inside of a user
   provided file.
   
   Once the unordered list is read it is, within
   this class, turned into a usable int arraylist
   by isort.
    
   Afterwards it returns the arraylist to isort.
*/
public class readfile {
      // This scanner will be used to open, read and close the choosen file.
      private Scanner read;
      
      // This arraylist holds the unordered list.
      private ArrayList<Float> unorderedList = new ArrayList<Float>();
      
      // This function opens the file.
      public void openFile (String fileName) {
         try {
            read = new Scanner (new File (fileName));
         } catch (Exception e) {
            System.out.println ("Could not find file\n");
         }
      }
      
      // This function reads the file and turns
      // the unordered list into a float arraylist
      // for isort, afterwards, it returns the
      // arraylist.
      public ArrayList<Float> readFile () {
         while (read.hasNext ()) {
            unorderedList.add (Float.parseFloat (read.next ().replaceAll ("[;]", "")));
         }
            
         return unorderedList;
      }
      
      // This function closes the file.
      public void closeFile () {
         read.close ();
      }
}