package controllers;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import judges.Judge;
import judges.Judge1;
import judges.Judge2;
import judges.Judge3;
import judges.Judge4;
import judges.Judge5;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

import answers.Index;

import play.mvc.Controller;

public class Application extends Controller {

	/**
	 * スコアボードに表示するインタフェースのリスト
	 */
	private static List<Class<?>> featureList = Index.getList();

	/**
	 * スコアボード表示
	 */
	public static void index() {
		Map<String, List<Class<?>>> featureMap = getFeatureMap();
		List<Class<?>> judgemen = createJudgemanList();

		Map<String, Map<String, String>> resultMap = new LinkedHashMap<String, Map<String, String>>();
		for (Map.Entry<String, List<Class<?>>> feature : featureMap.entrySet()) {
			String teamNm = feature.getKey();
			Map<String, String> scoreMap = new LinkedHashMap<String, String>();
			int questionIndex = 0;

			for (Class<?> judgeman : judgemen) {
				if (judgeman != null) {
					Judge judge = judgeman.getAnnotation(Judge.class);
					String questionNm = judge.value();
					String testResult = test(judgeman, feature.getValue().get(questionIndex));
					scoreMap.put(questionNm, testResult);
				}
				questionIndex++;
			}
			resultMap.put(teamNm, scoreMap);
		}
		render(resultMap);
	}

	/**
	 * FearureListからJudgemanのリストを作成する。
	 * @return Judgemanリスト
	 */
	private static List<Class<?>> createJudgemanList() {
		List<Class<?>> judgemen = new ArrayList<Class<?>>();
		for (Class<?> clz : featureList) {
			try {
				judgemen.add(Class.forName("judges." + clz.getSimpleName().replace("Answer", "Judge")));
			} catch (ClassNotFoundException e) {
				// 未作成のjudgeman
				judgemen.add(null);
			}
		}
		return judgemen;
	}

	/**
	 * 各チームのFeature実装クラスのマップを返す
	 *
	 * @return <チーム名, Feature実装クラスリスト>のマップ
	 */
	public static Map<String, List<Class<?>>> getFeatureMap() {
		Map<String, List<Class<?>>> map = new HashMap<String, List<Class<?>>>();
		map.put("えーちーむ", createTeamFeatureList("answers.a"));
		map.put("ビィチィム", createTeamFeatureList("answers.a"));
		return map;
	}

	/**
	 * チーム毎のFeature実装クラスのリストを取得する
	 * @param pkg パッケージ名(チーム毎に固定)
	 * @return クラスのリスト
	 */
	private static List<Class<? extends Object>> createTeamFeatureList(String pkg) {

		List<Class<?>> list = new ArrayList<Class<?>>();
		for (Class<?> clz : featureList) {
			try {
				// 実装済み
				list.add(Class.forName(pkg + "." + clz.getSimpleName()));
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
