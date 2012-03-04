package models;


import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import junit.framework.Assert;

import org.junit.Test;

public abstract class AbstractQuestionFunctionTest {
	
	public QuestionExecutable getInstance() {
		QuestionExecutable question = null;
		try {
			Object obj = Class.forName(this.getClass().getName().replaceAll("FunctionTest", "")).newInstance();
			question = (QuestionExecutable)obj;
		} catch (ClassNotFoundException e) {
			fail("Not yet implemented");
		} catch (InstantiationException e) {
			fail("miss implemented: " + e.getMessage());
		} catch (IllegalAccessException e) {
			fail("miss implemented: " + e.getMessage());
		} catch (ClassCastException e) {
			fail("miss implemented: " + e.getMessage());
		}
		return question;
	}

	@Test
	public void testInstance() {
		Assert.assertNotNull(getInstance());
	}
}
