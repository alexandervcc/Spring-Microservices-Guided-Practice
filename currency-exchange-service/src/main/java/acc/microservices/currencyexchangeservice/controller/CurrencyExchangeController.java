package acc.microservices.currencyexchangeservice.controller;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import acc.microservices.currencyexchangeservice.model.CurrencyExchange;
import acc.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CurrencyExchangeController {

    private CurrencyExchangeRepository currencyExchangeRepository;
    private Environment env;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        // CurrencyExchange ce = new CurrencyExchange(1000L, from, to,
        // BigDecimal.valueOf(75));

        CurrencyExchange ce = this.currencyExchangeRepository.findByFromAndTo(from, to);
        if (ce == null) {
            throw new RuntimeException("Unable to find data for " + from + " to " + to);
        }
        ce.setEnvironment(env.getProperty("local.server.port"));

        return ce;
    }
}
