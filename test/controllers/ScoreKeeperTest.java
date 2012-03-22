package controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import org.hamcrest.Matcher;
import org.junit.Test;

import play.test.UnitTest;

import features.Index;
import features.Index.Feature;

public class ScoreKeeperTest extends UnitTest {

	@Test
	public void test() {

		List<String> actual = new ArrayList<String>();
		for(Entry<Feature, ScoreDetail> e : ScoreKeeper.getTeamScore(Team.A).entrySet()) {
			actual.add(e.getValue().getResult());
		}

		List<String> expected = Arrays.asList("error", "error", "success", "success", "success", "notyet", "notyet");
		assertThat(actual, is(expected));
	}

}
