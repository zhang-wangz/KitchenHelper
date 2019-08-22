package control;

import model.BeanMyUser;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;
import util.BaseException;

import java.util.ArrayList;
import java.util.List;

import static util.HibernateUtil.getSession;

public class UserController {
    public List<BeanMyUser> loadAll(){
        List<BeanMyUser> list = new ArrayList<BeanMyUser>();
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        String hql = "from BeanMyUser";
        Query query = session.createQuery(hql);
        list = query.list();
        tx.commit();
        session.close();
        return list;
    }

    public BeanMyUser findUserByName(String name) throws BaseException {
        BeanMyUser user = new BeanMyUser();
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        String hql = "from BeanMyUser b where b.userName = :name";
        Query query = session.createQuery(hql);
        query.setParameter("name", name);
        if(query.list().size() == 0){
            throw new BaseException("�û�δ�ҵ�");
        }else{
            user = (BeanMyUser) query.list().get(0);
        }
        tx.commit();
        session.close();
        return user;
    }

    public BeanMyUser findUserById(int id) {
        BeanMyUser user = null;
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query  = session.createQuery("from BeanMyUser b where  b.userId = :id");
        query.setParameter("id",id);
        user = (BeanMyUser) query.list().get(0);
        tx.commit();
        session.close();
        return user;
    }

    public void addUser(BeanMyUser user) throws BaseException {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from BeanMyUser b where b.userName = :name");
        query.setParameter("name", user.getUserName());
        if(query.list().size() != 0){
            throw new BaseException("�û����Ѵ���");
        }else {
            session.save(user);
        }
        tx.commit();
        session.close();
    }

    public void delUser(Integer userId){
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("delete BeanMyUser u where u.userId = :id");
        query.setParameter("id",userId);
        query.executeUpdate();
        tx.commit();
        session.close();
    }

    public int getMyUserTotalCount(){
        return (int)getSession().createCriteria("BeanMyUser")
                .setProjection(Projections.rowCount())
                .uniqueResult();
    }

    public List<BeanMyUser> search(String text) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from BeanMyUser b where b.userName like :text");
        query.setParameter("text","%"+text+"%");
        List<BeanMyUser> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }
}
