package com.ksoft.kdm.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.ksoft.kdm.common.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 登录接口
 * @author zhangjianbo
 * @date 2017/5/11
 */
@Controller
public class LoginController {
    @Autowired
    private DefaultKaptcha defaultKaptcha;

    /**
     * 本系统登录
     * @return
     */
    @GetMapping(value = "login")
    public String login(){
        return "login/login";
    }

    /**
     * 第三方登录
     * @param client_id
     * @param response_type
     * @param redirect_uri
     * @return
     */
    @GetMapping(value = "kdmAuth/login")
    public ModelAndView  cuslogin(String client_id,String response_type,String redirect_uri){
        ModelAndView modelAndView=new ModelAndView("oauth2/kdmAuth");
        modelAndView.addObject("client_id",client_id);
        modelAndView.addObject("response_type",response_type);
        modelAndView.addObject("redirect_uri",redirect_uri);
        return modelAndView;
    }

    @GetMapping(value = "index")
    public String index() {

        return "index";
    }

    /**
     * 生成验证码
     *
     * @param response
     * @return
     * @throws Exception
     */
    @GetMapping("/img/code")
    public StreamingResponseBody getCaptcha(HttpServletResponse response, HttpSession session) throws Exception {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control",
                "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        String code = defaultKaptcha.createText();
        session.setAttribute(Constants.SESSION_KEY_KAPTCHA, code);
        final BufferedImage image = defaultKaptcha.createImage(code);
        return new StreamingResponseBody() {
            @Override
            public void writeTo(OutputStream outputStream) throws IOException {
                ImageIO.write(image, "jpg", outputStream);
            }
        };
    }
}
