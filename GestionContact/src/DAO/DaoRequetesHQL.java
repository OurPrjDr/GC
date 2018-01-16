package DAO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import Util.HibernateUtil;
import Domains.Address;
import Domains.Contact;
import Domains.ContactGroup;
import Domains.PhoneNumber;

public class DaoRequetesHQL {

    Session session = HibernateUtil.getSessionFactory().getCurrentSession();

    // TODO : a finir
    // reqFromJointure
    public Set<Contact> reqFromParam(String firstname, String lastname,
            String email, Address adr, Set<PhoneNumber> tels,
            Set<ContactGroup> groupes, String numSiret) {

        session.beginTransaction();

        StringBuffer reqS = new StringBuffer();
        reqS.append("select c from Contact c, Address a where c.id=a.id and "
                + "c.firstName like :firstName "
                + "and c.lastName like :lastName " + "and c.email like :email");
        Query requete = session.createQuery(reqS.toString())
                .setString("firstName", "%" + firstname + "%")
                .setString("lastName", "%" + lastname + "%")
                .setString("email", "%" + email + "%");

        List<Contact> resultats = (List<Contact>) requete.list();
        session.getTransaction().commit();

        Set<Contact> res = new HashSet<Contact>(resultats);

        return res;
    }

    // TODO : a finir
    public Set<Contact> reqCriteria(String firstname, String lastname,
            String email, Address adr, Set<PhoneNumber> tels,
            Set<ContactGroup> groupes, String numSiret) {

        session.beginTransaction();

        List<Contact> resultats = session.createCriteria(Contact.class)
                .add(Restrictions.like("firstName", "%" + firstname + "%"))
                .add(Restrictions.like("lastName", "%" + lastname + "%"))
                .add(Restrictions.like("email", "%" + email + "%"))
                .setMaxResults(5).addOrder(Order.asc("firstName")).list();

        session.getTransaction().commit();

        Set<Contact> res = new HashSet<Contact>(resultats);

        return res;
    }

    // TODO : a finir
    public Set<Contact> reqExample(String firstname, String lastname,
            String email, Address adr, Set<PhoneNumber> tels,
            Set<ContactGroup> groupes, String numSiret) {

        session.beginTransaction();

        Contact c = new Contact("%"+firstname+"%", "%"+lastname+"%", "%"+email+"%");

        Example example = Example.create(c).ignoreCase().enableLike();

        List<Contact> resultats = session.createCriteria(Contact.class)
                .add(example).list();

        session.getTransaction().commit();

        Set<Contact> res = new HashSet<Contact>(resultats);

        return res;
    }
}