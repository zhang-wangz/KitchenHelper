package ui.mainUser.toolbar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Toolbar {

    @FXML
    void addAdminStarter(ActionEvent event) {
        showWindow("/ui/add/addOperator/addOperator.fxml","��ӹ���Ա");
    }

    @FXML
    void addFoodTypeStarter(ActionEvent event) {
        showWindow("/ui/add/addFoodType/addFoodType.fxml","��ӷ���");
    }
    @FXML
    void addFoodInfoStarter(ActionEvent event) {
        showWindow("/ui/add/addFoodInfo/addFoodInfo.fxml","���ʳ����Ϣ");
    }

    @FXML
    void addUserStarter(ActionEvent event) {
        showWindow("/ui/add/addUser/addUser.fxml","�û�ע��");
    }

    @FXML
    void addFoodOrderStarter(ActionEvent event) {
        showWindow("/ui/add/addFoodOrder/addFoodOrder.fxml","��Ӷ���");
    }

    @FXML
    void addBuyOrderStarter(ActionEvent event) {
        showWindow("/ui/add/addBuyFood/addBuyFood.fxml","��Ӳɹ�");
    }



    private void showWindow(String loc, String title){
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED); //default style
            stage.getIcons().add(new Image("/ui/icons/icon.png"));
            stage.setTitle(title);
            stage.setResizable(false);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
