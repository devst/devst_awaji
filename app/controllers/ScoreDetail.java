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
}
