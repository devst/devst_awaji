package controllers;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import judges.Judge;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

import play.mvc.Controller;
import features.Index;
import features.Index.Entry;

public class Application extends Controller {

	/**
	 * スコアボード表示
	 */
	public static void index() {
		Map<Team, Map<String, String>> resultMap = createResultMap();
		render(resultMap);
	}

	protected static Map<Team, Map<String, String>> createResultMap() {

		Map<Team, Map<String, String>> resultMap = new EnumMap<Team, Map<String, String>>(Team.class);
		for (Team team : Team.values()) {
			Map<String, String> scoreMap = new LinkedHashMap<String, String>();

			for (Entry e : Index.getList()) {
				String testResult = test(e.judgeman, team.getFeature(e.feature));
				scoreMap.put(e.name, testResult);
			}
			resultMap.put(team, scoreMap);
		}
		return resultMap;
	}

	/**
	 * judgemanのテストを実行する
	 */
	public static String test(Class<?> test, final Class<?> answer) {

		if (answer == null) {
			return "notyet";
		}

		Result result;
		try {
			result = new JUnitCore().run(new BlockJUnit4ClassRunner(test) {
				@Override
				protected Object createTest() throws Exception {
					Judge obj = (Judge) super.createTest();
					obj.sut = answer.newInstance();
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
