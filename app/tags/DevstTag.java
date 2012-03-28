package tags;

import features.Feature;
import groovy.lang.Closure;

import internal.BingoCell;
import internal.Condition;
import internal.ScoreDetail;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.runner.notification.Failure;

import play.templates.FastTags;
import play.templates.GroovyTemplate.ExecutableTemplate;
import controllers.Team;

@FastTags.Namespace("devst")
public class DevstTag extends FastTags {

	public static void _badge(Map<?, ?> args, Closure body, PrintWriter out, ExecutableTemplate template, int fromLine) {
		ScoreDetail detail = (ScoreDetail) args.get("name");
		if (detail == null) {
			return;
		}
		String popover = "";
		String style = "badge";
		String label = "none";
		if (detail != ScoreDetail.NONE) {
			String result = detail.isSuccess() ? "success" : "error";
			style += " badge-" + result;
			popover = "rel='popover'";
			popover += String.format(" data-original-title='%s'", result);
			popover += String.format(" data-content='%s'", getContent(detail));
			label = detail.isSuccess() ? "ok" : Integer.toString(detail.getFailure());
		}
		out.printf("<span class='%s'%s>%s</span>", style, popover, label);
	}

	private static String getContent(ScoreDetail detail) {
		if (detail.isSuccess()) {
			return "Congratulations!";
		}

		StringBuilder sb = new StringBuilder("<ul>");
		for (Failure fail : detail.getFailures()) {
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

		Map<Integer, BingoCell> map = new HashMap<Integer, BingoCell>();
		int i = 1;
		map.put(i++, new BingoCell(Condition.perfect(result.get(Feature.TSURU_KAME))));
		map.put(i++, new BingoCell(Condition.successCount(result.get(Feature.TSURU_KAME), 6)));
		map.put(i++, new BingoCell(Condition.perfect(result.get(Feature.CALC))));

		map.put(i++, new BingoCell(Condition.successCount(result.get(Feature.MYERS), 3)));
		map.put(i++, new BingoCell(Condition.perfect(result.get(Feature.ENQ))));
		map.put(i++, new BingoCell(Condition.successCount(result.get(Feature.CALC), 6)));

		map.put(i++, new BingoCell(Condition.successCount(result.get(Feature.FIZZ_BUZZ), 4)));
		map.put(i++, new BingoCell(Condition.perfect(result.get(Feature.FIZZ_BUZZ))));
		map.put(i++, new BingoCell(Condition.perfect(result.get(Feature.MYERS))));

		int num = (int) Math.sqrt(map.size());
		int j = 0;
		for (int row = 0; row < num; row++) {
			out.print("<div class='row'>");
			for (int column = 0; column < num; column++) {
				j++;
				out.printf("<div class='bingo badge badge-%s'>%d</div>", map.get(j).judge() ? "success" : "error", j);
			}
			out.println("</div>");
		}
	}
}