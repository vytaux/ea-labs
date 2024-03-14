package bank.service.event.event;

public record WithdrawEvent(String customerName, Long accountNumber, Double amount) {
    public static String OPERATION = "withdraw";
}
