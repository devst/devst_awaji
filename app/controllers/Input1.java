package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import play.mvc.Controller;

public class Input1 extends Controller {

	/**
	 * 初期表示
	 */
	public static void show() {
		renderTemplate("Input/input1.html");
	}

	/**
	 * 各Answer実装クラスのメソッド実行
	 */
	public static void perform() {
		String param1 = params.get("param1");
		String param2 = params.get("param2");
		Map<String, String> resultMap = new HashMap<String, String>();
		String message = "";

		try {
			int intParam1 = Integer.parseInt(param1);
			int intParam2 = Integer.parseInt(param2);

			Map<String, List<Class<?>>> featureMap = Application.getFeatureMap();

			for (Map.Entry<String, List<Class<?>>>  feature : featureMap.entrySet()) {
				List<Class<?>> answerList = feature.getValue();
				if (answerList.get(0) != null) {
					try{
						answers.Answer1 answer = (answers.Answer1)answerList.get(0).newInstance();
						resultMap.put(feature.getKey(), Integer.toString(answer.plus(intParam1, intParam2)));
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

		renderTemplate("Input/input1.html", param1, param2, resultMap, message);
	}

}
