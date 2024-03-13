package bank.dto.request;

public class WithdrawRequest {
    private Double amount;

    public WithdrawRequest() {
    }

    public WithdrawRequest(double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
