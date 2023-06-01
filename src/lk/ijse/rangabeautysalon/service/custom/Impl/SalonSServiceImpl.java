package lk.ijse.rangabeautysalon.service.custom.Impl;
import lk.ijse.rangabeautysalon.dao.DaoFactory;
import lk.ijse.rangabeautysalon.dao.DaoTypes;
import lk.ijse.rangabeautysalon.dao.custom.SalonServiceDAO;
import lk.ijse.rangabeautysalon.db.DBConnection;
import lk.ijse.rangabeautysalon.dto.SalonServiceDTO;
import lk.ijse.rangabeautysalon.service.custom.*;
import lk.ijse.rangabeautysalon.service.util.Convertor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class SalonSServiceImpl implements SalonSService{
    private final SalonServiceDAO salonServiceDAO;
    private final Convertor convertor;
    private final Connection connection;

    public SalonSServiceImpl() throws SQLException, ClassNotFoundException {
        connection= DBConnection.getInstance().getConnection();
        salonServiceDAO= DaoFactory.getInstance().getDAO(connection, DaoTypes.SALONSERVICE );
        convertor=new Convertor();
    }

    @Override
    public boolean save(SalonServiceDTO salonServiceDTO) throws SQLException, ClassNotFoundException {
        Boolean save = salonServiceDAO.save(convertor.toSalonService(salonServiceDTO));
        return save;
    }

    @Override
    public boolean update(SalonServiceDTO salonServiceDTO) throws SQLException, ClassNotFoundException {
        Boolean update = salonServiceDAO.update(convertor.toSalonService(salonServiceDTO));
        return update;
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        boolean delete = salonServiceDAO.delete(id);
        return delete;
    }

    @Override
    public SalonServiceDTO search(String id) throws SQLException, ClassNotFoundException {
        SalonServiceDTO salonServiceDTO = convertor.fromSalonService(salonServiceDAO.search(id));
        return salonServiceDTO;
    }

    @Override
    public ArrayList<SalonServiceDTO> getAll() throws ClassNotFoundException, SQLException {
        return (ArrayList<SalonServiceDTO>) salonServiceDAO.getAll().stream().map(salonService -> convertor.fromSalonService(salonService)).collect(Collectors.toList());
    }

    @Override
    public ArrayList<String> loadIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> loadIds = salonServiceDAO.loadIds();
        return loadIds;
    }

    @Override
    public String searchName(String Sname) throws SQLException, ClassNotFoundException {
        String s = salonServiceDAO.searchName(Sname);
        return s;
    }

    @Override
    public ArrayList<String> loadSSNames() throws SQLException, ClassNotFoundException {
        ArrayList<String> ssNames = salonServiceDAO.loadSSNames();
        return ssNames;
    }

    @Override
    public SalonServiceDTO searchForObject(String name) throws SQLException, ClassNotFoundException {
        SalonServiceDTO salonServiceDTO = convertor.fromSalonService(salonServiceDAO.searchForObject(name));
        if(salonServiceDTO != null ){
            return salonServiceDTO;
        }
        return null;
    }
}
