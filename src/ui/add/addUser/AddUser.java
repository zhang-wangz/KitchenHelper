package ui.add.addUser;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import control.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.BeanMyUser;
import util.BaseException;
import util.KeyUtil;
import util.KitchenSystemUtil;

import java.sql.Timestamp;


public class AddUser {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXTextField userpwd;

    @FXML
    private JFXTextField tel;

    @FXML
    private  JFXTextField sex;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField address;

    @FXML
    private JFXButton btnRegister;

    @FXML
    private JFXButton btnCancel;

    private UserController userController;

    private Boolean isEditMode = false;
    private String userId = "0";
    private Timestamp regiscopy;
    private  String contact;

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void userAdd(ActionEvent event) {
        String tUsername = username.getText();
        String tUserpwd = userpwd.getText();
        String tsex = sex.getText();
        String tTel = tel.getText();
        String tEmail = email.getText();
        String rAddress = address.getText();
        BeanMyUser user = new BeanMyUser();
        user.setUserName(tUsername);
        if(!isEditMode) {
            user.setUserId(KeyUtil.getUniqueKey());
            user.setUserContact("暂无");
        }
        else {
            user.setUserId(userId);
            user.setUserContact(contact);
        }
        user.setPwd(tUserpwd);
        user.setUserTel(tTel);
        user.setUserEmail(tEmail);
        user.setCity(rAddress);
        user.setSex(tsex);

        user.setUserContact("暂无");
        if(!isEditMode) {
            user.setRegisterDate(new Timestamp(System.currentTimeMillis()));
        }
        String contentText = "";
        try{
            if(isEditMode){
                user.setRegisterDate(regiscopy);
                KitchenSystemUtil.update(user);
                contentText = "修改成功";
            }else{
                KitchenSystemUtil.userController.addUser(user);
                contentText = "添加成功";
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText(contentText);
            alert.showAndWait();
        }catch (BaseException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        cancel(new ActionEvent());
    }

    public void inflateUI(BeanMyUser user){
        username.setText(user.getUserName());
        tel.setText(user.getUserTel().toString());
        email.setText(user.getUserEmail());
        address.setText(user.getUserContact());
        userpwd.setText(user.getPwd());
        sex.setText(user.getSex());

        isEditMode = true;
        this.userId = user.getUserId();
        this.regiscopy = user.getRegisterDate();
        this.contact = user.getUserContact();
    }

}
