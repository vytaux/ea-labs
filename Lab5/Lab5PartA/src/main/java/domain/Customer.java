package domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstName;

	private String lastName;

	@OneToOne(cascade = CascadeType.PERSIST)
	private Address address;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
	private Collection<Order> theOrders = new ArrayList<>();

	public Customer() {
	}

	public Customer(String firstName, String lastName, String street,
					String city, String zip) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = new Address(street, city, zip);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Collection<Order> getTheOrders() {
		return Collections.unmodifiableCollection(theOrders);
	}

	public boolean addOrder(Order order) {
		boolean added = theOrders.add(order);
		if (added) {
			order.setCustomer(this);
		}
		return added;
	}

	public boolean removeOrder(Order order) {
		boolean removed = theOrders.remove(order);
		if (removed) {
			theOrders.remove(order);
		}
		return removed;
	}

}
