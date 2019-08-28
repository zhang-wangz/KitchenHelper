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
import javafx.scene.image.Image;
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
                BeanMyUser.currentUser = null;
                ((Stage) username.getScene().getWindow()).close();//��ȡ��ǰ��scene��������

            }else{
                KitchenSystemUtil.userController.login(userName, passWord);
                loadMain();
                BeanMyUser myUser = KitchenSystemUtil.userController.findUserByName(userName);
                BeanMyUser.currentUser = myUser;
                BeanOperator.currentOperator = null;
                ((Stage) username.getScene().getWindow()).close();//��ȡ��ǰ��scene��������
            }

        } catch (BaseException e) {
            username.getStyleClass().add("wrong-match");
            password.getStyleClass().add("wrong-match");
        }
    }

    void loadMain(){
        Parent parent = null;
        try {
            if(isAdmin.isSelected()) {
                 parent = FXMLLoader.load(getClass().getResource("/ui/main/main.fxml"));
            }else{
                 parent = FXMLLoader.load(getClass().getResource("/ui/mainUser/main.fxml"));
            }
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

    @FXML
    void addUserStarter(ActionEvent event) {
        showWindow("/ui/add/addUser/addUser.fxml","����û�");
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
