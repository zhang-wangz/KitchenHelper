package control;

import model.BeanFoodOrder;
import model.BeanOrderDetail;
import model.BeanRecipe;
import model.BeanRecipematerials;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;
import util.BaseException;

import java.util.List;

import static util.HibernateUtil.getSession;

public class RecipeController {




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

    public List<BeanRecipematerials> loadAllDetails() {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanRecipematerials");
        List<BeanRecipematerials> list = query.list();
        tx.commit();
        session.close();
        return list;
    }




}
