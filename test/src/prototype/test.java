//package prototype;
import java.io.BufferedReader;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String line = "";
		String[] accNum = new String[500];
		String[] maName = new String[500];
		String[] activity = new String[500];
		float[] balance = new float[500];
		int[] trans = new int[500];
		
		String[] cc = new String[500];
		String[] mtName = new String[500];
		String[] mtAccountNumber = new String[500];
		float[] mtAmount = new float[500];
		DecimalFormat df = new DecimalFormat("00000.00");
		DecimalFormat trn = new DecimalFormat("0000");
		 
		int count = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader("C:/Users/jim/Documents/sqa/prototype/src/prototype/masterAccount.txt"));
			
			while((line = br.readLine()) != null) {
				accNum[count] = line.substring(0,5);
				maName[count] = line.substring(6, 27);
				activity[count] = line.substring(27,28);
				balance[count] = Float.parseFloat(line.substring(29,38));
				trans[count] = Integer.parseInt(line.substring(38,42));
				// System.out.print(accNum[count]);
				// System.out.print(maName[count]);
				// System.out.print(activity[count]);
				// System.out.print(balance[count]);
				// System.out.print(trans[count]);
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
				mtAmount[count] = Float.parseFloat(line.substring(30,38));
				
				// System.out.print(cc[count]);
				// System.out.print(mtName[count]);
				// System.out.print(mtAccountNumber[count]);
				// System.out.print(mtAmount[count]);
				// System.out.println("");
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
			//if (cc[j].equals("05") & cc[j] != null) {	
			//	System.out.println("create logic");
			//}
			if(accNum[i] != null) {
				if (accNum[i].equals(mtAccountNumber[j])) {
					if(cc[j].equals("01")) {
							if(maName[i].substring(0,4).equals(mtName[j].substring(0,4))){
								balance[i] = balance[i] - mtAmount[j];
								trans[i]+=1;
							} else{
								System.out.println("ERROR: Two bank accounts have the same account number");
							}	
					}
					if(cc[j].equals("02")) {
					 //Subtract the balance by the transaction, add balance to transferred account
					 if(maName[i].substring(0,4).equals(mtName[j].substring(0,4))){
						balance[i] = balance[i] - mtAmount[j];
						trans[i]+=1;
					}	else{
						System.out.println("ERROR: Two bank accounts have the same account number");
					}	
					}
					if(cc[j].equals("03")) {
						if(maName[i].substring(0,4).equals(mtName[j].substring(0,4))){
							balance[i] = balance[i] - mtAmount[j];
							trans[i]+=1;
						}	else{
							System.out.println("ERROR: Two bank accounts have the same account number");
						}	
					}
					if(cc[j].equals("04")) {
						if(maName[i].substring(0,4).equals(mtName[j].substring(0,4))){
							balance[i] = balance[i] + mtAmount[j];
							trans[i]+=1;
						}	else{
							System.out.println("ERROR: Two bank accounts have the same account number");
						}	
					}
					if(cc[j].equals("06")) {
						System.out.println("delete logic"); //Remove account from current accounts but save in to master accounts file
					}
					if(cc[j].equals("07")) {
						activity[i] = "D"; //change activity from A to D
					}
					if(cc[j].equals("08")) {
						System.out.println("changeplan logic"); //student plan to non-student plan
					}
				}
			}
			
		}
	}

	// Perform the logic.. Write new files.
	count = 0;
	File file = new File("NewMasterAccount.txt");
      
      // creates the file
    try {
		file.createNewFile();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
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
      
      // creates the file
    try {
		file2.createNewFile();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
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
