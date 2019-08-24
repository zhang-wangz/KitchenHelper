package ui.add.addFoodType;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.BeanFoodType;
import util.KeyUtil;
import util.KitchenSystemUtil;

public class AddFoodType {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private JFXTextField foodTypeName;

    @FXML
    private JFXTextField foodTypeDes;

    private Boolean isEditMode = false;
    private String foodTypeId = "0";

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void addFoodType(ActionEvent event) {
        String name = foodTypeName.getText();
        String des = foodTypeDes.getText();
        BeanFoodType foodType = new BeanFoodType();
        foodType.setFoodTypeName(name);
        foodType.setFoodTypeDes(des);
        foodType.setFoodTypeId(KeyUtil.getUniqueKey());
        String contentText = "";

        if(isEditMode){
            foodType.setFoodTypeId(foodTypeId);
            KitchenSystemUtil.update(foodType);
            contentText = "修改成功";
        }else{
            KitchenSystemUtil.save(foodType);
            contentText = "添加成功";
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();

        cancel(new ActionEvent());
    }
    public void inflateUI( BeanFoodType foodType){
        foodTypeName.setText(foodType.getFoodTypeName());
        foodTypeDes.setText(foodType.getFoodTypeDes());
        isEditMode = true;
        this.foodTypeId =foodType.getFoodTypeId();
    }


}
