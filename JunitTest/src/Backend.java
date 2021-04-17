import java.io.BufferedReader;
import java.io.File;  
import java.io.FileNotFoundException; 
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import prototype.*;

import javax.swing.text.AttributeSet.ColorAttribute;


//AUTHORS: NICOLAS BELAIR, ANDREW ROMANOF, KEVIN CHANDRA, JIMKARAN ABBAS, DATE: 26/03/2021

/*class that processes data from the ReadMaster and ReadTransaction classes and writes them to the new output files.*/

public class Backend {

	public static void main(String[] args) {
		//check that we have two input file addresses
		if (args.length != 2){
            System.out.println("ERROR: Requires 2 Arguments, Master Acccount File (filepath) and Merged Transaction File (filepath) [in that order]");
            System.exit(0);
        }

		//account variables
		String[] accNum = new String[500];
		String[] maName = new String[500];
		String[] activity = new String[500];
		float[] balance = new float[500];
		int[] trans = new int[500];
		
		//transaction variables
		String[] cc = new String[500];
		String[] mtName = new String[500];
		String[] mtAccountNumber = new String[500];
		float[] mtAmount = new float[500];
		DecimalFormat df = new DecimalFormat("00000.00");
		DecimalFormat trn = new DecimalFormat("0000");
		

		//instantiate the input classes using the commandline inputted filepaths
		ReadMaster RM = new ReadMaster();
		RM.read(args[0]);
		ReadTransactions RT = new ReadTransactions();
		RT.read(args[1]);
		ArrayList<String[]> collatedTransactions = RT.getTransactionSums(); //format [account id, transaction sum, transaction count]


		//split accounts into their components, store in String arrays
		for(int i=0; i<RM.getAccounts().size(); i++) {
			//Splice the string and assign the value to each attribute from the master account
			accNum[i] = RM.getAccounts().get(i).substring(0,5);
			maName[i] = RM.getAccounts().get(i).substring(6, 27);
			activity[i] = RM.getAccounts().get(i).substring(27,28);
			balance[i] = Float.parseFloat(RM.getAccounts().get(i).substring(29,38));
			trans[i] = Integer.parseInt(RM.getAccounts().get(i).substring(38,42));
		}
		
		//split transactions into their components, store in String arrays
		for(int i=0; i<RT.getTransactionsRaw().size(); i++) {
			//Splice the string and assign the value to each attribute from the transaction line
			cc[i] = RT.getTransactionsRaw().get(i).substring(0,2);
			mtName[i] = RT.getTransactionsRaw().get(i).substring(3, 22);
			mtAccountNumber[i] = RT.getTransactionsRaw().get(i).substring(24,29);
			mtAmount[i] = Float.parseFloat(RT.getTransactionsRaw().get(i).substring(30,38));
	
		}
		
		//run through transactions and match them to accounts to apply balance changes
		for(int i=0; i<collatedTransactions.size(); i++) {
			breakpoint1:
			for(int j=0; j<accNum.length; j++) {
				//when there's a match
				if(collatedTransactions.get(i)[0]==accNum[j]) {
					//update balance if there's a need to
					if(Float.parseFloat(collatedTransactions.get(i)[1])!=0.0) {
						balance[j]+=Float.parseFloat(collatedTransactions.get(i)[1]);
					}
					//save the number of transactions
					trans[j]+=Integer.parseInt(collatedTransactions.get(i)[2]);
					break breakpoint1;						
				}
			}
		}// end for loop

		//non-balance affecting transactions 
		for (int i = 0; i < accNum.length; i++) {
			//if transaction exists,
			if(accNum[i] != null) {
			//run through every account number
				for (int j = 0; j < mtAccountNumber.length; j++) {
					//if the transaction's number matches 
					if (accNum[i].equals(mtAccountNumber[j])) {
						if(cc[j].equals("05")) {
							//05 - Create
						}
						if(cc[j].equals("06")) {
							//06 - Delete
							System.out.println("delete logic"); //Remove account from current accounts but save in to master accounts file
						}
						if(cc[j].equals("07")) {
							//07 - Disable
							activity[i] = "D"; //change activity from A to D
						}
						if(cc[j].equals("08")) {
							//08 - Change plan
							System.out.println("changeplan logic"); //student plan to non-student plan
						}
					}
				}
			}
		}// end for loop


		//index counter
		int count = 0;

		//create a new file for the master account
		File file = new File("NewMasterAccount.txt");
		try {
			file.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		//write the content of the array into file
		try {
			FileWriter myWriter = new FileWriter(file);
			while(true) {
				myWriter.write(accNum[count] + " " + maName[count] + activity[count] + " "+ df.format(balance[count]) + " "+ trn.format(trans[count]));
				myWriter.write("\n");
				count++;
				if(accNum[count] == null){
					break;
				}
			}
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		count = 0;
		File file2 = new File("NewCurrentAccounts.txt");
		
		//create a new file for the current account
		try {
			file2.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		//write the content of the array into file
		try {
			FileWriter myWriter = new FileWriter(file2);
			while(true) {
				myWriter.write(accNum[count] + " " + maName[count] + activity[count] + " "+ df.format(balance[count]));
				myWriter.write("\n");
				count++;
				if(accNum[count] == null){
					break;
				}
			}
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}// end main()
}// end class
