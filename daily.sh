#!/bin/sh

{
cd tests/daily 
    counter=1 #counter variable for incrementing the actual output files
    for c in tests/daily/*.inp ; do # loop through each file in the directory
        echo "Running Daily Transactions"
        ../../bank-atm tests/daily/output/transaction_daily${counter}.atf < ${c}# run the tests, and store the actual outputs in an output folder
        counter=$((counter+1)) #increment the counter variable
        done
}