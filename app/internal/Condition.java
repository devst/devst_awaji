package internal;

import controllers.ScoreDetail;

public abstract class Condition {

	public abstract boolean getStatus();

	public static Condition instance(final ScoreDetail detail) {
		return new Condition() {
			@Override
			public boolean getStatus() {
				return detail.hasInstance;
			}
		};
	}

	public static Condition perfect(final ScoreDetail detail) {
		return new Condition() {
			@Override
			public boolean getStatus() {
				return detail.hasInstance && detail.total > 0 && detail.failure == 0;
			}
		};
	}

	public static Condition successCount(final ScoreDetail detail, final int count) {
		return new Condition() {
			@Override
			public boolean getStatus() {
				return detail.hasInstance && detail.getSuccess() >= count;
			}
		};
	}
}
