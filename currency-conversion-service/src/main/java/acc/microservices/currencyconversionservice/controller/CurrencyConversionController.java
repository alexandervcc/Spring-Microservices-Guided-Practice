package acc.microservices.currencyconversionservice.controller;

import java.math.BigDecimal;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CurrencyConversionController {

    private Environment environment;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity) {

        return CurrencyConversion.builder()
                .id(1001L)
                .from(from)
                .to(to)
                .quantity(quantity)
                .conversionMultiple(BigDecimal.ONE)
                .totalCalculatedAmount(BigDecimal.ONE)
                .environment(environment.getProperty("local.server.port"))
                .build();
    }
}
