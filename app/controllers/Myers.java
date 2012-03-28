package controllers;

import internal.TeamFeatureRunner;

import java.util.EnumMap;

import play.mvc.Controller;

public class Myers extends Controller {

	private static final String TEMPLATE = "Input/myers.html";

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

		try {
			EnumMap<Team, String> resultMap = execute(param1, param2, param3);
			renderTemplate(TEMPLATE, param1, param2, param3, resultMap);
		} catch (Exception e) {
			// チームのメソッドに渡す前にエラーだったら全体メッセージ表示
			String message = e.getMessage();
			renderTemplate(TEMPLATE, param1, param2, param3, message);
		}
	}

	/**
	 * 全チームの実装クラスを実行する。
	 * 
	 * @param args パラメータ
	 * @return 各チームの出力を値とするEnumMap
	 */
	protected static EnumMap<Team, String> execute(String... args) {
		final int x = Integer.valueOf(args[0]);
		final int y = Integer.valueOf(args[1]);
		final int z = Integer.valueOf(args[2]);
		return new TeamFeatureRunner<features.Myers>(features.Myers.class) {
			@Override
			public String run(features.Myers feature) throws Exception {
				return feature.getName(x, y, z);
			}
		}.run();
	}

}
