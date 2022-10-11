package acc.microservices.currencyconversionservice.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//name will be the other service name configured
//@FeignClient(name = "currency-exchange", url = "localhost:8000")
@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {
    
  @GetMapping("/currency-exchange/from/{from}/to/{to}")
  public CurrencyConversion retrieveExchangeValue(
    @PathVariable String from,  @PathVariable String to);
}


/*
  FEIGN CONFIGURATION
    - the url of the potin will go into the method
    - the method will have the same structure as the method controller of the 
    - service to call
  
  DYNAMIC PORTS FOR PROXY
    - use Eureka Naming Server:  a discovery server
    - in this services all instances of all microservices will register in this
    - so services will ask the naming server for the instances of other services
    - once these are known the services know where to send requests

  LOAD BALANCING
    - Configure for Feign to talk to Eureka and load balance requests
	- not add the url property to the @FeignClient
*/
