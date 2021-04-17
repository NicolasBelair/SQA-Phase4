import java.io.BufferedReader;
import java.io.File;  
import java.io.FileNotFoundException; 
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

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
	//AUTHOR: NICOLAS BELAIR, DATE: 26/03/2021

	/*class whose purpose is to read in data from the masterAccount.txt file and store the raw data contained within (one line per array index) as Strings
	This class is called by the Process class.*/

	public class ReadMaster {
		//array for collected data, raw
		private ArrayList<String> accounts = new ArrayList<String>();
		
		//method that reads in all the accounts and stores them in ArrayList "accounts"
		public void read(String address) {
			try {
				//load file
				File file = new File(address);

				//if file exists
				if (file != null) {
					//read lines from file
					BufferedReader tr = new BufferedReader(new FileReader(file));
					String line = "";
					//for all lines in the file
					while((line = tr.readLine()) != null) {
						accounts.add(line);
					}
					tr.close();

				}
				else {
					System.exit(0);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}// end read()

		//getter methods

		public ArrayList<String> getAccounts() {
			return this.accounts;
		}

	}//end class

	//AUTHOR: NICOLAS BELAIR, DATE: 26/03/2021

	/*class whose purpose is to read in data from the mergedTransactions.txt file and store the raw data as well as a processed
	form where the change in balance applied on each account is summed up and the amount of transactions performed per account
	is also counted for the purposes of applying transaction fees. This class is called by the Process class.*/

	public class ReadTransactions {
		//array for collected data. [transaction code, account id, transaction amount] initially, then becomes [account id, transaction sum, transaction count]
		private ArrayList<String[]> transactionsCombi = new ArrayList<String[]>();
		//array for collected data, raw
		private ArrayList<String> transactionsMerged = new ArrayList<String>();


		/*method that reads in all the transactions and stores them in ArrayList "transactionsMerged"
		this method also combines all the transactions per-account to an overall change in balance
		and number of transactions performed in ArrayList "transactionsCombi"*/
		public void read(String address) {
			try {
				//load file
				File file = new File(address);

				//if file exists
				if (file != null) {
					//read lines from file
					BufferedReader tr = new BufferedReader(new FileReader(file));
					String line = "";
					//for all lines in the file
					while((line = tr.readLine()) != null) {
						transactionsMerged.add(line);
						transactionsCombi.add(new String[]{line.substring(0,2), line.substring(24,29), line.substring(30,38)});
					}
					tr.close();

				}
				else {
					System.exit(0);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			//array for combined transactions per account
			ArrayList<String[]> transactionsCombiTemp = new ArrayList<String[]>();
			//for each transaction
			for (int i=0; i<transactionsCombi.size(); i++) {
				//on current transaction
				String[] s = transactionsCombi.get(i);

				//if empty array
				if(transactionsCombiTemp.isEmpty()) {
					String[] temp = Arrays.copyOf(s, s.length+1);
					temp[3]="1";
					temp = Arrays.copyOfRange(temp, 1, temp.length);
					transactionsCombiTemp.add(temp);
				}

				//otherwise check if the userID already exists in the temp list
				breakpoint1:
				if(!transactionsCombiTemp.isEmpty()) {
					for(int j=0; j<transactionsCombiTemp.size(); j++) {
						//if there is a match in the temp array
						if(s[1]==transactionsCombiTemp.get(j)[0]) {
							//add transaction change
							if(s[0]=="04") {
								transactionsCombiTemp.get(j)[1] = String.valueOf(Float.parseFloat(transactionsCombiTemp.get(j)[1]) + Float.parseFloat(s[2]));
								transactionsCombiTemp.get(j)[3] = String.valueOf(Integer.parseInt(transactionsCombiTemp.get(j)[3])+1);
							}
							//remove transaction change
							else if(s[0]=="01" ||
									s[0]=="02" ||
									s[0]=="03" ) {
								transactionsCombiTemp.get(j)[1] = String.valueOf(Float.parseFloat(transactionsCombiTemp.get(j)[1]) - Float.parseFloat(s[2]));
								transactionsCombiTemp.get(j)[3] = String.valueOf(Integer.parseInt(transactionsCombiTemp.get(j)[3])+1);
							}
							//break out of else to avoid default condition
							break breakpoint1;
						}
					}
					//if user id not already in temp array
					String[] temp = Arrays.copyOf(s, s.length+1);
					temp[3]="1";
					temp = Arrays.copyOfRange(temp, 1, temp.length);
					transactionsCombiTemp.add(temp);
				}

				//replace the arrayList with its processed variant
				transactionsCombi = transactionsCombiTemp;
			}// end for loop
		}// end read()

		//getter methods

		public ArrayList<String[]> getTransactionSums() {
			return this.transactionsCombi;
		}

		public ArrayList<String> getTransactionsRaw() {
			return this.transactionsMerged;
		}
	}//end class
}// end class
