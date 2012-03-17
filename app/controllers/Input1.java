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

import answers.a.Answer1;

import play.mvc.Controller;
import play.mvc.Http.Request;

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
						resultMap.put(feature.getKey(), "【エラー】"  + e.getMessage());
					}
				} else {
					resultMap.put(feature.getKey(), "");
				}
			}
		} catch (Exception e) {
			// チームのメソッドに渡す前にエラーだったら全体メッセージ表示
			message = "【エラー】" + e.getMessage();
		}

		renderTemplate("Input/input1.html", param1, param2, resultMap, message);
	}

}
