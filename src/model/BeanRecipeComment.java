package model;

import java.io.Serializable;

public class BeanRecipeComment  implements Serializable {
    private static final long serialVersionUID = 1L;

    private String recipeId;
    private String userId;
    private String commentContent;
    private Integer browseSig;
    private Integer collSig;
    private Integer commentScore;

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Integer getBrowseSig() {
        return browseSig;
    }

    public void setBrowseSig(Integer browseSig) {
        this.browseSig = browseSig;
    }

    public Integer getCollSig() {
        return collSig;
    }

    public void setCollSig(Integer collSig) {
        this.collSig = collSig;
    }

    public Integer getCommentScore() {
        return commentScore;
    }

    public void setCommentScore(Integer commentScore) {
        this.commentScore = commentScore;
    }
}
