package prototype;
import java.io.BufferedReader;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileReader;
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
            File dir = new File(address);
            //load list of all files within the directory (individual transaction files)
            File[] directoryListing = dir.listFiles();

            //if files exist in the directory:
            if (directoryListing != null) {
                //collate the information from each file in the String array
                for (File child : directoryListing) {
                    // Do something with child
                    BufferedReader tr = new BufferedReader(new FileReader(child));
                    String line = "";
                    //for all lines in the file
                    while((line = tr.readLine()) != null) {
                        transactionsMerged.add(line);
                        transactionsCombi.add(new String[]{line.substring(0,2), line.substring(24,29), line.substring(30,38)});
                    }
                    tr.close();
                }
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




    }
}
