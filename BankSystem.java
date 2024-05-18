public class BankSystem {
    public static void main(String[] args) {
        try {
            Bank bank = new Bank("MyBank", 10, 5);
            System.out.println("Welcome to " + bank.getBankName());

            Account account1 = bank.createAccount("User1", 1000);
            Account account2 = bank.createAccount("User2", 500);

            bank.performTransaction(account1.getAccountId(), account2.getAccountId(), 100, "Transfer to User2", true);
            bank.performTransaction(account2.getAccountId(), account1.getAccountId(), 50, "Transfer to User1", false);

            bank.deposit(account1.getAccountId(), 200);
            bank.withdraw(account2.getAccountId(), 100);

            bank.listTransactions(account1.getAccountId());
            bank.checkAccountBalance(account1.getAccountId());
            bank.listAccounts();

            System.out.println("Total Transaction Fee Amount: " + bank.getTotalTransactionFeeAmount());
            System.out.println("Total Transfer Amount: " + bank.getTotalTransferAmount());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
