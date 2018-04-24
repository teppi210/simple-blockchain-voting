package com.chernikovmaksym.voting.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.web3j.protocol.exceptions.TransactionException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    public String handleBindingResultException(BindException exception, Model model) {
        String errorMessage = exception.getAllErrors().get(0).getDefaultMessage(); // Shows first error only
        log.warn("Binding exception intercepted: {}", errorMessage);
        model.addAttribute("exception", errorMessage);
        return "exception";
    }

    @ExceptionHandler(TransactionException.class)
    public String handleTransactionException(TransactionException exception, Model model) {
        log.error("Exception intercepted: ", exception);
        model.addAttribute("exception", "Error submitting transaction to blockchain. (Voted already?)");
        return "exception";
    }

    @ExceptionHandler(Exception.class)
    public String handleUnknownException(Exception exception, Model model) {
        log.error("Exception intercepted: ", exception);
        model.addAttribute("exception", exception.getMessage());
        return "exception";
    }
}
