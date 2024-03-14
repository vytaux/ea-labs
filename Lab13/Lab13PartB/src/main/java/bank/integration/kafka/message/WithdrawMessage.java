package bank.integration.kafka.message;

public class WithdrawMessage {
    private Long accountNumber;
    private Double amount;

    public WithdrawMessage() {
    }

    public WithdrawMessage(double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }
}
