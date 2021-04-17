#!/bin/bash

#filepaths for the backend input data
master_accounts_filepath="C:\Users\jim\Documents\GitHub\SQA-Phase4\Phase6\BankingATM\masterAccount.txt"
merged_transactions_filepath="C:\Users\jim\Documents\GitHub\SQA-Phase4\Phase6\BankingATM\transactions.etf"

#run backend with given datapaths
java Backend ${master_accounts_filepath} ${merged_transactions_filepath}