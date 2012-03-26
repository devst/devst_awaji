package internal;

public class BingoCell {

	private final Condition[] conditions;

	public BingoCell(Condition... conditions) {
		this.conditions = conditions;
	}

	public boolean judge() {
		for (Condition element : conditions) {
			if (element.getStatus()) {
				return true;
			}
		}
		return false;
	}
}
