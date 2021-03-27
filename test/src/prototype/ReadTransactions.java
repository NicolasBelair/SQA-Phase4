import java.io.BufferedReader;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileReader;
import java.nio.*;
import java.io.IOException;
import java.util.*;

public class ReadTransactions {
    //array for collected data. [transaction code, account id, transaction amount]
    ArrayList<String[]> transactionsCombi = new ArrayList<String[]>();
    //array for collected data, raw
    ArrayList<String> transactionsMerged = new ArrayList<String>();


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
              // Handle the case where dir is not really a directory.
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String[]> transactionsCombiTemp = new ArrayList<String[]>();
        for (int i=0; i<transactionsCombi.size(); i++) {
            String[] s = transactionsCombi.get(i);
            if(transactionsCombiTemp.isEmpty()) {
                s.add()
            }
            

        }


    }
}