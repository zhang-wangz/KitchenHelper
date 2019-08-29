package control;


import model.BeanFoodOrder;
import model.BeanOrderDetail;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;
import util.BaseException;
import util.KitchenSystemUtil;

import java.util.ArrayList;
import java.util.List;

import static util.HibernateUtil.getSession;

public class FoodOrderController {
    public List<BeanFoodOrder> loadAll(){
        List<BeanFoodOrder> lsit = null;
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanFoodOrder ");
        lsit = query.list();
        tx.commit();
        session.close();
        return lsit;
    }

    public void delOrder(String id) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("delete BeanOrderDetail b where b.orderId = :id");
        query.setParameter("id",id);
        query.executeUpdate();

        query = session.createQuery("delete BeanFoodOrder b where b.orderId = :id");
        query.setParameter("id",id);
        query.executeUpdate();
        tx.commit();
        session.close();
    }

    public BeanFoodOrder findOrderById(String id){
        BeanFoodOrder order = new BeanFoodOrder();
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanFoodOrder b where b.orderId = :id");
        query.setParameter("id", id);
        if(query.list().size()==0){
            return null;
        }
        order = (BeanFoodOrder) query.list().get(0);
        tx.commit();
        session.close();
        return  order;
    }

    public List<BeanFoodOrder> search(String text) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from BeanFoodOrder b where b.orderId like :text");
        query.setParameter("text","%"+text+"%");
        if(query.list().size()==0)return null;
        List<BeanFoodOrder> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    public List<BeanFoodOrder> findOrderByUserId(String userid){
        List<BeanFoodOrder> order = null;
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanFoodOrder b where b.userId = :id");
        query.setParameter("id", userid);
        if(query.list().size()== 0) return null;
        order =  query.list();
        tx.commit();
        session.close();
        return  order;
    }

    public List<BeanOrderDetail> findOrderDetailByFoodId(String foodId){
        List<BeanOrderDetail> order = null;
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanOrderDetail b where b.foodId = :id");
        query.setParameter("id", foodId);
        if(query.list().size()==0) return null;
        order =  query.list();
        tx.commit();
        session.close();
        return  order;
    }


    public List<BeanOrderDetail> loadAllDetails() {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanOrderDetail");
        List<BeanOrderDetail> list = query.list();
        tx.commit();
        session.close();
        return list;
    }



    public List<BeanOrderDetail> loadDetailByOrderId(String orderId) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanOrderDetail b where b.orderId = :id");
        query.setParameter("id", orderId);
        List<BeanOrderDetail> list = null;
        if(query.list().size() == 0) return null;
        list = query.list();
        tx.commit();
        session.close();
        return list;
    }




    public void delOrderDetail(BeanOrderDetail detail) {
        //BeanFoodOrder
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("delete BeanOrderDetail b where b.foodId = :foodid and b.orderId= :orderid");
        query.setParameter("foodid", detail.getFoodId());
        query.setParameter("orderid", detail.getOrderId());
        query.executeUpdate();

//        query = session.createQuery("update BeanOrderDetail b set b.num = b.num -:num, b.price = b.price - :price where orderId =:orderid and foodId = :foodid");
//        query.setParameter("num",detail.getNum());
//        query.setParameter("orderid",detail.getOrderId());
//        query.setParameter("foodid",detail.getFoodId());
//        query.setParameter("price", detail.getNum() * detail.getPrice());
//        query.executeUpdate();

        tx.commit();
        session.close();
    }

    public int getFoodOrderTotalCount(){
        return (int)getSession().createCriteria("BeanFoodOrder")
                .setProjection(Projections.rowCount())
                .uniqueResult();
    }

    public int getOrderCount(Integer cate1){
        Session session = getSession();
        Transaction rx = session.beginTransaction();

//        Query query = session.createQuery("from BeanFoodOrder b where b.orderStatus = '"+ cate+"'");
        Query query = session.createQuery("from BeanFoodOrder b where b.orderStatus = :cate");
        query.setParameter("cate",cate1);
        int size = query.list().size();
        rx.commit();
        session.close();
        return size;
    }


    public int getOrderCountByStatusandUserId(Integer cate1,String userId){
        Session session = getSession();
        Transaction rx = session.beginTransaction();

//        Query query = session.createQuery("from BeanFoodOrder b where b.orderStatus = '"+ cate+"'");
        Query query = session.createQuery("from BeanFoodOrder b where b.orderStatus = :cate and b.userId = :userId");
        query.setParameter("cate",cate1);
        query.setParameter("userId",userId);
        int size = query.list().size();
        rx.commit();
        session.close();
        return size;
    }

    public static void main(String[] args) {
        BeanFoodOrder order = KitchenSystemUtil.foodOrderController.findOrderByUserId("wzw").get(0);
        System.out.println(order.getOrderId());
    }


}
