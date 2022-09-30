package kba.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import kba.model.dto.BankAccountDTO;
import kba.model.dto.OperationDTO;
import kba.service.BankAccountSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bank")
@Api("Bank accounts actions controller")
public class BankAccountCtl {

    @Autowired
    BankAccountSvc bankAccountSvc;

    @PostMapping("/depose")
    @ApiOperation(value = "Make a deposit")
    ResponseEntity<OperationDTO> depose(
            @ApiParam(value="Amount of money to depose", required = true)
            @RequestParam("amount")long amount) {
        //TODO EXCEPTION HANDLER
        return new ResponseEntity<>(bankAccountSvc.depose(amount), HttpStatus.OK);
    }

    @PostMapping("/withdraw")
    @ApiOperation(value = "Make a withdrawal")
    ResponseEntity<OperationDTO> withdraw(
            @ApiParam(value="Amount of money to withdraw", required = true)
            @RequestParam("amount")long amount) {
        //TODO EXCEPTION HANDLER
        return new ResponseEntity<>(bankAccountSvc.withdraw(amount), HttpStatus.OK);
    }

    @GetMapping("/consult")
    @ApiOperation(value = "Check your balance and your operations")
    ResponseEntity<BankAccountDTO> consult() {
        //TODO EXCEPTION HANDLER
        return new ResponseEntity<>(bankAccountSvc.consult(), HttpStatus.OK);
    }

}
