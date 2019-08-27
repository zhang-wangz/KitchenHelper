package control;

import model.BeanRecipe;
import model.BeanRecipeBrow;
import model.BeanRecipeColl;
import model.BeanRecipeComment;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;
import util.HibernateUtil.*;
import util.KitchenSystemUtil;

import java.util.List;

public class RecipeCollAndBrowController {







    public BeanRecipeBrow findrecipeBrowByBrIdandUsrId(String userId,String recipeId){
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanRecipeBrow where  recipeId=:reId and browUserId = :usrId");
        query.setParameter("reId",recipeId);
        query.setParameter("usrId",userId);
        BeanRecipeBrow beanRecipeBrow ;
        int size = query.list().size();
        if(size == 0) return null;
        else  beanRecipeBrow = (BeanRecipeBrow) query.list().get(0);
        return beanRecipeBrow;
    }


    public BeanRecipeColl findrecipeCollByreIdandUsrId(String userId, String recipeId){
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanRecipeColl where  recipeId=:reId and collUserId = :usrId");
        query.setParameter("reId",recipeId);
        query.setParameter("usrId",userId);
        int size = query.list().size();
        if(size == 0) return null;
        BeanRecipeColl beanRecipeColl = (BeanRecipeColl) query.list().get(0);
        return beanRecipeColl;
    }


    public int countCollnum(String recipeId){
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("select count(recipeId) from BeanRecipeColl where  recipeId=:reId");
        query.setParameter("reId",recipeId);
        long num;
        num = (long) query.list().get(0);
        int i = Integer.parseInt(String.valueOf(num));
        return i;
    }

    public int countBrownum(String recipeId){
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("select count(recipeId) from BeanRecipeBrow where  recipeId=:reId");
        query.setParameter("reId",recipeId);
        long num;
        num = (long) query.list().get(0);
        int i = Integer.parseInt(String.valueOf(num));
        return i;
    }


    public void changeColSig(String usrId,String recipeId){
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery(" from BeanRecipeColl where  recipeId=:reId and collUserId = :usrId");
        query.setParameter("reId",recipeId);
        query.setParameter("usrId",usrId);
        int size = query.list().size();
        if(size == 0 ) return;
        else {
            BeanRecipeColl recipeColl = (BeanRecipeColl) query.list().get(0);
            BeanRecipeComment recipeComment = KitchenSystemUtil.recipeCommentController.findRecipeCommentByRecipeIdandUsrId(recipeColl.getRecipeId(),recipeColl.getCollUserId());
            recipeComment.setCollSig(1);
            KitchenSystemUtil.update(recipeComment);
        }
    }

    public void changeBrowSig(String usrId,String recipeId){
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery(" from BeanRecipeBrow where  recipeId=:reId and browUserId = :usrId");
        query.setParameter("reId",recipeId);
        query.setParameter("usrId",usrId);
        int size = query.list().size();
        if(size == 0 ) return;
        else {
            BeanRecipeBrow recipeBrow = (BeanRecipeBrow) query.list().get(0);
            BeanRecipeComment recipeComment = KitchenSystemUtil.recipeCommentController.findRecipeCommentByRecipeIdandUsrId(recipeBrow.getRecipeId(),recipeBrow.getBrowUserId());
            if(recipeComment !=null && recipeComment.getBrowseSig() == 1) return;
            if(recipeComment != null) {
                recipeComment.setBrowseSig(1);
                KitchenSystemUtil.update(recipeComment);
            }else{
                KitchenSystemUtil.save(recipeComment);
            }
        }
    }

    public void changeScore(String recipeId){
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("select count(commentScore) from BeanRecipeComment  where  recipeId=:reId ").setParameter("reId",recipeId);
        int num = Integer.parseInt(String.valueOf((long)(query.list().get(0))));
        query = session.createQuery("select sum(commentScore) from BeanRecipeComment  where  recipeId=:reId ").setParameter("reId",recipeId);
//        System.out.println(String.valueOf(query.list().get(0)));
        double score = Integer.parseInt(String.valueOf((long)(query.list().get(0))));

        double recipeScore = score * 1.0 / num;

        BeanRecipe recipe = KitchenSystemUtil.recipeController.findRecipeByRecipeId(recipeId);
        recipe.setRecipeScore((int)recipeScore);
        KitchenSystemUtil.update(recipe);
    }






    public static void main(String[] args) {
        RecipeCollAndBrowController recipeCollAndBrowController = new RecipeCollAndBrowController();
        RecipeController recipeController = new RecipeController();
//        recipeCollAndBrowController.changeColSig("1","1566799194372454792");
//      int b  = KitchenSystemUtil.recipeCollAndBrowController.findrecipeBrowByBrIdandUsrId("1","1566799194372454792");
//        System.out.println(b.getBrowUserId());
//        System.out.println(b);

    }


}
