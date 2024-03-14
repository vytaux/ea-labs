package bank.service.event.event;

public record DepositEurosEvent(String customerName, Long accountNumber, Double amount) {
    public static String OPERATION = "depositEuros";
}
