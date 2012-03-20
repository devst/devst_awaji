package controllers;


public class Feature {

	private static final Class<?> DUMMY = null;
	private final Team team;

	/**
	 * コンストラクタ
	 * @param team チーム
	 */
	public Feature(Team team) {
		this.team = team;
	}

	/**
	 * Featureのインタフェースに対応した、チーム毎の実装クラスを返します。
	 * @param featureInterface Featureインタフェースの型
	 * @return 実装クラスの型
	 */
	public Class<?> getFeature(Class<?> featureInterface) {
		try {
			return Class.forName(team.pkg + "." + featureInterface.getSimpleName());
		} catch (ClassNotFoundException e) {
			return DUMMY;
		}
	}

	/**
	 * チームとFeatureのインタフェースに対応した、実装クラスを返します。
	 * @param team チーム
	 * @param featureInterface Featureインタフェースの型
	 * @return 実装クラスの型
	 */
	public static Class<?> getFeature(Team team, Class<?> clz) {
		return new Feature(team).getFeature(clz);
	}

	
}
