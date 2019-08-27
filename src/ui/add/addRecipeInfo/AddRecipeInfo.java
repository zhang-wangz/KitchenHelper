package ui.add.addRecipeInfo;

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
import java.util.List;
import java.util.ResourceBundle;

public class AddRecipeInfo implements Initializable{

    @FXML
    private AnchorPane rootPane;


    @FXML
    private  JFXTextField recipeName;


    @FXML
    private JFXTextField recipeDes;


    @FXML
    private Label recipeScore;

    @FXML
    private Label recipeColl;

    @FXML
    private Label recipeBrow;


    @FXML
    private JFXComboBox<BeanFoodInfo> productBox1;

    @FXML
    private JFXTextField productNum1;

    @FXML
    private JFXTextField productunit1;

    @FXML
    private JFXComboBox<BeanFoodInfo> productBox2;

    @FXML
    private JFXTextField productNum2;

    @FXML
    private JFXTextField productunit2;

    @FXML
    private JFXComboBox<BeanFoodInfo> productBox3;

    @FXML
    private JFXTextField productNum3;

    @FXML
    private JFXTextField productunit3;

    @FXML
    private JFXComboBox<BeanFoodInfo> productBox4;

    @FXML
    private JFXTextField productNum4;

    @FXML
    private JFXTextField productunit4;


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



//    private Double discount = 1.0;
    private BeanFoodInfo product1 = null;
    private BeanFoodInfo product2 = null;
    private BeanFoodInfo product3 = null;
    private BeanFoodInfo product4 = null;
//    private LocalDate foodsenddate = null;

    private Integer num1 = 0;
    private Integer num2 = 0;
    private Integer num3 = 0;
    private Integer num4 = 0;
//    private int price1 = 0;
//    private int price2 = 0;
//    private int price3 = 0;
//    private int price4 = 0;
    private BeanMyUser user;
    private BeanOperator operator;
    private boolean isEditMode = false;
    private String foodid1 = "0";
    private String recipeid1 =  "0";
    private String foodid2 = "0";
    private String recipeid2 = "0";
    private String foodid3 = "0";
    private String recipeid3 = "0";
    private String foodid4 = "0";
    private String recipeid4 = "0";

    private BeanRecipe recipe= null;
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



