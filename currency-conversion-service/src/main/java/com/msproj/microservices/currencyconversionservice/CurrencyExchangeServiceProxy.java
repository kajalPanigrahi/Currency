
package com.msproj.microservices.currencyconversionservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "currency-exchange-service", fallback = CurrencyExchangeServiceProxy_Fallback.class)
@RibbonClient(name="currency-exchange-service") 
public interface CurrencyExchangeServiceProxy {

	
	@RequestMapping(value = "/currency-exchange/currency/{currency}", method = RequestMethod.GET)
	public CurrencyExchangeBean retrieveExchangeValue(@PathVariable("currency") String currency);



}
