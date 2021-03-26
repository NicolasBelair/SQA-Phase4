import java.io.FileWriter;
import java.io.IOException;

public class outputFiles {
  public void writeAccount(String accountContent) {
    try {
      FileWriter myWriter = new FileWriter("masterAccount.txt");
      myWriter.write(accountContent);
      myWriter.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public void writeMaster(String masterContent) {
    try {
      FileWriter myWriter = new FileWriter("mergedTransaction.txt");
      myWriter.write(masterContent);
      myWriter.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
