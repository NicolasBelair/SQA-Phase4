#!/bin/bash

#filepaths for the backend input data
master_accounts_filepath="C:\Users\nicol\Documents\GitHub\SQA-Phase4\JunitTest\src\accounts.txt"
merged_transactions_filepath="C:\Users\nicol\Documents\GitHub\SQA-Phase4\JunitTest\src\transactions.etf"

#run backend with given datapaths
java Backend ${master_accounts_filepath} ${merged_transactions_filepath}