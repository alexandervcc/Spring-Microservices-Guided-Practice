package acc.microservices.limitsservice.controller;

import org.springframework.web.bind.annotation.RestController;

import acc.microservices.limitsservice.components.Limits;
import acc.microservices.limitsservice.config.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class LimitsController {
    @Autowired
    Configuration configuration;

    @GetMapping(value = "/limits")
    public Limits retrieveLimits() {
        // return new Limits(1,1000);
        return new Limits(configuration.getMin(), configuration.getMax());
    }

}
