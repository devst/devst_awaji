package controllers;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

import play.mvc.Controller;
import features.Index;
import features.Index.Entry;

public class Application extends Controller {

	/**
	 * スコアボードに表示するインタフェースのリスト
	 */
	private static List<Entry> index = Index.getList();

	/**
	 * スコアボード表示
	 */
	public static void index() {
		Map<String, Map<String, String>> resultMap = createResultMap();
		render(resultMap);
	}

	protected static Map<String, Map<String, String>> createResultMap() {

		Map<String, Map<String, String>> resultMap = new LinkedHashMap<String, Map<String, String>>();
		for (Team team : Team.values()) {
			Feature feature = new Feature(team);
			Map<String, String> scoreMap = new LinkedHashMap<String, String>();

			for (Entry e : index) {
				String testResult = test(e.judgeman, feature.getFeature(e.feature));
				scoreMap.put(e.name, testResult);
			}
			resultMap.put(team.teamName, scoreMap);
		}
		return resultMap;
	}

	/**
	 * 各チームのFeature実装クラスのマップを返す
	 * 
	 * @return <チーム名, Feature実装クラスリスト>のマップ
	 */
	@Deprecated
	public static Map<String, List<Class<?>>> getFeatureMap() {
		Map<String, List<Class<?>>> map = new HashMap<String, List<Class<?>>>();
		map.put("えーちーむ", createTeamFeatureList("a"));
		map.put("ビィチィム", createTeamFeatureList("b"));
		return map;
	}

	/**
	 * チーム毎のFeature実装クラスのリストを取得する
	 * 
	 * @param team
	 *            パッケージ名(チーム毎に固定)
	 * @return クラスのリスト
	 */
	@Deprecated
	private static List<Class<? extends Object>> createTeamFeatureList(String team) {

		List<Class<?>> list = new ArrayList<Class<?>>();
		for (Entry d : index) {
			try {
				// 実装済み
				list.add(Class.forName("features." + team + "." + d.feature.getSimpleName()));
			} catch (ClassNotFoundException e) {
				// 未実装
				list.add(null);
			}
		}
		return list;
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
