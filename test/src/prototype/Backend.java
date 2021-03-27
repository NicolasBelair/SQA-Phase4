//package prototype;
import java.io.BufferedReader;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import prototype.ReadMaster;
import prototype.ReadTransactions;
public class Backend {

	public static void main(String[] args) {
		//Checking that we have two input file addresses
		if (args.length != 2){
            System.out.println("ERROR: Requires 2 Arguements, master account file and merged transaction file (in order)");
            System.exit(0);
        }

		//account variables
		String line = "";
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
		 
		//Taking arguements into variable so that it can read the file
		String masterPath = args[0];
		String transactionPath = args[1];

		//Using the arguements string (filename) to find the file
		ReadMaster RM = new ReadMaster();
		RM.read(masterPath);
		ReadTransactions RT = new ReadTransactions();
		RT.read(transactionPath);


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
	
		for (int i = 0; i < accNum.length; i++) {
			for (int j = 0; j < mtAccountNumber.length; j++) {
			
				if(accNum[i] != null) {

					if (accNum[i].equals(mtAccountNumber[j])) {
						if(cc[j].equals("01")) {
							//01 - Withdrawal
							if(maName[i].substring(0,4).equals(mtName[j].substring(0,4))){
								balance[i] = balance[i] - mtAmount[j];
								trans[i]+=1;
							} else{
								System.out.println("ERROR: Two bank accounts have the same account number 01");
							}	
						}
						if(cc[j].equals("02")) {
							//02 - Transfer
							//Subtract the balance by the transaction, add balance to transferred account
							if(maName[i].substring(0,4).equals(mtName[j].substring(0,4))){
								balance[i] = balance[i] - mtAmount[j];
								trans[i]+=1;
								i++;
								if(maName[i].substring(0,4).equals(mtName[j].substring(0,4))){
									balance[i] = balance[i] + mtAmount[j];
									trans[i]+=1;
								}else {
									System.out.println("ERROR: The account to transfer to doesn't exist");
								}
							}else{
								System.out.println("ERROR: Two bank accounts have the same account number 02");
							}	
						}
						if(cc[j].equals("03")) {
							//03 - Paybill
							if(maName[i].substring(0,4).equals(mtName[j].substring(0,4))){
								balance[i] = balance[i] - mtAmount[j];
								trans[i]+=1;
							}	else{
								System.out.println("ERROR: Two bank accounts have the same account number 03");
							}	
						}
						if(cc[j].equals("04")) {
							//04 - Deposit
							if(maName[i].substring(0,4).equals(mtName[j].substring(0,4))){
								balance[i] = balance[i] + mtAmount[j];
								trans[i]+=1;
							}	else{
								System.out.println("ERROR: Two bank accounts have the same account number 04");
							}	
						}
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
		}

	//Restart the loop
	int count = 0;

	//Create a new file for the master account
	File file = new File("NewMasterAccount.txt");

    try {
		file.createNewFile();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}

	//Write the content of the array into file
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
      
    //Create a new file for the current account
    try {
		file2.createNewFile();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}

	//Write the content of the array into file
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

	}}
