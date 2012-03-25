package tags;

import groovy.lang.Closure;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.junit.runner.notification.Failure;

import play.templates.FastTags;
import play.templates.GroovyTemplate.ExecutableTemplate;
import controllers.ScoreDetail;

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

		// Entry<Team, Map<Feature<?>, ScoreDetail>> result = (Entry<Team,
		// Map<Feature<?>, ScoreDetail>>) args
		// .get("result");

		Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
		int i = 0;
		map.put(i++, true);
		map.put(i++, false);
		map.put(i++, true);
		map.put(i++, false);
		map.put(i++, true);
		map.put(i++, false);
		map.put(i++, false);
		map.put(i++, true);
		map.put(i++, true);

		int num = boardSize;
		for (int row = 0; row < num; row++) {
			out.println("<div class='row'>");
			for (int column = 0; column < num; column++) {
				out.printf("<div class='bingo-%s'></div>", map.get(row * num + column) ? "ok" : "ng");
			}
			out.println("</div>");
		}
	}

	public static final int boardSize = 3;
}