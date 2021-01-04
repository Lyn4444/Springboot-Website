package com.example.demo.helper.server;

import org.springframework.util.FileCopyUtils;

import java.io.*;

/**
 * @ProjectName demo
 * @ClassName AvatarHelper
 * @Description TODO
 * @Author Lyn
 * @Date 2020/12/22 2:03
 * @Version 1.0
 * @Function 生成默认头像
 */

public class AvatarHelper {

    public static String AvatarDone(String avatar) throws IOException {
        String sourceAvatarName = "src/main/resources/static/img/avatar/default.jpg";
        File sourceFile = new File(sourceAvatarName);
        File targetFile = new File("src/main/resources/static/img/avatar/" + avatar + ".jpg");

        String targetName = "/img/avatar/" + avatar + ".jpg";
        InputStream inputStream = new FileInputStream(sourceFile);
        FileOutputStream fileOutputStream = new FileOutputStream(targetFile);
        FileCopyUtils.copy(inputStream, fileOutputStream);
        inputStream.close();
        fileOutputStream.close();
        return targetName;
    }

}
