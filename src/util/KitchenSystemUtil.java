package util;

import control.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import static util.HibernateUtil.getSession;

public class KitchenSystemUtil {


    public static OperatorController operatorController = new OperatorController();
    public static UserController userController = new UserController();
    public static FoodInfoController foodInfoController = new FoodInfoController();
    public static FoodTypeController foodTypeController = new FoodTypeController();
    public static FoodOrderController foodOrderController = new FoodOrderController();
    public static BuyFoodController buyFoodController = new BuyFoodController();
    public static RecipeController recipeController = new RecipeController();

//    public static AppointmentController appointmentController = new AppointmentController();
//    public static OrderController orderController = new OrderController();
//    public static ProductController productController = new ProductController();
//    public static ServiceController serviceController = new ServiceController();



    public static void update(Object o) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.update(o);
        tx.commit();
        session.close();
    }

    public static void save(Object o) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.save(o);
        tx.commit();
        session.close();
    }

    public static int getCount(String cate){
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from " + cate);
        int size = query.list().size();
        tx.commit();
        session.close();
        return size;
    }

    public static void main(String[] args) {
        int size = getCount("BeanBuyFood");
        System.out.println(size);
    }
}
