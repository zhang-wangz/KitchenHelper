package control;

import model.BeanFoodType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;
import util.BaseException;
import util.KitchenSystemUtil;

import java.util.ArrayList;
import java.util.List;

import static util.HibernateUtil.getSession;

public class FoodTypeController {
    public List<BeanFoodType> loadAll() {
        List<BeanFoodType> list = new ArrayList<BeanFoodType>();
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        String hql = "from BeanFoodType";
        Query query = session.createQuery(hql);
        list = query.list();
        tx.commit();
        session.close();
        return list;
    }

    public void add(BeanFoodType cate) throws BaseException {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanFoodType b where b.foodTypeName = :name");
        query.setParameter("name", cate.getFoodTypeName());
        if(query.list().size()!=0)
            throw new BaseException("该分类名已存在");
        else
            session.save(cate);
        tx.commit();
        session.close();
    }

    public void delCategory(String id){
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("delete BeanFoodType b where b.foodTypeId = :id");
        query.setParameter("id", id);
        query.executeUpdate();
        tx.commit();
        session.close();
    }

    public BeanFoodType findCategoryById(String id) {
        BeanFoodType category = null;
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanFoodType b where b.foodTypeId = :id");
        query.setParameter("id", id);
        category = (BeanFoodType) query.list().get(0);
        tx.commit();
        session.close();
        return category;
    }

    public int getCategoryTotalCount(){
        return (int)getSession().createCriteria("BeanFoodType")
                .setProjection(Projections.rowCount())
                .uniqueResult();
    }


    public List<BeanFoodType> search(String text) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from BeanFoodType b where b.foodTypeName like :text");
        query.setParameter("text","%"+text+"%");
        List<BeanFoodType> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }


    public static void main(String[] args) {
        List<BeanFoodType> list = KitchenSystemUtil.foodTypeController.loadAll();
        for (BeanFoodType e :list){
            System.out.println(e.getFoodTypeName());
        }
    }
}
