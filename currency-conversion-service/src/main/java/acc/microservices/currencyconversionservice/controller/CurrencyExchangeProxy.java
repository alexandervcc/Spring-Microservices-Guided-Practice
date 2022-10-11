package acc.microservices.currencyconversionservice.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//name will be the other service name configured
@FeignClient(name = "currency-exchange", url = "localhost:8000")
public interface CurrencyExchangeProxy {
    
  @GetMapping("/currency-exchange/from/{from}/to/{to}")
  public CurrencyConversion retrieveExchangeValue(
    @PathVariable String from,  @PathVariable String to);
}


/*
  - the url of the potin will go into the method
  - the method will have the same structure as the method controller of the 
  - service to call
*/
