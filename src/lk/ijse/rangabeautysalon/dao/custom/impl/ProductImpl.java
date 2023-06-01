package lk.ijse.rangabeautysalon.dao.custom.impl;

import lk.ijse.rangabeautysalon.dao.custom.ProductDAO;
import lk.ijse.rangabeautysalon.dao.exceptions.ConstraintViolationException;
import lk.ijse.rangabeautysalon.dao.util.CrudUtil;
import lk.ijse.rangabeautysalon.entity.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductImpl implements ProductDAO {
    private final Connection connection;

    public ProductImpl(Connection connection) {

        this.connection = connection;
    }

    @Override
    public Boolean save(Product product) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO product VALUES(?, ?, ?, ?)",
                product.getProductId(),
                product.getName(),
                product.getCost(),
                product.getQtyOnHand()
        );
    }

    @Override
    public Boolean update(Product product) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("Update product set name=?, cost=?, qtyOnHand=? where ProductId=?",
                product.getName(),
                product.getCost(),
                product.getQtyOnHand(),
                product.getProductId()
        );
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        return  CrudUtil.execute("Delete From product where ProductId='"+id+"'");
    }

    @Override
    public Product search(String id) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM product WHERE ProductId = ?", id);

        if(result.next()) {
            return new Product(
                    result.getString("ProductId"),
                    result.getString("name"),
                    result.getDouble("cost"),
                    result.getInt("qtyOnHand")
            );
        }
        return null;
    }

    @Override
    public ArrayList<Product> getAllProduct() throws ClassNotFoundException, SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * From product");
        ArrayList<Product>productList=new ArrayList<>();
        while(rst.next()){
            Product product = new Product(rst.getString("ProductId"), rst.getString("name"), rst.getDouble("cost"), rst.getInt("qtyOnHand"));
            productList.add(product);
        }
        return productList;
    }

    @Override
    public ArrayList<String> loadProductIds() throws SQLException, ClassNotFoundException {
        String sql = "SELECT ProductId FROM product";
        ResultSet result = CrudUtil.execute(sql);

        ArrayList<String> idList = new ArrayList<>();

        while (result.next()) {
            idList.add(result.getString(1));
        }
        return idList;
    }

    @Override
    public ArrayList<String> loadProductNames() throws SQLException, ClassNotFoundException {
        String sql = "SELECT name FROM product";
        ResultSet result = CrudUtil.execute(sql);

        ArrayList<String> NameList = new ArrayList<>();

        while (result.next()) {
            NameList.add(result.getString("name"));
        }
        return NameList;
    }

    @Override
    public Product searchName(String name) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM product WHERE name = ?", name);

        if(result.next()) {
            return new Product(
                    result.getString("ProductId"),
                    result.getString("name"),
                    result.getDouble("cost"),
                    result.getInt("qtyOnHand")
            );
        }
        return null;
    }
}
