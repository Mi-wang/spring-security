package cn.wolfcode;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;

import java.util.UUID;

/**
 * @author wby
 * @version 1.0
 * @date 2022/11/3 16:26
 */
public class EncryptTest {


    @Test
    public void bcrypt() throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode("123456");
        System.out.println(encode);

    }
    @Test
    public void encrypt() throws Exception {
        MessageDigestPasswordEncoder encoder = new MessageDigestPasswordEncoder("MD5");
        String encode = encoder.encode("123465");
        System.out.println(encode);

    }
}
