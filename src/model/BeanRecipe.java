package model;

public class BeanRecipe {
    private String recipeId;
    private String recipeName;
    private String contriUsr;
    private String recipeDes;
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
