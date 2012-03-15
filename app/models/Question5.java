package models;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import answers.Answer5;

import org.junit.Before;
import org.junit.Test;

@Question("ローマ数字変換")
public class Question5 {

	private Answer5 sut;

	/**
	 * 引数がnullの場合、RuntimeExceptionを投げる。
	 */
	@Test
	public void testNullArgument() {
		assertRuntimeExceptionThrown(null);
	}

	/**
	 * 引数が空文字列の場合、RuntimeExceptionを投げる。
	 */
	@Test
	public void testEmptyStringArgument() {
		assertRuntimeExceptionThrown("");
	}

	/**
	 * 1000の位の数字のみの場合。
	 */
	@Test
	public void testThousandsPlace() {
		assertThat(sut.toInt("MMM"), is(3000));
		assertThat(sut.toInt("MM"), is(2000));
		assertThat(sut.toInt("M"), is(1000));
	}

	/**
	 * 100の位の数字のみの場合。
	 */
	@Test
	public void testHundredsPlace() {
		assertThat(sut.toInt("CM"), is(900));
		assertThat(sut.toInt("DCCC"), is(800));
		assertThat(sut.toInt("DCC"), is(700));
		assertThat(sut.toInt("DC"), is(600));
		assertThat(sut.toInt("D"), is(500));
		assertThat(sut.toInt("CD"), is(400));
		assertThat(sut.toInt("CCC"), is(300));
		assertThat(sut.toInt("CC"), is(200));
		assertThat(sut.toInt("C"), is(100));
	}

	/**
	 * 10の位の数字のみの場合。
	 */
	@Test
	public void testTensPlace() {
		assertThat(sut.toInt("XC"), is(90));
		assertThat(sut.toInt("LXXX"), is(80));
		assertThat(sut.toInt("LXX"), is(70));
		assertThat(sut.toInt("LX"), is(60));
		assertThat(sut.toInt("L"), is(50));
		assertThat(sut.toInt("XL"), is(40));
		assertThat(sut.toInt("XXX"), is(30));
		assertThat(sut.toInt("XX"), is(20));
		assertThat(sut.toInt("X"), is(10));
	}

	/**
	 * 1の位の数字のみの場合。
	 */
	@Test
	public void testOnesPlace() {
		assertThat(sut.toInt("IX"), is(9));
		assertThat(sut.toInt("VIII"), is(8));
		assertThat(sut.toInt("VII"), is(7));
		assertThat(sut.toInt("VI"), is(6));
		assertThat(sut.toInt("V"), is(5));
		assertThat(sut.toInt("IV"), is(4));
		assertThat(sut.toInt("III"), is(3));
		assertThat(sut.toInt("II"), is(2));
		assertThat(sut.toInt("I"), is(1));
	}

	/**
	 * 1の位から1000の位までの数字を複数組み合わせた数の場合。
	 */
	@Test
	public void testConbinationOfEachPlace() {
		assertThat(sut.toInt("MCCCLXXXIV"), is(1384));	// 1000, 100, 10, 1

		assertThat(sut.toInt("MMCDXC"),     is(2490));	// 1000, 100, 10, -
		assertThat(sut.toInt("MMMDV"),      is(3505));	// 1000, 100, --, 1
		assertThat(sut.toInt("MXVI"),       is(1016));	// 1000, ---, 10, 1
		assertThat(sut.toInt("DCXXVII"),    is( 627));	// ----, 100, 10, 1

		assertThat(sut.toInt("MMDCC"),      is(2700));	// 1000, 100, --, -
		assertThat(sut.toInt("MMMVIII"),    is(3008));	// 1000, ---, --, 1
		assertThat(sut.toInt("XXXIX"),      is(  39));	// ----, ---, 10, 1
		assertThat(sut.toInt("MXL"),        is(1040));	// 1000, ---, 10, -
		assertThat(sut.toInt("DCCCI"),      is( 801));	// ----, 100, --, 1
		assertThat(sut.toInt("CML"),        is( 950));	// ----, 100, 10, -
	}

	/**
	 * 適当にいくつかの例。(Wikipediaに書かれていた例。)
	 */
	@Test
	public void testSomeCases() {
		assertThat(sut.toInt("XI"), is(11));
		assertThat(sut.toInt("XII"), is(12));
		assertThat(sut.toInt("XIV"), is(14));
		assertThat(sut.toInt("XVIII"), is(18));
		assertThat(sut.toInt("XXIV"), is(24));
		assertThat(sut.toInt("XLIII"), is(43));
		assertThat(sut.toInt("XCIX"), is(99));
		assertThat(sut.toInt("CDXCV"), is(495));
		assertThat(sut.toInt("MDCCCLXXXVIII"), is(1888));
		assertThat(sut.toInt("MCMXLV"), is(1945));
		assertThat(sut.toInt("MMMCMXCIX"), is(3999));
	}

	/**
	 * ローマ数字に使用されない文字が含まれていた場合、RuntimeExceptionを投げる。
	 */
	@Test
	public void testNonRomanNumeralCharacters() {
		assertRuntimeExceptionThrown("O");
		assertRuntimeExceptionThrown("iii");
		assertRuntimeExceptionThrown("200");
		assertRuntimeExceptionThrown("三千九百九十九");
	}

	/**
	 * ローマ数字に使用される文字だが、正しくない並びの場合、RuntimeExceptionを投げる。
	 */
	@Test
	public void testIncorrectSequences() {
		assertRuntimeExceptionThrown("IIII");
		assertRuntimeExceptionThrown("VIIII");
		assertRuntimeExceptionThrown("IIIV");
		assertRuntimeExceptionThrown("LXL");
		assertRuntimeExceptionThrown("MMMCMXCIXI");
	}

	/**
	 * argにtoIntメソッドを適用し、RuntimeExceptionが投げられることを確認する。
	 * 
	 * @param arg toIntメソッドを適用する引数。
	 */
	private void assertRuntimeExceptionThrown(String arg) {
		try {
			this.sut.toInt(arg);
			fail("例外が投げられなかった。");
		} catch (Exception e) {
			if (!RuntimeException.class.equals(e.getClass())) {
				fail("RuntimeException以外の例外が投げられた。");
			}
			// success.
		}
	}

}
