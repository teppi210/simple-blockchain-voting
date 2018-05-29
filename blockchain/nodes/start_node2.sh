#!/usr/bin/env bash
geth --datadir node2/ --syncmode 'full' --port 30312 --rpc --rpcaddr 'localhost' --rpcport 8502 --rpcapi 'personal,db,eth,net,web3,txpool,miner' --bootnodes 'enode://9caef081b0793c3a299b6dea7cfa0b620254e5a9781039f3a9fed9e3d4e95556e07a273d6886424168a8fd42a43734001b9b7e55d20e19a05c1c88045cc33b58@127.0.0.1:30310' --networkid 1488 --gasprice '1' --unlock '91ff29e8e8b10ff255e53c2bf3510c267ab844f5' --password node2/password.txt --targetgaslimit 100000000 --mine console