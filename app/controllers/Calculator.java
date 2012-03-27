package controllers;

import java.util.EnumMap;

import play.mvc.Controller;

public class Calculator extends Controller {

	private static final String TEMPLATE = "Input/calculator.html";

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

		try {
			EnumMap<Team, String> resultMap = execute(param1);
			renderTemplate(TEMPLATE, param1, resultMap);
		} catch (Exception e) {
			// チームのメソッドに渡す前にエラーだったら全体メッセージ表示
			String message = e.getMessage();
			renderTemplate(TEMPLATE, param1, message);
		}
	}

	/**
	 * 全チームの実装クラスを実行する。
	 * 
	 * @param args パラメータ
	 * @return 各チームの出力を値とするEnumMap
	 */
	protected static EnumMap<Team, String> execute(String... args) {
		final String formula = args[0];

		return new TeamFeatureRunner<features.Calculator>(features.Calculator.class) {
			@Override
			public String run(features.Calculator feature) throws Exception {
				return feature.execute(formula);
			}
		}.run();
	}
}
