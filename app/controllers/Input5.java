package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import play.mvc.Controller;

public class Input5 extends Controller {

	/**
	 * 初期表示
	 */
	public static void show() {
		renderTemplate("Input/input5.html");
	}

	/**
	 * 各Answer実装クラスのメソッド実行
	 */
	public static void perform() {
		String param1 = params.get("param1");
		Map<String, String> resultMap = new HashMap<String, String>();
		String message = "";

		try {

			Map<String, List<Class<?>>> featureMap = Application.getFeatureMap();

			for (Map.Entry<String, List<Class<?>>>  feature : featureMap.entrySet()) {
				List<Class<?>> answerList = feature.getValue();
				if (answerList.get(0) != null) {
					try{
						answers.Answer5 answer = (answers.Answer5)answerList.get(4).newInstance();
						resultMap.put(feature.getKey(), String.valueOf(answer.toInt(param1)));
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

		renderTemplate("Input/input5.html", param1, resultMap, message);
	}

}
