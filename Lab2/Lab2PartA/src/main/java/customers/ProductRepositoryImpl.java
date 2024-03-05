package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

	private Logger logger;

	@Autowired
	public ProductRepositoryImpl(Logger logger) {
		this.logger = logger;
	}

	@Override
	public void save(Product product) {
		try {
			Thread.sleep(350);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("ProductRepository: saving product "+product.getName());
		logger.log("Product is saved in the DB: "+ product.getName() );
	}
}
