package com.ssm.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class CustomerException implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj,
			Exception e) {
		e.printStackTrace();
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("msg", e.getMessage());
		modelAndView.setViewName("error");
		return modelAndView;
	}

}
