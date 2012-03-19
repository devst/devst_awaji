package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import play.mvc.Controller;

public class Input4 extends Controller {

	/**
	 * 初期表示
	 */
	public static void show() {
		renderTemplate("Input/input4.html");
	}

	/**
	 * 各Answer実装クラスのメソッド実行
	 */
	public static void perform() {
		String param1 = params.get("param1");
		String param2 = params.get("param2");
		String param3 = params.get("param3");
		String param4 = params.get("param4");
		String param5 = params.get("param5");
		Map<String, String> resultMap = new HashMap<String, String>();
		String message = "";

		try {

			String params[] = {param1, param2, param3, param4, param5};
			Map<String, List<Class<?>>> featureMap = Application.getFeatureMap();

			for (Map.Entry<String, List<Class<?>>>  feature : featureMap.entrySet()) {
				List<Class<?>> answerList = feature.getValue();
				if (answerList.get(0) != null) {
					try{
						answers.Answer4 answer = (answers.Answer4)answerList.get(3).newInstance();
						resultMap.put(feature.getKey(), answer.poker(params));
					} catch (Exception e) {
						// チーム別の例外表示
						resultMap.put(feature.getKey(), e.getMessage());
					}
				} else {
					resultMap.put(feature.getKey(), "");
				}
			}
		} catch (Exception e) {
			// チームのメソッドに渡す前にエラーだったら全体メッセージ表示
			message = e.getMessage();
		}

		renderTemplate("Input/input4.html", param1, param2, param3, param4, param5, resultMap, message);
	}

}
