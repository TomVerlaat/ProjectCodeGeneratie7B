package io.swagger.model;

import org.junit.Assert;
import org.junit.Test;
import org.threeten.bp.LocalDate;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void createUserShouldNotBeNull(){
        User user = new User(User.Type.CUSTOMER,"ChrisvanRoode","Welkom01!","Chris","vanRoode","cbvroode@gmail.com", LocalDate.of(2001,05,30),"Heerenweg 253","1851KR","Heiloo","0643182173");
        assertNotNull(user);
    }

    
    @Test
    public void invalidTypeShouldThrowIllegalArgumentException(){
        User user = new User();
        user.setUsername("CUSTOMER");
        Assert.assertTrue(user.getUsername().matches("^[A-Za-z0-9]{5,20}$"));
    }

    @Test
    public void invalidCurrencyShouldThrowIllegalArgumentException(){
        String inputFromUser = "CUSTOMER";
        boolean isValid = false;
        for (User.Type c : User.Type.values()){
            if (c.name().equals(inputFromUser)) {
                isValid = true;
            }
        }
        if (!isValid) throw new IllegalArgumentException("User type is not valid!");
    }

    @Test
    public void invalidPasswordShouldThrowIllegalArgumentException(){
        User user = new User();
        user.setPassword("Welkom01!");
        boolean isValid = true;
        if (user.getPassword().length() < 8) isValid = false;

        int charCount = 0;
        int numCount = 0;
        for (int i = 0; i < user.getPassword().length(); i++) {

            char ch = user.getPassword().charAt(i);

            if (is_Numeric(ch)) numCount++;
            else if (is_Letter(ch)) charCount++;
            else isValid = false;
        }


        isValid = (charCount >= 2 && numCount >= 2);

        if (!isValid) throw new IllegalArgumentException("Password is not valid!");
    }

    public static boolean is_Letter(char ch) {
        ch = Character.toUpperCase(ch);
        return (ch >= 'A' && ch <= 'Z');
    }

    public static boolean is_Numeric(char ch) {

        return (ch >= '0' && ch <= '9');
    }

    @Test
    public void invalidUsernameShouldThrowIllegalArgumentException(){
        User user = new User();
        user.setUsername("ChrisvanRoode");
        Assert.assertTrue(user.getUsername().matches("^[A-Za-z0-9]{5,20}$"));
    }

    @Test
    public void invalidFirstNameShouldThrowIllegalArgumentException(){
        User user = new User();
        user.setFirstName("Chris");
        Assert.assertTrue(user.getFirstName().matches("^[A-Za-z]{1,20}$"));
    }

    @Test
    public void invalidLastNameShouldThrowIllegalArgumentException(){
        User user = new User();
        user.setLastName("vanRoode");
        Assert.assertTrue(user.getLastName().matches("^[A-Za-z]{1,20}$"));
    }

    @Test
    public void invalidEmailShouldThrowIllegalArgumentException(){
        User user = new User();
        user.setEmail("cbvroode@gmail.com");
        Assert.assertTrue(user.getEmail().matches("^[A-Za-z]{1,20}[@][A-Za-z]{5,20}[.][a-z]{2,3}$"));
    }

    @Test
    public void invalidAddressShouldThrowIllegalArgumentException(){
        User user = new User();
        user.setAddress("Heerenweg 253");
        Assert.assertTrue(user.getAddress().matches("^\\D{5,30}\\s\\d{1,3}$"));
    }

    @Test
    public void invalidPostalCodeShouldThrowIllegalArgumentException(){
        User user = new User();
        user.setPostalcode("1851KR");
        Assert.assertTrue(user.getPostalcode().matches("^\\d{4}[A-Z]{2}$"));
    }

    @Test
    public void invalidCityShouldThrowIllegalArgumentException(){
        User user = new User();
        user.setCity("Heiloo");
        Assert.assertTrue(user.getCity().matches("^[A-Za-z]{3,30}$"));
    }

    @Test
    public void invalidPhonenumberShouldThrowIllegalArgumentException(){
        User user = new User();
        user.setPhoneNumber("0643182173");
        Assert.assertTrue(user.getPhoneNumber().matches("^06\\d{8}"));
    }
}
