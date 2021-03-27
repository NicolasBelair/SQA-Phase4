package prototype;
import java.io.BufferedReader;
import java.io.File;  
import java.io.FileNotFoundException;  
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

//AUTHOR: NICOLAS BELAIR, DATE: 26/03/2021

/*class whose purpose is to read in data from the mergedTransactions.txt file and store the raw data as well as a processed
form where the change in balance applied on each account is summed up and the amount of transactions performed per account
is also counted for the purposes of applying transaction fees. This class is called by the Process class.*/

public class ReadTransactions {
    //array for collected data. [transaction code, account id, transaction amount]
    private ArrayList<String[]> transactionsCombi = new ArrayList<String[]>();
    //array for collected data, raw
    private ArrayList<String> transactionsMerged = new ArrayList<String>();


    //method that gets the sum total of all transactions on each account, and the number of transactions on each account
    public void read(String address) {
        try {
            //load directory of transaction files
            File file = new File(address);
            //load list of all files within the directory (individual transaction files)

            //if files exist in the directory:
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

    ArrayList<String[]> getTransactionSums() {
        return this.transactionsCombi;
    }

    ArrayList<String> getTransactionsRaw() {
        return this.transactionsMerged;
    }

}//end class