package cn.vp.controller;

import cn.vp.bean.User;
import cn.vp.service.UserService;
import cn.vp.util.Log;
import cn.vp.util.MailUtil;
import cn.vp.util.ResultBean;
import cn.vp.util.TimeUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Random;

/**
 * 用户控制层
 */
@Controller
public class AccountController {

    @Autowired
    UserService userService;

    //邮箱收到的验证码
    private String code = "";

    private String time = "";

    /**
     * 用户登陆
     *
     * @param username     账号或手机号
     * @param userpassword 密码
     * @return json
     */
    @RequestMapping(value = "/api/v1/account/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(HttpServletRequest request, @RequestParam("username") String username, @RequestParam("userpassword") String userpassword) {
        Log.i(this.getClass(), "用户登陆的账号为：" + username + "   密码为：" + userpassword);
        String result = userService.login(username, userpassword);
        HttpSession session = request.getSession();
        //json对象转换成javabean对象
        JSONObject jsonObject = JSON.parseObject(result);
        JSONObject aa = jsonObject.getJSONObject("dataInfo");
        User user = JSONObject.toJavaObject(aa, User.class);
        System.err.println(user);
        String resultCode = (String) jsonObject.get("resultCode");
        if (resultCode.equals("1000")) {
            session.setAttribute("user", user);
        }
        System.err.println(result);
        return result;
    }


    /**
     * 邮箱验证码
     *
     * @return
     */
    @RequestMapping(value = "/api/v1/sendEmailCode")
    @ResponseBody
    public String sendEmailCode(HttpServletRequest request, @RequestParam("useremail") String userEmail) {
        //注册邮箱
        Log.i(this.getClass(), "注册的邮箱是：" + userEmail);
        code = getCode();
        time = TimeUtil.getTime();
        //放入session
        HttpSession session = request.getSession();
        session.setAttribute("email", userEmail);
        System.err.println("验证码为：" + code);
        String text = "收到的验证码为：" + code + ",该验证码10分钟内有效";
        boolean send = MailUtil.send(text, userEmail);
        return JSON.toJSONString(send);
    }

    /**
     * 判断验证码是否正确以及是否超过时间
     *
     * @param emailCode 验证码
     * @return
     */
    @RequestMapping(value = "/api/v1/emailCode")
    @ResponseBody
    public String emailCode(HttpServletRequest request, @RequestParam("emailCode") String emailCode) {
        if (TimeUtil.cmpTime(time) && code.equals(emailCode)) {
            HttpSession session = request.getSession();
            session.setAttribute("emailCode", emailCode);
            return JSON.toJSONString("true");
        } else {
            return JSON.toJSONString("false");
        }

    }

    /**
     * 随机生成6位数验证码
     *
     * @return
     */
    public String getCode() {
        String auth = "";
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            auth += random.nextInt(10);
        }
        return auth;
    }


    /**
     * 找回密码
     *
     * @param userEmail     邮箱
     * @param userpassword  用户密码
     * @param userpassword2 确认密码
     * @param verifycode    验证码
     * @return
     */
    @RequestMapping("/api/v1/account/updateuserpwd")
    @ResponseBody
    public String updateuserpwd(@RequestParam("userEmail") String userEmail, @RequestParam("userpassword") String userpassword
            , @RequestParam("userpassword2") String userpassword2
            , @RequestParam("verifycode") String verifycode) {
        Log.i(this.getClass(), "找回密码 邮箱为：" + userEmail + "  密码为：" + userpassword + "  验证码为：" + verifycode);
        ResultBean resultBean = new ResultBean();
        if (!userpassword.equals(userpassword2)) {
            resultBean.setMessage("两次输入密码不一致");
            resultBean.setResultCode("200");
            System.err.println("修改1：" + JSON.toJSONString(resultBean));
            return JSON.toJSONString(resultBean);
        }
        if (!TimeUtil.cmpTime(time) || !code.equals(verifycode)) {
            resultBean.setMessage("验证码无效");
            resultBean.setResultCode("200");
            System.err.println("修改2：" + JSON.toJSONString(resultBean));
            return JSON.toJSONString(resultBean);
        }
        String updateuserpwd = userService.updateuserpwd(userEmail, userpassword);
        System.err.println("修改：" + updateuserpwd);
        return updateuserpwd;
    }


    /**
     * 注册
     *
     * @return json 结果
     */
    @RequestMapping("/api/v1/account/register")
    @ResponseBody
    public String register(@RequestParam("username") String username,
                           @RequestParam("userpassword") String userpassword,
                           @RequestParam("useremail") String useremail,
                           @RequestParam(value = "userPhone", required = false) String userPhone,
                           @RequestParam("verifycode") String verifycode) {
        System.err.println("进入注册");
        Log.i(this.getClass(), "用户注册" + "username:" + username + "  userpassword:" + userpassword
                + "   useremail:" + useremail + "  userPhone:" + userPhone + "  verifycode" + verifycode);
        if (!TimeUtil.cmpTime(time) || !code.equals(verifycode)) {
            return JSON.toJSONString("验证码错误");
        }
        User user = new User();
        user.setUserName(username);
        user.setCreateTime(new Date());
        user.setUserEmail(useremail);
        user.setUserPhone(userPhone);
        user.setUserPassword(userpassword);
        return userService.addUser(user);
    }

    /**
     * 判断邮箱是否使用
     *
     * @param email 邮箱
     * @return json
     */
    @RequestMapping(value = "/api/v1/reg")
    @ResponseBody
    public String reg(@RequestParam("email") String email) {
        Log.i(this.getClass(),"判断邮箱是否使用 email:"+email);
        System.err.println("email:" + email);
        int i = userService.selectEmal(email);
        return JSON.toJSONString(i);
    }


    /**
     * 判断用户名是否使用
     *
     * @param username 用户名
     * @return json
     */
    @RequestMapping(value = "/api/v1/regUsername")
    @ResponseBody
    public String regUsername(@RequestParam("username") String username) {
        Log.i(this.getClass(),"判断用户名是否使用 username:"+username);
        System.err.println("username:" + username);
        int i = userService.selecrUsername(username);
        return JSON.toJSONString(i);
    }

}
