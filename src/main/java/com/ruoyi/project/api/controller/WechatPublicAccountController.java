package com.ruoyi.project.api.controller;

import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.project.system.wx.entity.InMessage;
import com.ruoyi.project.system.wx.entity.OutMessage;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@RestController
@RequestMapping("wechat/publicAccount")
public class WechatPublicAccountController {
    // 微信页面填写的token，必须保密
//    private static final String TOKEN = "930628";
    @Value("${wx.wxToken}")
    private String wxToken;

//    private static final String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
    @Value("${wx.getTokenUrl}")
    private String getTokenUrl;
//    private static final String APPID = "wx4bb3c1171f167f11";
    @Value("${wx.wxAppId}")
    private String appId;
//    private static final String APP_SECRET = "744bc7c43b43ed34c5f8550c74a39ad4";
    @Value("${wx.wxSecret}")
    private String appSecret;
    //创建菜单url
//    private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";
    @Value("${wx.createMenuUrl}")
    private String createMenuUrl;
    // 获取token放入缓存中 也可使用redis
    private Cache<String, String> tokenNotifyWxCache;

    @Autowired
    private CacheManager cacheManager;

    @PostConstruct
    public void init() {
        // 缓存三分钟
        tokenNotifyWxCache = cacheManager.getCache("token-RecordCache");
    }

    @GetMapping("/validate")
    public String validate(String signature,String timestamp,String nonce,String echostr){


        System.out.println("通了");

        // 1. 将token、timestamp、nonce三个参数进行字典序排序
        String[] arr = {timestamp, nonce, wxToken};
        Arrays.sort(arr);
        // 2. 将三个参数字符串拼接成一个字符串进行sha1加密
        StringBuilder sb = new StringBuilder();
        for (String temp : arr) {
            sb.append(temp);
        }
        // 这里利用了hutool的加密工具类
        String sha1 = SecureUtil.sha1(sb.toString());
        // 3. 加密后的字符串与signature对比，如果相同则该请求来源于微信，原样返回echostr
        if (sha1.equals(signature)){
            return echostr;
        }
        // 接入失败
        return null;
    }

    @PostMapping(value = "/validate",produces = "application/xml;charset=UTF-8")
    public Object HandleMessage(@RequestBody InMessage inMessage){
        // 创建响应消息实体对象
        OutMessage outMessage = new OutMessage();
        // 把原来的接收方设置为发送方
        outMessage.setFromUserName(inMessage.getToUserName());
        // 把原来的发送方设置为接收方
        outMessage.setToUserName(inMessage.getFromUserName());
        // 设置消息类型
        outMessage.setMsgType(inMessage.getMsgType());
        // 设置消息时间
        outMessage.setCreateTime(System.currentTimeMillis());
        // 根据接收到消息类型，响应对应的消息内容
        if ("text".equals(inMessage.getMsgType())){
            // 文本
            String content = inMessage.getContent();
            if (content.contains("游戏")){
                outMessage.setContent("玩什么游戏好好工作！");
            }else if (content.contains("工作")){
                outMessage.setContent("好好工作，努力赚钱！");
            }else {
                outMessage.setContent(content);
            }
        }else if ("image".equals(inMessage.getMsgType())){
            // 图片
            outMessage.setMediaId(new String[]{inMessage.getMediaId()});
            System.out.println(inMessage.getMediaId());

        }else if ("event".equals(inMessage.getMsgType())){
            String event = inMessage.getEvent();
            if ("subscribe".equals(event)) {
                outMessage.setMsgType("text");
                outMessage.setContent("http://naturenexus.free.idcfengye.com/");
            }
        }
        return outMessage;
    }


    private String getAccessToken(){
        String token = tokenNotifyWxCache.get("token");
        try {
            //如果缓存为空
            if (StringUtils.isEmpty(token)){
                String resultToken = HttpUtils.sendGet(getTokenUrl,
                        "grant_type=client_credential" + "&appid=" + appId + "&secret=" + appSecret, Constants.GBK);
                JSONObject jsonObject = JSON.parseObject(resultToken);
                token = jsonObject.getString("access_token");// 获取token值
                if (StringUtils.isBlank(token)) {
                    throw new Exception("获取token失败");
                }
                tokenNotifyWxCache.put("token", token);// 令牌放入缓存
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }

    @GetMapping("/createMenu")
    @ResponseBody
    public String createMenu(){
        String accessToken = getAccessToken();
        String ret = "";
        if (StringUtils.isNotEmpty(accessToken)){
            String body = "{\n" +
                    "    \"button\": [\n" +
                    "        {\n" +
                    "            \"type\": \"location_select\",\n" +
                    "            \"name\": \"地理位置\",\n" +
                    "            \"key\": \"V1001_TODAY_MUSIC\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"name\": \"菜单\",\n" +
                    "            \"sub_button\": [\n" +
                    "                {\n" +
                    "                    \"type\": \"view\",\n" +
                    "                    \"name\": \"测试\",\n" +
                    "                    \"url\": \"http://naturenexus.free.idcfengye.com/\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"type\": \"click\",\n" +
                    "                    \"name\": \"赞一下我们\",\n" +
                    "                    \"key\": \"V1001_GOOD\"\n" +
                    "                }\n" +
                    "            ]\n" +
                    "        }\n" +
                    "    ]\n" +
                    "}";
            ret = HttpUtils.sendPost(createMenuUrl + accessToken, body);
        }
        return ret;
    }
}
