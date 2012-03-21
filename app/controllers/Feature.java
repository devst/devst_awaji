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
	public <T> Class<T> getFeature(Class<T> featureInterface) {
		try {
			return (Class<T>) Class.forName(team.pkg + "." + featureInterface.getSimpleName());
		} catch (ClassNotFoundException e) {
			return (Class<T>) DUMMY;
		}
	}

	/**
	 * チームとFeatureのインタフェースに対応した、実装クラスを返します。
	 * @param team チーム
	 * @param featureInterface Featureインタフェースの型
	 * @return 実装クラスの型
	 */
	public static <T> Class<T> getFeature(Team team, Class<T> featureInterface) {
		return new Feature(team).getFeature(featureInterface);
	}

	
}
