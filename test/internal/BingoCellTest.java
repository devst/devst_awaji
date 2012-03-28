package internal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;


public class BingoCellTest {

	@Test
	public void judge_全部だめならfalse() throws Exception {
		BingoCell actual = new BingoCell(Condition.instance(ScoreDetail.NONE),
				Condition.instance(ScoreDetail.NONE));

		assertThat(actual.judge(), is(false));
	}

	@Test
	public void judge_一つでもOKならtrue() throws Exception {
		BingoCell actual = new BingoCell(Condition.instance(ScoreDetail.NONE),
				Condition.instance(ScoreDetail.instance(0, 0, null)));

		assertThat(actual.judge(), is(true));
	}
}
