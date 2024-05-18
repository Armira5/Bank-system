public class Transaction {
    private double amount;
    private String originatingAccountId;
    private String resultingAccountId;
    private String reason;
    private double fee;

    public Transaction(double amount, String originatingAccountId, String resultingAccountId, String reason, double fee) {
        this.amount = Math.round(amount * 100.0) / 100.0; 
        this.originatingAccountId = originatingAccountId;
        this.resultingAccountId = resultingAccountId;
        this.reason = reason;
        this.fee = Math.round(fee * 100.0) / 100.0; 
    }

    public double getAmount() {
        return amount;
    }

    public String getOriginatingAccountId() {
        return originatingAccountId;
    }

    public String getResultingAccountId() {
        return resultingAccountId;
    }

    public String getReason() {
        return reason;
    }

    public double getFee() {
        return fee;
    }

    @Override
    public String toString() {
        return String.format("Transaction [amount=%.2f, originatingAccountId=%s, resultingAccountId=%s, reason=%s, fee=%.2f]",
                amount, originatingAccountId, resultingAccountId, reason, fee);
    }
}
