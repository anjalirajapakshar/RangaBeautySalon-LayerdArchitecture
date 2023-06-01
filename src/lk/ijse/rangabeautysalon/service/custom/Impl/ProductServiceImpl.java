package lk.ijse.rangabeautysalon.service.custom.Impl;

import lk.ijse.rangabeautysalon.dao.DaoFactory;
import lk.ijse.rangabeautysalon.dao.DaoTypes;
import lk.ijse.rangabeautysalon.dao.custom.PaymentDAO;
import lk.ijse.rangabeautysalon.dao.custom.ProductDAO;
import lk.ijse.rangabeautysalon.db.DBConnection;
import lk.ijse.rangabeautysalon.dto.ProductDTO;
import lk.ijse.rangabeautysalon.service.util.Convertor;
import lk.ijse.rangabeautysalon.service.custom.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService{
    private final ProductDAO productDAO;
    private final Convertor convertor;
    private final Connection connection;

    public ProductServiceImpl() throws SQLException, ClassNotFoundException {
        connection= DBConnection.getInstance().getConnection();
        productDAO= DaoFactory.getInstance().getDAO(connection, DaoTypes.PRODUCT );
        convertor=new Convertor();
    }

    @Override
    public boolean save(ProductDTO productDTO) throws SQLException, ClassNotFoundException {
        Boolean save = productDAO.save(convertor.toProduct(productDTO));
        return save;
    }

    @Override
    public boolean update(ProductDTO productDTO) throws SQLException, ClassNotFoundException {
        Boolean update = productDAO.update(convertor.toProduct(productDTO));
        return update;
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        boolean delete = productDAO.delete(id);
        return delete;
    }

    @Override
    public ProductDTO search(String id) throws SQLException, ClassNotFoundException {
        ProductDTO productDTO = convertor.fromProduct(productDAO.search(id));
        return productDTO;
    }

    @Override
    public ArrayList<ProductDTO> getAllProduct() throws ClassNotFoundException, SQLException {
        return (ArrayList<ProductDTO>) productDAO.getAllProduct().stream().map(product -> convertor.fromProduct(product)).collect(Collectors.toList());

    }

    @Override
    public ArrayList<String> loadProductIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> strings = productDAO.loadProductIds();
        return strings;
    }

    @Override
    public ArrayList<String> loadProductNames() throws SQLException, ClassNotFoundException {
        ArrayList<String> strings = productDAO.loadProductNames();
        return strings;
    }

    @Override
    public ProductDTO searchName(String name) throws SQLException, ClassNotFoundException {
        ProductDTO productDTO = convertor.fromProduct(productDAO.searchName(name));
        return productDTO;
    }
}
