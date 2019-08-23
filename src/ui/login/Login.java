package ui.login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.BeanMyUser;
import model.BeanOperator;
import util.BaseException;
import util.KitchenSystemUtil;

import java.io.IOException;

public class Login {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXButton login;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXCheckBox isAdmin;

    @FXML
    void quit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void userLogin(ActionEvent event) {

        String userName = username.getText();
        //String passWord = DigestUtils.sha1Hex(password.getText());
        String passWord = password.getText();
        boolean isadmin = isAdmin.isSelected();
        System.out.println(passWord);
        try {
            if(isadmin) {
                KitchenSystemUtil.operatorController.login(userName, passWord);
                loadMain();
                BeanOperator operator = KitchenSystemUtil.operatorController.findOperatorByName(userName);
                BeanOperator.currentOperator = operator;
                ((Stage) username.getScene().getWindow()).close();//获取当前的scene并且销毁
            }else{
                KitchenSystemUtil.userController.login(userName, passWord);
                loadMain();
                BeanMyUser myUser = KitchenSystemUtil.userController.findUserByName(userName);
                BeanMyUser.currentUser = myUser;
                ((Stage) username.getScene().getWindow()).close();//获取当前的scene并且销毁
            }

        } catch (BaseException e) {
            username.getStyleClass().add("wrong-match");
            password.getStyleClass().add("wrong-match");
        }
    }

    void loadMain(){
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/ui/main/main.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("厨房小帮手管理系统");
            stage.setScene(new Scene(parent));
            stage.setResizable(false);
            stage.show();
            stage.setOnCloseRequest(e->System.exit(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
