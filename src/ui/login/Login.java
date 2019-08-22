package ui.login;

import com.jfoenix.controls.JFXButton;
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
    void quit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void userLogin(ActionEvent event) {

        String userName = username.getText();
        //String passWord = DigestUtils.sha1Hex(password.getText());
        String passWord = password.getText();
        System.out.println(passWord);
        try {
            KitchenSystemUtil.operatorController.login(userName, passWord);
            loadMain();
            BeanOperator operator =  KitchenSystemUtil.operatorController.findOperatorByName(userName);
            BeanOperator.currentOperator = operator;
            ((Stage)username.getScene().getWindow()).close();
        } catch (BaseException e) {
            username.getStyleClass().add("wrong-match");
            password.getStyleClass().add("wrong-match");
        }
    }

    void loadMain(){
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/ui/main/main.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("����С���ֹ���ϵͳ");
            stage.setScene(new Scene(parent));
            stage.setResizable(false);
            stage.show();
            stage.setOnCloseRequest(e->System.exit(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
