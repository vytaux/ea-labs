package client;

import client.dto.Account;
import client.dto.DepositRequest;
import client.dto.TransferRequest;
import client.dto.WithdrawRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private RestTemplate restTemplate = new RestTemplate();

	private String serverUrl = "http://localhost:8080/accounts";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// create from acc
		Account fromAcc = new Account(11, "John Doe");
		restTemplate.postForLocation(serverUrl, fromAcc);
		fromAcc = restTemplate.getForObject(serverUrl+"/{accountNumber}", Account.class, 11);
		System.out.println(fromAcc);

		// create to acc
		Account toAcc = new Account(22, "Jane Doe");
		restTemplate.postForLocation(serverUrl, toAcc);
		toAcc = restTemplate.getForObject(serverUrl+"/{accountNumber}", Account.class, 22);
		System.out.println(toAcc);

		// deposit to fromAcc
		DepositRequest depositRequest = new DepositRequest(1000);
		restTemplate.postForLocation(serverUrl + "/{accountNumber}/deposit", depositRequest, 11);
		fromAcc = restTemplate.getForObject(serverUrl+"/{accountNumber}", Account.class, 11);
		System.out.println(fromAcc);

		// depositEuros to fromAcc
		depositRequest = new DepositRequest(1000);
		restTemplate.postForLocation(serverUrl + "/{accountNumber}/depositEuros", depositRequest, 11);
		fromAcc = restTemplate.getForObject(serverUrl+"/{accountNumber}", Account.class, 11);
		System.out.println(fromAcc);

		// transfer to toAcc
		TransferRequest transferRequest = new TransferRequest(22L, 500.00, "transfer");
		restTemplate.postForLocation(serverUrl + "/{accountNumber}/transfer", transferRequest, 11);
		fromAcc = restTemplate.getForObject(serverUrl+"/{accountNumber}", Account.class, 11);
		toAcc = restTemplate.getForObject(serverUrl+"/{accountNumber}", Account.class, 22);
		System.out.println(fromAcc);
		System.out.println(toAcc);

		// withdraw from toAcc
		WithdrawRequest withdrawRequest = new WithdrawRequest(800);
		restTemplate.postForLocation(serverUrl + "/{accountNumber}/withdraw", withdrawRequest, 22);
		toAcc = restTemplate.getForObject(serverUrl+"/{accountNumber}", Account.class, 22);
		System.out.println(toAcc);

		// withdrawEuros from toAcc
		withdrawRequest = new WithdrawRequest(50);
		restTemplate.postForLocation(serverUrl + "/{accountNumber}/withdrawEuros", withdrawRequest, 22);
		toAcc = restTemplate.getForObject(serverUrl+"/{accountNumber}", Account.class, 22);
		System.out.println(toAcc);

		Account[] accounts = restTemplate.getForObject(serverUrl, Account[].class);
		System.out.println(Arrays.toString(accounts));
	}

}
