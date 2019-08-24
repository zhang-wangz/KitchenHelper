package control;

import model.BeanMyUser;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;
import util.BaseException;
import util.KeyUtil;

import java.sql.Timestamp;
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
            throw new BaseException("用户未找到");
        }else{
            user = (BeanMyUser) query.list().get(0);
        }
        tx.commit();
        session.close();
        return user;
    }

    public BeanMyUser findUserById(String id) {
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
            throw new BaseException("用户名已存在");
        }else {
            session.save(user);
        }
        tx.commit();
        session.close();
    }
    public BeanMyUser login(String username, String pwd) throws BaseException {

        Session session = getSession();
        String hql = "from BeanMyUser b where b.userName = :name";
        Query query = session.createQuery(hql);
        query.setParameter("name",username);
        if(query.list().size()==0){
            throw new BaseException("用户不存在");
        }
        BeanMyUser beanMyUser = (BeanMyUser) query.list().get(0);
        if(beanMyUser.getPwd().equals(pwd)){
            return beanMyUser;
        }else{
            throw new BaseException("账号密码不匹配");
        }
    }

    public BeanMyUser register(String username, String pwd1, String pwd2) throws BaseException {

        if (username.length() < 5) {
            throw new BaseException("用户名长度小于5");
        }

        if (!pwd1.equals(pwd2)) {
            System.out.println(pwd1 + " " + pwd2);
            throw new BaseException("两次密码输入不一致");
        }

        if (pwd1.length() < 6) {
            throw new BaseException("密码长度小于6");
        }

            Session session = getSession();
            Transaction tx = session.beginTransaction();
            String hql = "from BeanMyUser b where b.userName = :name";
            Query query = session.createQuery(hql);
            query.setParameter("name", username);
            BeanMyUser beanMyUser = (BeanMyUser) query.uniqueResult();
            BeanMyUser beanMyUser1 = null;
            if (beanMyUser == null) {
                beanMyUser1 = new BeanMyUser();
                beanMyUser1.setRegisterDate(new Timestamp(System.currentTimeMillis()));
                beanMyUser1.setPwd(pwd1);
                beanMyUser1.setSex("等待修改");
                beanMyUser1.setCity("等待修改");
                beanMyUser1.setUserEmail("等待修改");
                beanMyUser1.setUserTel("等待修改");
                beanMyUser1.setUserId(KeyUtil.getUniqueKey());
                beanMyUser1.setUserContact("等待修改");
                beanMyUser1.setUserName(username);
                session.save(beanMyUser1);
            } else {
                throw new BaseException("该用户名已被使用");
            }
            tx.commit();
            session.close();
            return beanMyUser1;

        }


    public void delUser(String userId){
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
