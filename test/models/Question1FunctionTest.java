package models;


import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import junit.framework.Assert;

import org.junit.Test;

public class Question1FunctionTest extends AbstractQuestionFunctionTest {

	@Test
	public void test1() {
		assertEquals("3", getInstance().execute("1,2"));
	}

	@Test
	public void test2() {
		assertEquals("4", getInstance().execute("1,3"));
	}

}
