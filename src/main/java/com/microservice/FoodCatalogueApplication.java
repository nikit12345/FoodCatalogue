package com.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.blocking.XForwardedHeadersTransformer;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication @EnableDiscoveryClient
public class FoodCatalogueApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodCatalogueApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	@Bean
	@ConditionalOnMissingBean(XForwardedHeadersTransformer.class)
	@ConditionalOnBean(LoadBalancerClientFactory.class)
	public XForwardedHeadersTransformer xForwarderHeadersTransformer(
			LoadBalancerClientFactory loadBalancerClientFactory) {
		return new XForwardedHeadersTransformer(loadBalancerClientFactory);
	}
}
