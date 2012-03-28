package controllers;

import internal.TeamFeatureRunner;

import java.util.EnumMap;

import play.mvc.Controller;

public class FizzBuzz extends Controller {

	private static final String TEMPLATE = "Input/fizzbuzz.html";

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
		final int i = Integer.valueOf(args[0]);
		return new TeamFeatureRunner<features.FizzBuzz>(features.FizzBuzz.class) {
			@Override
			public String run(features.FizzBuzz feature) throws Exception {
				return feature.fizzBuzz(i);
			}
		}.run();
	}
}
