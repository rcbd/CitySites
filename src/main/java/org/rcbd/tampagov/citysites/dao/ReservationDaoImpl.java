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
import org.rcbd.tampagov.citysites.model.Reservation;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Robin Curts [github.com/rcbd]
 */
@Repository("reservationDao")
@Transactional
public class ReservationDaoImpl implements ReservationDao {

    @Inject
    private SessionFactory sessionFactory;
    
    @Override
    public Reservation getReservation(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Reservation> getReservationsByCitySite(CitySite citySite) {
        return getReservationsByCitySite(citySite.getId());
    }
    
    @Override
    public List<Reservation> getReservationsByCitySite(String citySiteId) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Reservation.class);  
        criteria.createCriteria("citySite").add(Restrictions.eq("id", citySiteId));
        return (List<Reservation>)criteria.list();        
    }
    
    @Override
    @Transactional(readOnly=false)
    public void saveReservation(Reservation reservation) {
        sessionFactory.getCurrentSession().saveOrUpdate(reservation);
    }

}
