package control;

import model.BeanRecipeComment;
import model.BeanRecipematerials;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

import static util.HibernateUtil.getSession;

public class RecipeCommentController {
    public List<BeanRecipeComment> loadRecipeCommentDetailByRecipeId(String orderId) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanRecipeComment b where b.recipeId = :id");
        query.setParameter("id", orderId);
        List<BeanRecipeComment> list = query.list();
        tx.commit();
        session.close();
        return list;
    }


    public List<BeanRecipeComment> loadAll() {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanRecipeComment");
        List<BeanRecipeComment> list = query.list();
        tx.commit();
        session.close();
        return list;
    }

    public BeanRecipeComment delRecipeComment(String recipeId,String usrId) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("delete  from BeanRecipeComment b where b.recipeId = :rid and b.userId= :usrId");
        query.setParameter("rid", recipeId);
        query.setParameter("usrId", usrId);
        BeanRecipeComment list = (BeanRecipeComment) query.list().get(0);
        tx.commit();
        session.close();
        return list;
    }

    public BeanRecipeComment findRecipeCommentByRecipeIdandUsrId(String recipeId,String usrId) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanRecipeComment b where b.recipeId = :rid and b.userId= :usrId");
        query.setParameter("rid", recipeId);
        query.setParameter("usrId", usrId);
        int size = query.list().size();
        if(size == 0 ) return  null;
        BeanRecipeComment list = (BeanRecipeComment) query.list().get(0);
        tx.commit();
        session.close();
        return list;
    }




}
