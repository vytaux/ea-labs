package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


//create table supplier (suppliernumber varchar(100) primary key not null,
//name varchar(100) ,
//phone varchar(100),
//productnumber int
// ) ;

@Repository
public class SupplierRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void clearDB() {
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        jdbcTemplate.update("DELETE from customer",namedParameters);
        jdbcTemplate.update("DELETE from creditcard",namedParameters);
    }

    public void save(Supplier supplier) {
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        namedParameters.put("suppliernumber", supplier.getSupplierNumber());
        namedParameters.put("name", supplier.getName());
        namedParameters.put("phone", supplier.getPhone());
        jdbcTemplate.update("INSERT INTO supplier VALUES ( :suppliernumber, :name, :phone)",namedParameters);
    }



    public Customer getCustomer(int customerNumber){
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        namedParameters.put("customerNumber", customerNumber);
        Customer customer = jdbcTemplate.queryForObject("SELECT * FROM customer WHERE "
                        + "customerNumber =:customerNumber ",
                namedParameters,
                (rs, rowNum) -> new Customer( rs.getInt("customerNumber"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone")));

        CreditCard creditCard = getCreditCardForCustomer(customer.getCustomerNumber());
        customer.setCreditCard(creditCard);
        return customer;

    }

    public List<Customer> getAllCustomers(){
        List<Customer> customers = jdbcTemplate.query("SELECT * FROM customer",
                new HashMap<String, Customer>(),
                (rs, rowNum) -> new Customer( rs.getInt("customerNumber"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone")));

        for (Customer customer: customers){
            CreditCard creditCard = getCreditCardForCustomer(customer.getCustomerNumber());
            customer.setCreditCard(creditCard);
        }
        return customers;
    }

    CreditCard getCreditCardForCustomer(int customerNumber){
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        namedParameters.put("customerNumber", customerNumber);
        CreditCard creditCard = jdbcTemplate.queryForObject("SELECT * FROM creditcard WHERE "
                        + "customerNumber =:customerNumber ",
                namedParameters,
                (rs, rowNum) -> new CreditCard( rs.getString("cardnumber"),
                        rs.getString("type"),
                        rs.getString("validationDate")));

        return creditCard;
    }

}
