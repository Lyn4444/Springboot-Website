package com.example.demo.helper.encryption;


import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @ProjectName demo
 * @ClassName RAS
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/8 20:50
 * @Version 1.0
 * @Function
 */

public class RSAUtil {

    /**
     * 生成密钥对
     *
     * @return {@link KeyPair}* @throws Exception 异常
     */
    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(512, new SecureRandom());
        KeyPair pair = generator.generateKeyPair();

        return pair;
    }


    /**
     * 提取公钥的比特编码经过Base64转换后保存到文件，注意公钥的比特编码是X.509格式
     *
     * @param publicKey  公钥
     * @param outPutFile 把文件
     * @throws Exception 异常
     */
    public static void savePublicKey2File(PublicKey publicKey, File outPutFile) throws Exception {
        byte[] pbks = Base64.getEncoder().encode(publicKey.getEncoded());
        FileOutputStream fos = new FileOutputStream(outPutFile);
        fos.write(pbks);
        fos.flush();
        fos.close();
    }


    /**
     * 提取私钥的比特编码经过Base64转换后保存到文件，注意私钥的比特编码是pkcs8格式
     *
     * @param privateKey 私钥
     * @param outPutFile 把文件
     * @throws Exception 异常
     */
    public static void savePrivateKey2File(PrivateKey privateKey, File outPutFile) throws Exception {
        byte[] prks = Base64.getEncoder().encode(privateKey.getEncoded());
        FileOutputStream fos = new FileOutputStream(outPutFile);
        fos.write(prks);
        fos.flush();
        fos.close();
    }


    /**
     * 从文件中获取公钥(公钥的比特编码是X.509格式)
     *
     * @param certPath 证书路径
     * @return {@link PublicKey}* @throws Exception 异常
     */
    public static PublicKey getPublicKeyByFile(String certPath) throws Exception {
        FileInputStream fis = new FileInputStream(certPath);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fis.read(buffer)) > 0) {
            bos.write(buffer, 0, len);
        }
        byte[] pbks = Base64.getDecoder().decode(bos.toByteArray());
        X509EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(pbks);
        PublicKey newPbk = KeyFactory.getInstance("RSA").generatePublic(encodedKeySpec);
        return newPbk;
    }


    /**
     * 从文件中获取公钥(私钥的比特编码是pkcs8格式)
     *
     * @param keyPath 关键路径
     * @return {@link PrivateKey}* @throws Exception 异常
     */
    public static PrivateKey getPrivateKeyByFile(String keyPath) throws Exception {
        FileInputStream fis = new FileInputStream(keyPath);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = fis.read(buffer)) > 0) {
            bos.write(buffer, 0, len);
        }
        byte[] prks = Base64.getDecoder().decode(bos.toByteArray());
        /**/
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(prks);
        PrivateKey newPrk = KeyFactory.getInstance("RSA").generatePrivate(pkcs8EncodedKeySpec);
        return newPrk;
    }

    /**
     * 加密
     *
     * @param plainText 纯文本
     * @param publicKey 公钥
     * @return {@link String}* @throws Exception 异常
     */
    public static String encrypt(String plainText, PublicKey publicKey) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] cipherText = encryptCipher.doFinal(plainText.getBytes());

        return Base64.getEncoder().encodeToString(cipherText);
    }


    /**
     * 解密
     *
     * @param cipherText 密文
     * @param privateKey 私钥
     * @return {@link String}* @throws Exception 异常
     */
    public static String decrypt(String cipherText, PrivateKey privateKey) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(cipherText);

        Cipher decriptCipher = Cipher.getInstance("RSA");
        decriptCipher.init(Cipher.DECRYPT_MODE, privateKey);

        return new String(decriptCipher.doFinal(bytes));
    }


    /**
     * 签名
     *
     * @param plainText  纯文本
     * @param privateKey 私钥
     * @return {@link String}* @throws Exception 异常
     */
    public static String sign(String plainText, PrivateKey privateKey) throws Exception {
        Signature privateSignature = Signature.getInstance("SHA256withRSA");
        privateSignature.initSign(privateKey);
        privateSignature.update(plainText.getBytes(StandardCharsets.UTF_8));

        byte[] signature = privateSignature.sign();

        return Base64.getEncoder().encodeToString(signature);
    }


    /**
     * @param plainText 纯文本
     * @param signature 签名
     * @param publicKey 公钥
     * @return boolean* @throws Exception 异常
     */
    public static boolean verify(String plainText, String signature, PublicKey publicKey) throws Exception {
        Signature publicSignature = Signature.getInstance("SHA256withRSA");
        publicSignature.initVerify(publicKey);
        publicSignature.update(plainText.getBytes(StandardCharsets.UTF_8));

        byte[] signatureBytes = Base64.getDecoder().decode(signature);

        return publicSignature.verify(signatureBytes);
    }


//    public static void main(String[] args) throws Exception {
//        /*
//         * 随机生成秘钥对
//         */
//        KeyPair keyPair = RSA.generateKeyPair();
//        PublicKey publicKey = keyPair.getPublic();
//        PrivateKey privateKey = keyPair.getPrivate();
//
//        /*
//         * 使用原始私钥加密，重新生成的公钥解密
//         */
//        Cipher cipher = Cipher.getInstance("RSA");
//        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//        byte[] bytes = cipher.doFinal("helloworld".getBytes());
//        System.out.println("原始公钥加密=" + Base64.getEncoder().encodeToString(bytes));
//
//        cipher.init(Cipher.DECRYPT_MODE, privateKey);
//        bytes = cipher.doFinal(bytes);
//        System.out.println("原始私钥解密=" + new String(bytes));
//
//
//        File privateKeyFile = new File("private.key");
//        RSA.savePrivateKey2File(privateKey, privateKeyFile);
//
//        File publicKeyFile = new File("public.key");
//        RSA.savePublicKey2File(publicKey, publicKeyFile);
//
//
//        String plainText = "test";
//        File privateKeyFile = new File("private.key");
//        PrivateKey privateKey = RSAUtil.getPrivateKeyByFile(privateKeyFile.getPath());
//        File publicKeyFile = new File("public.key");
//        PublicKey publicKey = RSAUtil.getPublicKeyByFile(publicKeyFile.getPath());
//
//        String encrypt = RSAUtil.encrypt(plainText, publicKey);
//        System.out.println(encrypt);
//        String res = RSAUtil.decrypt(encrypt, privateKey);
//        System.out.println(res);
//    }

}
