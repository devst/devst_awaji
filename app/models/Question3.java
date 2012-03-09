package models;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import answers.Answer3;


@Question("FizzBuzz")
public class Question3 {

	private Answer3 sut;

	@Test
	public void 三の倍数() throws Exception {
		assertThat(sut.fizzBuzz(3), is("Fizz"));
	}
}
