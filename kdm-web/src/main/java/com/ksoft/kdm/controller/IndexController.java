package com.ksoft.kdm.controller;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.ksoft.kdm.common.constants.Constants;
import com.ksoft.kdm.common.dto.CasualInfo;
import com.ksoft.kdm.dto.ResponseDto;
import com.ksoft.kdm.dto.ShowDzLogSaveDto;
import com.ksoft.kdm.service.MemberService;
import com.ksoft.kdm.service.StoreShowService;
import com.ksoft.kdm.service.WechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * index
 *
 * @author HZH
 * @date 2017/3/30
 */
@Controller
public class IndexController {

    @Value("${zcckj.wechat.appid}")
    private String appId;
    @Value("${zcckj.wechat.redirecturi}")
    private String redirectUri;

    private static final String AUTHORIZE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize";

    /*@Reference(version = "1.0.0")
    private IStoreReadService storeReadService;*/
    @Reference(version = "1.0.0")
    private StoreShowService storeShowService;
    @Autowired
    private WechatService wechatService;
    @Autowired
    private MemberService memberService;

    /**
     * ERROR页面
     * @param model
     * @param msg
     * @return
     */
    @RequestMapping(value="/errors",method = RequestMethod.GET)
    public String index(Map<String,Object> model, @ModelAttribute("msg") String msg){
        if(StringUtils.isNotEmpty(msg)){
            model.put("msg", msg);
        }
        return "index/index";
    }

    /**
     * 轮胎二维码跳转页面
     * @param barcode
     * @return
     */
    @RequestMapping(value="barcode/{barcode}",method = RequestMethod.GET)
    public String barcode(@PathVariable(value = "barcode") String barcode) {
        String param = "barcode:"+barcode;
        return "forward:/browserCheck?param="+param;
    }

    /**
     * 卖家秀跳转页面
     * @param attr
     * @param showid
     * @return
     */
    @RequestMapping(value="/showid/{showid}",method = RequestMethod.GET)
    public String showid(RedirectAttributes attr, @PathVariable String showid){
        String param = "showid:"+showid;
        return "forward:/browserCheck?param="+param;
    }

    /**
     * 验证客户端来源
     * @param model
     * @param param
     * @return
     */
    @RequestMapping(value="/browserCheck",method = RequestMethod.GET)
    public String browserCheck(Map<String,Object> model, @RequestHeader ("User-Agent") String userAgent, @RequestParam("param") String param){
        if(wechatService.isWechat(userAgent)){
            try {
                String redirect_uri = URLEncoder.encode(redirectUri+"/wechat/code","utf-8");
                model.put("url",AUTHORIZE_URL+"?appid="+appId+"&redirect_uri="+redirect_uri+"&response_type=code&scope=snsapi_base&state="+URLEncoder.encode(param,"utf-8")+"#wechat_redirect");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return "browserCheck";
        }else{
            return "redirect:"+getPath(param);
        }
    }

    /**
     * 获取需要跳转的完整地址
     * @param param
     * @return
     */
    private String getPath(String param){
        String[] params = param.split(":");
        Map<String,String> pathMap = new HashMap<>();
        pathMap.put("showid","/");
        pathMap.put("barcode","/web/tyreInfbybarcode");
        String path = null;
        if(null != pathMap.get(params[0])){
            path = pathMap.get(params[0]);
            path += "?"+params[0]+"="+params[1];
        }
        return path;
    }

    /**
     * 微信回调地址获取openID，倒转到对应页面
     * @param code
     * @param param
     * @return
     */
    @RequestMapping(value="/wechat/code",method = RequestMethod.GET)
    public String getCode(RedirectAttributes attr, HttpServletRequest request, @ModelAttribute(value = "code") String code, @ModelAttribute(value = "state") String param){
        if(StringUtils.isBlank(code)){
            return "forward:/errors?msg=微信code获取不了";
        }
        String openid = wechatService.getOpenid(code);
        if(StringUtils.isBlank(openid)){
            return "forward:/errors?msg=微信openid获取不了";
        }
        String[] params = param.split(":");
        if(null == params || params.length>2 || StringUtils.isBlank(params[1])){
            return "forward:/errors?msg="+param+",格式错误";
        }
        request.getSession().setAttribute(Constants.WECHAT_OPENID_ATTRIBUTE_NAME,openid);
        String path = getPath(param);
        if(StringUtils.isNotEmpty(path)){
            return "redirect:"+path;
        }
        return "forward:/errors?msg="+param+"，state未定义的状态";
    }

    /**
     * 二维码查看轮胎信息
     * @param model
     * @param request
     * @param userAgent
     * @param barcode
     * @return
     */
    @RequestMapping(value="/web/tyreInfbybarcode",method = RequestMethod.GET)
    public String tyreInfByBarcode(Map<String,Object> model, HttpServletRequest request, HttpServletResponse response, @RequestHeader ("User-Agent") String userAgent, @ModelAttribute("barcode") String barcode){
        if(StringUtils.isBlank(barcode)){
            return "forward:/errors?msg=二维码不能为空";
        }
        String openid = (String) request.getSession().getAttribute(Constants.WECHAT_OPENID_ATTRIBUTE_NAME);
        if(wechatService.isWechat(userAgent)){
            if(StringUtils.isBlank(openid)){
                String param = "barcode:"+barcode;
                return "forward:/browserCheck?param="+param;
            }
        }/*else{
            openid = "";
        }*/
        /*model.put("barcode",barcode);
        model.put("openid",openid);*/
        //StoreInfo storeInfo = storeReadService.findById(1l);

        //判断是否已经有DZ_COOKIE，没有则加入
        Cookie[] cookies = request.getCookies();
        Cookie dzcookie=null;
        if(null != cookies && cookies.length>0) {
            for (Cookie c : cookies) {
                if (Constants.DZ_COOKIE.equals(c.getName())) {
                    dzcookie = c;
                    break;
                }
            }
        }
        if(dzcookie==null){
            dzcookie=new Cookie(Constants.DZ_COOKIE, UUID.randomUUID().toString());
            //作用范围代表站点的根目录
            dzcookie.setPath("/");
            response.addCookie(dzcookie);
        }
        return "tyreInfByBarcode";
    }


    /**
     * 网页或微信 点赞
     * @param showDzLogSaveDto
     * @param request
     * @return
     */
    @RequestMapping(value="/webDzOpt",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto<Integer> webDzOpt(@RequestBody ShowDzLogSaveDto showDzLogSaveDto,HttpServletRequest request){
        CasualInfo casualInfo=memberService.getCasualInfo(request);
        showDzLogSaveDto.setCreateBy(casualInfo.getId());
        showDzLogSaveDto.setCreateByName(casualInfo.getId());
        showDzLogSaveDto.setDzChannel(casualInfo.getSource());
        return storeShowService.dzOpt(showDzLogSaveDto);
    }
}
