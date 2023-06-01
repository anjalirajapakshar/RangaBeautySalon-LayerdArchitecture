package lk.ijse.rangabeautysalon.dao.custom.impl;

import lk.ijse.rangabeautysalon.dao.custom.OrderProductDAO;
import lk.ijse.rangabeautysalon.dao.exceptions.ConstraintViolationException;
import lk.ijse.rangabeautysalon.dao.util.CrudUtil;
import lk.ijse.rangabeautysalon.entity.OrderProductDetail;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderProductImpl implements OrderProductDAO {
    private final Connection connection;

    public OrderProductImpl(Connection connection) {

        this.connection = connection;
    }

    @Override
    public Boolean save(OrderProductDetail orderProductDetail) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO orderproductdetail VALUES(?, ?, ?, ?)",
                orderProductDetail.getOrderId(),
                orderProductDetail.getProductId(),
                orderProductDetail.getDate(),
                orderProductDetail.getQty()
        );
    }

    @Override
    public Boolean update(OrderProductDetail orderProductDetail) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("Update orderproductdetail set ProductId=?, date=?, qty=? where Oid=?",
                orderProductDetail.getProductId(),
                orderProductDetail.getDate(),
                orderProductDetail.getQty(),
                orderProductDetail.getProductId()
        );
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        return  CrudUtil.execute("Delete From orderproductdetail where Oid='"+id+"'");
    }

    @Override
    public OrderProductDetail search(String id) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM orderproductdetail WHERE Oid = ?", id);

        if(result.next()) {
            return new OrderProductDetail(
                    result.getString("Oid"),
                    result.getString("ProductId"),
                    result.getString("date"),
                    result.getInt("qty")
            );
        }
        return null;
    }

    @Override
    public ArrayList<OrderProductDetail> getAll() throws ClassNotFoundException, SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * From orderproductdetail");
        ArrayList<OrderProductDetail>orderProductDetailList=new ArrayList<>();
        while(rst.next()){
            OrderProductDetail orderProductDetail = new OrderProductDetail(rst.getString("Oid"), rst.getString("ProductId"), rst.getString("date"), rst.getInt("qty"));
            orderProductDetailList.add(orderProductDetail);
        }
        return orderProductDetailList;
    }

    @Override
    public ArrayList<String> loadIds() throws SQLException, ClassNotFoundException {
        return null;
    }
}
