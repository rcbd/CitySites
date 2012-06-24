/*
 * TampaGov Hackathon 2012 Submission
 * Tampa Tech Marks
 */

package org.rcbd.tampagov.citysites.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.rcbd.tampagov.citysites.dao.CitySiteDao;
import org.rcbd.tampagov.citysites.dao.ReservationDao;
import org.rcbd.tampagov.citysites.model.CitySite;
import org.rcbd.tampagov.citysites.model.Reservation;
import org.rcbd.tampagov.citysites.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



/**
 *
 * @author Robin Curts [github.com/rcbd]
 */
@Controller
@RequestMapping("/reservations")
public class ReservationsController {
    
    @Inject 
    ReservationService reservationService;
    
    @Inject 
    CitySiteDao citySiteDao;
    
    @Inject
    ReservationDao reservationDao;
    
    @RequestMapping("/create/{citySiteId}/{reservationDate}")
    public ModelAndView reserveForm(@PathVariable String citySiteId, @PathVariable String reservationDate) {
        CitySite cs = citySiteDao.getCitySite(citySiteId);
        
        ModelAndView mav = new ModelAndView();
        mav.setViewName("reservations/create");
        mav.addObject("citySite", cs);
        Reservation reservation = new Reservation();
        try {
            Date d = new SimpleDateFormat("MMMM d, yyyy").parse(reservationDate);
            reservation.setReservationDate(d);
        } catch (Exception e) {
            Logger.getLogger(ReservationsController.class).error("", e);
        }
        mav.addObject("reservation", reservation);
                
        return mav;
    }
    
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public ModelAndView saveItem(@Valid Reservation reservation, BindingResult result, HttpServletRequest request) {
        String csId = request.getParameter("citySiteId");
        CitySite cs = citySiteDao.getCitySite(csId);
        reservation.setCitySite(cs);
        reservationDao.saveReservation(reservation);
		ModelAndView mav = new ModelAndView();
        mav.setViewName("reservations/saved");
        mav.addObject("reservation", reservation);
        mav.addObject("citySite", cs);
        return mav;
	}    
    
}
