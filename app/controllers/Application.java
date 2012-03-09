package controllers;

import java.lang.reflect.Field;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

import play.mvc.Controller;

public class Application extends Controller {

	public static void index() {
		render();
	}

	public static String test(Class<?> test, final Class<?> answer) {
		Result result;
		try {
			result = new JUnitCore().run(new BlockJUnit4ClassRunner(test) {
				@Override
				protected Object createTest() throws Exception {
					Object obj = super.createTest();
					Field field = getTestClass().getJavaClass().getDeclaredField("sut");
					field.setAccessible(true);
					field.set(obj, answer.newInstance());
					return obj;
				}
			});
			if (0 == result.getIgnoreCount() && 0 == result.getFailureCount()) {
				return "success";
			} else {
				return "error";
			}
		} catch (InitializationError e) {
			e.printStackTrace();
			return "error";
		}
	}
}

