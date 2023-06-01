package lk.ijse.rangabeautysalon.dao;

import lk.ijse.rangabeautysalon.dao.exceptions.ConstraintViolationException;
import lk.ijse.rangabeautysalon.entity.SuperEntity;

import java.io.Serializable;
import java.sql.SQLException;


public interface CrudDAO<T extends Serializable ,TM extends SuperEntity> extends SuperDAO{

    T save(TM entity) throws ConstraintViolationException, SQLException, ClassNotFoundException;

    T update(TM entity) throws ConstraintViolationException, SQLException, ClassNotFoundException;

}
