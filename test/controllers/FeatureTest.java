package controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;

import org.junit.Test;

import play.test.UnitTest;

public class FeatureTest extends UnitTest {

	@Test
	public void 該当チームの実装クラスを取得する() {
		Feature sut = new Feature(Team.A);
		Class actual = sut.getFeature(features.Answer1.class);
		assertThat(actual, is(sameInstance(features.a.Answer1.class)));
	}

	@Test
	public void 該当チームの実装クラスを取得する2() {
		Feature sut = new Feature(Team.A);
		Class actual = sut.getFeature(features.JapaneseSyllabary.class);
		assertThat(actual, is(sameInstance(features.a.JapaneseSyllabary.class)));
	}

	@Test
	public void 該当チームの実装クラスを取得する_static() {
		Class actual = Feature.getFeature(Team.A, features.Answer1.class);
		assertThat(actual, is(sameInstance(features.a.Answer1.class)));
	}

}
