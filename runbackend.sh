#!/bin/bash

#filepaths for the backend input data
master_accounts_filepath=''
merged_transactions_filepath=''

#run backend with given datapaths
java Backend ${master_accounts_filepath} ${merged_transactions_filepath}