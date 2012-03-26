package tags;

import features.Feature;
import groovy.lang.Closure;

import internal.BingoCell;
import internal.Condition;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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
				out.printf("<span class='badge badge-%s' rel='popover' data-original-title='%s' data-content=\"%s\">",
						result, result, content);
				out.print(detail.getProgress());
				out.print("</span>");
			} else {
				out.printf("<span class='badge'>none</span>");
			}
		}
	}

	private static String getContent(ScoreDetail detail) {
		StringBuilder sb = new StringBuilder();

		if (detail.failure == 0) {
			sb.append("Congratulations!<br/><br/>");
		}

		sb.append("<ul class='test-headers'>");
		int i = 0;
		for (String testHeader : detail.testHeaders) {
			sb.append("<li>");
			sb.append(detail.isSuccess.get(i) ? "<i class='icon-check'></i> "
					: "<i class='icon-fire'></i> ");
			sb.append(testHeader);
			sb.append("</li>");
			i++;
		}
		sb.append("</ul>");
		return sb.toString();
	}

	public static void _bingo(Map<?, ?> args, Closure body, PrintWriter out, ExecutableTemplate template, int fromLine) {

		Map<Feature, ScoreDetail> result = ((Map<Feature, ScoreDetail>) args.get("result"));

		;

		Map<Integer, BingoCell> map = new HashMap<Integer, BingoCell>();
		int i = 0;
		map.put(i++, new BingoCell(Condition.instance(result.get(Feature.Q1))));
		map.put(i++, new BingoCell(Condition.instance(result.get(Feature.Q2))));
		map.put(i++, new BingoCell(Condition.instance(result.get(Feature.Q3))));
		map.put(i++, new BingoCell(Condition.instance(result.get(Feature.Q4))));
		map.put(i++, new BingoCell(Condition.instance(result.get(Feature.Q5))));
		map.put(i++, new BingoCell(Condition.instance(result.get(Feature.Q6))));
		map.put(i++, new BingoCell(Condition.instance(result.get(Feature.Q7))));
		map.put(i++, new BingoCell(Condition.perfect(result.get(Feature.Q1))));
		map.put(i++, new BingoCell(Condition.successCount(result.get(Feature.Q6), 1)));

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