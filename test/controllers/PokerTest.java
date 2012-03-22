package controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.EnumMap;

import org.junit.Test;

public class PokerTest {

	@Test
	public void test() {
		EnumMap<Team, String> expected = new EnumMap<Team, String>(Team.class);
		expected.put(Team.A, "フラッシュ");
		expected.put(Team.B, "");

		EnumMap<Team, String> actual = Input4.execute("D9", "D3", "DA", "D6", "DK");
		assertThat(actual, is(expected));
	}
}
