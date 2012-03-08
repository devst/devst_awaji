package models;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import questions.Question1;
import questions.Question2;
import questions.Question3;
import answers.Answer1;
import answers.Answer2;

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
