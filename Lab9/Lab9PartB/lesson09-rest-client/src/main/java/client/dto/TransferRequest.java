package client.dto;

public class TransferRequest {
    private Long toAccountNumber;
    private Double amount;
    private String description;

    public TransferRequest() {
    }

    public TransferRequest(Long toAccountNumber, Double amount, String description) {
        this.toAccountNumber = toAccountNumber;
        this.amount = amount;
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(Long toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
