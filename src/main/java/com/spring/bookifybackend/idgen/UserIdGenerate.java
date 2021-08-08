package com.spring.bookifybackend.idgen;

import com.spring.bookifybackend.entities.User;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;

import java.io.Serializable;

public class UserIdGenerate extends IdentityGenerator {
    /*@Override
    public Serializable generate(SharedSessionContractImplementor s, Object obj) {
        if (obj == null) throw new HibernateException(new NullPointerException()) ;

        if ((((User) obj).getId()) == null) {//id is null it means generate ID
            Serializable id = super.generate(s, obj) ;
            return id;
        } else {
            return ((User) obj).getId();//id is not null so using assigned id.

        }
    }*/

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        Serializable id = session.getEntityPersister(null, object).getClassMetadata().getIdentifier(object, session);
        return id != null ? id : super.generate(session, object);
    }
}
