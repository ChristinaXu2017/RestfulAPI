package com.example.xu.xuCalculator;

import java.text.*;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class XuController {
	
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");	
	
    @RequestMapping("say")
    @ResponseBody
    public String sayHello() {
        return "Hello! What are you learning today?";
    }
    
    @RequestMapping("ok")
    @ResponseBody
    public String letStar() throws ParseException {
    	Calendar today = Calendar.getInstance();   	     	
    	String s = dateFormat.format(today.getTime());
    	XuCalculator init = new XuCalculator("2000-01-01", s);
        return init.toString() + "//n" + calculator(init);
    }
    
    

	
	
	//GET, POST
	@RequestMapping(value="welcome", method = RequestMethod.GET)
    public String showInput(ModelMap model ) throws ParseException {
    	Calendar today = Calendar.getInstance();   	     	
    	String s = dateFormat.format(today.getTime());
    	XuCalculator xuCalculator = new XuCalculator("2000-01-01", s);
   	
    	model.put("xuCalculator", xuCalculator);
		return "welcome";
    }
	
	//GET, POST
	@RequestMapping(value="welcome", method = RequestMethod.POST)
    public String showResult(ModelMap model, XuCalculator cal, BindingResult result) throws ParseException {
    	
    	model.put("xuCalculator", cal);
    	model.put("result", calculator(cal));
    	
		return "welcome";
    }

	
	
    @RequestMapping("say-hello")
      public String sayHelloJsp() {
       // return "WEB-INF/jsp/sayHello";
       return "sayHello";
    }

    
    
	private String calculator(XuCalculator cal) throws ParseException {		
		Date start = XuController.dateFormat.parse(cal.getStartDate());		
		Date end = XuController.dateFormat.parse(cal.getEndDate());
		long diffInMillies = Math.abs(end.getTime() - start.getTime());
	    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

		return String.format("The gap between %s and %s is %d days", cal.getStartDate(), cal.getEndDate(), diff);
	}

}
