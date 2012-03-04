package models;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class Question1FunctionTest extends AbstractQuestionFunctionTest {

	@Test
	public void test1() {
		assertThat(getInstance().execute("1,2"), is("3"));
	}

	@Test
	public void test2() {
		assertThat(getInstance().execute("1,3"), is("4"));
	}

}
