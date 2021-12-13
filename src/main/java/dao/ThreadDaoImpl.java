package dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import config.HibernateConfig;
import model.TableOne;
import model.TableTwo;
import model.Value;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import parser.ObjectParseJSON;

import javax.persistence.Query;


public class ThreadDaoImpl implements ThreadDaoAbstract {
    Session session = HibernateConfig.sessionFactory().openSession();
    ObjectParseJSON obj = new ObjectParseJSON();

    @Override
    public void saveNewEntity(Value val) {
        try {
            Transaction tr = session.beginTransaction();
            TableOne t = new TableOne(obj.parseObjToJson(val));

            t.setKey(null);
            session.save(t);
            tr.commit();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(TableOne tableOne) {
        Transaction transaction = session.beginTransaction();
        session.delete(tableOne);
        transaction.commit();
    }

    @Override
    public void merge(TableOne tableOne) {
        Transaction transaction = session.beginTransaction();
        session.persist(tableOne);
        transaction.commit();
    }

    @Override
    public TableOne getEntity() {
        Transaction tr = session.beginTransaction();
        Query query = session.createNativeQuery("SELECT * FROM `t1` ORDER BY RAND() LIMIT 1 ", TableOne.class);
        TableOne tableOne = (TableOne) query.getSingleResult();
        tr.commit();
        return tableOne;
    }

    @Override
    public void saveExistEntity(TableTwo tableTwo) {
        Transaction transaction = session.beginTransaction();
        session.save(tableTwo);
        transaction.commit();
    }
}
