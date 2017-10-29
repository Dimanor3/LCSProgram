/* 	
	Bijan Razavi
	Elizabeth Thomas
	Gyovanni Boston-Crompton
*/

import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class writefile {
   // This function creates a new file, answers.txt,
   // assuming the file doesn't already exist.
   public void createFile () {
      try {
         File newFile = new File ("answers.txt");
         
         if (newFile.createNewFile ()) {
            System.out.println ("File is created!\n");
         } else {
            System.out.println ("File already exists.\n");
         }
         
      } catch (IOException e) {
         System.out.println ("Oh no... Something failed!!!\n");
         e.printStackTrace ();
      }
   }
   
   // This function updates answers.txt with the orderedList.
   public void addOrderedList (String orderedList) {
   	BufferedWriter bw = null;
		FileWriter fw = null;
   
      try {
         fw = new FileWriter ("answers.txt");
         bw = new BufferedWriter (fw);
         bw.write (orderedList);
         
         System.out.println ("answers.txt has been updated with the answer!");
      } catch (IOException e) {
         System.out.println ("Oh no... Something failed!!!\n");
         e.printStackTrace ();
      } finally {
         try {
            if (bw != null) {
               bw.close ();
            }
            
            if (fw != null) {
               fw.close ();
            }
         } catch (IOException e) {
            System.out.println ("Oh no... Something failed!!!\n");
            e.printStackTrace ();
         }
      }
   }
}
