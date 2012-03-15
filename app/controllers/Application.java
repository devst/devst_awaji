package controllers;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Question1;
import models.Question2;
import models.Question3;
import models.Question4;
import models.Question5;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

import play.mvc.Controller;

public class Application extends Controller {

	public static void index() {
		render();
	}

	// TODO judgeman.jar から取得するようにする
	public static Class<?>[] judgemans = { Question1.class, Question2.class,
			Question3.class, Question4.class, Question5.class };

	// TODO judgemans から組立てる
	public static Map<String, List<Class<?>>> getFeatureMap() {
		Map<String, List<Class<?>>> map = new HashMap<String, List<Class<?>>>();
		map.put("えーちーむ", Arrays.asList(answers.a.Answer1.class, answers.a.Answer2.class, null, null));
		map.put("ビィチィム", Arrays.asList(answers.a.Answer1.class, answers.a.Answer2.class, null, null));
		return map;
	}

	public static String test(Class<?> test, final Class<?> answer) {
		Result result;
		try {
			result = new JUnitCore().run(new BlockJUnit4ClassRunner(test) {
				@Override
				protected Object createTest() throws Exception {
					Object obj = super.createTest();
					Field field = getTestClass().getJavaClass()
							.getDeclaredField("sut");
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
