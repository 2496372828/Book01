package com.sdjtxy.web;

import com.google.gson.Gson;
import com.mysql.cj.Session;
import com.sdjtxy.pojo.User;
import com.sdjtxy.service.UserService;
import com.sdjtxy.service.impl.UserServiceImpl;
import com.sdjtxy.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {
    private UserService userService=new UserServiceImpl();
/*
    BaseServlet的抽取：
    在实际的开发项目中，一般一个模块只使用一个servlet程序

    if else语句可以进行优化
    使用反射来避免使用if else判断
    以后新添加业务，根本不需要重新添加else if("".equals(action)) 直接根据反射来进行业务方法的调用
 */



    protected void ajaxExistsUsername(HttpServletRequest request,HttpServletResponse response) throws IOException {
        System.out.println("jinlaile ");
        //获取传来的参数username
        String username = request.getParameter("username");
        boolean existsUsername = userService.existUsername(username);
        Map<String, Object> map=new HashMap<>();
        map.put("existsUsername",existsUsername);
        Gson gson = new Gson();
        String json = gson.toJson(map);
        response.getWriter().write(json);


    }




    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        //1.获取请求的参数
        /*String username=request.getParameter("username");
        String password=request.getParameter("password");
        String email=request.getParameter("email");*/
        String codeReceive=request.getParameter("code");//验证码

        //2.检查验证码是否正确 (暂时没讲验证码如何生成，先写死，等后期学习之后在修改)

        //获取谷歌生成的验证码(保存在Session中)
        String code=(String)request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //得到验证码后将Session中的验证码删除
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        /*
        BeanUtils的使用
        把所有的请求参数注入到user对象中
        底层实际对每一个参数执行user对象的set方法
        BeanUtils.populate(user,request.getParameterMap());
        此处可以不使用User user=(User)WebUtils.copyParameterToBean(new User(),request.getParameterMap());进行强转
        可以使用泛型，
        */
        User user=WebUtils.copyParameterToBean(new User(),request.getParameterMap());


        if(codeReceive.equals(code)&&code!=null){
            //3.检查用户名是否可用
            if(userService.existUsername(user.getUsername())){

                //用户名不可用
                System.out.println("用户名不可用");
                request.setAttribute("msg","用户名不可用");
                request.setAttribute("username",user.getUsername());
                request.setAttribute("email",user.getEmail());
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
            }else{
                //可用
                //将用户请求信息封装到bean对象中
                 //user=new User(null,username,password,email);
                userService.registerUser(user);

                //将user对象放到Session域中
                request.getSession().setAttribute("user",user);
                //跳转到注册成功的页面

                //*****此处"/"为映射到webapp文件目录下，注意从webapp文件目录下开始出发寻找******
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request,response);

            }
        }else {
            //如果验证码输入错误--请求转发跳转到登陆页面
            request.setAttribute("msg","您所输入的验证码有误");
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
        }

}
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        //登录操作
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        //验证是否登录用户是否存在

        User user=userService.login(new User(null, username, password, null));


        //User user=new User(null,username,password,null);
        if(user==null){//userService.login(user)
            //账户不存在,

            //把错误的信息，和回显的表单项信息，保存到request域中
            request.setAttribute("msg","用户名或密码输入错误");
            request.setAttribute("username",username);

            //跳转到登录页面
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);

        }else{
            //账户存在，跳转到登陆成功页面
            //将账户信息保存到Session域中

            request.getSession().setAttribute("user",user);
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request,response);
        }

    }
    protected void logout(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //用户注销步骤：
        //1、销毁 Session 中用户登录的信息（或者销毁 Session）
        //2、重定向到首页（或登录页面）。
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath());

    }


}
