package bank.service.event.event;

public record TransferFundsEvent(
        Long fromAccountNumber,
        String fromCustomerName,
        Long toAccountNumber,
        String toCustomerName,
        Double amount
) {
    public static String OPERATION = "transferFunds";
}
