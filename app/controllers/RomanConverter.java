package controllers;

import java.util.EnumMap;

import play.mvc.Controller;

public class RomanConverter extends Controller {

	private static final String TEMPLATE = "Input/romanconverter.html";

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
	protected static EnumMap<Team, String> execute(final String... args) {
		return new TeamFeatureRunner<features.RomanConverter>(
				features.RomanConverter.class) {
			@Override
			public String run(features.RomanConverter feature) throws Exception {
				return String.valueOf(feature.toArabic(args[0]));
			}
		}.run();
	}
}
