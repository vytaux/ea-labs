package domain;

import jakarta.persistence.*;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Passenger {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@OneToMany(cascade = CascadeType.PERSIST)
	@OrderColumn(name = "sequence")
	private List<Flight> flights = new ArrayList<>();

	public Passenger() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flight) {
		this.flights = flight;
	}

	@Override
	public String toString() {
		return "Passenger{" +
				"id=" + id +
				", name='" + name + '\'' +
				", flights=" + flights +
				'}';
	}

	public void addFlight(Flight flight) {
		flights.add(flight);
	}
}
