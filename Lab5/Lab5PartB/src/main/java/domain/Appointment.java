package domain;

import jakarta.persistence.*;

@Entity
public class Appointment {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "appdate")
	private String appDate;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "patient")
	private Patient patient;

	@Embedded
	private Payment payment;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "doctor")
	private Doctor doctor;

	public Appointment() {
	}

	public Appointment(String appDate, Patient patient, Payment payment,
					   Doctor doctor) {
		this.appDate = appDate;
		this.patient = patient;
		this.payment = payment;
		this.doctor = doctor;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAppDate() {
		return appDate;
	}

	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	@Override
	public String toString() {
		return "Appointment{" +
				"id=" + id +
				", appDate='" + appDate + '\'' +
				", patient=" + patient +
				", payment=" + payment +
				", doctor=" + doctor +
				'}';
	}
}
