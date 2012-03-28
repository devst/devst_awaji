package controllers;

public enum Team {

	MONKEY("さるさん"),
	TURTLE("かめさん"),
	RABBIT("うさぎさん"),
	GIRAFFE("きりんさん"),
	ELEPHANT("ぞうさん"),
	PENGUIN("ぺんぎんさん"),
	TIGER("とらさん"),
	SHEEP("ひつじさん"),
	DOG("いぬさん"),
	MOUSE("ねずみさん"),
	COW("うしさん"),
	SNAKE("へびさん"),
	HORSE("うまさん"),
	BIRD("とりさん"),
	WILDBOAR("いのししさん"), ;

	final String teamName;

	Team(String teamName) {
		this.teamName = teamName;
	}

	@Override
	public String toString() {
		return teamName;
	}

	/**
	 * Featureのインタフェースに対応した、チーム毎の実装クラスを返します。
	 * @param clz Featureインタフェースの型
	 * @return 実装クラスの型
	 */
	public <T> Class<T> getFeature(Class<T> clz) {
		try {
			String className = "features." + name().toLowerCase() + "." + clz.getSimpleName();
			return (Class<T>) Class.forName(className);
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
}
