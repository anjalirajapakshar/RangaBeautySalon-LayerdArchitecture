package lk.ijse.rangabeautysalon.service.custom.Impl;
import lk.ijse.rangabeautysalon.dao.DaoFactory;
import lk.ijse.rangabeautysalon.dao.DaoTypes;
import lk.ijse.rangabeautysalon.dao.custom.DressingDAO;
import lk.ijse.rangabeautysalon.db.DBConnection;
import lk.ijse.rangabeautysalon.dto.CustomerDTO;
import lk.ijse.rangabeautysalon.dto.DressingDTO;
import lk.ijse.rangabeautysalon.service.custom.*;
import lk.ijse.rangabeautysalon.service.util.Convertor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class DressingServiceImpl implements DressingService{

    private final DressingDAO dressingDAO;
    private final Convertor convertor;
    private final Connection connection;

    public DressingServiceImpl() throws SQLException, ClassNotFoundException {
        connection= DBConnection.getInstance().getConnection();
        dressingDAO= DaoFactory.getInstance().getDAO(connection, DaoTypes.DRESSING );
        convertor=new Convertor();
    }

    @Override
    public boolean save(DressingDTO dressingDTO) throws SQLException, ClassNotFoundException {
        Boolean save = dressingDAO.save(convertor.toDressing(dressingDTO));
        return save;
    }

    @Override
    public boolean update(DressingDTO dressingDTO) throws SQLException, ClassNotFoundException {
        Boolean update = dressingDAO.update(convertor.toDressing(dressingDTO));
        return update;
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        boolean delete = dressingDAO.delete(id);
        return delete;
    }

    @Override
    public DressingDTO search(String id) throws SQLException, ClassNotFoundException {
        DressingDTO dressingDTO = convertor.fromDressing(dressingDAO.search(id));
        return dressingDTO;
    }

    @Override
    public ArrayList<DressingDTO> getAllDressing() throws ClassNotFoundException, SQLException {
        return (ArrayList<DressingDTO>) dressingDAO.getAllDressing().stream().map(dressing -> convertor.fromDressing(dressing)).collect(Collectors.toList());
    }

    @Override
    public ArrayList<String> loadDressingIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> dressingIds = dressingDAO.loadDressingIds();
        return dressingIds;
    }

    @Override
    public ArrayList<String> loadDressingNames() throws SQLException, ClassNotFoundException {
        ArrayList<String> dressingNames = dressingDAO.loadDressingNames();
        return dressingNames;
    }

    @Override
    public DressingDTO searchForObject(String name) throws SQLException, ClassNotFoundException {
        DressingDTO dressingDTO = convertor.fromDressing(dressingDAO.searchForObject(name));
        if(dressingDTO != null ){
            return dressingDTO;
        }
        return null;
    }

    @Override
    public String searchName(String type) throws SQLException, ClassNotFoundException {
        String name = dressingDAO.searchName(type);
        return name;
    }
}
