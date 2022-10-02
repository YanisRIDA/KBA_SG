package kba.dao;

import kba.utils.OperationType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OperationDAOTest {

    @Test
    public void TestCreateOperation() {
        OperationDAO operationDAO = new OperationDAO();

        Assertions.assertEquals(200, operationDAO.createOperation(
                OperationType.DEPOSIT, 200, 0, 200).getNewBalance());
        Assertions.assertEquals(100, operationDAO.createOperation(
                OperationType.WITHDRAWAL, 100, 200, 100).getNewBalance());
    }

}
