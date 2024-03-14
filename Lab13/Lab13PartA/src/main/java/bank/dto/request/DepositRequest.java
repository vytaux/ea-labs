package bank.dto.request;

public class DepositRequest {
    private Double amount;

    public DepositRequest() {
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
