#!/bin/sh

{   rm transactions.etf
    find . -type f -name 'out_*.etf' -exec cat {} + >> transactions.etf
    sed -i '/^00/d' transactions.etf
    cat transactions.etf
}