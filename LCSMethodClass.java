//hellloo there
public class LCSMethodClass {

	/**
	 *
	 * @param str1
	 * @param str2
	 * @return lcs
	 */
	public static char[] findLCS(String str1, String str2)
	{
		int str1Length = str1.length();
		int str2Length = str2.length();
		int[][] table = new int[str1Length + 1][str2Length + 1];

		//bottom up, compare characters in X and Y
		//fill the table with integers accordingly
		for (int row = 0; row <= str1Length; row++)
	        {
	            for (int column = 0; column <= str2Length; column++)
	            {
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
        while (i > 0 && j > 0)
        {
        	//if characters at indices are the same, corresponds to the diagonal movement in the table
            if (str1.charAt(i-1) == str2.charAt(j-1))
            {
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

//        System.out.println("------------------------------------------------------------------");
//        System.out.println("The LCS of DNA sequences: \n" + str1 + "\n" + str2 + "\n is: ");
//        for (int k = 0; k < lcs.length - 1; k++)
//        {
//        	System.out.print(lcs[k]);
//        }
//        System.out.println("\n The length is " + table[str1Length][str2Length]);
//        System.out.println("------------------------------------------------------------------");
	}
}
