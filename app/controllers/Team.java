package controllers;

public enum Team {

	A("Team-A", "features.a"),
	B("Team-B", "features.b"),
	C("3", "features.b"),
	D("4", "features.b"),
	E("5", "features.b"),
	F("6", "features.b"),
	G("7", "features.b"),
	H("8", "features.b"),
	I("9", "features.b"),
	J("かんじゃば", "features.kanjava"),
	;

	private static final Class<?> DUMMY = null;

	final String teamName;
	final String pkg;

	Team(String teamName, String pkg) {
		this.teamName = teamName;
		this.pkg = pkg;
	}

	@Override
	public String toString() {
		return teamName;
	}

	/**
	 * Featureのインタフェースに対応した、チーム毎の実装クラスを返します。
	 * @param featureInterface Featureインタフェースの型
	 * @return 実装クラスの型
	 */
	public <T> Class<T> getFeature(Class<T> featureInterface) {
		try {
			return (Class<T>) Class.forName(this.pkg + "." + featureInterface.getSimpleName());
		} catch (ClassNotFoundException e) {
			return (Class<T>) DUMMY;
		}
	}
}
