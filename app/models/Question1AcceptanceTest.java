package models;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import questions.Question1;
import answers.Answer1;

@Question("足し算")
public class Question1AcceptanceTest {

	private Question1 sut;

	@Before
	public void setUp() {
		sut = new Answer1();
	}

	@Test
	public void test1() {
		assertThat(sut.plus(1, 2), is(3));
	}

	@Test
	public void test2() {
		assertThat(sut.plus(1, 3), is(4));
	}

}
