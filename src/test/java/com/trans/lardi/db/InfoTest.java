package com.trans.lardi.db;

import junit.framework.TestCase;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertNotEquals;

/**
 * Created by Dmytriy on 01.11.2016.
 */
public class InfoTest extends TestCase {
    @Test
    public void testInfo() {

        Info ivanov = setupInfoIvanov();

        assertEquals("Expect first name Иван", "Иван", ivanov.getFirstname());
        assertEquals("Expect second name Иванов", "Иванов", ivanov.getSecondname());
        assertEquals("Expect middle name(отчество) Иванович", "Иванович", ivanov.getMiddlename());
        assertEquals("Expect mobile +380(66)1234567", "+380(66)1234567", ivanov.getMobilephone());
        assertEquals("Expect telephone 123456", "123456", ivanov.getHomephone());
        assertEquals("Expect adress Kiev", "Kiev", ivanov.getAdress());
        assertEquals("Expect email mail@mail.ru", "mail@mail.ru", ivanov.getEmail());
        assertEquals("Expect users_name = I", "I", ivanov.getUsers_name());

        Info petrov = new Info();
        assertNotEquals(ivanov, petrov);
        petrov.setSecondname("Петров");
        petrov.setFirstname("Петр");
        petrov.setMiddlename("Петрович");
        petrov.setMobilephone("12345678");
        petrov.setHomephone("123456");
        petrov.setAdress("Kiev");
        petrov.setEmail("mail@mail.ru");

        assertNotEquals("Must not equals by full name", ivanov, petrov);

        petrov.setSecondname("Иванов");
        petrov.setFirstname("Иван");
        petrov.setMiddlename("Иванович");
        petrov.setMobilephone("+380(66)1234567");
        petrov.setHomephone("123456");
        petrov.setAdress("Kiev");
        petrov.setEmail("mail@mail.ru");
        petrov.setUsers_name("I");

        assertEquals("Must be equals", ivanov, petrov);

    }

    @Test
    public void testValidationInfo(){

        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();

        Info ivanov = setupInfoIvanov();

        Set<ConstraintViolation<Info>> violations = validator.validate(ivanov);
        assertTrue(violations.isEmpty());

        ivanov.setFirstname("Ива");
        ivanov.setEmail("mail@mailru");
        ivanov.setMobilephone("12");

        violations = validator.validate(ivanov);
        assertEquals(3, violations.size());

    }

    static Info setupInfoIvanov(){
        Info ivanov = new Info();
        ivanov.setSecondname("Иванов");
        ivanov.setFirstname("Иван");
        ivanov.setMiddlename("Иванович");
        ivanov.setMobilephone("+380(66)1234567");
        ivanov.setHomephone("123456");
        ivanov.setAdress("Kiev");
        ivanov.setEmail("mail@mail.ru");
        ivanov.setUsers_name("I");

        return ivanov;
    }
}
