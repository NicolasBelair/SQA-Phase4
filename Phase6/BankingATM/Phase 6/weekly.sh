#!/bin/sh


#The script will start with monday, the monday transaction will take the accounts.txt file, and perform the transaction in the weekly/monday/inputs folder
#Once that is complete, it will concatenate all the transaction outputs it got into one big file called the Monday_Transactions.etf
#That file will be used with the masterAccount.txt file to get the NewCurrentAccounts file, and the NewMasterAccount file.


#MONDAY
{   rm -r weekly/monday/output/*
    counter=1 #counter variable for incrementing the actual output files
       for c in weekly/monday/input/*.inp ; do # loop through each file in the directory
        echo "Running Monday Transactions"
        ./BankFrontEnd.exe accounts.txt weekly/monday/output/out_${counter}.etf < weekly/monday/input/test${counter}.inp # run the tests, and store the actual outputs in an output folder
         counter=$((counter+1)) #increment the counter variable
         done
}

#Concatenate the outputs and remove end of file lines that start with the 00 transaction.
{   
    find . -type f -name 'out_*.etf' -exec cat {} + >> Monday_Transactions.etf
    sed -i '/^00/d' Monday_Transactions.etf
    cat Monday_Transactions.etf
}

{
    #run backend with given datapaths
javac Backend.java
java Backend masterAccount.txt Monday_Transactions.etf
}

#TUESDAY
{  rm -r weekly/tuesday/output/*
    counter=1 #counter variable for incrementing the actual output files
       for c in weekly/tuesday/input/*.inp ; do # loop through each file in the directory
        echo "Running Tuesday Transactions"
        ./BankFrontEnd.exe NewCurrentAccounts.txt weekly/tuesday/output/out_${counter}.etf < weekly/tuesday/input/test${counter}.inp # run the tests, and store the actual outputs in an output folder
         counter=$((counter+1)) #increment the counter variable
         done
}

{   
    find . -type f -name 'out_*.etf' -exec cat {} + >> Tuesday_Transactions.etf
    sed -i '/^00/d' Tuesday_Transactions.etf
    cat Tuesday_Transactions.etf
}

{
    #run backend with given datapaths
java Backend NewMasterAccount.txt Tuesday_Transactions.etf
}

#Wednesday
{  rm -r weekly/wednesday/output/*
    counter=1 #counter variable for incrementing the actual output files
       for c in weekly/wednesday/input/*.inp ; do # loop through each file in the directory
        echo "Running Wednesday Transactions"
        ./BankFrontEnd.exe NewCurrentAccounts.txt weekly/wednesday/output/out_${counter}.etf < weekly/wednesday/input/test${counter}.inp # run the tests, and store the actual outputs in an output folder
         counter=$((counter+1)) #increment the counter variable
         done
}

{   
    find . -type f -name 'out_*.etf' -exec cat {} + >> Wednesday_Transactions.etf
    sed -i '/^00/d' Wednesday_Transactions.etf
    cat Wednesday_Transactions.etf
}

{
    #run backend with given datapaths
java Backend NewMasterAccount.txt Wednesday_Transactions.etf
}

#Thursday
{  rm -r weekly/thursday/output/*
    counter=1 #counter variable for incrementing the actual output files
       for c in weekly/thursday/input/*.inp ; do # loop through each file in the directory
        echo "Running Thursday Transactions"
        ./BankFrontEnd.exe NewCurrentAccounts.txt weekly/thursday/output/out_${counter}.etf < weekly/thursday/input/test${counter}.inp # run the tests, and store the actual outputs in an output folder
         counter=$((counter+1)) #increment the counter variable
         done
}

{   
    find . -type f -name 'out_*.etf' -exec cat {} + >> Thursday_Transactions.etf
    sed -i '/^00/d' Thursday_Transactions.etf
    cat Thursday_Transactions.etf
}

{
    #run backend with given datapaths
java Backend NewMasterAccount.txt Thursday_Transactions.etf
}

#Friday
{  rm -r weekly/friday/output/*
    counter=1 #counter variable for incrementing the actual output files
       for c in weekly/friday/input/*.inp ; do # loop through each file in the directory
        echo "Running Friday Transactions"
        ./BankFrontEnd.exe NewCurrentAccounts.txt weekly/friday/output/out_${counter}.etf < weekly/friday/input/test${counter}.inp # run the tests, and store the actual outputs in an output folder
         counter=$((counter+1)) #increment the counter variable
         done
}

{   
    find . -type f -name 'out_*.etf' -exec cat {} + >> Friday_Transactions.etf
    sed -i '/^00/d' Friday_Transactions.etf
    cat Friday_Transactions.etf
}

{
    #run backend with given datapaths
java Backend NewMasterAccount.txt Friday_Transactions.etf
}