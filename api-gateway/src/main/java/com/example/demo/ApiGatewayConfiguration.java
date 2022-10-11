package com.example.demo;

import java.util.function.Function;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		Function<PredicateSpec,Buildable<Route>> routeFunctionExample=
			p -> p.path("/get")
				.filters(f -> f
					.addRequestHeader("apiGatewayHeader", "Bearer XD")
					.addRequestParameter("apiGatewayParam", "xd%20xd")
				)
				.uri("http://httpbin.org:80")
			
		;
		
		return builder.routes()
			.route(routeFunctionExample)	
			.route(p -> p
					.path("/currency-exchange/**")
					.uri("lb://currency-exchange")
			)
			.route(p -> p
					.path("/currency-conversion/**")
					.uri("lb://currency-conversion")
			)
			.route(p -> p
					.path("/currency-conversion-feign/**")
					.uri("lb://currency-conversion")
			)
			.route(p -> p
					.path("/currency-conversion-new/**")
					.filters(f -> f.rewritePath(
						"/currency-conversion-new/(?<segment>.*)", 
						"/currency-conversion-feign/${segment}")
					)
					.uri("lb://currency-conversion")
			)
			
			.build();
	}
}
/*
  - Create custom URLs
  - inside route() the URLs are added
  	- inside it a lambda will be created
  	- when a req comes to path() it will be redirected to uri()
  	- you can add query params, headers to the request when this is passed

  - to add the load balancer to request, when redirecting with uri()
    add 'lb://' before the URL for redirection
    - when adding this, disable the property for discorey of the gateway

  - if you wanna convert and input URL of the gateway into an output
    to the the services use .filter(), and use regex to extract and inject params
*/
