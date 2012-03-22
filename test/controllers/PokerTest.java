package controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.EnumMap;

import org.junit.Test;

import play.test.UnitTest;

public class PokerTest extends UnitTest {

	@Test
	public void test() {
		EnumMap<Team, String> expected = new EnumMap<Team, String>(Team.class);
		for (Team t : Team.values()) {
			expected.put(t, "");
		}
		expected.put(Team.A, "フラッシュ");

		EnumMap<Team, String> actual = Input4.execute("D9", "D3", "DA", "D6", "DK");
		assertThat(actual, is(expected));
	}
}
