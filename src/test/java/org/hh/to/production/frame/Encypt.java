package org.hh.to.production.frame;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encypt {
    @Test
    public void encryptTest() {
        System.out.println(new BCryptPasswordEncoder().encode("123123"));
    }

    @Test
    public void Test() {
        System.out.println(new BCryptPasswordEncoder().matches("123123", " "));
    }

    @Test
    public void longPass() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 265; i++) str.append("s");
        System.out.println(str.length());
        System.out.println(str);
    }

    @Test
    public void bashVsJavaEncoders() {
        System.out.println(new BCryptPasswordEncoder().matches("123123",
                "$2y$10$NacjfWDKe6n9CCmrVCRi9.S/Cvw5OJHw2d14LtbMYf2GAebNRUZiu"));
    }

}
