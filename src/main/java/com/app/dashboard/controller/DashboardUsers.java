package com.app.dashboard.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardUsers {
	@RequestMapping("/dashboardusers")
    public ModelAndView generateReportMateri(ModelAndView m, 
            @RequestParam(value = "format", required = false) String format){
        
        m.setViewName("dashboard/dashboardusers");
        return m;
    }
}
