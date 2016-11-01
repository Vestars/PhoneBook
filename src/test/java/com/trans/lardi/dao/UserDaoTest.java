package com.trans.lardi.dao;

import com.trans.lardi.config.AppConfig;
import com.trans.lardi.db.User;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;

import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
@TestExecutionListeners(listeners={ServletTestExecutionListener.class, DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class})
public class UserDaoTest extends TestCase {

    @Autowired
    private UserDAO userDAO;

    @Configuration
    static class ContextConfiguration {

        AppConfig appConfig = new AppConfig();

        @Bean
        public UserDAO userDAO() {
            UserDAO userDAO = new UserDAOImpl();
            return userDAO;
        }

        @Bean
        public DataSource dataSource(){
            return appConfig.dataSource();
        }
    }

    @Autowired
    private DataSource dataSource;

    @Before
    public void setUp() throws Exception {
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);

        jdbc.execute("delete from info");
        jdbc.execute("delete from users");
    }

    @Test
    public void testSaveAndExistUser(){

        User user = setupUser();

        assertTrue(userDAO.save(user));
        assertTrue(userDAO.exists(user.getUsername()));

    }

    @Test
    public void testGetAllUsers(){
        User user = setupUser();
        assertTrue(userDAO.save(user));
        assertEquals(1, userDAO.getAllUsers().size());
        user.setUsername("testuser");
        assertTrue(userDAO.save(user));
        assertEquals(2, userDAO.getAllUsers().size());

    }

    static User setupUser(){
        User user = new User();
        user.setUsername("vestarss");
        user.setPassword("passpass");
        user.setFullname("Воронин Дмитрий");
        user.setAuthority("ROLE_USER");
        user.setEnabled(true);
        return user;
    }
}
