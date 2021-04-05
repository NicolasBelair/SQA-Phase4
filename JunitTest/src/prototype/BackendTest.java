package prototype;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class BackendTest {

	@Test
	void testReadMaster1() {
		//Test if it can read a file
		ReadMaster RM = new ReadMaster();
		try {
			RM.read("D:\\Porn\\SQA\\SQA-Phase4\\JunitTest\\src\\prototype\\masterAccount.txt");
			Assert.assertNotNull(RM.getAccounts());
		}	catch (Exception e) {
			fail("Failed to read the file");
		}
	}
	
	@Test
	void testReadMaster2() {
		//Test if it can split the content with every new line
		ReadMaster RM = new ReadMaster();
		RM.read("D:\\Porn\\SQA\\SQA-Phase4\\JunitTest\\src\\prototype\\masterAccount.txt");
		
		ArrayList<String> rmActual = RM.getAccounts();
		
		Assert.assertEquals("00001 John Bank            A 00500.00 0030", rmActual.get(0));
	}
	
	
	@Test
	void testReadMaster3() {
		//Test that the get returns in ArrayList
		ReadMaster RM = new ReadMaster();
		RM.read("D:\\Porn\\SQA\\SQA-Phase4\\JunitTest\\src\\prototype\\masterAccount.txt");
		
		ArrayList<String> rmExpected = new ArrayList<String>();
		
		rmExpected.add("00001 John Bank            A 00500.00 0030");
		rmExpected.add("00002 Free Mann            A 00420.00 0237");
		rmExpected.add("00003 Cayde Six            D 00010.00 0420");
		rmExpected.add("00004 Adrian Shephard      A 01000.00 0230");
		Assert.assertEquals(rmExpected, RM.getAccounts());
	}
	
	@Test
	void testReadMaster4() {
		//Test that the return value is the same as the text file
		ReadMaster RM = new ReadMaster();
		RM.read("D:\\Porn\\SQA\\SQA-Phase4\\JunitTest\\src\\prototype\\masterAccount.txt");
		
		ArrayList<String> rmExpected = new ArrayList<String>();
		
		rmExpected.add("00001 John Bank            A 00500.00 0030");
		rmExpected.add("00002 Free Mann            A 00420.00 0237");
		rmExpected.add("00003 Cayde Six            D 00010.00 0420");
		rmExpected.add("00004 Adrian Shephard      A 01000.00 0230");
		Assert.assertEquals(rmExpected, RM.getAccounts());
	}

}
