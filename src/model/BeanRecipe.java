package model;

import java.util.Objects;

public class BeanRecipe {
    private String recipeId;
    private String recipeName;
    private String contriUsr;
    private String recipeDes = "该菜谱暂时没有描述";
    private Integer recipeScore;
    private Integer recipeColl;
    private Integer recipeBrow;

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getContriUsr() {
        return contriUsr;
    }

    public void setContriUsr(String contriUsr) {
        this.contriUsr = contriUsr;
    }

    public String getRecipeDes() {
        return recipeDes;
    }

    public void setRecipeDes(String recipeDes) {
        this.recipeDes = recipeDes;
    }

    public Integer getRecipeScore() {
        return recipeScore;
    }

    public void setRecipeScore(Integer recipeScore) {
        this.recipeScore = recipeScore;
    }

    public Integer getRecipeColl() {
        return recipeColl;
    }

    @Override
    public String toString() {
        return getRecipeName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BeanRecipe)) return false;
        BeanRecipe that = (BeanRecipe) o;
        return Objects.equals(getRecipeId(), that.getRecipeId()) &&
                Objects.equals(getRecipeName(), that.getRecipeName()) &&
                Objects.equals(getContriUsr(), that.getContriUsr()) &&
                Objects.equals(getRecipeDes(), that.getRecipeDes()) &&
                Objects.equals(getRecipeScore(), that.getRecipeScore()) &&
                Objects.equals(getRecipeColl(), that.getRecipeColl()) &&
                Objects.equals(getRecipeBrow(), that.getRecipeBrow());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRecipeId(), getRecipeName(), getContriUsr(), getRecipeDes(), getRecipeScore(), getRecipeColl(), getRecipeBrow());
    }

    public void setRecipeColl(Integer recipeColl) {
        this.recipeColl = recipeColl;
    }

    public Integer getRecipeBrow() {
        return recipeBrow;
    }

    public void setRecipeBrow(Integer recipeBrow) {
        this.recipeBrow = recipeBrow;
    }
}
