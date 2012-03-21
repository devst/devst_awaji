package controllers;

import java.util.EnumMap;

import play.mvc.Controller;
import features.Answer4;

public class Input4 extends Controller {

	private static final String TEMPLATE = "Input/input4.html";

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
		String param3 = params.get("param3");
		String param4 = params.get("param4");
		String param5 = params.get("param5");

		try {
			EnumMap<Team, String> resultMap = execute(param1, param2, param3, param4, param5);
			renderTemplate(TEMPLATE, param1, param2, param3, param4, param5, resultMap);
		} catch (Exception e) {
			// チームのメソッドに渡す前にエラーだったら全体メッセージ表示
			String message = e.getMessage();
			renderTemplate(TEMPLATE, param1, param2, param3, param4, param5, message);
		}
	}

	/**
	 * 全チームの実装クラスを実行する。
	 * 
	 * @param args パラメータ
	 * @return 各チームの出力を値とするEnumMap
	 */
	protected static EnumMap<Team, String> execute(String... args) {
		return new TeamFeatureRunner<Answer4>(Answer4.class) {
			@Override
			public String run(Answer4 feature, String... args) throws Exception {
				return feature.poker(args);
			}
		}.run(args);
	}
}
