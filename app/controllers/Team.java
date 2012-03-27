package controllers;

public enum Team {

	A("さるさん", "features.monkey"),
	B("かめさん", "features.turtle"),
	J("うさぎさん", "features.rabbit"),
	C("きりんさん", "features.giraffe"),
	D("ぞうさん", "features.elephant"),
	E("ぺんぎんさん", "features.penguin"),
	F("とらさん", "features.tiger"),
	G("ひつじさん", "features.sheep"),
	H("いぬさん", "features.dog"),
	I("ねずみさん", "features.mouse"),
	K("うしさん", "features.cow"),
	L("へびさん", "features.snake"),
	M("うまさん", "features.horse"),
	N("とりさん", "features.bird"),
	O("いのししさん", "features.wildboar"),
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
