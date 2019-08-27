package ui.add.addRecipeComment;

import com.jfoenix.controls.*;
import com.jfoenix.controls.events.JFXDialogEvent;
import control.RecipeController;
import control.RecipeStepController;
import enums.BrowSigEnums;
import enums.CollSigEnums;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.*;
import util.BaseException;
import util.EnumUtils;
import util.KitchenSystemUtil;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddRecipeComment implements Initializable {

    RecipeController recipeController = new RecipeController();

    RecipeStepController recipeStepController = new RecipeStepController();

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label recipeName;
    @FXML

    private Label recipeCollSig;

    @FXML
    private Label recipeBroeSig;

    @FXML
    private JFXTextField recipeCommentDes;

    @FXML
    private JFXComboBox <Integer> commentScore;


    private ObservableList<Integer> scoreList = FXCollections.observableArrayList();

    private Boolean isEditMode = false;
    private String recipeId = "0";
    private String userId = "0";
    private Integer collSig = 0;
    private Integer browSig = 0;
    private Integer score = 0;

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void addRecipeComment(ActionEvent event) throws BaseException {
        BeanRecipeComment recipeComment = new BeanRecipeComment();
        String des = recipeCommentDes.getText();
        recipeComment.setRecipeId(recipeId);
        recipeComment.setCommentContent(des);

        //初始为0，修改时在里面进行定义
        recipeComment.setBrowseSig(browSig);
        recipeComment.setCollSig(collSig);
        recipeComment.setCommentScore(score);
        String contentText;

        if(isEditMode || KitchenSystemUtil.recipeCommentController.findRecipeCommentByRecipeIdandUsrId(recipeId,userId)!= null){
            recipeComment.setUserId(userId);
            KitchenSystemUtil.update(recipeComment);
            contentText = "评论成功，注意每个用户只会保留一条评论哦～";
        }else{
            if(BeanOperator.currentOperator == null) recipeComment.setUserId(BeanMyUser.currentUser.getUserId());
            else recipeComment.setUserId(BeanOperator.currentOperator.getOpId());
            KitchenSystemUtil.save(recipeComment);
            contentText = "添加成功";
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
        cancel(new ActionEvent());


        KitchenSystemUtil.recipeCollAndBrowController.changeScore(recipeId);
    }
    private ObservableList<Integer> getScoreList(){
        ObservableList<Integer> score = FXCollections.observableArrayList();
        for (int i = 1; i<=10; i++){
            score.add(i);
        }
        return score;
    }


    @FXML
    void ScoreSelected(ActionEvent event) {
         this.score = commentScore.getSelectionModel().getSelectedItem();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.scoreList = getScoreList();
        commentScore.setItems(scoreList);
        recipeCollSig.setText(EnumUtils.getByCode(collSig, CollSigEnums.class).getMsg());
        recipeBroeSig.setText(EnumUtils.getByCode(browSig, BrowSigEnums.class).getMsg());
        ScoreSelected(new ActionEvent());
    }

    //用来生成
    public void inflateUIAdd(BeanRecipe recipe){

        if(BeanMyUser.currentUser == null) userId = BeanOperator.currentOperator.getOpId();
        else userId = BeanMyUser.currentUser.getUserId();
        recipeName.setText(recipe.getRecipeName());
        this.recipeId = recipe.getRecipeId();
        isEditMode = false;
        BeanRecipeComment recipeComment = KitchenSystemUtil.recipeCommentController.findRecipeCommentByRecipeIdandUsrId(recipeId,userId);
        this.collSig = recipeComment == null ? 0:recipeComment.getCollSig();
        this.browSig = recipeComment == null? 0:recipeComment.getBrowseSig();

    }


    //用来编辑
    public void inflateUI( BeanRecipeComment recipeComment){
        recipeName.setText(recipeController.findRecipeByRecipeId(recipeComment.getRecipeId()).getRecipeName());
        recipeCommentDes.setText(recipeComment.getCommentContent());
        this.recipeId = recipeComment.getRecipeId();
        this.userId = recipeComment.getUserId();
        this.recipeBroeSig.setText(recipeComment.getBrowseSig().toString());
        this.recipeCollSig.setText(recipeComment.getCollSig().toString());
        isEditMode = true;
    }




}
