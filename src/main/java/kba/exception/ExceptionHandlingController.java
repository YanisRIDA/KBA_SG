package kba.exception;

import kba.service.BankAccountSvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlingController {

    private final Logger LOG = LoggerFactory.getLogger(ExceptionHandlingController.class);

    // Exception handling methods

    @ExceptionHandler(InvalidAmountException.class)
    public ResponseEntity<ErrorInfo> handleInvalidAmountError(HttpServletRequest req, InvalidAmountException ex) {
        LOG.error("[KATA BANK] Request: {} raised error: {}", req.getRequestURL(), ex.getDetails());
        return new ResponseEntity<>(new ErrorInfo(req.getRequestURL(), ex), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BankAccountNotFoundException.class)
    public ResponseEntity<ErrorInfo> handleBankAccountNotFoundError(HttpServletRequest req, BankAccountNotFoundException ex) {
        LOG.error("[KATA BANK] Request: {} raised error: {}", req.getRequestURL(), ex.getDetails());
        return new ResponseEntity<>(new ErrorInfo(req.getRequestURL(), ex), HttpStatus.NOT_FOUND);
    }
}
