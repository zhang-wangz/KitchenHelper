package ui.add.addOperator;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.BeanOperator;
import util.BaseException;
import util.KitchenSystemUtil;

public class AddOperator {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXPasswordField confirmPassword;

    @FXML
    private JFXButton btnRegister;

    @FXML
    private JFXButton cancel;

    private Boolean isEditMode = false;
    private String operatorId = "0";

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void userRegister(ActionEvent event) {
        String tUserName = username.getText();
        String tPassword = password.getText();
        String tConfirmPassword = confirmPassword.getText();

        if(tConfirmPassword.isEmpty() || tPassword.isEmpty() || tUserName.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("��������������");
            alert.showAndWait();
            return;
        }

        if(!tConfirmPassword.equals(tPassword)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("�������벻һ��");
            alert.showAndWait();
            return;
        }
        BeanOperator operator = new BeanOperator();
        operator.setOpLevel(1);
        operator.setOpName(tUserName);
        operator.setOpPwd(tPassword);
        String contentText = "";
        try {
            if(isEditMode){
                operator.setOpId(operatorId);
                KitchenSystemUtil.update(operator);
                contentText = "�޸ĳɹ�";
            } else {
                KitchenSystemUtil.operatorController.addOperator(operator);
                contentText = "��ӳɹ�";
            }
        } catch (BaseException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.show();
        cancel(new ActionEvent());
        return;
    }

    public void inflateUI(BeanOperator operator){
        username.setText(operator.getOpName());
        password.setText(operator.getOpPwd());
        confirmPassword.setText(operator.getOpPwd());
        this.isEditMode = true;
        this.operatorId = operator.getOpId();
    }

}
