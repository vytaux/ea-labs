package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("test")
public class CustomerRepositoryMock implements CustomerRepository{

	@Override
	public void save(Customer customer) {
		System.out.println("CustomerRepositoryMock: saving customer "+customer.getName());
	}
}
