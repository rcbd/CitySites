/*
 * TampaGov Hackathon 2012 Submission
 * Tampa Tech Marks
 */
package org.rcbd.tampagov.citysites.controller;

import java.util.List;
import java.util.ResourceBundle;
import javax.inject.Inject;
import org.rcbd.tampagov.citysites.dao.CitySiteDao;
import org.rcbd.tampagov.citysites.model.CitySite;
import org.rcbd.tampagov.citysites.service.CitySiteService;
import org.rcbd.tampagov.citysites.service.ReservationService;
import org.rcbd.tampagov.citysites.utility.GeographicPoint;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Robin Curts [github.com/rcbd]
 */
@Controller
@RequestMapping("/site")
public class CitySiteController {

    ResourceBundle csBundle = ResourceBundle.getBundle("CitySites");
    @Inject
    private CitySiteDao citySiteDao;
    @Inject
    private CitySiteService citySiteService;
    @Inject
    private ReservationService reservationService;
    
    @RequestMapping("")
    public ModelAndView noMapping() {
        return lookupCitySites();
    }

    @RequestMapping(value = "/near/{lat}/{lng}/{distance}", method = RequestMethod.GET)
    public @ResponseBody List<CitySite> nearbySites(@PathVariable double lat, @PathVariable double lng, @PathVariable double distance) {
        return citySiteService.findSitesNearMe(new GeographicPoint(lat, lng), distance);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView lookupCitySites() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("site/search");
        return mav;
    }

    @RequestMapping("/{id}")
    public ModelAndView getSiteById(@PathVariable String id) {
        return getSiteByIdAndType(id, null);
    }

    @RequestMapping("/{id}/{type}")
    public ModelAndView getSiteByIdAndType(@PathVariable String id, @PathVariable String type) {

        CitySite cs = citySiteDao.getCitySite(id);

        String view = "site/" + cs.getType().toLowerCase();

        ModelAndView mav = new ModelAndView();
        mav.setViewName(view);
        mav.addObject("citySite", cs);

        if (cs.getType().equalsIgnoreCase("live")) {
            mav.addObject("liveFeed", citySiteService.loadLiveFeed(cs));
        } else if (cs.getType().equalsIgnoreCase("functional")) {
            mav.addObject("availableDates", reservationService.findAvailableReservation(cs.getId()));
        }

        return mav;
    }
}
