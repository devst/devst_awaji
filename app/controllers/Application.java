package controllers;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import play.mvc.Controller;
import features.Index;
import features.Index.Feature;

public class Application extends Controller {

	/**
	 * スコアボード表示
	 */
	public static void index() {
		Map<Team, Map<Feature, ScoreDetail>> resultMap = createResultMap();
		Object features = Index.getList();
		render(resultMap, features);
	}

	protected static Map<Team, Map<Feature, ScoreDetail>> createResultMap() {

		Map<Team, Map<Feature, ScoreDetail>> resultMap = new EnumMap<Team, Map<Feature, ScoreDetail>>(Team.class);
		for (Team team : Team.values()) {
			resultMap.put(team, ScoreKeeper.getTeamScore(team));
		}
		return resultMap;
	}

}
