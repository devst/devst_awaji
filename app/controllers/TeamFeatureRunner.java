package controllers;

import java.util.EnumMap;

/**
 * 全チームのfeatureを実行する抽象クラス。
 * @param <T> 実行するfeature
 */
abstract class TeamFeatureRunner<T> {

	/**
	 * インタフェースの型
	 */
	private final Class<T> featureInterface;

	/**
	 * コンストラクタ
	 * @param featureInterface インタフェースの型
	 */
	TeamFeatureRunner(Class<T> featureInterface) {
		this.featureInterface = featureInterface;
	}

	/**
	 * 全てのチームに対し、引数のパラメータで実行します。
	 * @param params パラメータ
	 * @return 各チームの出力を値とするEnumMap
	 */
	EnumMap<Team, String> run(String... params) {
		EnumMap<Team, String> resultMap = new EnumMap<Team, String>(Team.class);

		for (Team team : Team.values()) {
			String result;
			Class<T> featureClass = Feature.getFeature(team, featureInterface);
			if (featureClass == null) {
				result = "";
			} else {
				try {
					result = run(featureClass, params);
				} catch (Exception e) {
					result = e.getMessage();
				}
			}
			resultMap.put(team, result);
		}
		return resultMap;
	}

	/**
	 * インスタンス生成がデフォルトコンストラクタで出来ない場合、このメソッドをオーバーライドしてください。
	 * @param featureClass チーム毎のfeatureクラス
	 * @param params パラメータ
	 * @return 処理結果
	 * @throws Exception
	 */
	protected String run(Class<T> featureClass, String... params) throws Exception {
		return run(featureClass.newInstance(), params);
	}

	/**
	 * 各Featureの処理を実行し、処理結果を文字列で返します。
	 * @param feature チーム毎のfeatureインスタンス
	 * @param params パラメータ
	 * @return 処理結果
	 * @throws Exception
	 */
	protected abstract String run(T feature, String... params) throws Exception;
}