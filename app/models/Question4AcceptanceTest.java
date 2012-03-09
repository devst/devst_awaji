package models;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import questions.Question4;

@Question("ポーカー")
public class Question4AcceptanceTest {

	private Question4 sut;

	@Before
	public void setUp() {
		sut = null;
	}

	@Test
	public void ストレートフラッシュ() throws Exception {
		assertThat(sut.poker("S3", "S4", "S5", "S6", "S7"), is("ストレートフラッシュ"));
	}
}
