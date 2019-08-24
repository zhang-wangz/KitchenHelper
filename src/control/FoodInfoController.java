package control;

import model.BeanFoodInfo;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;
import util.KitchenSystemUtil;

import java.util.ArrayList;
import java.util.List;

import static util.HibernateUtil.getSession;

public class FoodInfoController {

    public BeanFoodInfo findFoodByName(String foodName){
        BeanFoodInfo foodInfo = new BeanFoodInfo();
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanFoodInfo b where b.foodName = :name");
        query.setParameter("name", foodName);
        foodInfo = (BeanFoodInfo) query.list().get(0);
        tx.commit();
        session.close();
        return foodInfo;
    }

    public BeanFoodInfo findFoodById(String id) {
        BeanFoodInfo foodInfo = null;
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanFoodInfo  b where b.foodId = :id ");
        query.setParameter("id",id);
        foodInfo = (BeanFoodInfo) query.list().get(0);
        tx.commit();
        session.close();
        return foodInfo;
    }

    public List<BeanFoodInfo> loadAll() {
        List<BeanFoodInfo> list = new ArrayList<BeanFoodInfo>();
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        String hql = "from BeanFoodInfo";
        Query query = session.createQuery(hql);
        list = query.list();
        tx.commit();
        session.close();
        return list;
    }

    public void delFoodInfo(String foodid) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("delete BeanFoodInfo foodinfo where foodinfo.foodId = :id");
        query.setParameter("id",foodid);
        query.executeUpdate();
        tx.commit();
        session.close();
    }

    public int getFoodTotalCount(){
        return (int)getSession().createCriteria(BeanFoodInfo.class)
                .setProjection(Projections.rowCount())
                .uniqueResult();
    }

    public List<BeanFoodInfo> search(String text) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from BeanFoodInfo b where b.foodName like :text");
        query.setParameter("text","%"+text+"%");
        List<BeanFoodInfo> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }


    public static void main(String[] args) {
        List<BeanFoodInfo> list = new ArrayList<>();
        list = KitchenSystemUtil.foodInfoController.loadAll();
        for(BeanFoodInfo e : list){
            System.out.println(e.getFoodName());
        }
    }

}
