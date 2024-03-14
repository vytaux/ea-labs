package bank.service.event.event;

public record WithdrawEurosEvent(String customerName, Long accountNumber, Double amount) {
    public static String OPERATION = "withdrawEuros";
}
