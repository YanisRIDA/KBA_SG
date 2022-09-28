package kba.repository;

import kba.model.Client;
import kba.model.Operation;
import kba.utils.OperationType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class OperationRepository {

    private final List<Operation> operations = new ArrayList<>();

    public Operation createOperation(OperationType operationType, long amount) {
        Operation operation = new Operation(operations.size(), amount, operationType, new Date());
        operations.add(operation);
        return operation;
    }

}
