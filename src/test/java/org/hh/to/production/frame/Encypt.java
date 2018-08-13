package org.hh.to.production.frame;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encypt {
    @Test
   public void encryptTest(){
        System.out.println(new BCryptPasswordEncoder().encode("123123"));
    }
}
