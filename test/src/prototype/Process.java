public class Process {

    //PLACEHOLDER
    private static String tempTest = "01 John Bank            00001 00100.00   ";

    private static void processData() {

        //DELCARING VARIABLES (PLACEHOLDER)
        String code = tempTest.substring(0,2);
        String account = tempTest.substring(24,29);
        Float value = Float.parseFloat(tempTest.substring(30,38));

        //CHECKING THE TRANSACTION
        switch(code){
            case "01":
                System.out.println("Code 01 - Withdrawal");
                break;

            case "02":
                System.out.println("Code 02 - Transfer");
                break;

            case "03":
                System.out.println("Code 03 - Paybill");
                break;

            case "04":
                System.out.println("Code 04 - Deposit");
                break;

            case "05":
                System.out.println("Code 05 - Create");
                break;

            case "06":
                System.out.println("Code 06 - Delete");
                break;

            case "07":
                System.out.println("Code 07 - Disable");
                break;

            case "08":
                System.out.println("Code 08 - Changeplan");
                break;

            default:
                System.out.println("Code 00 - End of Session");
                break;
        }
    }
    public static void main(String[] args) {

        if (args.length < 2){
            System.out.println("Requires 2 Arguement, master account file and merged transaction file (in order)");
            System.exit(0);
        }

        String transactionFile = args[1];
        String masterFile = args[0];
        //ReadTransaction transactions;
        //ReadMaster accounts;
        String accountContent;
        String masterContent;

        processData();
    }
}
