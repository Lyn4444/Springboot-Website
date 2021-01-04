package com.example.demo.helper.encryption;

import java.io.File;
import java.security.PrivateKey;

/**
 * @ProjectName demo
 * @ClassName RSAService
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/10 0:21
 * @Version 1.0
 * @Function
 */

public class RSAService {

    public static String decrypt(String encryptText) throws Exception {
        File privateKeyFile = new File("private.key");
        PrivateKey privateKey = RSAUtil.getPrivateKeyByFile(privateKeyFile.getPath());
        return RSAUtil.decrypt(encryptText, privateKey);
    }

}
