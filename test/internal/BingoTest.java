package internal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.List;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.notification.Failure;


public class BingoTest {

	@Test
	public void 条件取得_未達成() throws Exception {
		ScoreDetail detail = ScoreDetail.NONE;

		Condition actual = Condition.instance(detail);
		assertThat(actual.getStatus(), is(false));
	}

	@Test
	public void 条件取得_達成() throws Exception {
		ScoreDetail detail = ScoreDetail.instance(0, 0, null);

		Condition actual = Condition.instance(detail);
		assertThat(actual.getStatus(), is(true));
	}

	@Test
	public void 条件_全テストパス() throws Exception {
		ScoreDetail detail =ScoreDetail.instance(10, 0, null);

		Condition actual = Condition.perfect(detail);
		assertThat(actual.getStatus(), is(true));
	}

	@Test
	public void 条件_全テストパスしてない() throws Exception {
		ScoreDetail detail =ScoreDetail.instance(10, 1, null);

		Condition actual = Condition.perfect(detail);
		assertThat(actual.getStatus(), is(false));
	}

	@Test
	public void 条件_達成ケース数指定_達成() throws Exception {
		ScoreDetail detail =ScoreDetail.instance(10, 7, null);

		Condition actual = Condition.successCount(detail, 3);
		assertThat(actual.getStatus(), is(true));
	}
	@Test
	public void 条件_達成ケース数指定_未達成() throws Exception {
		ScoreDetail detail = ScoreDetail.instance(10, 8, null);

		Condition actual = Condition.successCount(detail, 3);
		assertThat(actual.getStatus(), is(false));
	}

}
