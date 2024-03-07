package com.example.demo;

import com.example.demo.repository.HyperSqlPersonRepository;
import com.example.demo.repository.MongoDbPersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private final HyperSqlPersonRepository hyperSqlPersonRepository;
	private final MongoDbPersonRepository mongoDbPersonRepository;
	private final StopWatch stopWatch;

	public Application(
		HyperSqlPersonRepository hyperSqlPersonRepository,
		MongoDbPersonRepository mongoDbPersonRepository,
		StopWatch stopWatch
	) {
		this.hyperSqlPersonRepository = hyperSqlPersonRepository;
		this.mongoDbPersonRepository = mongoDbPersonRepository;
		this.stopWatch = stopWatch;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Creating 10,000 records in HyperSQL");
		long createHyperSqlRecords = create10kHyperSqlPersonRecords();
		System.out.println("Elapsed time: " + createHyperSqlRecords + "ms");
		System.out.println("Creating 10,000 records in MongoDB");
		long createMongoDbRecords = create10kMongoDbPersonRecords();
		System.out.println("Elapsed time: " + createMongoDbRecords + "ms");
		System.out.println("Fetching all records from HyperSQL");
		long fetchHyperSqlRecords = fetchAllHyperSqlRecords();
		System.out.println("Elapsed time: " + fetchHyperSqlRecords + "ms");
		System.out.println("Fetching all records from MongoDB");
		long fetchMongoDbRecords = fetchAllMongoDbRecords();
		System.out.println("Elapsed time: " + fetchMongoDbRecords + "ms");


		System.out.println();
		System.out.println("+----------------------+----------------------+----------------------+");
		System.out.printf("| %-20s | %20s | %20s |%n", "Operation", "HyperSQL Time (ms)", "MongoDB Time (ms)");
		System.out.println("+----------------------+----------------------+----------------------+");
		System.out.printf("| %-20s | %20d | %20d |%n", "Create Records", createHyperSqlRecords, createMongoDbRecords);
		System.out.printf("| %-20s | %20d | %20d |%n", "Fetch Records", fetchHyperSqlRecords, fetchMongoDbRecords);
		System.out.println("+----------------------+----------------------+----------------------+");
		System.out.println();
	}

	private long fetchAllMongoDbRecords() {
        stopWatch.start();
		mongoDbPersonRepository.findAll();
        return stopWatch.getElapsedTimeMs();
	}

	private long fetchAllHyperSqlRecords() {
        stopWatch.start();
		hyperSqlPersonRepository.findAll();

		return stopWatch.getElapsedTimeMs();
	}

	private long create10kHyperSqlPersonRecords() {
		com.example.demo.entity.hypersql.Pet hyperSqlPet;
		com.example.demo.entity.hypersql.Person hyperSqlPerson;

		stopWatch.start();

		for (int i = 0; i < 10000; i++) {
			hyperSqlPerson = new com.example.demo.entity.hypersql.Person();
			hyperSqlPerson.setFirstName("John");
			hyperSqlPerson.setLastName("Doe");
			for (int j = 0; j < 10; j++) {
				hyperSqlPet = new com.example.demo.entity.hypersql.Pet();
				hyperSqlPet.setName("Pet " + j);
				hyperSqlPet.setAge(j);
				hyperSqlPerson.addPet(hyperSqlPet);
			}
			hyperSqlPersonRepository.save(hyperSqlPerson);
		}

		return stopWatch.getElapsedTimeMs();
	}

	private long create10kMongoDbPersonRecords() {
		com.example.demo.entity.mongodb.Person mongoDbPerson;
		com.example.demo.entity.mongodb.Pet mongoDbPet;

		stopWatch.start();

		for (int i = 0; i < 10000; i++) {
			mongoDbPerson = new com.example.demo.entity.mongodb.Person();
			mongoDbPerson.setFirstName("John");
			mongoDbPerson.setLastName("Doe");
			for (int j = 0; j < 10; j++) {
				mongoDbPet = new com.example.demo.entity.mongodb.Pet();
				mongoDbPet.setName("Pet " + j);
				mongoDbPet.setAge(j);
				mongoDbPerson.addPet(mongoDbPet);
			}
			mongoDbPersonRepository.save(mongoDbPerson);
		}

		return stopWatch.getElapsedTimeMs();
	}
}
