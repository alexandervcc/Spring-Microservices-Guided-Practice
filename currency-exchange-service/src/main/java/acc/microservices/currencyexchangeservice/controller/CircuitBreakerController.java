package acc.microservices.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {
	
	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	
	@GetMapping("/sample-api")
	//@Retry(name = "sample-api", fallbackMethod = "harcodedResponse")
	//@CircuitBreaker(name = "default", fallbackMethod = "harcodedResponse")
	//@RateLimiter(name = "default")
	@Bulkhead(name = "default")
	public String sampleAPI() {
		logger.info("Sample API call received");
		ResponseEntity<String> forEntiry =	new RestTemplate().getForEntity(
				"http://localhost:8080/xd", String.class);
		return forEntiry.getBody();
	}
	
	public String harcodedResponse(Exception ex) {
		return "fallback-response";
	}
}

/*
  - To add cirbuit breaker use a RestController
    and add the annotation: @Retry: to retries the execution when there
    is an exception, is 3 times as default

  - you can also configure the fallback method for the retries
    when all the retries happen but it still was not successful

  - the annotation: @CircuitBreaker
    use the same @Retry parameters
    
    the method of the route is not fired, instead the fallback fire inmediatly
    so it is not necessary to call the function of the endpoint
    configuration and manage as a 'finite state machine'
    	- https://resilience4j.readme.io/docs/circuitbreaker
	
  - @RateLimiter: 10s => 10000 calls to the sample api
    - this can be confiured as a property
    - limits the quantity of request in a period of time that can be handle
    
  - @BulkHead: 
    - configure the max of concurrent calls
    
    
*/