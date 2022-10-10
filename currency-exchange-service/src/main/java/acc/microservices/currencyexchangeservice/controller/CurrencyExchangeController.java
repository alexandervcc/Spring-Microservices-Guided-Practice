package acc.microservices.currencyexchangeservice.controller;

import java.math.BigDecimal;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CurrencyExchangeController {

    private Environment env;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        CurrencyExchange ce = new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(75));
        ce.setEnvironment(env.getProperty("local.server.port"));
        return ce;
    }
}
