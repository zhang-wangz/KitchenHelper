package control;

import model.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;
import util.BaseException;
import util.KeyUtil;
import util.KitchenSystemUtil;

import java.security.Key;
import java.util.List;

import static util.HibernateUtil.getSession;

public class RecipeController {

    RecipeCollAndBrowController recipeCollAndBrowController =new RecipeCollAndBrowController();


    public List<BeanRecipematerials> loadRecipeDetailByRecipeId(String orderId) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanRecipematerials b where b.recipeId = :id");
        query.setParameter("id", orderId);
        List<BeanRecipematerials> list = query.list();
        tx.commit();
        session.close();
        return list;
    }


    public void delRecipeDetail(BeanRecipematerials detail) {

        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("delete BeanRecipematerials b where b.foodId = :foodId and b.recipeId = :recipeId");
        query.setParameter("foodId", detail.getFoodId());
        query.setParameter("recipeId", detail.getRecipeId());
        query.executeUpdate();

//        query = session.createQuery("update BeanFoodInfo b set b.foodNum = b.foodNum -:num where foodId =:foodId");
//        query.setParameter("num",detail.getNumOfFood());
//        query.setParameter("foodId",detail.getFoodId());

//        query.executeUpdate();

        tx.commit();
        session.close();
    }


    public void delBeanRecipe(String recipeId) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("delete  BeanRecipe  where recipeId = :recipeId");
        query.setParameter("recipeId", recipeId);
        query.executeUpdate();

        tx.commit();
        session.close();
    }

    public void delBeanRecipeComment(String recipeId) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("delete  BeanRecipeComment  where recipeId = :recipeId");
        query.setParameter("recipeId", recipeId);
        query.executeUpdate();
        tx.commit();
        session.close();
    }

    public void delBeanRecipeColl(String recipeId) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("delete  BeanRecipeColl  where recipeId = :recipeId");
        query.setParameter("recipeId", recipeId);
        query.executeUpdate();
        tx.commit();
        session.close();
    }

    public void delBeanRecipeBrow(String recipeId) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("delete  BeanRecipeBrow  where recipeId = :recipeId");
        query.setParameter("recipeId", recipeId);
        query.executeUpdate();
        tx.commit();
        session.close();
    }

    public void delBeanRecipeStep(String recipeId) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("delete  BeanRecipeStep  where recipeId = :recipeId");
        query.setParameter("recipeId", recipeId);
        query.executeUpdate();
        tx.commit();
        session.close();
    }

    public void delBeanRecipematerials(String recipeId) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("delete  BeanRecipematerials  where recipeId = :recipeId");
        query.setParameter("recipeId", recipeId);
        query.executeUpdate();
        tx.commit();
        session.close();
    }

    public BeanRecipematerials findRecipeDetail (BeanRecipematerials detail){
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanRecipematerials b where b.foodId = :foodId and b.recipeId = :recipeId");
        query.setParameter("foodId", detail.getFoodId());
        query.setParameter("recipeId", detail.getRecipeId());
        BeanRecipematerials recipematerials = null;
        if(query.list().size()!=0)  recipematerials = (BeanRecipematerials) query.list().get(0);
        return recipematerials;

    }


    public List<BeanRecipe> loadAll(){
        List<BeanRecipe> lsit = null;
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanRecipe ");
        lsit = query.list();
        tx.commit();
        session.close();
        return lsit;
    }

    public BeanRecipe findRecipeByRecipeId(String recipeId){
        Session session = getSession();
        BeanRecipe recipe = null;
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanRecipe b where b.recipeId = :recipeId");
        query.setParameter("recipeId",recipeId);
        int size = query.list().size();
        if(size == 0) recipe = null;
        else recipe = (BeanRecipe) query.list().get(0);
        tx.commit();
        session.close();
        return recipe;
    }

    public List<BeanRecipe> findRecipeByuserName(String userName){
        Session session = getSession();
        List<BeanRecipe> recipeList = null;
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanRecipe b where b.contriUsr = :userName");
        query.setParameter("userName",userName);
        int size = query.list().size();
        if(size == 0) recipeList = null;
        else recipeList =  query.list();
        tx.commit();
        session.close();
        return recipeList;
    }

    public List<BeanRecipematerials> findRecipeByFoodId(String foodId){
        Session session = getSession();
        List<BeanRecipematerials> recipeList = null;
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanRecipematerials b where b.foodId = :foodId");
        query.setParameter("foodId",foodId);
        int size = query.list().size();
        if(size == 0) recipeList = null;
        else recipeList =  query.list();
        tx.commit();
        session.close();
        return recipeList;
    }



    public List<BeanRecipematerials> loadAllDetails() {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanRecipematerials");
        List<BeanRecipematerials> list = query.list();
        tx.commit();
        session.close();
        return list;
    }


    public void AddRecipeBrow(BeanRecipe recipe){
        String usrId;
        if(BeanMyUser.currentUser == null) usrId = (BeanOperator.currentOperator.getOpId());
        else usrId = (BeanMyUser.currentUser.getUserId());

        BeanRecipeBrow recipeBrow = new BeanRecipeBrow();
        recipeBrow.setBrowUserId(usrId);
        KitchenSystemUtil.recipeCollAndBrowController.changeBrowSig(usrId,recipe.getRecipeId());
        if(recipeCollAndBrowController.findrecipeBrowByBrIdandUsrId(usrId,recipe.getRecipeId()) == null)
            recipeBrow.setBrowId(KeyUtil.getUniqueKey());
        else return;


        recipeBrow.setIsBrow(true);
        recipeBrow.setRecipeId(recipe.getRecipeId());
        KitchenSystemUtil.save(recipeBrow);
    }

    public String AddRecipeColl(BeanRecipe recipe){

        String usrId;
        if(BeanMyUser.currentUser == null) usrId = (BeanOperator.currentOperator.getOpId());
        else usrId = (BeanMyUser.currentUser.getUserId());
        BeanRecipeColl recipeColl = new BeanRecipeColl();
        recipeColl.setCollUserId(usrId);
        if(recipeCollAndBrowController.findrecipeCollByreIdandUsrId(usrId,recipe.getRecipeId()) == null){
            recipeColl.setCollId(KeyUtil.getUniqueKey());
        }
        else return recipeColl.getCollUserId();//如果找到了就返用户名，表示不用再存了

        recipeColl.setIsColl(true);
        recipeColl.setRecipeId(recipe.getRecipeId());
        KitchenSystemUtil.save(recipeColl);
        return "ok";
    }


    public List<BeanRecipe> search(String text) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from BeanRecipe b where b.recipeName like :text");
        query.setParameter("text","%"+text+"%");
        List<BeanRecipe> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    public static void main(String[] args) throws BaseException {
        RecipeController recipeController = new RecipeController();
//        UserController userController = new UserController();
//        BeanRecipe recipe = recipeController.findRecipeByRecipeId("1566799194372454792");
//        BeanMyUser.currentUser = userController.findUserByName("wzw");
//        String i = recipeController.AddRecipeColl(recipe);
        recipeController.delBeanRecipe("1566990061983189127");
//        System.out.println(i);
    }



}
