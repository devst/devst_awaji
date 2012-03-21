package controllers;

import java.util.EnumMap;

import play.mvc.Controller;
import features.Answer1;

public class Input1 extends Controller {

	private static final String TEMPLATE = "Input/input1.html";

	/**
	 * 初期表示
	 */
	public static void show() {
		renderTemplate(TEMPLATE);
	}

	/**
	 * 各Answer実装クラスのメソッド実行
	 */
	public static void perform() {
		String param1 = params.get("param1");
		String param2 = params.get("param2");

		try {
			EnumMap<Team, String> resultMap = execute(param1, param2);
			renderTemplate(TEMPLATE, param1, param2, resultMap);
		} catch (Exception e) {
			// チームのメソッドに渡す前にエラーだったら全体メッセージ表示
			String message = e.getMessage();
			renderTemplate(TEMPLATE, param1, param2, message);
		}
	}

	/**
	 * 全チームの実装クラスを実行する。
	 * 
	 * @param args パラメータ
	 * @return 各チームの出力を値とするEnumMap
	 */
	protected static EnumMap<Team, String> execute(String... args) {
		return new TeamFeatureRunner<Answer1>(Answer1.class) {
			@Override
			public String run(Answer1 feature, String... args) throws Exception {
				int i = Integer.valueOf(args[0]);
				int j = Integer.valueOf(args[1]);

				int result = feature.plus(i, j);
				return String.valueOf(result);
			}
		}.run(args);
	}
}
