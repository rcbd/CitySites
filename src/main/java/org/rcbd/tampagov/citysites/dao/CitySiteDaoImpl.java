/*
 * TampaGov Hackathon 2012 Submission
 * Tampa Tech Marks
 */

package org.rcbd.tampagov.citysites.dao;

import java.util.List;
import javax.inject.Inject;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.rcbd.tampagov.citysites.model.CitySite;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Robin Curts [github.com/rcbd]
 */
@Repository("citySiteDao")
@Transactional
public class CitySiteDaoImpl implements CitySiteDao {

    @Inject
    private SessionFactory sessionFactory;

//    @Autowired
//    public void setSessionfactory(SessionFactory sessionFactory) {
//        hibernateTemplate = new HibernateTemplate(sessionFactory);
//    }
    
    @Override
    @Transactional(readOnly=false)
    public void saveCitySite(CitySite citySite) {
        sessionFactory.getCurrentSession().saveOrUpdate(citySite);
    }

    @Override
    public List<CitySite> listCitySites(CitySite meta) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CitySite.class);  
        
        if (meta != null) {
            if (StringUtils.isNotBlank(meta.getType())) {
                criteria.add(Restrictions.ilike("type", meta.getType()));
            }
            
            if (StringUtils.isNotBlank(meta.getZip())) {
                criteria.add(Restrictions.ilike("zip", meta.getZip()));
            }
            
            if (StringUtils.isNotBlank(meta.getName()) && meta.getName().length() > 2) {
                criteria.add(Restrictions.ilike("name", "%" + meta.getName() + "%"));
            }
        }
        
        return (List<CitySite>)criteria.list();
    }

    @Override
    public CitySite getCitySite(String id) {
        return (CitySite)sessionFactory.getCurrentSession().get(CitySite.class, id);
    }

}
