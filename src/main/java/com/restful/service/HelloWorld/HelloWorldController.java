package com.restful.service.HelloWorld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;
	
@RequestMapping(value = "/hello-world", method = RequestMethod.GET)	
public String getHelloWorld()
{
	return "Hello World";
}


@GetMapping(path = "/hello-world-bean")
public HelloWorldBean getHelloWorldBean()
{
	return new HelloWorldBean("Hello World");
}


@RequestMapping(value = "/hello-world/path_variable/{name}", method = RequestMethod.GET)	
public HelloWorldBean getHelloWorldBeanPath(@PathVariable String name)
{
	return new HelloWorldBean(String.format("Hello World %s", name));
}

@GetMapping(value = "/hello-world/internationalized")
public String getInternationalized()
{
	return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	//instead of passing in the method using @RequestHeader
}
}
