package dev.coffeeprog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.coffeeprog.model.Cards;
import dev.coffeeprog.model.Customer;
import dev.coffeeprog.repository.CardsRepository;

@RestController
public class CardsController {

    @Autowired
    private CardsRepository cardsRepository;

    @PostMapping("/myCards")
    public List<Cards> getCardDetails(@RequestBody Customer customer) {
        List<Cards> cards = cardsRepository.findByCustomerId(customer.getId());
        if (cards != null ) {
            return cards;
        }else {
            return null;
        }
    }

}