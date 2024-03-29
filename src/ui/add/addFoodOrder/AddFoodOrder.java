package ui.add.addFoodOrder;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import enums.FoodOrderStatusEnum;
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
import util.EnumUtils;
import util.KeyUtil;
import util.KitchenSystemUtil;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

public class AddFoodOrder implements Initializable{

    @FXML
    private AnchorPane rootPane;


    @FXML
    private JFXDatePicker foodOrderSendTime;

    @FXML
    private Label foodOrderUsrName;

    @FXML
    private JFXTextField foodOrderSendAddress;

    @FXML
    private JFXTextField foodOrderUserTel;

    @FXML
    private Label foodDiscount;


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

    @FXML
    private HBox hbox5;

    private Double discount;
    private BeanFoodInfo product1 = null;
    private BeanFoodInfo product2 = null;
    private BeanFoodInfo product3 = null;
    private BeanFoodInfo product4 = null;
    private LocalDate foodsenddate = null;

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
    private Integer size;

    private BeanFoodOrder order= null;
    private JFXDepthManager jfxDepthManager = null;

    private ObservableList<BeanFoodInfo> products = FXCollections.observableArrayList();

    private ObservableList<BeanMyUser> users = FXCollections.observableArrayList();

    @FXML
    void foodOrderSendTimeSelected(ActionEvent event) {
        foodsenddate = foodOrderSendTime.getValue();
    }


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
        foodOrderSendTimeSelected(new ActionEvent());


        if(!isEditMode) {
            order = new BeanFoodOrder();
            order.setOrderId(KeyUtil.getUniqueKey());
            order.setOrderStatus(0);
            foodOrderUsrName.setText(BeanMyUser.currentUser.getUserName());
            order.setUserId(BeanMyUser.currentUser.getUserId());
        }

        order.setSendAddress(foodOrderSendAddress.getText());
        order.setUserTel(foodOrderUserTel.getText());


//        discount = Double.parseDouble(foodDiscount.getText());

        if(!isEditMode){
            if(foodsenddate.isBefore(LocalDate.now())){
                beforeAlert();
            }else{
                order.setSendTime(Date.valueOf(foodsenddate));
            }
        }else{
            order.setSendTime(Date.valueOf(foodsenddate));
        }

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
            detail.setPrice(price1);
            discount = getDiscout(price1+price2+price3+price4);
            detail.setDiscount(discount);


