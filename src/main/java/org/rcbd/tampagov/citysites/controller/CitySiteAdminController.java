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
@RequestMapping("/admin")
public class CitySiteAdminController {
    
    ResourceBundle csBundle = ResourceBundle.getBundle("CitySites");
    
    @Inject
    private CitySiteDao citySiteDao;
    
    @Inject
    private CitySiteService citySiteService;
    
    private String[] citySiteTypeChoices = { "External", "Historical", "Functional", "Live" };
    
	@RequestMapping("")
	public ModelAndView noMapping() {
		return getCitySites();
	}
    
    @RequestMapping(value="/qr/{id}", method=RequestMethod.GET)
    public ModelAndView viewQRCode(@PathVariable String id) {
        CitySite cs = citySiteDao.getCitySite(id);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/qr-view");
        mav.addObject("citySite", cs);
        mav.addObject("qrFile", cs.getCitySiteDestination());
        
        return mav;
    }

	@RequestMapping(value="/map/{id}", method=RequestMethod.GET)
	public ModelAndView viewCitySiteMap(@PathVariable String id) {
		
		CitySite cs = citySiteDao.getCitySite(id);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/map");
		mav.addObject("citySite", cs);
		return mav;
	}    
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView getCitySites() {
        CitySite metaSearch = new CitySite();
        List<CitySite> citySites = (List<CitySite>)citySiteDao.listCitySites(metaSearch);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/list");
		mav.addObject("citySites", citySites);
		return mav;
	}
    
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editCitySite(@PathVariable String id) {
		
		CitySite cs = citySiteDao.getCitySite(id);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/edit");
		mav.addObject("citySite", cs);
        mav.addObject("csTypes", citySiteTypeChoices);
		return mav;
	}    
    
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public ModelAndView newCitySite() {
		
		CitySite cs = new CitySite();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/edit");
		mav.addObject("citySite", cs);
        mav.addObject("csTypes", citySiteTypeChoices);
		return mav;
	}        
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public ModelAndView saveItem(@Valid CitySite cs, BindingResult result) {
        citySiteService.geocodeCitySite(cs);
        citySiteDao.saveCitySite(cs);		
        citySiteService.generateQRCode(cs);
		return getCitySites();
	}
}
