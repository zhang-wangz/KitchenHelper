package model;

import java.io.Serializable;

public class BeanRecipeStep implements Serializable {
    private static final long serialVersionUID = 1L;

    private String recipeId;
    private Integer stepId;
    private String stepDes;

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public Integer getStepId() {
        return stepId;
    }

    public void setStepId(Integer stepId) {
        this.stepId = stepId;
    }

    public String getStepDes() {
        return stepDes;
    }

    public void setStepDes(String stepDes) {
        this.stepDes = stepDes;
    }
}
