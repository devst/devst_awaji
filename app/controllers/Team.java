package controllers;

public enum Team {

	A("さるさん", "features.a"),
	B("かめさん", "features.b"),
	J("うさぎさん", "features.kanjava"),
	C("きりんさん", "features.giraffe"),
	D("ぞうさん", "features.elephant"),
	E("らいおんさん", "features.b"),
	F("とらさん", "features.b"),
	G("ひつじさん", "features.b"),
	H("いぬさん", "features.b"),
	I("ねずみさん", "features.b"),
	K("うしさん", "features.b"),
	L("へびさん", "features.b"),
	M("うまさん", "features.b"),
	N("とりさん", "features.b"),
	O("いのししさん", "features.b"),
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