            if(isEditMode){
                detail.setOrderId(orderid1);
                detail.setFoodId(foodid1);
                if(size == 1)
                    KitchenSystemUtil.update(detail);
                else if(size == 0){
                    KitchenSystemUtil.save(detail);
                }
            }else{
                BeanFoodInfo  beanFoodInfo = KitchenSystemUtil.foodInfoController.findFoodById(detail.getFoodId());
                if(num1 > beanFoodInfo.getFoodNum()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("食材1库存不足，请等待补充后再次购买");
                    alert.showAndWait();
                    cancel(new ActionEvent());
                    return;
                }else{
                    beanFoodInfo.setFoodNum(beanFoodInfo.getFoodNum() - num1);
                    KitchenSystemUtil.update(beanFoodInfo);
                }
                KitchenSystemUtil.save(detail);
            }
        }

        if(product2!=null && num2 !=0){
            BeanOrderDetail detail = new BeanOrderDetail();
            detail.setOrderId(order.getOrderId());
            detail.setFoodId(product2.getFoodId());
            detail.setNum(num2);
            detail.setPrice(price2);
            discount = getDiscout(price1+price2+price3+price4);
            detail.setDiscount(discount);

            if(isEditMode){
                detail.setOrderId(orderid2);
                detail.setFoodId(foodid2);
                if(size == 2)
                    KitchenSystemUtil.update(detail);
                else if(size == 1){
                    KitchenSystemUtil.save(detail);
                }
            }else{
                BeanFoodInfo  beanFoodInfo = KitchenSystemUtil.foodInfoController.findFoodById(detail.getFoodId());
                if(num2 > beanFoodInfo.getFoodNum()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("食材2库存不足，请等待补充后再次购买");
                    alert.showAndWait();
                    cancel(new ActionEvent());
                    return;
                }else{
                    beanFoodInfo.setFoodNum(beanFoodInfo.getFoodNum() - num1);
                    KitchenSystemUtil.update(beanFoodInfo);
                }
                KitchenSystemUtil.save(detail);
            }
        }

        if(product3!=null && num3 !=0){
            BeanOrderDetail detail = new BeanOrderDetail();
            detail.setOrderId(order.getOrderId());
            detail.setFoodId(product3.getFoodId());
            detail.setNum(num3);
            detail.setPrice(price3);
            discount = getDiscout(price1+price2+price3+price4);
            detail.setDiscount(discount);

            if(isEditMode){
                detail.setOrderId(orderid3);
                detail.setFoodId(foodid3);
                if(size == 3)
                    KitchenSystemUtil.update(detail);
                else if(size == 2){
                    KitchenSystemUtil.save(detail);
                }
            }else{
                BeanFoodInfo  beanFoodInfo = KitchenSystemUtil.foodInfoController.findFoodById(detail.getFoodId());
                if(num3 > beanFoodInfo.getFoodNum()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("食材3库存不足，请等待补充后再次购买");
                    alert.showAndWait();
                    cancel(new ActionEvent());
                    return;
                }else{
                    beanFoodInfo.setFoodNum(beanFoodInfo.getFoodNum() - num1);
                    KitchenSystemUtil.update(beanFoodInfo);
                }
                KitchenSystemUtil.save(detail);
            }
        }


        if(product4!=null && num4 !=0){
            BeanOrderDetail detail = new BeanOrderDetail();
            detail.setOrderId(order.getOrderId());
            detail.setFoodId(product4.getFoodId());
            detail.setNum(num4);
            detail.setPrice(price4);
            discount = getDiscout(price1+price2+price3+price4);
            detail.setDiscount(discount);
            if(isEditMode){
                detail.setOrderId(orderid4);
                detail.setFoodId(foodid4);
                if(size == 4)
                    KitchenSystemUtil.update(detail);
                else if(size == 3){
                    KitchenSystemUtil.save(detail);
                }
            }else{
                BeanFoodInfo  beanFoodInfo = KitchenSystemUtil.foodInfoController.findFoodById(detail.getFoodId());
                if(num4 > beanFoodInfo.getFoodNum()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("食材4库存不足，请等待补充后再次购买");
                    alert.showAndWait();
                    cancel(new ActionEvent());
                    return;
//                    throw new BaseException("食材4库存不足，请等待补充后再次购买");
                }else{
                    beanFoodInfo.setFoodNum(beanFoodInfo.getFoodNum() - num1);
                    KitchenSystemUtil.update(beanFoodInfo);
                }
                KitchenSystemUtil.save(detail);
            }
        }

        String contentText = "";
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        if(isEditMode) {
            contentText = "修改成功";

        }else {
            contentText = "添加成功";
        }
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
//            price2 = (int)(product2.getFoodPrice()*num2 * discount);

            foodDiscount.setText("折扣:"+String.valueOf(getDiscout(price1+price2+price3+price4)));
            price2 = (int)(product2.getFoodPrice() * num2* getDiscout(price1+price2+price3+price4));
            productPrice2.setText(String.valueOf(price2)+"元");
        }
    }

    @FXML
    void product2Selected(ActionEvent event) {
        product2 = productBox2.getSelectionModel().getSelectedItem();

        if(num2 == 0 || product2 == null){
            productPrice2.setText("价格");
        }else {
            foodDiscount.setText("折扣:"+String.valueOf(getDiscout(price1+price2+price3+price4)));
            price2 = (int)(product2.getFoodPrice() * num2* getDiscout(price1+price2+price3+price4));
            productPrice2.setText(String.valueOf(price2)+"元");
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


            foodDiscount.setText("折扣:"+String.valueOf(getDiscout(price1+price2+price3+price4)));
            price3 = (int)(product3.getFoodPrice()*num3* getDiscout(price1+price2+price3+price4));
            productPrice3.setText(String.valueOf(price3)+"元");
        }
    }

    @FXML
    void product3Selected(ActionEvent event) {
        product3 = productBox3.getSelectionModel().getSelectedItem();

        if(num3 == 0 || product3 == null){
            productPrice3.setText("价格");
        }else {


            foodDiscount.setText("折扣:"+String.valueOf(getDiscout(price1+price2+price3+price4)));
            price3 = (int)(product3.getFoodPrice()*num3* getDiscout(price1+price2+price3+price4));
            productPrice3.setText(String.valueOf(price3)+"元");
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


            foodDiscount.setText("折扣:"+String.valueOf(getDiscout(price1+price2+price3+price4)));
            price4 = (int)(product4.getFoodPrice()*num4* getDiscout(price1+price2+price3+price4));
            productPrice4.setText(String.valueOf(price4)+"元");

        }
    }

    @FXML
    void product4Selected(ActionEvent event) {
        product4 = productBox4.getSelectionModel().getSelectedItem();

        if(num4 == 0 || product4 == null){
            productPrice4.setText("");
        }else {

            foodDiscount.setText("折扣:"+String.valueOf(getDiscout(price1+price2+price3+price4)));
            price4 = (int)(product4.getFoodPrice()*num4 * getDiscout(price1+price2+price3+price4));
            productPrice4.setText(String.valueOf(price4)+"元");
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
            foodDiscount.setText("折扣:"+String.valueOf(getDiscout(price1+price2+price3+price4)));
            price1 =(int)( product1.getFoodPrice()*num1* getDiscout(price1+price2+price3+price4));
            productPrice1.setText(String.valueOf(price1)+"元");
        }
    }

    @FXML
    void product1Selected(ActionEvent event) {
        product1 = productBox1.getSelectionModel().getSelectedItem();

        if(num1 == 0 || product1 == null){
            productPrice1.setText("价格");
        }else {

            foodDiscount.setText("折扣:"+String.valueOf(getDiscout(price1+price2+price3+price4)));
            price1 =(int)( product1.getFoodPrice()*num1* getDiscout(price1+price2+price3+price4));
            productPrice1.setText(String.valueOf(price1)+"元");
        }

    }

    private  double getDiscout(Integer num){
        double discount = 1;
        if(num>100) {
            discount = 0.9;
        }
        if(num<200 && num >100)  discount = 0.85;
        if(num>200 && num <300)  discount = 0.8;
        if(num > 500) discount = 0.77;
        return  discount;
    }

    private LocalDate Date2LocalDate(java.util.Date dateToConvert) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateToConvert);
        return cal.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        jfxDepthManager = new JFXDepthManager();
        jfxDepthManager.setDepth(hbox1,2);
        jfxDepthManager.setDepth(hbox2,2);
        jfxDepthManager.setDepth(hbox3,2);
        jfxDepthManager.setDepth(hbox4,2);
        jfxDepthManager.setDepth(hbox5,2);

        this.products = getProduct();
