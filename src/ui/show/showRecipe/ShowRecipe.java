package ui.show.showRecipe;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import control.RecipeController;
import control.RecipeStepController;
import enums.BrowSigEnums;
import enums.CollSigEnums;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.*;
import util.BaseException;
import util.EnumUtils;
import util.KitchenSystemUtil;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ShowRecipe implements Initializable {

    RecipeController recipeController = new RecipeController();

    RecipeStepController recipeStepController = new RecipeStepController();

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label recipeName;

    @FXML
    private Label recipeDes;

    @FXML
    private Label recipeCollSig;

    @FXML
    private Label recipeBroeSig;

    @FXML
    private Label recipeFoodInfo;

    @FXML
    private Label recipeSteps;

    private String recipeId = "0";
    private String recipeInfoString = "菜谱食材信息:\n";
    private String recipeStepString = "菜谱步骤信息:\n";

    private List<BeanRecipeStep>  recipeStepList = null;
    private List<BeanRecipematerials> recipematerialsList = null;

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void showRecipe(ActionEvent event) throws BaseException {



    }
    private ObservableList<Integer> getScoreList(){
        ObservableList<Integer> score = FXCollections.observableArrayList();
        for (int i = 1; i<=10; i++){
            score.add(i);
        }
        return score;
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    //用来查看
    public void inflateUI( BeanRecipe recipe){
        recipeName.setText("菜谱名称:\n" + recipe.getRecipeName());
        recipeDes.setText("菜谱描述:\n" +recipe.getRecipeDes());
        this.recipeId = recipe.getRecipeId();
        this.recipematerialsList = getRecipeDetail(recipeId);
        this.recipeStepList = getRecipeSteps(recipeId);
        for(BeanRecipematerials e : this.recipematerialsList ){
            this.recipeInfoString = this.recipeInfoString + " " + e.getFoodName() + ":" +" "+e.getNumOfFood().toString()+e.getWorkAddress() + "\n";
        }
        for(BeanRecipeStep e : this.recipeStepList ){
            this.recipeStepString = this.recipeStepString + " 第" + e.getStepId() + "步" + ":" +" "+e.getStepDes()+ "\n";
        }
        recipeFoodInfo.setText(recipeInfoString);
        recipeSteps.setText(recipeStepString);

        this.recipeBroeSig.setText("浏览人数:"+recipe.getRecipeBrow().toString());
        this.recipeCollSig.setText("收藏人数:"+recipe.getRecipeColl().toString());

    }

    private ObservableList<BeanRecipeStep> getRecipeSteps(String recipeId){
        ObservableList<BeanRecipeStep> details = FXCollections.observableArrayList();
        List<BeanRecipeStep> list = KitchenSystemUtil.recipeStepController.loadRecipeStepsByRecipeId(recipeId);
        for (BeanRecipeStep e: list){
            details.add(e);
        }
        return details;
    }


    private ObservableList<BeanRecipematerials> getRecipeDetail(String recipeId){
        ObservableList<BeanRecipematerials> details = FXCollections.observableArrayList();
        List<BeanRecipematerials> list = KitchenSystemUtil.recipeController.loadRecipeDetailByRecipeId(recipeId);
        for (BeanRecipematerials e: list){
            details.add(e);
        }
        return details;
    }





}