    @FXML
    void RecipeAdd(ActionEvent event) throws BaseException {

        product1 = productBox1.getSelectionModel().getSelectedItem();
        product2 = productBox1.getSelectionModel().getSelectedItem();
        product3 = productBox1.getSelectionModel().getSelectedItem();
        product4 = productBox1.getSelectionModel().getSelectedItem();

        try {
            num1 = Integer.parseInt(productNum1.getText());
        }catch (Exception e){
            //do nothing
        }
        try {
            num2 = Integer.parseInt(productNum2.getText());
        }catch (Exception e){
            //do nothing
        }
        try {
            num3 = Integer.parseInt(productNum3.getText());
        }catch (Exception e){
            //do nothing
        }
        try {
            num4 = Integer.parseInt(productNum4.getText());
        }catch (Exception e){
            //do nothing
        }



//
//        product1NumInput(new ActionEvent());
//        product1Selected(new ActionEvent());
//        product2NumInput(new ActionEvent());
//        product2Selected(new ActionEvent());
//        product3NumInput(new ActionEvent());
//        product3Selected(new ActionEvent());
//        product4NumInput(new ActionEvent());
//        product4Selected(new ActionEvent());
//        foodOrderSendTimeSelected(new ActionEvent());


        if(!isEditMode) {
            recipe = new BeanRecipe();
            recipe.setRecipeId(KeyUtil.getUniqueKey());
            if(BeanOperator.currentOperator == null)    recipe.setContriUsr(BeanMyUser.currentUser.getUserName());
            else recipe.setContriUsr(BeanOperator.currentOperator.getOpName());
            recipe.setRecipeId(KeyUtil.getUniqueKey());
        }else{
            recipe.setRecipeId(recipe.getRecipeId());
            recipe.setContriUsr(recipe.getRecipeName());
        }
        recipe.setRecipeName(recipeName.getText());
        recipe.setRecipeDes(recipeDes.getText());
        //wait 三个收藏,浏览暂时指定为1
        recipe.setRecipeBrow(0);
        recipe.setRecipeColl(0);
        recipe.setRecipeScore(0);

//        discount = Double.parseDouble(foodDiscount.getText());



        if(isEditMode){
            KitchenSystemUtil.update(recipe);
        }else{
            KitchenSystemUtil.save(recipe);
        }


        if(product1 == null || num1 == 0 || recipeName == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("缺少信息");
            alert.showAndWait();
        }


        if(product1!=null && num1 != 0){

            System.out.println(product1.getFoodName() + " "+ num1);


            product1 = productBox1.getSelectionModel().getSelectedItem();
            BeanRecipematerials detail = new BeanRecipematerials();
            detail.setRecipeId(recipe.getRecipeId());
            detail.setFoodId(product1.getFoodId());
            detail.setNumOfFood(num1);
            detail.setWorkAddress(productunit1.getText());

//            System.out.println(detail.getNumOfFood() + detail.getWorkAddress());
//            discount = Double.parseDouble(foodDiscount.getText());
//            detail.setDiscount(discount);


            if(isEditMode){
                detail.setRecipeId(recipeid1);
                detail.setFoodId(foodid1);
                KitchenSystemUtil.update(detail);
            }else{
                KitchenSystemUtil.save(detail);
            }
        }

        if(product2!=null && num2 !=0){
            product2 = productBox2.getSelectionModel().getSelectedItem();
            BeanRecipematerials detail = new BeanRecipematerials();
            detail.setRecipeId(recipe.getRecipeId());
            detail.setFoodId(product2.getFoodId());
            if(num2!=0)
                detail.setNumOfFood(Integer.parseInt(productNum2.getText()));
            detail.setWorkAddress(productunit2.getText());
//            discount = Double.parseDouble(foodDiscount.getText());
//            detail.setDiscount(discount);


            if(isEditMode){
                detail.setRecipeId(recipeid2);
                detail.setFoodId(foodid2);
                KitchenSystemUtil.update(detail);
            }else{
                KitchenSystemUtil.save(detail);
            }
        }

        if(product3!=null && num3 !=0){
            product3 = productBox3.getSelectionModel().getSelectedItem();
            BeanRecipematerials detail = new BeanRecipematerials();
            detail.setRecipeId(recipe.getRecipeId());
            detail.setFoodId(product3.getFoodId());
            detail.setNumOfFood(Integer.parseInt(productNum3.getText()));
            detail.setWorkAddress(productunit3.getText());
//            discount = Double.parseDouble(foodDiscount.getText());
//            detail.setDiscount(discount);


            if(isEditMode){
                detail.setRecipeId(recipeid3);
                detail.setFoodId(foodid3);
                KitchenSystemUtil.update(detail);
            }else{
                KitchenSystemUtil.save(detail);
            }
        }


        if(product4!=null && num4 !=0){
            product4 = productBox4.getSelectionModel().getSelectedItem();
            BeanRecipematerials detail = new BeanRecipematerials();
            detail.setRecipeId(recipe.getRecipeId());
            detail.setFoodId(product4.getFoodId());
            detail.setNumOfFood(Integer.parseInt(productNum4.getText()));
            detail.setWorkAddress(productunit4.getText());
//            discount = Double.parseDouble(foodDiscount.getText());
//            detail.setDiscount(discount);


            if(isEditMode){
                detail.setRecipeId(recipeid4);
                detail.setFoodId(foodid4);
                KitchenSystemUtil.update(detail);
            }else{
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

//    @FXML
//    void product2NumInput(ActionEvent event) {
//        try{
//            num2 = Integer.parseInt(productNum2.getText());
//        }catch (Exception e){
//            //do nothing
//        }

//        if(num2 == 0 || product2 == null){
//            price2.setText("价格");
//        }else {
//            price2 = (int)(product2.getFoodPrice()*num2 * discount);
//            productPrice2.setText(String.valueOf(price2)+"元");
////            orderTotalPrice.setText("总价:"+String.valueOf(price1+price2+price3+price4)+"元");
//        }
//    }
//
//    @FXML
//    void product2Selected(ActionEvent event) {
//        product2 = productBox2.getSelectionModel().getSelectedItem();
//
//        if(num2 == 0 || product2 == null){
//            productPrice2.setText("价格");
//        }else {
//            price2 = (int)(product2.getFoodPrice() * num2* discount);
//            productPrice2.setText(String.valueOf(price2)+"元");
////            orderTotalPrice.setText("总价:"+String.valueOf(price1+price2+price3+price4)+"元");
//        }
//
//    }

//    @FXML
//    void product3NumInput(ActionEvent event) {
//        try{
//            num3 = Integer.parseInt(productNum3.getText());
//        }catch (Exception e){
//            //do nothing
//        }
//
//        if(num3 == 0 || product3 == null){
//            productPrice3.setText("价格");
//        }else {
//            price3 = (int)(product3.getFoodPrice()*num3* discount);
//            productPrice3.setText(String.valueOf(price3)+"元");
////            orderTotalPrice.setText("总价:"+String.valueOf(price1+price2+price3+price4)+"元");
//        }
//    }
//
//    @FXML
//    void product3Selected(ActionEvent event) {
//        product3 = productBox3.getSelectionModel().getSelectedItem();
//
//        if(num3 == 0 || product3 == null){
//            productPrice3.setText("价格");
//        }else {
//            price3 = (int)(product3.getFoodPrice()*num3* discount);
//            productPrice3.setText(String.valueOf(price3)+"元");
////            orderTotalPrice.setText("总价:"+String.valueOf(price1+price2+price3+price4)+"元");
//        }
//    }
//
//    @FXML
//    void product4NumInput(ActionEvent event) {
//        try{
//            num4 = Integer.parseInt(productNum4.getText());
//        }catch (Exception e){
//            //do nothing
//        }
//
//        if(num4 == 0 || product4 == null){
//            productPrice4.setText("价格");
//        }else {
//            price4 =(int) (product4.getFoodPrice()*num4* discount);
//            productPrice4.setText(String.valueOf(price4)+"元");
////            orderTotalPrice.setText("总价:"+String.valueOf(price1+price2+price3+price4)+"元");
//
//        }
//    }
//
//    @FXML
//    void product4Selected(ActionEvent event) {
//        product4 = productBox4.getSelectionModel().getSelectedItem();
//
//        if(num4 == 0 || product4 == null){
//            productPrice4.setText("价格");
//        }else {
//            price4 = (int)(product4.getFoodPrice()*num4* discount);
//            productPrice4.setText(String.valueOf(price4)+"元");
////            orderTotalPrice.setText("总价:"+String.valueOf(price1+price2+price3+price4)+"元");
//        }
//    }
//
//    @FXML
//    void product1NumInput(ActionEvent event) {
//        try{
//            num1 = Integer.parseInt(productNum1.getText());
//        }catch (Exception e){
//            //do nothing
//        }
//
//        if(num1 == 0 || product1 == null){
//            productPrice1.setText("价格");
//        }else {
//            price1 =(int)( product1.getFoodPrice()*num1* discount);
//            productPrice1.setText(String.valueOf(price1)+"元");
////            orderTotalPrice.setText("总价:"+String.valueOf(price1+price2+price3+price4)+"元");
//        }
//    }
//
//    @FXML
//    void product1Selected(ActionEvent event) {
//        product1 = productBox1.getSelectionModel().getSelectedItem();
//
//        if(num1 == 0 || product1 == null){
//            productPrice1.setText("价格");
//        }else {
//            price1 = (int)(product1.getFoodPrice()*num1* discount);
//            productPrice1.setText(String.valueOf(price1)+"元");
////            orderTotalPrice.setText("总价:"+String.valueOf(price1+price2+price3+price4)+"元");
//        }
//
//    }

    private LocalDate Date2LocalDate(java.util.Date dateToConvert) {
        return dateToConvert.toInstant()
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

    public void inflateUI(BeanRecipe recipe) {
        List<BeanRecipematerials> details = KitchenSystemUtil.recipeController.loadRecipeDetailByRecipeId(recipe.getRecipeId());
//        userBox.setValue(order.getOrderUser());
        recipeName.setText(recipe.getRecipeName());
        recipeDes.setText(recipe.getRecipeDes());

        recipeBrow.setText(recipe.getRecipeBrow().toString());
        recipeColl.setText(recipe.getRecipeColl().toString());
        recipeScore.setText(recipe.getRecipeScore().toString());


        int size = details.size();
        this.isEditMode = true;
        if(size == 1){
            product1 = KitchenSystemUtil.foodInfoController.findFoodById(details.get(0).getFoodId());
            productunit1.setText(details.get(0).getWorkAddress());
            productBox1.setValue(product1);
            num1 = details.get(0).getNumOfFood();
            productNum1.setText(String.valueOf(num1));
            foodid1 = details.get(0).getFoodId();
            recipeid1 = details.get(0).getRecipeId();


            // orderTotalPrice.setText("总价:"+String.valueOf(order.getOrderPrice())+"元");
        }else if(size == 2) {
            product1 = KitchenSystemUtil.foodInfoController.findFoodById(details.get(0).getFoodId());
            productunit1.setText(details.get(0).getWorkAddress());
            productBox1.setValue(product1);
            num1 = details.get(0).getNumOfFood();
            productNum1.setText(String.valueOf(num1));
            foodid1 = details.get(0).getFoodId();
            recipeid1 = details.get(0).getRecipeId();

            product2 = KitchenSystemUtil.foodInfoController.findFoodById(details.get(1).getFoodId());
            productunit2.setText(details.get(1).getWorkAddress());
            productBox2.setValue(product2);
            num2 = details.get(1).getNumOfFood();
            productNum2.setText(String.valueOf(num2));
            foodid2 = details.get(1).getFoodId();
            recipeid2 = details.get(1).getRecipeId();


        }else if(size == 3){
            product1 = KitchenSystemUtil.foodInfoController.findFoodById(details.get(0).getFoodId());
            productunit1.setText(details.get(0).getWorkAddress());
            productBox1.setValue(product1);
            num1 = details.get(0).getNumOfFood();
            productNum1.setText(String.valueOf(num1));
            foodid1 = details.get(0).getFoodId();
            recipeid1 = details.get(0).getRecipeId();

            product2 = KitchenSystemUtil.foodInfoController.findFoodById(details.get(1).getFoodId());
            productunit2.setText(details.get(1).getWorkAddress());
            productBox2.setValue(product2);
            num2 = details.get(1).getNumOfFood();
            productNum2.setText(String.valueOf(num2));
            foodid2 = details.get(1).getFoodId();
            recipeid2 = details.get(1).getRecipeId();

            product3 = KitchenSystemUtil.foodInfoController.findFoodById(details.get(2).getFoodId());
            productunit3.setText(details.get(2).getWorkAddress());
            productBox3.setValue(product3);
            num3 = details.get(2).getNumOfFood();
            productNum3.setText(String.valueOf(num3));
            foodid3 = details.get(2).getFoodId();
            recipeid3 = details.get(2).getRecipeId();


        }else if(size == 4){
            product1 = KitchenSystemUtil.foodInfoController.findFoodById(details.get(0).getFoodId());
            productunit1.setText(details.get(0).getWorkAddress());
            productBox1.setValue(product1);
            num1 = details.get(0).getNumOfFood();
            productNum1.setText(String.valueOf(num1));
            foodid1 = details.get(0).getFoodId();
            recipeid1 = details.get(0).getRecipeId();

            product2 = KitchenSystemUtil.foodInfoController.findFoodById(details.get(1).getFoodId());
            productunit2.setText(details.get(1).getWorkAddress());
            productBox2.setValue(product2);
            num2 = details.get(1).getNumOfFood();
            productNum2.setText(String.valueOf(num2));
            foodid2 = details.get(1).getFoodId();
            recipeid2 = details.get(1).getRecipeId();

            product3 = KitchenSystemUtil.foodInfoController.findFoodById(details.get(2).getFoodId());
            productunit3.setText(details.get(2).getWorkAddress());
            productBox3.setValue(product3);
            num3 = details.get(2).getNumOfFood();
            productNum3.setText(String.valueOf(num3));
            foodid3 = details.get(2).getFoodId();
            recipeid3 = details.get(2).getRecipeId();

            product4 = KitchenSystemUtil.foodInfoController.findFoodById(details.get(3).getFoodId());
            productunit4.setText(details.get(3).getWorkAddress());
            productBox4.setValue(product4);
            num4 = details.get(3).getNumOfFood();
            productNum4.setText(String.valueOf(num4));
            foodid4 = details.get(3).getFoodId();
            recipeid4 = details.get(3).getRecipeId();


        }

        this.recipe = recipe;

    }
}
