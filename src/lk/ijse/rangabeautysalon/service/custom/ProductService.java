package lk.ijse.rangabeautysalon.service.custom;

import lk.ijse.rangabeautysalon.dto.ProductDTO;
import lk.ijse.rangabeautysalon.service.SuperService;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductService extends SuperService {
    public boolean save(ProductDTO productDTO) throws SQLException, ClassNotFoundException;
    public boolean update(ProductDTO productDTO) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws ClassNotFoundException, SQLException;
    public ProductDTO search(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<ProductDTO> getAllProduct() throws ClassNotFoundException, SQLException;
    public ArrayList<String> loadProductIds() throws SQLException, ClassNotFoundException;
    public ArrayList<String> loadProductNames() throws SQLException, ClassNotFoundException ;
    public ProductDTO searchName(String name) throws SQLException, ClassNotFoundException ;
}
