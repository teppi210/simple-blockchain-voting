package com.chernikovmaksym.voting.blockchain;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
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
import java.util.concurrent.Callable;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.4.0.
 */
public class VotingContract extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b5060405161052a38038061052a8339810160405280510160005b8151811015610088576000828281518110151561004357fe5b6020908102919091018101518254600180820185556000948552929093209092018054600160a060020a031916600160a060020a03909316929092179091550161002a565b5050610491806100996000396000f3006080604052600436106100825763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416630121b93f81146100875780631af10679146100a157806343e31c1a146100d5578063442b70f7146100ed578063b780e50714610152578063d375e15e14610167578063dadfd8561461019a575b600080fd5b34801561009357600080fd5b5061009f6004356101af565b005b3480156100ad57600080fd5b506100b96004356102b7565b60408051600160a060020a039092168252519081900360200190f35b3480156100e157600080fd5b506100b96004356102df565b3480156100f957600080fd5b506101026102ed565b60408051602080825283518183015283519192839290830191858101910280838360005b8381101561013e578181015183820152602001610126565b505050509050019250505060405180910390f35b34801561015e57600080fd5b5061010261034f565b34801561017357600080fd5b50610188600160a060020a03600435166103f3565b60408051918252519081900360200190f35b3480156101a657600080fd5b50610102610405565b600080805b6000548210156101f85760008054839081106101cc57fe5b600091825260209091200154600160a060020a03163314156101ed57600192505b6001909101906101b4565b82151561020457600080fd5b60015415610251575060005b60015481101561025157600180548290811061022857fe5b600091825260209091200154600160a060020a031633141561024957600080fd5b600101610210565b50503360008181526002602052604081209390935560018054808201825593527fb10e2d527612073b26eecdfd717e6a320cf44b4afac2b0732d9fcbe2b7fa0cf6909201805473ffffffffffffffffffffffffffffffffffffffff191690921790915550565b60008054829081106102c557fe5b600091825260209091200154600160a060020a0316905081565b60018054829081106102c557fe5b6060600180548060200260200160405190810160405280929190818152602001828054801561034557602002820191906000526020600020905b8154600160a060020a03168152600190910190602001808311610327575b5050505050905090565b6060806000600180549050604051908082528060200260200182016040528015610383578160200160208202803883390190505b509150600090505b6001548110156103ed57600260006001838154811015156103a857fe5b6000918252602080832090910154600160a060020a0316835282019290925260400190205482518390839081106103db57fe5b6020908102909101015260010161038b565b50919050565b60026020526000908152604090205481565b6060600080548060200260200160405190810160405280929190818152602001828054801561034557602002820191906000526020600020908154600160a060020a031681526001909101906020018083116103275750505050509050905600a165627a7a72305820735745c50725f26203b7eb3d63df41b29f841cd7f895eb7584a5ffb5f3d563130029";
    public static final String ABI = "[{\"constant\":false,\"inputs\":[{\"name\":\"mark\",\"type\":\"uint256\"}],\"name\":\"vote\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"votersAddresses\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"votedAddresses\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getVotedAddresses\",\"outputs\":[{\"name\":\"\",\"type\":\"address[]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getMarks\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256[]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"address\"}],\"name\":\"voterMarks\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getVotersAddresses\",\"outputs\":[{\"name\":\"\",\"type\":\"address[]\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"name\":\"_voters\",\"type\":\"address[]\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"}]";

    public static final String FUNC_VOTE = "vote";

    public static final String FUNC_VOTERSADDRESSES = "votersAddresses";

    public static final String FUNC_VOTEDADDRESSES = "votedAddresses";

    public static final String FUNC_GETVOTEDADDRESSES = "getVotedAddresses";

    public static final String FUNC_GETMARKS = "getMarks";

    public static final String FUNC_VOTERMARKS = "voterMarks";

    public static final String FUNC_GETVOTERSADDRESSES = "getVotersAddresses";

    protected VotingContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected VotingContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<TransactionReceipt> vote(BigInteger mark) {
        final Function function = new Function(
                FUNC_VOTE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(mark)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> votersAddresses(BigInteger param0) {
        final Function function = new Function(FUNC_VOTERSADDRESSES,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
                }));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> votedAddresses(BigInteger param0) {
        final Function function = new Function(FUNC_VOTEDADDRESSES,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
                }));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<List> getVotedAddresses() {
        final Function function = new Function(FUNC_GETVOTEDADDRESSES,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {
                }));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<List> getMarks() {
        final Function function = new Function(FUNC_GETMARKS,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {
                }));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<BigInteger> voterMarks(String param0) {
        final Function function = new Function(FUNC_VOTERMARKS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<List> getVotersAddresses() {
        final Function function = new Function(FUNC_GETVOTERSADDRESSES,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {
                }));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
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
