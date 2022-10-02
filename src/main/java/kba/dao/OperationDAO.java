package kba.dao;

import kba.domain.Operation;
import kba.utils.OperationType;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class OperationDAO {

    private final List<Operation> operations = new ArrayList<>();

    public Operation createOperation(OperationType operationType, long amount, long oldBalance, long newBalance) {
        Operation operation = new Operation(operations.size(), amount, operationType, new Date(), oldBalance, newBalance);
        operations.add(operation);
        return operation;
    }

}
