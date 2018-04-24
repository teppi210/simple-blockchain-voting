#!/usr/bin/env bash
rm -r node1/geth
rm -r node2/geth
rm node1/history
rm node2/history
geth --datadir node1/ init genesis.json
geth --datadir node2/ init genesis.json
bootnode -nodekey boot.key -verbosity 9 -addr :30310
