package internal;

import java.util.EnumMap;

import controllers.Team;

/**
 * 全チームのfeatureを実行する抽象クラス。
 * @param <T> 実行するfeature
 */
public abstract class TeamFeatureRunner<T> {

	/**
	 * インタフェースの型
	 */
	private final Class<T> featureInterface;

	/**
	 * コンストラクタ
	 * @param featureInterface インタフェースの型
	 */
	public TeamFeatureRunner(Class<T> featureInterface) {
		this.featureInterface = featureInterface;
	}

	/**
	 * 全てのチームに対し、引数のパラメータで実行します。
	 * @param params パラメータ
	 * @return 各チームの出力を値とするEnumMap
	 */
	public EnumMap<Team, String> run() {
		EnumMap<Team, String> resultMap = new EnumMap<Team, String>(Team.class);

		for (Team team : Team.values()) {
			String result;
			Class<T> featureClass = team.getFeature(featureInterface);
			if (featureClass == null) {
				continue;
			}
			try {
				result = run(featureClass);
			} catch (Exception e) {
				result = e.toString();
			}
			resultMap.put(team, result);
		}
		return resultMap;
	}

	/**
	 * インスタンス生成がデフォルトコンストラクタで出来ない場合、このメソッドをオーバーライドしてください。
	 * @param featureClass チーム毎のfeatureクラス
	 * @return 処理結果
	 * @throws Exception
	 */
	protected String run(Class<T> featureClass) throws Exception {
		return run(featureClass.newInstance());
	}

	/**
	 * 各Featureの処理を実行し、処理結果を文字列で返します。
	 * @param feature チーム毎のfeatureインスタンス
	 * @return 処理結果
	 * @throws Exception
	 */
	protected abstract String run(T feature) throws Exception;
}