package controllers;

import java.util.EnumMap;
import java.util.Map;

import play.mvc.Controller;
import features.Feature;

public class Application extends Controller {

	/**
	 * スコアボード表示
	 */
	public static void index() {
		Map<Team, Map<Feature, ScoreDetail>> resultMap = createResultMap();
		render(resultMap);
	}

	protected static Map<Team, Map<Feature, ScoreDetail>> createResultMap() {

		Map<Team, Map<Feature, ScoreDetail>> resultMap = new EnumMap<Team, Map<Feature, ScoreDetail>>(Team.class);
		for (Team team : Team.values()) {
			resultMap.put(team, ScoreKeeper.getTeamScore(team));
		}
		return resultMap;
	}

}
