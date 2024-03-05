package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//create table product (productnumber varchar(100) primary key not null,
//name varchar(100) ,
//price int
// ) ;

@Repository
public class ProductRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public Product findByProductNumber(int productNumber){
        Map<String,Object> namedParameters = new HashMap<>();
        namedParameters.put("productnumber", productNumber);
        Product product = jdbcTemplate.queryForObject(
                "SELECT * FROM product WHERE productNumber =:productnumber",
                namedParameters,
                (rs, rowNum) -> new Product( rs.getInt("productNumber"),
                        rs.getString("name"),
                        rs.getInt("price"))
        );

        Supplier supplier = getSupplierForProduct(product.getProductNumber());
        product.setSupplier(supplier);

        return product;

    }

    Supplier getSupplierForProduct(int productNumber){
        Map<String,Object> namedParameters = new HashMap<>();
        namedParameters.put("productNumber", productNumber);
        Supplier supplier = jdbcTemplate.queryForObject(
                "SELECT * FROM supplier WHERE productNumber =:productNumber ",
                namedParameters,
                (rs, rowNum) -> new Supplier( rs.getInt("supplierNumber"),
                        rs.getString("name"),
                        rs.getString("phone")));

        return supplier;
    }

    public void save(Product product) {
        Map<String,Object> namedParameters = new HashMap<>();
        namedParameters.put("productnumber", product.getProductNumber());
        namedParameters.put("name", product.getName());
        namedParameters.put("price", product.getPrice());
        jdbcTemplate.update("INSERT INTO product VALUES ( :productnumber, :name, :price)",namedParameters);

        Map<String,Object> namedParametersp = new HashMap<>();
        namedParametersp.put("suppliernumber", product.getSupplier().getSupplierNumber());
        namedParametersp.put("name", product.getSupplier().getName());
        namedParametersp.put("phone", product.getSupplier().getPhone());
        namedParametersp.put("productnumber", product.getProductNumber());
        jdbcTemplate.update("INSERT INTO supplier VALUES ( :suppliernumber, :name, :phone, :productnumber)",namedParametersp);
    }

    public Product findByProductName(String productName){
        Map<String,Object> namedParameters = new HashMap<>();
        namedParameters.put("name", productName);
        Product product = jdbcTemplate.queryForObject(
                "SELECT * FROM product WHERE name =:name ",
                namedParameters,
                (rs, rowNum) -> new Product( rs.getInt("productnumber"),
                        rs.getString("name"),
                        rs.getInt("price")));

        Supplier supplier = getSupplierForProduct(product.getProductNumber());
        product.setSupplier(supplier);

        return product;

    }

    public List<Product> getAllProducts(){
        List<Product> products = jdbcTemplate.query(
                "SELECT * FROM product",
                new HashMap<String, Product>(),
                (rs, rowNum) -> new Product( rs.getInt("productnumber"),
                        rs.getString("name"),
                        rs.getInt("price")));


        for (Product product: products){
            Supplier supplier = getSupplierForProduct(product.getProductNumber());
            product.setSupplier(supplier);
        }

        return products;
    }

    public void removeProduct(int productNumber) {
        Map<String,Object> namedParameters = new HashMap<>();
        namedParameters.put("productnumber", productNumber);
        jdbcTemplate.update("DELETE from product WHERE productnumber=:productnumber",namedParameters);
    }
}
