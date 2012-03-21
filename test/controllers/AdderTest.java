package controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.EnumMap;

import org.junit.Test;

public class AdderTest {

	@Test
	public void test() {
		EnumMap<Team, String> expected = new EnumMap<Team, String>(Team.class);
		expected.put(Team.A, "5");
		expected.put(Team.B, "");

		EnumMap<Team, String> actual = Input1.execute("2", "3");
		assertThat(actual, is(expected));
	}
}
