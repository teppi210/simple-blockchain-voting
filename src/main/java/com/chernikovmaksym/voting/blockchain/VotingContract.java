package com.chernikovmaksym.voting.blockchain;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 * <p>
 * <p>Generated with web3j version 3.2.0.
 */
public class VotingContract extends Contract {
    private static final String BINARY = "6060604052341561000f57600080fd5b6040516104dd3803806104dd83398101604052808051909101905060005b8151811015610096576000805460018101610048838261009d565b9160005260206000209001600084848151811061006157fe5b906020019060200201518254600160a060020a039182166101009390930a92830291909202199091161790555060010161002d565b50506100e7565b8154818355818115116100c1576000838152602090206100c19181019083016100c6565b505050565b6100e491905b808211156100e057600081556001016100cc565b5090565b90565b6103e7806100f66000396000f30060606040526004361061006c5763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416630121b93f81146100715780631af106791461008957806343e31c1a146100bb578063b780e507146100d1578063d375e15e14610137575b600080fd5b341561007c57600080fd5b610087600435610168565b005b341561009457600080fd5b61009f600435610276565b604051600160a060020a03909116815260200160405180910390f35b34156100c657600080fd5b61009f60043561029e565b34156100dc57600080fd5b6100e46102ac565b60405160208082528190810183818151815260200191508051906020019060200280838360005b8381101561012357808201518382015260200161010b565b505050509050019250505060405180910390f35b341561014257600080fd5b610156600160a060020a036004351661034d565b60405190815260200160405180910390f35b600080805b6000548210156101b557600080548390811061018557fe5b60009182526020909120015433600160a060020a03908116911614156101aa57600192505b60019091019061016d565b8215156101c157600080fd5b60015415610212575060005b6001548110156102125760018054829081106101e557fe5b60009182526020909120015433600160a060020a039081169116141561020a57600080fd5b6001016101cd565b600160a060020a03331660009081526002602052604090208490556001805480820161023e838261035f565b506000918252602090912001805473ffffffffffffffffffffffffffffffffffffffff191633600160a060020a031617905550505050565b600080548290811061028457fe5b600091825260209091200154600160a060020a0316905081565b600180548290811061028457fe5b6102b4610388565b6102bc610388565b6001546000906040518059106102cf5750595b90808252806020026020018201604052509150600090505b600154811015610347576002600060018381548110151561030457fe5b6000918252602080832090910154600160a060020a0316835282019290925260400190205482828151811061033557fe5b602090810290910101526001016102e7565b50919050565b60026020526000908152604090205481565b8154818355818115116103835760008381526020902061038391810190830161039a565b505050565b60206040519081016040526000815290565b6103b891905b808211156103b457600081556001016103a0565b5090565b905600a165627a7a7230582061a035b302b4cc28e352e0fe713272a8835fb4b9557ecdc37bc88aab3a4583140029";

    protected VotingContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected VotingContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<TransactionReceipt> vote(BigInteger mark) {
        Function function = new Function(
                "vote",
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(mark)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> votersAddresses(BigInteger param0) {
        Function function = new Function("votersAddresses",
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
                }));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> votedAddresses(BigInteger param0) {
        Function function = new Function("votedAddresses",
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
                }));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> voterMarks(String param0) {
        Function function = new Function("voterMarks",
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public static RemoteCall<VotingContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, List<String> _voters) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                org.web3j.abi.Utils.typeMap(_voters, org.web3j.abi.datatypes.Address.class))));
        return deployRemoteCall(VotingContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<VotingContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, List<String> _voters) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                org.web3j.abi.Utils.typeMap(_voters, org.web3j.abi.datatypes.Address.class))));
        return deployRemoteCall(VotingContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static VotingContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new VotingContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static VotingContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new VotingContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}