package customers;

public class Supplier {

	private int supplierNumber;
	private String name;
	private String phone;

	public Supplier(int supplierNumber, String name, String phone) {
		this.supplierNumber = supplierNumber;
		this.name = name;
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"supplierNumber=" + supplierNumber +
				", name='" + name + '\'' +
				", phone='" + phone + '\'' +
				'}';
	}

	public int getSupplierNumber() {
		return supplierNumber;
	}

	public void setSupplierNumber(int supplierNumber) {
		this.supplierNumber = supplierNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
