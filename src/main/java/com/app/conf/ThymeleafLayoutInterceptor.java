package com.app.conf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ThymeleafLayoutInterceptor extends HandlerInterceptorAdapter {
	
	 private static final String DEFAULT_LAYOUT = "dashboardlayout";
	    private static final String DEFAULT_VIEW_ATTRIBUTE_NAME = "view";
	 
	    @Override
	    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	        
	    	Boolean allowed = (Boolean)request.getAttribute("allowed");
	    	if (modelAndView == null || !modelAndView.hasView()) {
	            return;
	        }

	    	String originalViewName = modelAndView.getViewName();
	    	if(allowed) {
	    		if(originalViewName.startsWith("redirect:") || originalViewName.startsWith("report")){
		            return;
		        }
		        modelAndView.setViewName(DEFAULT_LAYOUT);
		        modelAndView.addObject(DEFAULT_VIEW_ATTRIBUTE_NAME, originalViewName);
		    	
	    	}else {
	    		modelAndView.setViewName(originalViewName);
	    	}
	    	
	    }

		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
			// TODO Auto-generated method stub
			System.out.println("request URI:"+request.getRequestURI());
			request.setAttribute("allowed",new Boolean(true));
			
			if(request.getRequestURI().contains("login")) {
				request.setAttribute("allowed",new Boolean(false));
						
			}
			return super.preHandle(request, response, handler);
		}    
	    
	    
}
