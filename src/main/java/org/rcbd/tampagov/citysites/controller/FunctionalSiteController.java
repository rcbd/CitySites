/*
 * TampaGov Hackathon 2012 Submission
 * Tampa Tech Marks
 */

package org.rcbd.tampagov.citysites.controller;

import java.util.List;
import java.util.ResourceBundle;
import javax.inject.Inject;
import javax.validation.Valid;
import org.rcbd.tampagov.citysites.dao.CitySiteDao;
import org.rcbd.tampagov.citysites.model.CitySite;
import org.rcbd.tampagov.citysites.service.CitySiteService;
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
@RequestMapping("/fsc")
public class FunctionalSiteController {
    
//    @Inject     
//    ReservationService reservationService;
    
    @Inject 
    CitySiteDao citySiteDao;
    
//    @Inject
//    ReservationDao reservationDao;
    
    @RequestMapping("/create/{id}")
    public ModelAndView getSiteById(@PathVariable String id) {
        CitySite cs = citySiteDao.getCitySite(id);
        
		ModelAndView mav = new ModelAndView();
		mav.setViewName("reservations/create");
        mav.addObject("citySite", cs);
		return mav;
    }
    
	@RequestMapping(value="/new/${id}", method=RequestMethod.GET)
	public ModelAndView createReservation(@PathVariable String id) {
				
        CitySite cs = citySiteDao.getCitySite(id);
        
		ModelAndView mav = new ModelAndView();
		mav.setViewName("reservations/create");
        mav.addObject("citySite", cs);
		return mav;
	}        
//	
//	@RequestMapping(value="/save", method=RequestMethod.POST)
//	public ModelAndView saveItem(@Valid CitySite cs, BindingResult result) {
//        citySiteService.geocodeCitySite(cs);
//        citySiteDao.saveCitySite(cs);		
//        citySiteService.generateQRCode(cs);
//		return getCitySites();
//	}
}
