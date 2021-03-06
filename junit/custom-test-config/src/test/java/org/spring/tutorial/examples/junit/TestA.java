package org.spring.tutorial.examples.junit;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.spring.tutorial.examples.junit.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class TestA {

    @Autowired
    private ConfigClass config;

    @Autowired
    private User user;

    @Test
    public void verifyConfiguration() {
        /*
             by default spring will look for a an xml file named {test class}-context.xml
             or any inner static classes annotated with @Configuration.
         */
        assertNotNull(config);

        assertEquals(77, user.getId());
        assertEquals("john", user.getName());
        assertEquals("john.snow@gmail.com", user.getEmail());
    }

    @Configuration
    static class ConfigClass {

        @Bean
        public User returnUser() {
            return new User(77, "john", "john.snow@gmail.com");
        }
    }

}
