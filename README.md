This is a simple voting app based on Ethereum blockchain.
---
### Running application (for *nix environments):

#### Requirements:
bootnode, geth 1.8.2+, java 8
#### How to run:
Execute `init_blockchain.sh` script in "./blockchain/nodes" folder to initialise blockchain data, then start nodes by executing `start_node1.sh` and `start_node2.sh`.

IPC path is configured by default: `~/geth/geth.ipc`. Use it if you would like to connect to the node via other clients.

Run `simple-blockchain-voting-1.0.STABLE.jar` to run the application.

### Modify and build:
#### Requirements:
bootnode, geth 1.8.2+, java 8, gradle, web3j, solcjs.

#### Building project:
Use `gradle clean build` to rebuild the project.

Default properties stored in `config.yml`. You can modify it directly in file (rebuild required) or pass them via arguments on application startup.

If contract code is modified, `.bin`, `.abi` and Java wrappers needs to be updated. Use `generate_wrapper.sh` for this. ABI interface should be added to wrapper code manually.
#
Created by *Maksym Chernikov*.

You can freely use the project for study purposes. Contact me for support.

