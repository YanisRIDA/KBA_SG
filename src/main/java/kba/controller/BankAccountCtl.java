package kba.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kba.dto.ClientDTO;
import kba.exception.BankAccountNotFoundException;
import kba.exception.EmptyParameterException;
import kba.exception.InvalidAmountException;
import kba.dto.BankAccountDTO;
import kba.dto.OperationDTO;
import kba.service.BankAccountSvc;
import kba.utils.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bankaccounts")
@Api("Bank accounts actions controller")
public class BankAccountCtl {

    @Autowired
    BankAccountSvc bankAccountSvc;

    @PostMapping("")
    @ApiOperation(value = "Create a bank account")
    ResponseEntity<BankAccountDTO> createBankAccount(
            @ApiParam(value="Information about the client", required = true)
            @RequestBody ClientDTO client) throws EmptyParameterException {
        return new ResponseEntity<>(bankAccountSvc.createAccount(client), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/depose")
    @ApiOperation(value = "Make a deposit")
    ResponseEntity<OperationDTO> depose(
            @ApiParam(value="Id of the bank account", required = true)
            @PathVariable("id") int id,
            @ApiParam(value="Amount of money to depose", required = true)
            @RequestParam("amount")Long amount) throws InvalidAmountException, BankAccountNotFoundException, EmptyParameterException {
        return new ResponseEntity<>(bankAccountSvc.operation(id, amount, OperationType.DEPOSIT), HttpStatus.OK);
    }

    @PostMapping("/{id}/withdraw")
    @ApiOperation(value = "Make a withdrawal")
    ResponseEntity<OperationDTO> withdraw(
            @ApiParam(value="Id of the bank account", required = true)
            @PathVariable("id") int id,
            @ApiParam(value="Amount of money to withdraw", required = true)
            @RequestParam("amount")Long amount) throws InvalidAmountException, BankAccountNotFoundException, EmptyParameterException {
        return new ResponseEntity<>(bankAccountSvc.operation(id, amount, OperationType.WITHDRAWAL), HttpStatus.OK);
    }

    @GetMapping("/{id}/consult")
    @ApiOperation(value = "Check your balance and your operations")
    ResponseEntity<BankAccountDTO> consult(
            @ApiParam(value="Id of the bank account", required = true)
            @PathVariable("id") int id) throws BankAccountNotFoundException {
        return new ResponseEntity<>(bankAccountSvc.consult(id), HttpStatus.OK);
    }

}
