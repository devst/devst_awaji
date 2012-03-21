package controllers;

import java.util.EnumMap;

import play.mvc.Controller;
import features.Answer5;

public class Input5 extends Controller {

	private static final String TEMPLATE = "Input/input5.html";

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
		return new TeamFeatureRunner<Answer5>(Answer5.class) {
			@Override
			public String run(Answer5 feature) throws Exception {
				return String.valueOf(feature.toInt(args[0]));
			}
		}.run();
	}
}
