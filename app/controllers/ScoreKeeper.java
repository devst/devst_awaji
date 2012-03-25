package controllers;

import java.util.LinkedHashMap;
import java.util.Map;

import judges.Judge;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

import features.Feature;


public class ScoreKeeper {

	public static Map<Feature, ScoreDetail> getTeamScore(Team team) {
		Map<Feature, ScoreDetail> scoreMap = new LinkedHashMap<Feature, ScoreDetail>();
	
		for (Feature feature : Feature.values()) {
			ScoreDetail testResult = test(feature.judge, team.getFeature(feature.feature));
			scoreMap.put(feature, testResult);
		}
		return scoreMap;
	}

	/**
	 * judgemanのテストを実行する
	 */
	private static ScoreDetail test(Class<?> test, final Class<?> answer) {

		if (answer == null) {
			ScoreDetail result = new ScoreDetail(false);
			return result;
		}

		ScoreDetail detail = new ScoreDetail(true);
		try {
			Result result = new JUnitCore().run(new BlockJUnit4ClassRunner(test) {
				@Override
				protected Object createTest() throws Exception {
					Judge obj = (Judge) super.createTest();
					obj.sut = answer.newInstance();
					return obj;
				}
			});
			
			detail.total = result.getRunCount();
			detail.failure = result.getFailureCount();
			detail.failures = result.getFailures();
		} catch (InitializationError e) {
			e.printStackTrace();
		}
		return detail;
	}
}
