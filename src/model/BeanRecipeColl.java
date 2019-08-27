package model;

public class BeanRecipeColl {
    private String collId;
    private String collUserId;
    private String recipeId;

    public String getCollUserId() {
        return collUserId;
    }

    public void setCollUserId(String collUserId) {
        this.collUserId = collUserId;
    }

    private boolean isColl = false;

    public String getCollId() {
        return collId;
    }

    public void setCollId(String collId) {
        this.collId = collId;
    }

    public boolean getIsColl() {
        return isColl;
    }

    public void setIsColl(boolean coll) {
        isColl = coll;
    }


    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }


}
