package tags;

import features.Feature;
import groovy.lang.Closure;

import internal.BingoCell;
import internal.Condition;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.runner.notification.Failure;

import play.templates.FastTags;
import play.templates.GroovyTemplate.ExecutableTemplate;
import controllers.ScoreDetail;
import controllers.Team;

@FastTags.Namespace("devst")
public class DevstTag extends FastTags {

	public static void _badge(Map<?, ?> args, Closure body, PrintWriter out, ExecutableTemplate template, int fromLine) {
		if (args.get("name") != null) {
			ScoreDetail detail = (ScoreDetail) args.get("name");
			if (detail.hasInstance) {
				String result = detail.getResult();
				String content = getContent(detail);
				out.printf("<span class='badge badge-%s' rel='popover' data-original-title='%s' data-content='%s'>",
						result, result, content);
				out.print(detail.getProgress());
				out.print("</span>");
			} else {
				out.printf("<span class='badge'>none</span>");
			}
		}
	}

	private static String getContent(ScoreDetail detail) {
		if (detail.failure == 0) {
			return "Congratulations!";
		}

		StringBuilder sb = new StringBuilder("<ul>");
		for (Failure fail : detail.failures) {
			String name = fail.getTestHeader();
			sb.append("<li>");
			sb.append(name.substring(0, name.lastIndexOf('(')));
			sb.append("</li>");
		}
		sb.append("</ul>");
		return sb.toString();
	}

	public static void _bingo(Map<?, ?> args, Closure body, PrintWriter out, ExecutableTemplate template, int fromLine) {

		Map<Feature, ScoreDetail> result = ((Map<Feature, ScoreDetail>) args.get("result"));

		;

		Map<Integer, BingoCell> map = new HashMap<Integer, BingoCell>();
		int i = 0;
		map.put(i++, new BingoCell(Condition.perfect(result.get(Feature.TSURU_KAME))));
		map.put(i++, new BingoCell(Condition.successCount(result.get(Feature.TSURU_KAME), 6)));
		map.put(i++, new BingoCell(Condition.perfect(result.get(Feature.CALC))));

		map.put(i++, new BingoCell(Condition.successCount(result.get(Feature.MYERS), 3)));
		map.put(i++, new BingoCell(Condition.perfect(result.get(Feature.ENQ))));
		map.put(i++, new BingoCell(Condition.successCount(result.get(Feature.CALC), 6)));

		map.put(i++, new BingoCell(Condition.successCount(result.get(Feature.FIZZ_BUZZ), 4)));
		map.put(i++, new BingoCell(Condition.perfect(result.get(Feature.FIZZ_BUZZ))));
		map.put(i++, new BingoCell(Condition.perfect(result.get(Feature.MYERS))));

		int num = boardSize;
		for (int row = 0; row < num; row++) {
			out.println("<div class='row'>");
			for (int column = 0; column < num; column++) {
				out.printf("<div class='bingo-%s'></div>", map.get(row * num + column).judge() ? "ok" : "ng");
			}
			out.println("</div>");
		}
	}

	public static final int boardSize = 3;
}