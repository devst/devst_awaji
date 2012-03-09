package models;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import questions.Question3;

@Question("FizzBuzz")
public class Question3AcceptanceTest {

	private Question3 sut;

	@Before
	public void setUp() {
		sut = null;
	}

	@Test
	public void 三の倍数() throws Exception {
		assertThat(sut.fizzBuzz(3), is("Fizz"));
	}
}
