package controllers;


import models.Question1FunctionTest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import play.mvc.Controller;

public class Application extends Controller {

    public static void index() {
        render();
    }
    
    public static String test(Class<?>... test) {
    	Result result = JUnitCore.runClasses(test);
    	if (0 == result.getIgnoreCount() && 0 == result.getFailureCount() ) {
			return "success";
		} else {
			return "error";
		}
    }
}