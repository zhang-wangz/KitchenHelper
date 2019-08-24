package ui.add.addFoodOrder;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.BeanFoodInfo;
import model.BeanFoodOrder;
import model.BeanMyUser;
import model.BeanOrderDetail;
import util.BaseException;
import util.KeyUtil;
import util.KitchenSystemUtil;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class AddFoodOrder implements Initializable{

    @FXML
    private AnchorPane rootPane;


    @FXML
    private JFXDatePicker foodOrderSendTime;

//wait
    @FXML
    private JFXTextField foodDiscount;


    @FXML
    private  JFXTextField foodOrderUsrName;

    @FXML
    private JFXTextField foodOrderSendAddress;

    @FXML
    private JFXTextField foodOrderUserTel;

    @FXML
    private JFXTextField foodOrderOrderStatus;


    @FXML
    private JFXComboBox<BeanFoodInfo> productBox1;

    @FXML
    private JFXTextField productNum1;

    @FXML
    private Label productPrice1;

    @FXML
    private JFXComboBox<BeanFoodInfo> productBox2;

    @FXML
    private JFXTextField productNum2;

    @FXML
    private Label productPrice2;

    @FXML
    private JFXComboBox<BeanFoodInfo> productBox3;

    @FXML
    private JFXTextField productNum3;

    @FXML
    private Label productPrice3;

    @FXML
    private JFXComboBox<BeanFoodInfo> productBox4;

    @FXML
    private JFXTextField productNum4;

    @FXML
    private Label productPrice4;


    @FXML
    private JFXButton btnOk;


    @FXML
    private HBox hbox1;

    @FXML
    private HBox hbox2;

    @FXML
    private HBox hbox3;

    @FXML
    private HBox hbox4;


    private Integer discount = 1;
    private BeanFoodInfo product1 = null;
    private BeanFoodInfo product2 = null;
    private BeanFoodInfo product3 = null;
    private BeanFoodInfo product4 = null;
    private LocalDate date = null;

    private int num1 = 0;
    private int num2 = 0;
    private int num3 = 0;
    private int num4 = 0;
    private int price1 = 0;
    private int price2 = 0;
    private int price3 = 0;
    private int price4 = 0;
    private boolean isEditMode = false;
    private String foodid1 = "0";
    private String orderid1 =  "0";
    private String foodid2 = "0";
    private String orderid2 = "0";
    private String foodid3 = "0";
    private String orderid3 = "0";
    private String foodid4 = "0";
    private String orderid4 = "0";

    private BeanFoodOrder order= null;
    private JFXDepthManager jfxDepthManager = null;

    private ObservableList<BeanFoodInfo> products = FXCollections.observableArrayList();

    private ObservableList<BeanMyUser> users = FXCollections.observableArrayList();

//    @FXML
//    void appointmentDate2Selected(ActionEvent event) {
//        date = foodOrderSendTime.getValue();
//    }


    private ObservableList<BeanFoodInfo> getProduct(){
        ObservableList<BeanFoodInfo> products = FXCollections.observableArrayList();
        List<BeanFoodInfo> list = KitchenSystemUtil.foodInfoController.loadAll();
        for (BeanFoodInfo e: list){
            products.add(e);
        }
        return products;
    }


    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }


    @FXML
    void orderAdd(ActionEvent event) throws BaseException {
        if(foodOrderSendAddress == null || product1 == null || num1 == 0  || foodOrderUserTel == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("缺少信息");
            alert.showAndWait();
            return;
        }

        product1NumInput(new ActionEvent());
        product1Selected(new ActionEvent());
        product2NumInput(new ActionEvent());
        product2Selected(new ActionEvent());
        product3NumInput(new ActionEvent());
        product3Selected(new ActionEvent());
        product4NumInput(new ActionEvent());
        product4Selected(new ActionEvent());


        if(!isEditMode)
            order = new BeanFoodOrder();



//        order.setOrderPrice(price1+price2+price3+price4);
//        order.setOrderNum(num1+num2+num3+num4);
        order.setOrderStatus(0);
        order.setOrderId(KeyUtil.getUniqueKey());
        order.setSendAddress(foodOrderSendAddress.getText());
//        order.setSendTime();  wait
        order.setUserTel(foodOrderUserTel.getText());

        //会有找不到用户的错误产生可能
        order.setUserId(KitchenSystemUtil.userController.findUserByName(foodOrderUsrName.getText()).getUserId());


        if(isEditMode){
            KitchenSystemUtil.update(order);
        }else{
            KitchenSystemUtil.save(order);
        }

        if(product1!=null && num1 !=0){
            BeanOrderDetail detail = new BeanOrderDetail();
            detail.setOrderId(order.getOrderId());
            detail.setFoodId(product1.getFoodId());
            detail.setNum(num1);
            detail.setPrice(Integer.parseInt(productPrice1.getText()));
            detail.setDiscount(Integer.parseInt(foodDiscount.getText()));
            if(isEditMode){
                detail.setOrderId(orderid1);
                detail.setFoodId(foodid1);
                KitchenSystemUtil.update(detail);
            }else{
                KitchenSystemUtil.save(detail);
            }
        }

        if(product2!=null && num2 !=0){
            BeanOrderDetail detail = new BeanOrderDetail();
            detail.setOrderId(order.getOrderId());
            detail.setFoodId(product2.getFoodId());
            detail.setNum(num2);
            detail.setPrice(Integer.parseInt(productPrice2.getText()));
            detail.setDiscount(Integer.parseInt(foodDiscount.getText()));
            if(isEditMode){
                detail.setOrderId(orderid2);
                detail.setFoodId(foodid2);
                KitchenSystemUtil.update(detail);
            }else{
                KitchenSystemUtil.save(detail);
            }
        }

        if(product3!=null && num3 !=0){
            BeanOrderDetail detail = new BeanOrderDetail();
            detail.setOrderId(order.getOrderId());
            detail.setFoodId(product3.getFoodId());
            detail.setNum(num3);
            detail.setPrice(Integer.parseInt(productPrice3.getText()));
            detail.setDiscount(Integer.parseInt(foodDiscount.getText()));

            if(isEditMode){
                detail.setOrderId(orderid3);
                detail.setFoodId(foodid3);
                KitchenSystemUtil.update(detail);
            }else{
                KitchenSystemUtil.save(detail);
            }
        }


        if(product4!=null && num4 !=0){
            BeanOrderDetail detail = new BeanOrderDetail();
            detail.setOrderId(order.getOrderId());
            detail.setFoodId(product4.getFoodId());
            detail.setNum(num4);
            detail.setPrice(Integer.parseInt(productPrice4.getText()));
            detail.setDiscount(Integer.parseInt(foodDiscount.getText()));
            if(isEditMode){
                detail.setOrderId(orderid4);
                detail.setFoodId(foodid4);
                KitchenSystemUtil.update(detail);
            }else{
                KitchenSystemUtil.save(detail);
            }
        }

        cancel(new ActionEvent());
        return;
    }

    @FXML
    void product2NumInput(ActionEvent event) {
        try{
            num2 = Integer.parseInt(productNum2.getText());
        }catch (Exception e){
            //do nothing
        }

        if(num2 == 0 || product2 == null){
            productPrice2.setText("价格");
        }else {
            price2 = product2.getFoodPrice()*num2;
            productPrice2.setText(String.valueOf(price2)+"元");
//            orderTotalPrice.setText("总价:"+String.valueOf(price1+price2+price3+price4)+"元");
        }
    }

    @FXML
    void product2Selected(ActionEvent event) {
        product2 = productBox2.getSelectionModel().getSelectedItem();

        if(num2 == 0 || product2 == null){
            productPrice2.setText("价格");
        }else {
            price2 = product2.getFoodPrice()*num2;
            productPrice2.setText(String.valueOf(price2)+"元");
//            orderTotalPrice.setText("总价:"+String.valueOf(price1+price2+price3+price4)+"元");
        }

    }

    @FXML
    void product3NumInput(ActionEvent event) {
        try{
            num3 = Integer.parseInt(productNum3.getText());
        }catch (Exception e){
            //do nothing
        }

        if(num3 == 0 || product3 == null){
            productPrice3.setText("价格");
        }else {
            price3 = product3.getFoodPrice()*num3;
            productPrice3.setText(String.valueOf(price3)+"元");
//            orderTotalPrice.setText("总价:"+String.valueOf(price1+price2+price3+price4)+"元");
        }
    }

    @FXML
    void product3Selected(ActionEvent event) {
        product3 = productBox3.getSelectionModel().getSelectedItem();

        if(num3 == 0 || product3 == null){
            productPrice3.setText("价格");
        }else {
            price3 = product3.getFoodPrice()*num3;
            productPrice3.setText(String.valueOf(price3)+"元");
//            orderTotalPrice.setText("总价:"+String.valueOf(price1+price2+price3+price4)+"元");
        }
    }

    @FXML
    void product4NumInput(ActionEvent event) {
        try{
            num4 = Integer.parseInt(productNum4.getText());
        }catch (Exception e){
            //do nothing
        }

        if(num4 == 0 || product4 == null){
            productPrice4.setText("价格");
        }else {
            price4 = product4.getFoodPrice()*num4;
            productPrice4.setText(String.valueOf(price4)+"元");
//            orderTotalPrice.setText("总价:"+String.valueOf(price1+price2+price3+price4)+"元");

        }
    }

    @FXML
    void product4Selected(ActionEvent event) {
        product4 = productBox4.getSelectionModel().getSelectedItem();

        if(num4 == 0 || product4 == null){
            productPrice4.setText("");
        }else {
            price4 = product4.getFoodPrice()*num4;
            productPrice4.setText(String.valueOf(price4)+"元");
//            orderTotalPrice.setText("总价:"+String.valueOf(price1+price2+price3+price4)+"元");
        }
    }

    @FXML
    void product1NumInput(ActionEvent event) {
        try{
            num1 = Integer.parseInt(productNum1.getText());
        }catch (Exception e){
            //do nothing
        }

        if(num1 == 0 || product1 == null){
            productPrice1.setText("价格");
        }else {
            price1 = product1.getFoodPrice()*num1;
            productPrice1.setText(String.valueOf(price1)+"元");
//            orderTotalPrice.setText("总价:"+String.valueOf(price1+price2+price3+price4)+"元");
        }
    }

    @FXML
    void product1Selected(ActionEvent event) {
        product1 = productBox1.getSelectionModel().getSelectedItem();

        if(num1 == 0 || product1 == null){
            productPrice1.setText("价格");
        }else {
            price1 = product1.getFoodPrice()*num1;
            productPrice1.setText(String.valueOf(price1)+"元");
//            orderTotalPrice.setText("总价:"+String.valueOf(price1+price2+price3+price4)+"元");
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        jfxDepthManager = new JFXDepthManager();
        jfxDepthManager.setDepth(hbox1,2);
        jfxDepthManager.setDepth(hbox2,2);
        jfxDepthManager.setDepth(hbox3,2);
        jfxDepthManager.setDepth(hbox4,2);

        this.products = getProduct();
//        this.users = getUser();
        productBox1.setItems(products);
        productBox2.setItems(products);
        productBox3.setItems(products);
        productBox4.setItems(products);
//        userBox.setItems(users);

        RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator();
        NumberValidator numberValidator = new NumberValidator();
        requiredFieldValidator.setMessage("此为必填项");
        numberValidator.setMessage("必须为纯数字");

        productBox1.getValidators().add(requiredFieldValidator);
        productNum1.getValidators().add(requiredFieldValidator);
        productNum1.getValidators().add(numberValidator);
        productNum2.getValidators().add(numberValidator);
        productNum3.getValidators().add(numberValidator);
        productNum4.getValidators().add(numberValidator);
        foodOrderUsrName.getValidators().add(requiredFieldValidator);
//        userBox.getValidators().add(requiredFieldValidator);


        foodOrderUsrName.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    foodOrderUsrName.validate();
                }
            }
        });

        productBox1.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    productBox1.validate();
                }
            }
        });

        productNum1.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    productNum1.validate();
                }
            }
        });

        productNum2.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    productNum2.validate();
                }
            }
        });

        productNum3.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    productNum3.validate();
                }
            }
        });

        productNum4.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    productNum4.validate();
                }
            }
        });
    }

    public void inflateUI(BeanFoodOrder order) {
        List<BeanOrderDetail> details = KitchenSystemUtil.orderController.loadDetailByOrderId(order.getOrderId());
//        userBox.setValue(order.getOrderUser());
        foodOrderUsrName.setText(KitchenSystemUtil.userController.findUserById(order.getOrderId()).getUserName());
        int size = details.size();
        this.isEditMode = true;
        if(size == 1){
            product1 = details.get(0).getProduct();
            productBox1.setValue(product1);
            num1 = details.get(0).getProdNum();
            productNum1.setText(String.valueOf(num1));
            orderTotalPrice.setText("总价:"+String.valueOf(order.getOrderPrice())+"元");
            detailId1 = details.get(0).getDetailId();
        }else if(size == 2) {
            product1 = details.get(0).getProduct();
            productBox1.setValue(product1);
            num1 = details.get(0).getProdNum();
            productNum1.setText(String.valueOf(num1));
            orderTotalPrice.setText("总价:"+String.valueOf(order.getOrderPrice())+"元");
            detailId1 = details.get(0).getDetailId();
            product2 = details.get(1).getProduct();
            productBox2.setValue(product2);
            num2 = details.get(1).getProdNum();
            productNum2.setText(String.valueOf(num2));
            orderTotalPrice.setText("总价:"+String.valueOf(order.getOrderPrice())+"元");
            detailId2 = details.get(1).getDetailId();
        }else if(size == 3){
            product1 = details.get(0).getProduct();
            productBox1.setValue(product1);
            num1 = details.get(0).getProdNum();
            productNum1.setText(String.valueOf(num1));
            orderTotalPrice.setText("总价:"+String.valueOf(order.getOrderPrice())+"元");
            detailId1 = details.get(0).getDetailId();
            product2 = details.get(1).getProduct();
            productBox2.setValue(product2);
            num2 = details.get(1).getProdNum();
            productNum2.setText(String.valueOf(num2));
            orderTotalPrice.setText("总价:"+String.valueOf(order.getOrderPrice())+"元");
            detailId2 = details.get(1).getDetailId();
            product3 = details.get(2).getProduct();
            productBox3.setValue(product3);
            num3 = details.get(2).getProdNum();
            productNum3.setText(String.valueOf(num3));
            orderTotalPrice.setText("总价:"+String.valueOf(order.getOrderPrice())+"元");
            detailId3 = details.get(2).getDetailId();
        }else if(size == 3){
            product1 = details.get(0).getProduct();
            productBox1.setValue(product1);
            num1 = details.get(0).getProdNum();
            productNum1.setText(String.valueOf(num1));
            orderTotalPrice.setText("总价:"+String.valueOf(order.getOrderPrice())+"元");
            detailId1 = details.get(0).getDetailId();
            product2 = details.get(1).getProduct();
            productBox2.setValue(product2);
            num2 = details.get(1).getProdNum();
            productNum2.setText(String.valueOf(num2));
            orderTotalPrice.setText("总价:"+String.valueOf(order.getOrderPrice())+"元");
            detailId2 = details.get(1).getDetailId();
            product3 = details.get(2).getProduct();
            productBox3.setValue(product3);
            num3 = details.get(2).getProdNum();
            productNum3.setText(String.valueOf(num3));
            orderTotalPrice.setText("总价:"+String.valueOf(order.getOrderPrice())+"元");
            detailId3 = details.get(2).getDetailId();
            product4 = details.get(3).getProduct();
            productBox4.setValue(product4);
            num4 = details.get(3).getProdNum();
            productNum4.setText(String.valueOf(num4));
            orderTotalPrice.setText("总价:"+String.valueOf(order.getOrderPrice())+"元");
            detailId4 = details.get(3).getDetailId();
        }

        this.order = order;

    }
}
