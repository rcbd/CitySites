/*
 * TampaGov Hackathon 2012 Submission
 * Tampa Tech Marks
 */

package org.rcbd.tampagov.citysites.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.rcbd.tampagov.citysites.model.CitySite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Robin Curts [github.com/rcbd]
 */
@Repository("citySiteDao")
@Transactional
public class CitySiteDaoImpl implements CitySiteDao {

    private HibernateTemplate hibernateTemplate;

    @Autowired
    public void setSessionfactory(SessionFactory sessionFactory) {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    
    @Override
    @Transactional(readOnly=false)
    public CitySite saveCitySite(CitySite citySite) {
        hibernateTemplate.saveOrUpdate(citySite);
        return citySite;
    }

    @Override
    public List<CitySite> listCitySites(CitySite meta) {
        return (List<CitySite>) hibernateTemplate.find("from " + meta.getClass().getName());
    }

    @Override
    public CitySite getCitySite(String id) {
        return hibernateTemplate.get(CitySite.class, id);
    }

}
