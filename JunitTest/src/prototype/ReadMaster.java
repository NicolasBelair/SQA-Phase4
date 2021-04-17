package prototype;
import java.io.BufferedReader;
import java.io.File;  
import java.io.FileNotFoundException;  
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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