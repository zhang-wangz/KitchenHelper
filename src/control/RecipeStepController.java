package control;

import model.BeanRecipe;
import model.BeanRecipeStep;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.BaseException;
import util.KitchenSystemUtil;

import java.util.List;

import static util.HibernateUtil.getSession;



public class RecipeStepController {

    public List<BeanRecipeStep> loadRecipeStepsByRecipeId(String orderId) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanRecipeStep b where b.recipeId = :id");
        query.setParameter("id", orderId);
        List<BeanRecipeStep> list = query.list();
        tx.commit();
        session.close();
        return list;
    }

    public List<BeanRecipeStep> loadAll(){
        List<BeanRecipeStep> lsit = null;
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanRecipeStep ");
        lsit = query.list();
        tx.commit();
        session.close();
        return lsit;
    }
    public Integer getNextStepId(String recipeId) throws BaseException {
        BeanRecipe recipe = KitchenSystemUtil.recipeController.findRecipeByRecipeId(recipeId);
        if(recipe == null) throw new BaseException("该菜单不存在");
        Integer StepId = 1;
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Object obj = session.createSQLQuery("SELECT max(step_Id) FROM recipestep WHERE recipe_id = ?")
                .setParameter(1,recipeId)
                .list().get(0);
        if(obj !=null) {
            StepId = (Integer)(obj) + 1;
        }else{
            StepId = 1;
        }
        return  StepId;
    }


    public void delRecipeStep(BeanRecipeStep recipeStep) throws BaseException {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("delete from BeanRecipeStep where recipeId = :recipeId and stepId = :stepId");
        query.setParameter("recipeId",recipeStep.getRecipeId());
        query.setParameter("stepId",recipeStep.getStepId());
        query.executeUpdate();

        int max = getNextStepId(recipeStep.getRecipeId());
        if(recipeStep.getStepId() != max){
            query = session.createQuery("UPDATE BeanRecipeStep SET stepId = stepId - 1 WHERE recipeId = :recipeId AND stepId > :stepId");
            query.setParameter("recipeId",recipeStep.getRecipeId());
            query.setParameter("stepId",recipeStep.getStepId());
            query.executeUpdate();
        }
        tx.commit();
        session.close();
    }

    public BeanRecipeStep findRecipeStepByRecipeIdandStepId(String recipeId,Integer stepId){
        Session session = getSession();
        BeanRecipeStep step = null;
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanRecipeStep  where recipeId = :recipeId and stepId = :stepId");
        query.setParameter("recipeId",recipeId);
        query.setParameter("stepId",stepId);
        step = (BeanRecipeStep)query.list().get(0);
        return step;
    }


    public static void main(String[] args) throws BaseException {
        RecipeStepController recipeStepController = new RecipeStepController();
        BeanRecipeStep recipeStep = recipeStepController.findRecipeStepByRecipeIdandStepId("1566799194372454792",1);
        recipeStepController.delRecipeStep(recipeStep);
    }
}
