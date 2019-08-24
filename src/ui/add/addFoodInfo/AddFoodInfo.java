package ui.add.addFoodInfo;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.BeanFoodType;
import model.BeanMyUser;
import model.BeanFoodInfo;
import util.KeyUtil;
import util.KitchenSystemUtil;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddFoodInfo implements Initializable{

    @FXML
    private AnchorPane rootPane;

    @FXML
    private JFXTextField foodName;

    @FXML
    private JFXTextField foodPrice;

    @FXML
    private JFXTextField num;


    @FXML
    private JFXComboBox<String> foodTypeNameJFXComboBox;

    @FXML
    private JFXTextField foodDes;


    @FXML
    private JFXButton btnOk;

    private boolean isEditMode = false;
    private String id = "0";


    private ObservableList<String> getFoodTypeName(){
        ObservableList<String> foodTypes = FXCollections.observableArrayList();
        List<BeanFoodType> list = KitchenSystemUtil.foodTypeController.loadAll();
        for(BeanFoodType e : list){
            foodTypes.add(e.getFoodTypeName());
        }
        return foodTypes;
    }


    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void foodInfoAdd(ActionEvent event) {
        String name = foodName.getText();
        String des = foodDes.getText();
        Integer price = Integer.parseInt(foodPrice.getText());
        Integer number = Integer.parseInt(num.getText());


        if(name.isEmpty() || price==null ){
            return;
        }

        BeanFoodInfo foodInfo = new BeanFoodInfo();
        String contentText = "";
        foodInfo.setFoodName(name);
        foodInfo.setFoodPrice(price);
        foodInfo.setFoodNum(number);
        foodInfo.setFoodDes(des);
        foodInfo.setFoodTypeNameOfFoodInfo(foodTypeNameJFXComboBox.getSelectionModel().getSelectedItem());
        if (!isEditMode){
            foodInfo.setFoodId(KeyUtil.getUniqueKey());
        }

        if(isEditMode){
            btnOk.setText("修改");
            foodInfo.setFoodId(id);
            KitchenSystemUtil.update(foodInfo);
            contentText = "修改成功";
        }else{
            btnOk.setText("添加");
            KitchenSystemUtil.save(foodInfo);
            contentText = "添加成功";
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.show();
        cancel(new ActionEvent());
        return;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RequiredFieldValidator validator = new RequiredFieldValidator();
        NumberValidator numberValidator = new NumberValidator();
        validator.setMessage("此为必填项");
        numberValidator.setMessage("必须为纯数字");
        foodPrice.getValidators().add(validator);
        foodName.getValidators().add(validator);
        foodDes.getValidators().add(validator);
        num.getValidators().add(validator);
        num.getValidators().add(numberValidator);
        foodPrice.getValidators().add(numberValidator);


        foodPrice.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    foodPrice.validate();
                }
            }
        });

        foodName.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    foodName.validate();
                }
            }
        });

        foodDes.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    foodDes.validate();
                }
            }
        });

        num.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    num.validate();
                }
            }
        });

        foodTypeNameJFXComboBox.setItems(getFoodTypeName());

    }

    public void inflateUI(BeanFoodInfo foodInfo){
        foodDes.setText(foodInfo.getFoodId());
        foodName.setText(foodInfo.getFoodName());
        foodPrice.setText(foodInfo.getFoodPrice().toString());
        num.setText(foodInfo.getFoodNum().toString());
        foodTypeNameJFXComboBox.setValue(foodInfo.getFoodTypeNameOfFoodInfo());
        this.isEditMode = true;
        this.id = foodInfo.getFoodId();
    }
}
