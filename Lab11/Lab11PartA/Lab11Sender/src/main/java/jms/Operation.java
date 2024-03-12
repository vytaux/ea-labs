package jms;


public class Operation {

	private String operator;
	private Integer value;

	public Operation() {
	}

	public Operation(String firstName, Integer lastName) {
		this.operator = firstName;
		this.value = lastName;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
}
