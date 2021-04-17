#!/bin/sh

{   rm -r daily/output/*
    counter=1 #counter variable for incrementing the actual output files
       for c in daily/input/*.inp ; do # loop through each file in the directory
        echo "Running Daily Transactions"
        ./BankFrontEnd.exe accounts.txt daily/output/out_${counter}.etf < daily/input/test${counter}.inp # run the tests, and store the actual outputs in an output folder
         counter=$((counter+1)) #increment the counter variable
         done
}

{   rm Daily_Transactions.etf
    find . -type f -name 'out_*.etf' -exec cat {} + >> Daily_Transactions.etf
    sed -i '/^00/d' Daily_Transactions.etf
    cat Daily_Transactions.etf
}

{
    #run backend with given datapaths
javac Backend.java
java Backend masterAccount.txt transactions.etf
}