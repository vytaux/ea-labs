package bank.integration.kafka.message;

public class KafkaMessage {
    private String operation;
    private String data;

    public KafkaMessage() {
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
