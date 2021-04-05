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
		//Test that the function stores in an ArrayList
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
		//Test that the stored value is the same as the text file
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
	void testReadMaster5() {
		//Test if the total length of a line is 42
		ReadMaster RM = new ReadMaster();
		RM.read("D:\\Porn\\SQA\\SQA-Phase4\\JunitTest\\src\\prototype\\masterAccount.txt");
		
		ArrayList<String> rmActual = RM.getAccounts();
		
		Assert.assertEquals(42, rmActual.get(0).length());
	}
	
	@Test
	void getAccounts1() {
		//Test if it returns in an ArrayList of String
		ReadMaster RM = new ReadMaster();
		
		ArrayList<String> expected = new ArrayList<String>();
		
		Assert.assertEquals(expected, RM.getAccounts());
	}
	
	@Test
	void getAccounts2() {
		//Test if it returns all the stored value from the read function
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
	void testReadTransaction1() {
		//Test if it can read a file
		ReadTransactions RT = new ReadTransactions();
		try {
			RT.read("D:\\Porn\\SQA\\SQA-Phase4\\JunitTest\\src\\prototype\\mergedTransaction.txt");
			Assert.assertNotNull(RT);
		} catch(Exception e) {
			fail("Failed to read the file");
		}
		
	}
	
	@Test
	void testReadTransaction2() {
		//Test if it can split the content with every new line
		ReadTransactions RT = new ReadTransactions();
		RT.read("D:\\Porn\\SQA\\SQA-Phase4\\JunitTest\\src\\prototype\\mergedTransaction.txt");
		
		ArrayList<String> rtActual = RT.getTransactionsRaw();

		Assert.assertEquals("01 John Bank            00001 00100.00   ", rtActual.get(0));
	}
	
	@Test
	void testReadTransaction3() {
		//Test that the function stores the value
		ReadTransactions RT = new ReadTransactions();
		RT.read("D:\\Porn\\SQA\\SQA-Phase4\\JunitTest\\src\\prototype\\mergedTransaction.txt");
		
		ArrayList<String> rtActual = RT.getTransactionsRaw();

		Assert.assertNotNull(rtActual);
	}
	
	@Test
	void testReadTransaction4() {
		//Test that the function stores the same value as the text file
		ReadTransactions RT = new ReadTransactions();
		RT.read("D:\\Porn\\SQA\\SQA-Phase4\\JunitTest\\src\\prototype\\mergedTransaction.txt");
		
		ArrayList<String> rtExpected = new ArrayList<String>();
		
		rtExpected.add("01 John Bank            00001 00100.00   ");
		rtExpected.add("00 John Bank            00000 00000.00   ");
		rtExpected.add("04 Cayde Six            00001 00100.00   ");
		rtExpected.add("00 Cayde Six            00000 00000.00   ");
		rtExpected.add("00 John Bank            00000 00000.00   ");

		Assert.assertEquals(rtExpected, RT.getTransactionsRaw());
	}
	
	@Test
	void testReadTransaction5() {
		//Test that each line has the total length of 41
		ReadTransactions RT = new ReadTransactions();
		RT.read("D:\\Porn\\SQA\\SQA-Phase4\\JunitTest\\src\\prototype\\mergedTransaction.txt");
		
		ArrayList<String> rtActual = RT.getTransactionsRaw();

		Assert.assertEquals(41, rtActual.get(0).length());
	}
	
	@Test
	void getTransactionRaw1() {
		//Test that it returns in an ArrayList of String
		ReadTransactions RT = new ReadTransactions();
		
		ArrayList<String> rtExpected = new ArrayList<String>();

		Assert.assertEquals(rtExpected, RT.getTransactionsRaw());
	}
	
	@Test
	void getTransactionRaw2() {
		//Test that it returns all the stored value from the read function
		ReadTransactions RT = new ReadTransactions();
		RT.read("D:\\Porn\\SQA\\SQA-Phase4\\JunitTest\\src\\prototype\\mergedTransaction.txt");
		
		ArrayList<String> rtExpected = new ArrayList<String>();
		
		rtExpected.add("01 John Bank            00001 00100.00   ");
		rtExpected.add("00 John Bank            00000 00000.00   ");
		rtExpected.add("04 Cayde Six            00001 00100.00   ");
		rtExpected.add("00 Cayde Six            00000 00000.00   ");
		rtExpected.add("00 John Bank            00000 00000.00   ");

		Assert.assertEquals(rtExpected, RT.getTransactionsRaw());
	}
	
	@Test
	void getTransactionSums1() {
		//Test that it returns in an ArrayList of String
		ReadTransactions RT = new ReadTransactions();
		
		ArrayList<String[]> rtExpected = new ArrayList<String[]>();

		Assert.assertEquals(rtExpected, RT.getTransactionsRaw());
	}
	
	@Test
	void getTransactionSums2() {
		//Test that it only has the legitimate transaction
		ReadTransactions RT = new ReadTransactions();
		RT.read("D:\\Porn\\SQA\\SQA-Phase4\\JunitTest\\src\\prototype\\mergedTransaction.txt");

		Assert.assertEquals(1, RT.getTransactionSums().size());
	}
	
	@Test
	void getTransactionSums3() {
		//Test that the second dimension first array is an account number
		ReadTransactions RT = new ReadTransactions();
		RT.read("D:\\Porn\\SQA\\SQA-Phase4\\JunitTest\\src\\prototype\\mergedTransaction.txt");

		Assert.assertEquals(5, RT.getTransactionSums().get(0)[0].length());
	}
	
	@Test
	void getTransactionSums4() {
		//Test that the second dimension second array is the total fund
		ReadTransactions RT = new ReadTransactions();
		RT.read("D:\\Porn\\SQA\\SQA-Phase4\\JunitTest\\src\\prototype\\mergedTransaction.txt");

		Assert.assertEquals("00100.00", RT.getTransactionSums().get(0)[1]);
	}
	
	@Test
	void getTransactionSums5() {
		//Test that the second dimension second array is in the proper length
		ReadTransactions RT = new ReadTransactions();
		RT.read("D:\\Porn\\SQA\\SQA-Phase4\\JunitTest\\src\\prototype\\mergedTransaction.txt");

		Assert.assertEquals(8, RT.getTransactionSums().get(0)[1].length());
	}
	
	@Test
	void getTransactionSums6() {
		//Test that the second dimension third array is the total transaction
		ReadTransactions RT = new ReadTransactions();
		RT.read("D:\\Porn\\SQA\\SQA-Phase4\\JunitTest\\src\\prototype\\mergedTransaction.txt");

		Assert.assertEquals("1", RT.getTransactionSums().get(0)[2]);
	}

}
