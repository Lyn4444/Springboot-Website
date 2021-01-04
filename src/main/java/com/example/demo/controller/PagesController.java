package com.example.demo.controller;

import com.example.demo.helper.http.HttpUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import java.io.*;

@Controller
@RequestMapping("/")
public class PagesController {

    @RequestMapping("/indextest")
    public String index1() {
        return "indextest";
    }

    @RequestMapping("/registertest")
    public String register() {
        return "registertest";
    }

    @RequestMapping("/logined")
    public String logined(SessionStatus sessionStatus, HttpSession httpSession) {
        if(null == httpSession.getAttribute("username")) {
            return "redirect:/index";
        }
        else {
            return "testSession";
        }
    }
/*
    @RequestMapping("/logout")
    public String logout(SessionStatus sessionStatus, HttpSession httpSession) {
        httpSession.removeAttribute("username");
        httpSession.removeAttribute("avatar");
        httpSession.removeAttribute("email");
        httpSession.removeAttribute("isAdmin");
        System.out.println("已注销");
        return "redirect:/index";
    }
*/
    @RequestMapping("/logout")
    public String logout(SessionStatus sessionStatus, HttpSession httpSession) {
        httpSession.removeAttribute("username");
        httpSession.removeAttribute("avatar");
        httpSession.removeAttribute("email");
        httpSession.removeAttribute("isAdmin");
        System.out.println("已注销");
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/user")
    public String user(SessionStatus sessionStatus, HttpSession httpSession) {
        if(null == httpSession.getAttribute("username")) {
            return "redirect:/index";
        }
        else {
            return "user";
        }
    }

    @RequestMapping("/usergravatar")
    public String usegravatar(SessionStatus sessionStatus, HttpSession httpSession) {
        String gravatarEmail = "http://cn.gravatar.com/avatar/" + DigestUtils.md5Hex(httpSession.getAttribute("email").toString());
        String targetName = "src/main/resources/static" + httpSession.getAttribute("avatar").toString();
        try {
            HttpUtil.downloadFile(targetName, gravatarEmail);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "user";
    }

    @RequestMapping("/article/{id}")
    public String article(SessionStatus sessionStatus, HttpSession httpSession) {
        return "article";
    }

    @RequestMapping("/uploadavatar")
    public String uploadavatar(SessionStatus sessionStatus, HttpSession httpSession, @RequestParam("file") MultipartFile file, Model model) {
        String avatarName = DigestUtils.md5Hex(httpSession.getAttribute("email").toString()) + ".jpg";
        //判断文件是否为空
        if(file.isEmpty()){
            model.addAttribute("result_singlefile", "文件为空");
            return "index";
        }

        //创建输入输出流
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            //指定上传的位置
            String path = "src/main/resources/static/img/avatar/";
            //获取文件的输入流
            inputStream = file.getInputStream();
            //获取上传时的文件名
            //String fileName = file.getOriginalFilename();
            String fileName = avatarName;
            //注意是路径+文件名
            File targetFile = new File(path + fileName);


            //判断文件父目录是否存在
            if(!targetFile.getParentFile().exists()){
                //不存在就创建一个
                targetFile.getParentFile().mkdir();
            }

            //获取文件的输出流
            outputStream = new FileOutputStream(targetFile);
            //最后使用资源访问器FileCopyUtils的copy方法拷贝文件
            FileCopyUtils.copy(inputStream, outputStream);
            /*参数是通过源码
                public static int copy(InputStream in, OutputStream out) throws IOException {
                    ......
                }
           而得知的*/

            //告诉页面上传成功了
            model.addAttribute("result_singlefile", "上传成功");
        } catch (IOException e) {
            e.printStackTrace();
            //出现异常，则告诉页面失败
            model.addAttribute("result_singlefile", "上传失败");
        } finally {
            //无论成功与否，都有关闭输入输出流
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "redirect:/user";
    }

}
