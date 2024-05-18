import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Bank {
    private String bankName;
    private List<Account> accounts;
    private double totalTransactionFeeAmount;
    private double totalTransferAmount;
    private double transactionFlatFeeAmount;
    private double transactionPercentFeeValue;
    private List<Transaction> transactions;

    public Bank(String bankName, double flatFee, double percentFee) {
        this.bankName = bankName;
        this.accounts = new ArrayList<>();
        this.totalTransactionFeeAmount = 0;
        this.totalTransferAmount = 0;
        this.transactionFlatFeeAmount = flatFee;
        this.transactionPercentFeeValue = percentFee;
        this.transactions = new ArrayList<>();
        System.out.println("Bank " + bankName + " created with flat fee: " + flatFee + " and percent fee: " + percentFee);
    }

    public Account createAccount(String name, double initialBalance) {
        Account account = new Account(UUID.randomUUID().toString(), name, initialBalance);
        accounts.add(account);
        return account;
    }

    public Account getAccount(String accountId) throws Exception {
        return accounts.stream()
                .filter(acc -> acc.getAccountId().equals(accountId))
                .findFirst()
                .orElseThrow(() -> new Exception("Account not found"));
    }

    public void performTransaction(String fromAccountId, String toAccountId, double amount, String reason, boolean isFlatFee) throws Exception {
        Account fromAccount = getAccount(fromAccountId);
        Account toAccount = getAccount(toAccountId);

        double fee = isFlatFee ? transactionFlatFeeAmount : (amount * transactionPercentFeeValue / 100);
        fee = Math.round(fee * 100.0) / 100.0; // Ensuring 2 decimals
        double totalAmount = amount + fee;

        if (fromAccount.getBalance() < totalAmount) {
            throw new Exception("Not enough funds");
        }

        fromAccount.withdraw(totalAmount);
        toAccount.deposit(amount);

        totalTransactionFeeAmount += fee;
        totalTransactionFeeAmount = Math.round(totalTransactionFeeAmount * 100.0) / 100.0; 
        totalTransferAmount += amount;
        totalTransferAmount = Math.round(totalTransferAmount * 100.0) / 100.0; 

        Transaction transaction = new Transaction(amount, fromAccountId, toAccountId, reason, fee);
        transactions.add(transaction);
        fromAccount.addTransaction(transaction);
        toAccount.addTransaction(transaction);
    }

    public void deposit(String accountId, double amount) throws Exception {
        Account account = getAccount(accountId);
        account.deposit(amount);
    }

    public void withdraw(String accountId, double amount) throws Exception {
        Account account = getAccount(accountId);
        account.withdraw(amount);
    }

    public void listTransactions(String accountId) throws Exception {
        Account account = getAccount(accountId);
        account.printTransactions();
    }

    public void checkAccountBalance(String accountId) throws Exception {
        Account account = getAccount(accountId);
        System.out.println("Balance: " + account.getBalance());
    }

    public void listAccounts() {
        accounts.forEach(account -> System.out.println("Account ID: " + account.getAccountId() + ", Name: " + account.getName() + ", Balance: " + account.getBalance()));
    }

    public double getTotalTransactionFeeAmount() {
        return totalTransactionFeeAmount;
    }

    public double getTotalTransferAmount() {
        return totalTransferAmount;
    }

    public String getBankName() {
        return bankName;
    }
}
