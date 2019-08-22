package ui.main.toolbar;

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
    void AddAppointmentStarter(ActionEvent event) {
        showWindow("/ui/add/addAppointment/addAppointment.fxml","���ԤԼ");
    }

    @FXML
    void addAdminStarter(ActionEvent event) {
        showWindow("/ui/add/addOperator/addOperator.fxml","��ӹ���Ա");
    }

    @FXML
    void addCategoryStarter(ActionEvent event) {
        showWindow("/ui/add/addCategory/addCategory.fxml","��ӷ���");
    }

    @FXML
    void addOrderStater(ActionEvent event) {
        showWindow("/ui/add/addOrder/addOrder.fxml", "��Ӷ���");
    }

    @FXML
    void addPetStarter(ActionEvent event) {
        showWindow("/ui/add/addPet/addPet.fxml","��ӳ���");
    }

    @FXML
    void addProductStarter(ActionEvent event) {
        showWindow("/ui/add/addProduct/addProduct.fxml","��Ӳ�Ʒ");
    }

    @FXML
    void addServiceStarter(ActionEvent event) {
        showWindow("/ui/add/addService/addService.fxml","��ӷ���");
    }

    @FXML
    void addUserStarter(ActionEvent event) {
        showWindow("/ui/add/addUser/addUser.fxml","����û�");
    }

    private void showWindow(String loc, String title){
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
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
