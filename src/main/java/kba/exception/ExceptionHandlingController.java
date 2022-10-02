package kba.exception;

import kba.service.BankAccountSvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlingController {

    private final Logger LOG = LoggerFactory.getLogger(ExceptionHandlingController.class);

    // Exception handling methods

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorInfo> handleMissingParameterError(HttpServletRequest req, MissingServletRequestParameterException ex) {
        LOG.error("[KATA BANK] Request: {} raised error: {}", req.getRequestURL(), ex.getMessage());
        return new ResponseEntity<>(new ErrorInfo(req.getRequestURL(), "Missing required parameter", ex), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyParameterException.class)
    public ResponseEntity<ErrorInfo> handleEmptyParameterError(HttpServletRequest req, EmptyParameterException ex) {
        LOG.error("[KATA BANK] Request: {} raised error: {}", req.getRequestURL(), ex.getDetails());
        return new ResponseEntity<>(new ErrorInfo(req.getRequestURL(), ex), HttpStatus.BAD_REQUEST);
    }

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
