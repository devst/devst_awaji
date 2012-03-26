package controllers;

import java.util.List;

import org.junit.runner.notification.Failure;

public class ScoreDetail {

	public ScoreDetail(boolean hasInstance) {
		this.hasInstance = hasInstance;
	}
	public final boolean hasInstance;
	public int total;
	public int failure;
	public List<Failure> failures;
	public List<String> testHeaders;
	public List<Boolean> isSuccess;

	public int getSuccess() {
		return total - failure;
	}

	public String getResult() {
		if (!hasInstance) {
			return "notyet";
		}
		return failure == 0 ? "success": "error";
	}

	public String getProgress() {
		return hasInstance && failure > 0 ? String.format("%d / %d", getSuccess(), total) : "success";
	}

	public static ScoreDetail instance(int total, int failure, List<Failure> failures) {
		ScoreDetail detail = new ScoreDetail(true);
		detail.total = total;
		detail.failure = failure;
		detail.failures = failures;
		return detail;
	}
}
