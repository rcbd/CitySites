/*
 * TampaGov Hackathon 2012 Submission
 * Tampa Tech Marks
 */

package org.rcbd.tampagov.citysites.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import junit.framework.Assert;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rcbd.tampagov.citysites.controller.ReservationsController;
import org.rcbd.tampagov.citysites.service.ReservationService;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

/**
 *
 * @author Robin Curts [github.com/rcbd]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:web-context.xml"})
public class ReservationTest {

    private Logger log = Logger.getLogger(ReservationTest.class);
    @Inject
    private ApplicationContext applicationContext;
    
    @Inject 
    ReservationService reservationService;    
    
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private RequestMappingHandlerAdapter handlerAdapter;
    private ReservationsController controller;

//    @Before
//    public void setUp() {
//       request = new MockHttpServletRequest();
//       response = new MockHttpServletResponse();
//       handlerAdapter = (RequestMappingHandlerAdapter)applicationContext.getBean(RequestMappingHandlerAdapter.class);
//       controller = (ReservationsController)applicationContext.getBean(ReservationsController.class);
//    }
//
//    @Test
//    public void testReservationsController() throws Exception {
//        ReservationsController c = (ReservationsController)applicationContext.getBean("reservationsController");
//        Assert.assertNotNull(c);
////        ModelAndView mav = c.reserveForm("40289981381b64d601381bb4c8da0003", "2012-06-26");
////        Assert.assertNotNull(mav);
//        
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        request.setRequestURI("/reservations/create/40289981381b64d601381bb4c8da0003/2012-06-26");
//        Map<String, String> pathvars = new HashMap<String, String>();
//        pathvars.put("citySiteId", "reservationDate");
//        request.setAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, pathvars);
//        ModelAndView mav = new AnnotationMethodHandlerAdapter().handle(request, new MockHttpServletResponse(), c);
//       Assert.assertNotNull(mav);
//       Assert.assertNotNull(mav.getViewName());
//       log.info(ToStringBuilder.reflectionToString(mav));
//       // assert something
//    }
    
    @Test
    public void findReservations() {
        List<String> dates = reservationService.findAvailableReservation("40289981381b64d601381bb4c8da0003");
        Assert.assertNotNull(dates);
        for (String d : dates) {
            log.info("Available Date: " + d);
        }
    }
}
