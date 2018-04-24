pragma solidity ^0.4.16;

contract Voting {

    address[] public votersAddresses;
    address[] public votedAddresses;

    mapping(address => uint) public voterMarks;

    modifier ableToVote {
        bool flag = false;

        // Check if address is in voters list;
        for (uint i = 0; i < votersAddresses.length; i++) {
            if (msg.sender == votersAddresses[i]) flag = true;
        }

        if (flag == false) revert();

        // Check if not voted yet;
        if (votedAddresses.length != 0) {
            for (uint k = 0; k < votedAddresses.length; k++) {
                if (msg.sender == votedAddresses[k]) revert();
            }
        }
        _;
    }

    function Voting(address[] _voters) public {
        for (uint i = 0; i < _voters.length; i++) {
            votersAddresses.push(_voters[i]);
        }
    }

    function vote(uint mark) public ableToVote {
        voterMarks[msg.sender] = mark;
        votedAddresses.push(msg.sender);
    }

    function getMarks() public view returns (uint[]) {
        uint[] memory marks = new uint[](votedAddresses.length);
        for (uint i = 0; i < votedAddresses.length; i++) {
            marks[i] = voterMarks[votedAddresses[i]];
        }
        return marks;
    }

    function getVotersAddresses() public view returns (address[]) {
        return votersAddresses;
    }

    function getVotedAddresses() public view returns (address[]) {
        return votedAddresses;
    }
}
