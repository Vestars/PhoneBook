package com.trans.lardi.dao;

import com.trans.lardi.config.AppConfig;
import com.trans.lardi.db.Info;
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
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
@TestExecutionListeners(listeners={ServletTestExecutionListener.class, DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class})
public class InfoDaoTest extends TestCase {

    @Autowired
    InfoDAO infoDAO;

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
        public InfoDAO infoDAO() {
            InfoDAO infoDAO = new InfoDAOImpl();
            return infoDAO;
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

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        jdbcTemplate.execute("delete from info");
        jdbcTemplate.execute("delete from users");

        Info info = getInfo();
        User user = UserDaoTest.setupUser();

        assertTrue(userDAO.save(user));
        assertTrue(infoDAO.save(info));
    }


    @Test
    public void testGetAllInfo(){
        List<Info> info = infoDAO.getAllInformation();
        assertNotNull("List of Info is null", info);
        assertEquals("Info size must be 0", 1, info.size());


    }

    @Test
    public void testGetAllInfoByUser(){
        List<Info> info = infoDAO.getAllInfo("I");
        assertNotNull("List of Info is null", info);
        assertEquals("Info size must be 0", info.size(), 0);
        info = infoDAO.getAllInfo("vestarss");
        assertEquals("Info size must be 0", 1, info.size());
    }

    @Test
    public void testSaveInfo() {

        assertEquals(1, infoDAO.getAllInformation().size());
        assertEquals(1, infoDAO.getAllInfo("vestarss").size());

        Info info = getInfo();
        User user = UserDaoTest.setupUser();
        info.setUsers_name("testuser");
        user.setUsername("testuser");
        assertTrue(infoDAO.save(info));
        assertTrue(userDAO.save(user));
        assertEquals(2, infoDAO.getAllInformation().size());
        assertEquals(1, infoDAO.getAllInfo("testuser").size());

    }

    @Test
    public void testGetUpdateInfo(){

        Info retrivedInfo =  infoDAO.getAllInfo("vestarss").get(0);
        retrivedInfo.setEmail("another@mail.ua");
        retrivedInfo.setMiddlename("Иванович");
        retrivedInfo.setMobilephone("+380(93)1234567");
        assertTrue(infoDAO.update(retrivedInfo));
        Info updatedInfo =  infoDAO.getInfo(retrivedInfo.getId());
        assertEquals("Иванович", updatedInfo.getMiddlename());
        assertEquals("+380(93)1234567", updatedInfo.getMobilephone());
        assertEquals("another@mail.ua", updatedInfo.getEmail());
    }

    @Test
    public void testDeleteInfo(){

        Info info =  infoDAO.getAllInfo("vestarss").get(0);
        infoDAO.delete(info.getId());
        assertEquals(0, infoDAO.getAllInformation().size());
    }

    @Test
    public void testSearchInfo(){

        String pattern = "sadadaa";
        String username = getInfo().getUsers_name();
        assertEquals(1, infoDAO.getAllInfo(username).size());
        List<Info> info = infoDAO.search(pattern, username);
        assertEquals(1, info.size());
        Info infos = getInfo();
        User user = UserDaoTest.setupUser();
        infoDAO.save(infos);
        assertEquals(2, infoDAO.getAllInfo(username).size());
        info = infoDAO.search(pattern, username);
        assertEquals(2, info.size());

        System.out.println("List for user " + infoDAO.getAllInfo(username));
        System.out.println("List for user and query " + infoDAO.search(pattern, username));

    }


    static Info getInfo(){
        Info info = new Info();
        info.setSecondname("Алексеев");
        info.setFirstname("Алексей");
        info.setMiddlename("Алексеевич");
        info.setMobilephone("+380(50)2046725");
        info.setHomephone("123456");
        info.setEmail("sadadaa");
        info.setAdress("fdsf");
        info.setUsers_name("vestarss");

        return info;
    }
}
