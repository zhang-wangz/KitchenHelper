package ui.add.addBuyFood;

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
import model.*;
import util.BaseException;
import util.KeyUtil;
import util.KitchenSystemUtil;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class AddBuyFood implements Initializable{

    @FXML
    private AnchorPane rootPane;




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


    private Double discount = 1.0;
    private BeanFoodInfo product1 = null;
    private BeanFoodInfo product2 = null;
    private BeanFoodInfo product3 = null;
    private BeanFoodInfo product4 = null;


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
    private Integer status1 = 0;
    private String foodid2 = "0";
    private Integer status2 = 0;
    private String foodid3 = "0";
    private Integer status3 = 0;
    private String foodid4 = "0";
    private Integer status4 = 0;

    private String buyorderId = "0";
    private Integer size;

    private JFXDepthManager jfxDepthManager = null;

    private ObservableList<BeanFoodInfo> products = FXCollections.observableArrayList();

    private ObservableList<BeanMyUser> users = FXCollections.observableArrayList();

//    @FXML
//    void foodOrderSendTimeSelected(ActionEvent event) {
//        foodsenddate = foodOrderSendTime.getValue();
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

    private void beforeAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("预约时间不能早于当前时间");
        alert.setHeaderText(null);
        alert.showAndWait();
    }


    @FXML
    void orderAdd(ActionEvent event) throws BaseException {
        if( product1 == null || num1 == 0  || discount == null){
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
//        foodOrderSendTimeSelected(new ActionEvent());


        if(!isEditMode) {
            buyorderId = KeyUtil.getUniqueKey();
        }

//
//        if(isEditMode){
//            KitchenSystemUtil.update(buyorder);
//        }else{
//            KitchenSystemUtil.save(buyorder);
//        }

        if(product1!=null && num1 !=0){
            BeanBuyFood buyFood = new BeanBuyFood();
            buyFood.setBuyOrderId(buyorderId);
            buyFood.setFoodId(product1.getFoodId());
            buyFood.setNum(num1);
            buyFood.setStatus(status1);




            if(isEditMode){
                buyFood.setBuyOrderId(buyorderId);
                buyFood.setFoodId(product1.getFoodId());
                if(size == 1)
                    KitchenSystemUtil.update(buyFood);
                else if(size == 0){
                    KitchenSystemUtil.save(buyFood);
                }
            }else{
                KitchenSystemUtil.save(buyFood);
            }
        }

        if(product2!=null && num2 !=0){
            BeanBuyFood buyFood = new BeanBuyFood();
            buyFood.setBuyOrderId(buyorderId);
            buyFood.setFoodId(product2.getFoodId());
            buyFood.setNum(num2);
            buyFood.setStatus(status2);

//            detail.setPrice(price2);


            if(isEditMode){
                buyFood.setBuyOrderId(buyorderId);
                buyFood.setFoodId(product2.getFoodId());
                if(size == 2)
                    KitchenSystemUtil.update(buyFood);
                else if(size == 1){
                    KitchenSystemUtil.save(buyFood);
                }
            }else{
                KitchenSystemUtil.save(buyFood);
            }
        }

        if(product3!=null && num3 !=0){
            BeanBuyFood buyFood = new BeanBuyFood();
            buyFood.setBuyOrderId(buyorderId);
            buyFood.setFoodId(product1.getFoodId());
            buyFood.setNum(num1);
            buyFood.setStatus(status3);



//            buyFood.setPrice(price3);
//            discount = Double.parseDouble(foodDiscount.getText());
//            detail.setDiscount(discount);

            if(isEditMode){
                buyFood.setBuyOrderId(buyorderId);
                buyFood.setFoodId(product3.getFoodId());
                if(size == 3)
                    KitchenSystemUtil.update(buyFood);
                else if(size == 2){
                    KitchenSystemUtil.save(buyFood);
                }
            }else{
                KitchenSystemUtil.save(buyFood);

            }
        }


        if(product4!=null && num4 !=0){
            BeanBuyFood buyFood = new BeanBuyFood();
            buyFood.setBuyOrderId(buyorderId);
            buyFood.setFoodId(product1.getFoodId());
            buyFood.setNum(num1);
            buyFood.setStatus(status4);


//            detail.setPrice(price4);
//            discount = Double.parseDouble(foodDiscount.getText());
//            detail.setDiscount(discount);
            if(isEditMode){
                buyFood.setBuyOrderId(buyorderId);
                buyFood.setFoodId(product4.getFoodId());
                if(size == 4)
                    KitchenSystemUtil.update(buyFood);
                else if(size == 3){
                    KitchenSystemUtil.save(buyFood);
                }
            }else{
                KitchenSystemUtil.save(buyFood);

            }
        }
        String contentText = "";
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        if(isEditMode)
            contentText = "修改成功";
        else
            contentText = "添加成功";
            alert.setContentText(contentText);
        alert.showAndWait();
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
            price2 = (int)(product2.getFoodPrice()*num2 * discount);
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
            price2 = (int)(product2.getFoodPrice() * num2* discount);
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
            price3 = (int)(product3.getFoodPrice()*num3* discount);
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
            price3 = (int)(product3.getFoodPrice()*num3* discount);
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
            price4 =(int) (product4.getFoodPrice()*num4* discount);
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
            price4 = (int)(product4.getFoodPrice()*num4* discount);
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
            price1 =(int)( product1.getFoodPrice()*num1* discount);
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
            price1 = (int)(product1.getFoodPrice()*num1* discount);
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
//        jfxDepthManager.setDepth(hbox5,2);

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
//        foodOrderUsrName.getValidators().add(requiredFieldValidator);
//        foodDiscount.getValidators().add(requiredFieldValidator);
        productNum1.getValidators().add(numberValidator);
        productNum2.getValidators().add(numberValidator);
        productNum3.getValidators().add(numberValidator);
        productNum4.getValidators().add(numberValidator);

//        userBox.getValidators().add(requiredFieldValidator);


//        foodOrderUsrName.focusedProperty().addListener(new ChangeListener<Boolean>() {
//            @Override
//            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//                if(!newValue){
//                    foodOrderUsrName.validate();
//                }
//            }
//        });

//        foodDiscount.focusedProperty().addListener(new ChangeListener<Boolean>() {
//            @Override
//            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//                if(!newValue){
//                    foodDiscount.validate();
//                }
//            }
//        });

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

    public void inflateUI(String orderId) {
        List<BeanBuyFood> details = KitchenSystemUtil.buyFoodController.loadBuyDetailByOrderId(orderId);

        int size = 0;
        this.isEditMode = true;
        size = details.size();
        if(size == 1){
            product1 = KitchenSystemUtil.foodInfoController.findFoodById(details.get(0).getFoodId());
            productBox1.setValue(product1);
            num1 = details.get(0).getNum();
            productNum1.setText(String.valueOf(num1));
            foodid1 = details.get(0).getFoodId();
            status1 = details.get(0).getStatus();
            buyorderId = details.get(0).getBuyOrderId();

//            orderid1 = details.get(0).getOrderId();
//            foodOrderSendTime.setValue(foodsenddate);
            // orderTotalPrice.setText("总价:"+String.valueOf(order.getOrderPrice())+"元");
        }else if(size == 2) {
            product1 = KitchenSystemUtil.foodInfoController.findFoodById(details.get(0).getFoodId());
            productBox1.setValue(product1);
            num1 = details.get(0).getNum();
            productNum1.setText(String.valueOf(num1));
            foodid1 = details.get(0).getFoodId();
            status1 = details.get(0).getStatus();
            buyorderId = details.get(0).getBuyOrderId();

            product2 = KitchenSystemUtil.foodInfoController.findFoodById(details.get(1).getFoodId());
            productBox2.setValue(product2);
            num2 = details.get(1).getNum();
            productNum2.setText(String.valueOf(num2));
            foodid2 = details.get(1).getFoodId();
            status2 = details.get(1).getStatus();
            buyorderId = details.get(1).getBuyOrderId();
//            foodOrderSendTime.setValue(foodsenddate);

        }else if(size == 3){
            product1 = KitchenSystemUtil.foodInfoController.findFoodById(details.get(0).getFoodId());
            productBox1.setValue(product1);
            num1 = details.get(0).getNum();
            productNum1.setText(String.valueOf(num1));
            foodid1 = details.get(0).getFoodId();
            status1 = details.get(0).getStatus();
            buyorderId = details.get(0).getBuyOrderId();

            product2 = KitchenSystemUtil.foodInfoController.findFoodById(details.get(1).getFoodId());
            productBox2.setValue(product2);
            num2 = details.get(1).getNum();
            productNum2.setText(String.valueOf(num2));
            foodid2 = details.get(1).getFoodId();
            status2 = details.get(1).getStatus();
            buyorderId = details.get(1).getBuyOrderId();

            product3 = KitchenSystemUtil.foodInfoController.findFoodById(details.get(2).getFoodId());
            productBox3.setValue(product3);
            num3 = details.get(2).getNum();
            productNum3.setText(String.valueOf(num3));
            foodid3 = details.get(2).getFoodId();
            status3 = details.get(2).getStatus();
            buyorderId = details.get(2).getBuyOrderId();
//            foodOrderSendTime.setValue(foodsenddate);

        }else if(size == 4){
            product1 = KitchenSystemUtil.foodInfoController.findFoodById(details.get(0).getFoodId());
            productBox1.setValue(product1);
            num1 = details.get(0).getNum();
            productNum1.setText(String.valueOf(num1));
            foodid1 = details.get(0).getFoodId();
            status1 = details.get(0).getStatus();
            buyorderId = details.get(0).getBuyOrderId();

            product2 = KitchenSystemUtil.foodInfoController.findFoodById(details.get(1).getFoodId());
            productBox2.setValue(product2);
            num2 = details.get(1).getNum();
            productNum2.setText(String.valueOf(num2));
            foodid2 = details.get(1).getFoodId();
            status2 = details.get(1).getStatus();
            buyorderId = details.get(1).getBuyOrderId();

            product3 = KitchenSystemUtil.foodInfoController.findFoodById(details.get(2).getFoodId());
            productBox3.setValue(product3);
            num3 = details.get(2).getNum();
            productNum3.setText(String.valueOf(num3));
            foodid3 = details.get(2).getFoodId();
            status3 = details.get(3).getStatus();
            buyorderId = details.get(2).getBuyOrderId();

            product4 = KitchenSystemUtil.foodInfoController.findFoodById(details.get(3).getFoodId());
            productBox4.setValue(product4);
            num4 = details.get(3).getNum();
            productNum4.setText(String.valueOf(num3));
            foodid4 = details.get(3).getFoodId();
            status4 = details.get(3).getStatus();
            buyorderId = details.get(3).getBuyOrderId();
//            foodOrderSendTime.setValue(foodsenddate);

        }
        this.size = size;



    }
}
