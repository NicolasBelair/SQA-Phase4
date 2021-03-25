package prototype;
import java.io.BufferedReader;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String line = "";
		String[] accNum = new String[500];
		String[] name = new String[500];
		String[] activity = new String[500];
		String[] balance = new String[500];
		String[] trans = new String[500];
		int count = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader("C:/Users/jim/Documents/sqa/prototype/src/prototype/masterAccount.txt"));
			
			while((line = br.readLine()) != null) {
				accNum[count] = line.substring(0,5);
				name[count] = line.substring(6, 27);
				activity[count] = line.substring(27,28);
				balance[count] = line.substring(29,38);
				trans[count] = line.substring(38,42);
				System.out.print(accNum[count]);
				System.out.print(name[count]);
				System.out.print(activity[count]);
				System.out.print(balance[count]);
				System.out.print(trans[count]);
				count++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// TODO:
		// Read in the transactions.., store them in variables

		// For each transaction, if account numbers are equal, then continue with logic
		// When account numbers are equal check the transaction code.
		// 01-withdrawal, 02-transfer, 03-paybill, 04-deposit, 05-create, 06-delete, 07-disable,08-changeplan
		// add to the transaction count
		
		// Make two strings one for the new current accounts file, and one that has every account.
		// write the new current accounts file
		
		
}	
}
