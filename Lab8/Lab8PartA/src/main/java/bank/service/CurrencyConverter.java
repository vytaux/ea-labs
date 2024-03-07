package bank.service;

import org.springframework.stereotype.Component;

public interface CurrencyConverter {

    public double euroToDollars (double amount);

    public double dollarsToEuros (double amount);

}
