package controllers;

import internal.TeamFeatureRunner;

import java.util.EnumMap;

import play.mvc.Controller;

public class FirstPairWork extends Controller {

	private static final String TEMPLATE = "Input/firstpairwork.html";

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
		try {
			EnumMap<Team, String> resultMap = execute();
			renderTemplate(TEMPLATE, resultMap);
		} catch (Exception e) {
			// チームのメソッドに渡す前にエラーだったら全体メッセージ表示
			String message = e.getMessage();
			renderTemplate(TEMPLATE, message);
		}
	}

	/**
	 * 全チームの実装クラスを実行する。
	 *
	 * @param args パラメータ
	 * @return 各チームの出力を値とするEnumMap
	 */
	protected static EnumMap<Team, String> execute(String... args) {
		return new TeamFeatureRunner<features.FirstPairWork>(features.FirstPairWork.class) {
			@Override
			public String run(features.FirstPairWork feature) throws Exception {
				return feature.getMembers() + "　『" + feature.getIkigomi() + "』";
			}
		}.run();
	}
}
