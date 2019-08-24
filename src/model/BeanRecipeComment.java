package model;

import java.io.Serializable;
import java.util.Objects;

public class BeanRecipeComment  implements Serializable {
    private static final long serialVersionUID = 1L;

    private String recipeId;
    private String userId;
    private String commentContent = "该菜谱暂时没有评价";
    private Integer browseSig;
    private Integer collSig;
    private Integer commentScore;

    @Override
    public String toString() {
        return getCommentContent();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BeanRecipeComment)) return false;
        BeanRecipeComment that = (BeanRecipeComment) o;
        return Objects.equals(getRecipeId(), that.getRecipeId()) &&
                Objects.equals(getUserId(), that.getUserId()) &&
                Objects.equals(getCommentContent(), that.getCommentContent()) &&
                Objects.equals(getBrowseSig(), that.getBrowseSig()) &&
                Objects.equals(getCollSig(), that.getCollSig()) &&
                Objects.equals(getCommentScore(), that.getCommentScore());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRecipeId(), getUserId(), getCommentContent(), getBrowseSig(), getCollSig(), getCommentScore());
    }

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
