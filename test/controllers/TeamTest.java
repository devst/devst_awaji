package controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;

import org.junit.Test;

import play.test.UnitTest;

public class TeamTest extends UnitTest {

	@Test
	public void 該当チームの実装クラスを取得する() {
		Class actual = Team.A.getFeature(features.Calculator.class);
		assertThat(actual, is(sameInstance(features.a.Answer1.class)));
	}

	@Test
	public void 該当チームの実装クラスを取得する2() {
		Class actual = Team.A.getFeature(features.JapaneseSyllabary.class);
		assertThat(actual, is(sameInstance(features.a.JapaneseSyllabary.class)));
	}
}
