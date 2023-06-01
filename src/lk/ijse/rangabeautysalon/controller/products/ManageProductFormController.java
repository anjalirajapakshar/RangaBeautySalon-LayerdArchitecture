package lk.ijse.rangabeautysalon.controller.products;


import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import lk.ijse.rangabeautysalon.dto.ProductDTO;
import lk.ijse.rangabeautysalon.service.ServiceFactory;
import lk.ijse.rangabeautysalon.service.ServiceTypes;
import lk.ijse.rangabeautysalon.service.custom.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManageProductFormController implements Initializable {
    public TableView<ProductDTO> tblProduct;
    public JFXTextField txtProductId;
    public JFXTextField txtName;
    public JFXTextField txtCost;
    public AnchorPane pane;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colCost;
    public TableColumn colQtyOnHand;
    public TextField txtSearchProduct;
    public JFXTextField txtQtyOnHand;

    public ProductService productService;

    public void txtSearchProductOnAction(ActionEvent actionEvent) {
        String id = txtSearchProduct.getText();
        try {
            ProductDTO product = productService.search(id);
            if(product != null) {
                fillData(product);

            } else {
                new Alert(Alert.AlertType.WARNING, "Product Not Found!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        txtSearchProduct.setText("");
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        String id = txtProductId.getText();
        String name = txtName.getText();
        double cost = Double.parseDouble(txtCost.getText());
        int qty=Integer.parseInt(txtQtyOnHand.getText());

        ProductDTO product = new ProductDTO(id, name, cost, qty);
        try {
            boolean isAdded = productService.save(product);
            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Product Added!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadProducts();
        clearFields();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = txtProductId.getText();
        String name = txtName.getText();
        double cost = Double.parseDouble(txtCost.getText());
        int qty=Integer.parseInt(txtQtyOnHand.getText());

        ProductDTO product = new ProductDTO(id, name, cost, qty);

        try {
            boolean isUpdate = productService.update(product);
            if (isUpdate) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Cannot Update Product!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        loadProducts();
        clearFields();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        ProductDTO selectedItem = (ProductDTO) tblProduct.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem);
        String productId = selectedItem.getProductId();
        try {
            boolean isDeleted=productService.delete(productId);
            if(isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Product Deleted!").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Cannot Find Product!").show();
            }
        } catch (ClassNotFoundException | SQLException ex) {
        }
        loadProducts();
        clearFields();
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    private void fillData(ProductDTO product) {
        txtProductId.setText(product.getProductId());
        txtName.setText(product.getName());
        txtCost.setText(String.valueOf(product.getCost()));
        txtQtyOnHand.setText(String.valueOf(product.getQtyOnHand()));
    }

    public void loadProducts()  {
        try {
            ArrayList<ProductDTO> allProducts = productService.getAllProduct();
            ObservableList<ProductDTO> products= FXCollections.observableArrayList();
            for(ProductDTO p : allProducts){
                ProductDTO product=new ProductDTO(p.getProductId(),p.getName(),p.getCost(),p.getQtyOnHand());
                System.out.println(product);
                products.add(product);
            }
            tblProduct.setItems(products);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearFields(){
        txtProductId.setText("");
        txtName.setText("");
        txtCost.setText("");
        txtQtyOnHand.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));



        try {
            this.productService= ServiceFactory.getInstance().getService(ServiceTypes.PRODUCT);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        loadProducts();
    }

    public void MouseClickedOnAction(MouseEvent mouseEvent) {
        ProductDTO selectedItem = (ProductDTO) tblProduct.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem);
        fillData(selectedItem);
    }

}
