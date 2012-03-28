package internal;

import java.util.Collections;
import java.util.List;

import org.junit.runner.notification.Failure;

public class ScoreDetail {

	public static final ScoreDetail NONE = new ScoreDetail(0, 0, null);

	private ScoreDetail(int total, int failure, List<Failure> failures) {
		this.total = total;
		this.failure = failure;
		this.failures = failures;
	}

	private final int total;
	private final int failure;
	private final List<Failure> failures;

	public int getFailure() {
		return failure;
	}

	public List<Failure> getFailures() {
		return Collections.unmodifiableList(failures);
	}

	public int getTotal() {
		return total;
	}

	public boolean isSuccess() {
		return total > 0 && failure == 0;
	}

	public static ScoreDetail instance(int total, int failure, List<Failure> failures) {
		return new ScoreDetail(total, failure, failures);
	}

}
