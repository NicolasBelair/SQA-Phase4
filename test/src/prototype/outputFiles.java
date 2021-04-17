package prototype;

import java.io.FileWriter;
import java.io.IOException;

public class outputFiles {
  public void writeAccount(String[] accountContent) {
    try {
      FileWriter myWriter = new FileWriter("masterAccount.txt");
      for (int i = 0; i < accountContent.length; i++) {
          myWriter.write(accountContent[i]);
      }
      myWriter.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public void writeMaster(String[] masterContent) {
    try {
      FileWriter myWriter = new FileWriter("mergedTransaction.txt");
      for (int i = 0; i < masterContent.length; i++) {
          myWriter.write(masterContent[i]);
      }
      myWriter.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
