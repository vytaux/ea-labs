package jms;

import org.springframework.stereotype.Service;

@Service
public class JmsCalculator {

    private Integer sum = 0;

    public void applyOperation(Operation operation) {
        switch (operation.getOperator()) {
            case "+":
                sum += operation.getValue();
                break;
            case "-":
                sum -= operation.getValue();
                break;
            case "*":
                sum *= operation.getValue();
                break;
            default:
                throw new IllegalArgumentException("Invalid operation: " + operation.getOperator());
        }
    }

    public Integer getSum() {
        return sum;
    }
}
