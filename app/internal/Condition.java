package internal;


public abstract class Condition {

	public abstract boolean getStatus();

	public static Condition instance(final ScoreDetail detail) {
		return new Condition() {
			@Override
			public boolean getStatus() {
				return detail != ScoreDetail.NONE;
			}
		};
	}

	public static Condition perfect(final ScoreDetail detail) {
		return new Condition() {
			@Override
			public boolean getStatus() {
				return detail.isSuccess();
			}
		};
	}

	public static Condition successCount(final ScoreDetail detail, final int count) {
		return new Condition() {
			@Override
			public boolean getStatus() {
				return detail.getTotal() - detail.getFailure() >= count;
			}
		};
	}
}
