package model;

public class BeanRecipeBrow {

    private String browId;
    private String browUserId;
    private String recipeId;
    private boolean isBrow;



    public String getBrowId() {
        return browId;
    }

    public void setBrowId(String browId) {
        this.browId = browId;
    }

    public boolean getIsBrow() {
        return isBrow;
    }

    public void setIsBrow(boolean brow) {
        isBrow = brow;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public String getBrowUserId() {
        return browUserId;
    }

    public void setBrowUserId(String browUserId) {
        this.browUserId = browUserId;
    }
}
