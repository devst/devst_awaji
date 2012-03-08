package models;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import questions.Question1;
import questions.Question2;
import questions.Question3;
import questions.Question4;
import answers.Answer1;
import answers.Answer2;

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
