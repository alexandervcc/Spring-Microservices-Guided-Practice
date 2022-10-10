package acc.microservices.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CurrencyConversionController {

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity) {

        // Path values
        HashMap<String, String> uriVariables = new HashMap<String, String>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        // Http Client
        ResponseEntity<CurrencyConversion> response = new RestTemplate()
                .getForEntity(
                        "http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                        CurrencyConversion.class,
                        uriVariables);

        CurrencyConversion cc = response.getBody();

        return CurrencyConversion.builder()
                .id(1001L)
                .from(from)
                .to(to)
                .quantity(quantity)
                .conversionMultiple(cc.getConversionMultiple())
                .totalCalculatedAmount(quantity.multiply(cc.getConversionMultiple()))
                .environment(cc.getEnvironment())
                .build();
    }
}
