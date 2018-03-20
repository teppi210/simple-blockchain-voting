# simple-blockchain-voting

*Run geth locally:*

geth --rpc --rpcaddr "0.0.0.0" --rpcapi "admin,debug,miner,shh,txpool,personal,eth,net,web3" --ipcpath "~/geth/geth.ipc" --datadir ~/geth/datadir/ --networkid 1488 console


*Connect Mist:*

open -a /Applications/Mist.app --args --rpc ~/geth/geth.ipc
