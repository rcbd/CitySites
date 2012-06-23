/*
 * TampaGov Hackathon 2012 Submission
 * Tampa Tech Marks
 */

package org.rcbd.tampagov.techmarks.test;

import javax.inject.Inject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 *
 * @author Robin Curts [github.com/rcbd]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:web-context.xml"})
public class TBTMInitilizationTest {

    @Inject
    private ApplicationContext applicationContext;
    
    @Test
    public void foo() {
        assertTrue(true);
    }
}
