/*
 * TampaGov Hackathon 2012 Submission
 * Tampa Tech Marks
 */

package org.rcbd.tampagov.citysites.controller;

import javax.inject.Inject;
import org.rcbd.tampagov.citysites.dao.CitySiteDao;
import org.rcbd.tampagov.citysites.service.CitySiteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Robin Curts [github.com/rcbd]
 */
@Controller
@RequestMapping("/home")
public class IndexController {

    @Inject
    private CitySiteDao citySiteDao;
    @Inject
    private CitySiteService citySiteService;    
    
    @RequestMapping("")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("home");
        mav.addObject("sites", citySiteDao.listCitySites(null));        
        return mav;
    }
}
