package dev.coffeeprog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.coffeeprog.model.Customer;
import dev.coffeeprog.model.Loans;
import dev.coffeeprog.repository.LoanRepository;

@RestController
public class LoansController {

    @Autowired
    private LoanRepository loanRepository;

    @PostMapping("/myLoans")
    public List<Loans> getLoanDetails(@RequestBody Customer customer) {
        List<Loans> loans = loanRepository.findByCustomerIdOrderByStartDtDesc(customer.getId());
        if (loans != null ) {
            return loans;
        }else {
            return null;
        }
    }

}