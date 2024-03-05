package domain;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "flightnumber")
	private String flightNumber;

	@Column(name = "`from`")
	private String from;

	@Column(name = "`to`")
	private String to;

	private LocalDate date;

	public Flight() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Flight{" +
				"id=" + id +
				", flightNumber='" + flightNumber + '\'' +
				", from='" + from + '\'' +
				", to='" + to + '\'' +
				", date=" + date +
				'}';
	}
}
