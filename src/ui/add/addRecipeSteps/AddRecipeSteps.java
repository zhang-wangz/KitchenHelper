package ui.add.addRecipeSteps;

import com.jfoenix.controls.JFXTextField;
import control.RecipeController;
import control.RecipeStepController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.BeanFoodType;
import model.BeanRecipe;
import model.BeanRecipeStep;
import util.BaseException;
import util.KeyUtil;
import util.KitchenSystemUtil;

public class AddRecipeSteps {

    RecipeController recipeController = new RecipeController();

    RecipeStepController recipeStepController = new RecipeStepController();

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label recipeName;

    @FXML
    private JFXTextField recipeStepsDes;


    private Boolean isEditMode = false;
    private String recipeId = "0";
    private Integer recipeStepId = 0;

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void addRecipeSteps(ActionEvent event) throws BaseException {
        BeanRecipeStep recipeStep = new BeanRecipeStep();
        String des = recipeStepsDes.getText();
        recipeStep.setRecipeId(recipeId);
        recipeStep.setStepDes(des);
        recipeStep.setStepId(recipeStepId);
        String contentText;

        if(isEditMode){
            KitchenSystemUtil.update(recipeStep);
            contentText = "修改成功";
        }else{
            recipeStep.setStepId(recipeStepController.getNextStepId(recipeId));
            KitchenSystemUtil.save(recipeStep);
            contentText = "添加成功";
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
        cancel(new ActionEvent());
    }


    //用来生成
    public void inflateUIAdd(BeanRecipe recipe){
        recipeName.setText(recipe.getRecipeName());
        this.recipeId = recipe.getRecipeId();
        isEditMode = false;
    }


    //用来编辑
    public void inflateUI( BeanRecipeStep recipeStep){
        recipeName.setText(recipeController.findRecipeByRecipeId(recipeStep.getRecipeId()).getRecipeName());
        recipeStepsDes.setText(recipeStep.getStepDes());
        this.recipeId = recipeStep.getRecipeId();
        this.recipeStepId = recipeStep.getStepId();
        isEditMode = true;
    }


}