//        this.users = getUser();
        productBox1.setItems(products);
        productBox2.setItems(products);
        productBox3.setItems(products);
        productBox4.setItems(products);
//        userBox.setItems(users);

        if(!isEditMode) {
            String usrName;
            if (BeanMyUser.currentUser == null) usrName = BeanOperator.currentOperator.getOpName();
            else usrName = BeanMyUser.currentUser.getUserName();
            foodOrderUsrName.setText("用户:"+usrName);
        }

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
//
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

    public void inflateUI(BeanFoodOrder order) {

        List<BeanOrderDetail> details = KitchenSystemUtil.foodOrderController.loadDetailByOrderId(order.getOrderId());
        foodOrderUsrName.setText(KitchenSystemUtil.userController.findUserById(order.getUserId()).getUserName());
        foodOrderSendAddress.setText(order.getSendAddress());
        foodOrderUserTel.setText(order.getUserTel());
//        foodOrderOrderStatus.setText(EnumUtils.getByCode(order.getOrderStatus(), FoodOrderStatusEnum.class).getMsg());
        int size = 0;
        if(details != null) {
            foodDiscount.setText(details.get(0).getDiscount().toString());
            size = details.size();
        }
        foodOrderSendTime.setValue(Date2LocalDate(order.getSendTime()));


        this.isEditMode = true;

        if(size == 1){
            product1 = KitchenSystemUtil.foodInfoController.findFoodById(details.get(0).getFoodId());
            productBox1.setValue(product1);
            num1 = details.get(0).getNum();
            productNum1.setText(String.valueOf(num1));
            foodid1 = details.get(0).getFoodId();
            orderid1 = details.get(0).getOrderId();
            // orderTotalPrice.setText("总价:"+String.valueOf(order.getOrderPrice())+"元");

        }else if(size == 2) {
            product1 = KitchenSystemUtil.foodInfoController.findFoodById(details.get(0).getFoodId());
            productBox1.setValue(product1);
            num1 = details.get(0).getNum();
            productNum1.setText(String.valueOf(num1));
            foodid1 = details.get(0).getFoodId();
            orderid1 = details.get(0).getOrderId();
            product2 = KitchenSystemUtil.foodInfoController.findFoodById(details.get(1).getFoodId());
            productBox2.setValue(product2);
            num2 = details.get(1).getNum();
            productNum2.setText(String.valueOf(num2));
            foodid2 = details.get(1).getFoodId();
            orderid2 = details.get(1).getOrderId();


        }else if(size == 3){
            product1 = KitchenSystemUtil.foodInfoController.findFoodById(details.get(0).getFoodId());
            productBox1.setValue(product1);
            num1 = details.get(0).getNum();
            productNum1.setText(String.valueOf(num1));
            foodid1 = details.get(0).getFoodId();
            orderid1 = details.get(0).getOrderId();

            product2 = KitchenSystemUtil.foodInfoController.findFoodById(details.get(1).getFoodId());
            productBox2.setValue(product2);
            num2 = details.get(1).getNum();
            productNum2.setText(String.valueOf(num2));
            foodid2 = details.get(1).getFoodId();
            orderid2 = details.get(1).getOrderId();

            product3 = KitchenSystemUtil.foodInfoController.findFoodById(details.get(2).getFoodId());
            productBox3.setValue(product3);
            num3 = details.get(2).getNum();
            productNum3.setText(String.valueOf(num3));
            foodid3 = details.get(2).getFoodId();
            orderid3 = details.get(2).getOrderId();


        }else if(size == 4){
            product1 = KitchenSystemUtil.foodInfoController.findFoodById(details.get(0).getFoodId());
            productBox1.setValue(product1);
            num1 = details.get(0).getNum();
            productNum1.setText(String.valueOf(num1));
            foodid1 = details.get(0).getFoodId();
            orderid1 = details.get(0).getOrderId();

            product2 = KitchenSystemUtil.foodInfoController.findFoodById(details.get(1).getFoodId());
            productBox2.setValue(product2);
            num2 = details.get(1).getNum();
            productNum2.setText(String.valueOf(num2));
            foodid2 = details.get(1).getFoodId();
            orderid2 = details.get(1).getOrderId();

            product3 = KitchenSystemUtil.foodInfoController.findFoodById(details.get(2).getFoodId());
            productBox3.setValue(product3);
            num3 = details.get(2).getNum();
            productNum3.setText(String.valueOf(num3));
            foodid3 = details.get(2).getFoodId();
            orderid3 = details.get(2).getOrderId();

            product4 = KitchenSystemUtil.foodInfoController.findFoodById(details.get(3).getFoodId());
            productBox4.setValue(product4);
            num4= details.get(3).getNum();
            productNum4.setText(String.valueOf(num4));
            foodid4 = details.get(3).getFoodId();
            orderid4 = details.get(3).getOrderId();


        }
        if(details != null)
            foodDiscount.setText("折扣："+ details.get(0).getDiscount());
        else
            foodDiscount.setText("折扣: ");

        this.order = order;
        this.size = size;
    }


    public void inflateUIAdd(BeanRecipe recipe) {
        List<BeanRecipematerials> details = KitchenSystemUtil.recipeController.loadRecipeDetailByRecipeId(recipe.getRecipeId());

        int size = details.size();
        this.isEditMode = false;
        if(size == 1){
            product1 = KitchenSystemUtil.foodInfoController.findFoodById(details.get(0).getFoodId());
            productBox1.setValue(product1);
            num1 = details.get(0).getNumOfFood();
            productNum1.setText(String.valueOf(num1));
            foodid1 = details.get(0).getFoodId();
//            orderid1 = details.get(0).getRecipeId();

        }else if(size == 2) {
            product1 = KitchenSystemUtil.foodInfoController.findFoodById(details.get(0).getFoodId());
            productBox1.setValue(product1);
            num1 = details.get(0).getNumOfFood();
            productNum1.setText(String.valueOf(num1));
            foodid1 = details.get(0).getFoodId();
//            orderid1 = details.get(0).getOrderId();

            product2 = KitchenSystemUtil.foodInfoController.findFoodById(details.get(1).getFoodId());
            productBox2.setValue(product2);
            num2 = details.get(1).getNumOfFood();
            productNum2.setText(String.valueOf(num2));
            foodid2 = details.get(1).getFoodId();
//            orderid2 = details.get(1).getOrderId();


        }else if(size == 3){
            product1 = KitchenSystemUtil.foodInfoController.findFoodById(details.get(0).getFoodId());
            productBox1.setValue(product1);
            num1 = details.get(0).getNumOfFood();
            productNum1.setText(String.valueOf(num1));
            foodid1 = details.get(0).getFoodId();
//            orderid1 = details.get(0).getOrderId();

            product2 = KitchenSystemUtil.foodInfoController.findFoodById(details.get(1).getFoodId());
            productBox2.setValue(product2);
            num2 = details.get(1).getNumOfFood();
            productNum2.setText(String.valueOf(num2));
            foodid2 = details.get(1).getFoodId();
//            orderid2 = details.get(1).getOrderId();

            product3 = KitchenSystemUtil.foodInfoController.findFoodById(details.get(2).getFoodId());
            productBox3.setValue(product3);
            num3 = details.get(2).getNumOfFood();
            productNum3.setText(String.valueOf(num3));
            foodid3 = details.get(2).getFoodId();
//            orderid3 = details.get(2).getOrderId();


        }else if(size == 4){
            product1 = KitchenSystemUtil.foodInfoController.findFoodById(details.get(0).getFoodId());
            productBox1.setValue(product1);
            num1 = details.get(0).getNumOfFood();
            productNum1.setText(String.valueOf(num1));
            foodid1 = details.get(0).getFoodId();
//            orderid1 = details.get(0).getOrderId();

            product2 = KitchenSystemUtil.foodInfoController.findFoodById(details.get(1).getFoodId());
            productBox2.setValue(product2);
            num2 = details.get(1).getNumOfFood();
            productNum2.setText(String.valueOf(num2));
            foodid2 = details.get(1).getFoodId();
//            orderid2 = details.get(1).getOrderId();

            product3 = KitchenSystemUtil.foodInfoController.findFoodById(details.get(2).getFoodId());
            productBox3.setValue(product3);
            num3 = details.get(2).getNumOfFood();
            productNum3.setText(String.valueOf(num3));
            foodid3 = details.get(2).getFoodId();
//            orderid3 = details.get(2).getOrderId();

            product4 = KitchenSystemUtil.foodInfoController.findFoodById(details.get(3).getFoodId());
            productBox4.setValue(product4);
            num4= details.get(3).getNumOfFood();
            productNum4.setText(String.valueOf(num4));
            foodid4 = details.get(3).getFoodId();
//            orderid4 = details.get(3).getOrderId();

        }


    }


}
