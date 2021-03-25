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
		String[] maName = new String[500];
		String[] activity = new String[500];
		String[] balance = new String[500];
		String[] trans = new String[500];
		
		String[] cc = new String[500];
		String[] mtName = new String[500];
		String[] mtAccountNumber = new String[500];
		String[] mtAmount = new String[500];
		 
		int count = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader("C:/Users/jim/Documents/sqa/prototype/src/prototype/masterAccount.txt"));
			
			while((line = br.readLine()) != null) {
				accNum[count] = line.substring(0,5);
				maName[count] = line.substring(6, 27);
				activity[count] = line.substring(27,28);
				balance[count] = line.substring(29,38);
				trans[count] = line.substring(38,42);
				System.out.print(accNum[count]);
				System.out.print(maName[count]);
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
		count = 0;
		System.out.println("");
		try {
			BufferedReader tr = new BufferedReader(new FileReader("C:/Users/jim/Documents/sqa/prototype/src/prototype/mergedTransaction.txt"));
			
			while((line = tr.readLine()) != null) {
				cc[count] = line.substring(0,2);
				mtName[count] = line.substring(3, 22);
				mtAccountNumber[count] = line.substring(24,29);
				mtAmount[count] = line.substring(30,38);
				
				System.out.print(cc[count]);
				System.out.print(mtName[count]);
				System.out.print(mtAccountNumber[count]);
				System.out.print(mtAmount[count]);
				System.out.println("");
				count++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		for (int i = 0; i < accNum.length; i++) {
			for (int j = 0; j < mtAccountNumber.length; j++) {
			if (cc[j].equals("05")) {	
				System.out.println("create logic");
			}
			if(accNum[i] != null) {
				if (accNum[i].equals(mtAccountNumber[j])) {
					if(cc[j].equals("01")) {
						System.out.println("withdrawal logic"); //Subtract the balance by the transaction
					}
					if(cc[j].equals("02")) {
						System.out.println("transfer logic"); //Subtract the balance by the transaction, add balance to transferred account
					}
					if(cc[j].equals("03")) {
						System.out.println("paybill logic"); //Subtract the balance by the transaction
					}
					if(cc[j].equals("04")) {
						System.out.println("deposit logic"); //Add the transaction to the balance
					}
					if(cc[j].equals("06")) {
						System.out.println("delete logic"); //Remove account from current accounts but save in to master accounts file
					}
					if(cc[j].equals("07")) {
						System.out.println("disable logic"); //change activity from A to D
					}
					if(cc[j].equals("08")) {
						System.out.println("changeplan logic"); //student plan to non-student plan
					}
				}
			}
			
		}
	}
	// Perform the logic.. Write new files.
}	
}
