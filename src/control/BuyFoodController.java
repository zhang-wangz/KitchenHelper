package control;

import model.BeanBuyFood;
import model.BeanFoodOrder;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.BaseException;

import java.util.*;

import static util.HibernateUtil.getSession;

public class BuyFoodController {

    public List<BeanBuyFood> loadBuyDetailByOrderId(String orderId) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanBuyFood b where b.BuyOrderId = :id");
        query.setParameter("id", orderId);
        List<BeanBuyFood> list = query.list();
        tx.commit();
        session.close();
        return list;
    }

    public  List<BeanBuyFood> findOrderById(String id) throws BaseException {

        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanBuyFood b where b.BuyOrderId = :id");
        query.setParameter("id", id);
        if(query.list().size()==0){
            throw new BaseException("采购单不存在");
        }
        List<BeanBuyFood> order  = query.list();
        tx.commit();
        session.close();
        return  order;
    }

    public List<BeanBuyFood> loadDetailByOrderId(String orderId) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanBuyFood b where b.BuyOrderId = :id");
        query.setParameter("id", orderId);
        List<BeanBuyFood> list = query.list();
        tx.commit();
        session.close();
        return list;
    }

    public void delOrderDetail(BeanBuyFood detail) {

        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("delete BeanBuyFood b where b.foodId = :foodId and b.BuyOrderId = :orderId");
        query.setParameter("foodId", detail.getFoodId());
        query.setParameter("orderId", detail.getBuyOrderId());
        query.executeUpdate();

        query = session.createQuery("update BeanFoodInfo b set b.foodNum = b.foodNum -:num where foodId =:foodId");
        query.setParameter("num",detail.getNum());
        query.setParameter("foodId",detail.getFoodId());

        query.executeUpdate();

        tx.commit();
        session.close();
    }

    public void cancelOrderDetail(BeanBuyFood detail) {

        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("delete BeanBuyFood b where b.foodId = :foodId and b.BuyOrderId = :orderId");
        query.setParameter("foodId", detail.getFoodId());
        query.setParameter("orderId", detail.getBuyOrderId());
        query.executeUpdate();

        query = session.createQuery("update BeanFoodInfo b set b.foodNum = b.foodNum -:num where foodId =:foodId");
        query.setParameter("num",detail.getNum());
        query.setParameter("foodId",detail.getFoodId());

        query.executeUpdate();

        tx.commit();
        session.close();
    }


    public List<BeanBuyFood> loadAll(){
        List<BeanBuyFood> lsit = null;
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanBuyFood ");
        lsit = query.list();
        tx.commit();
        session.close();
        return lsit;
    }

    public List<String> loadAllOnlyOne(){
        List<String> lsit = null;
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createSQLQuery("select distinct buy_order_id from buyfood");
        lsit = query.list();
        tx.commit();
        session.close();
        return lsit;
    }


    public void delOrder(String orderId){
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("delete from BeanBuyFood  b where b.BuyOrderId  = :orderid");
        query.setParameter("orderid",orderId);
        tx.commit();
    }
    public int getBuyOrderCount(Integer cate1){
        Session session = getSession();
        Transaction rx = session.beginTransaction();
        Query query = session.createQuery("from BeanBuyFood b where b.status = :cate");
        query.setParameter("cate",cate1);
        int size = query.list().size();
        rx.commit();
        session.close();
        return size;
    }


}
