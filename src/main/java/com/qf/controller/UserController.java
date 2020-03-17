package com.qf.controller;

import com.github.pagehelper.PageInfo;
import com.qf.common.entity.Page;
import com.qf.entity.User;
import com.qf.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * @author ZouXianTao
 * @Date2019/12/25
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private IUserService userService;
    /** 前端用户登录*/
    @RequestMapping(value = "/userLogin")
    public void userLogin(User user, HttpSession session, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
         User user1=userService.checkLogin(user);
         if(user1!=null){
            if(user1.getLockstate()==0){
                session.setAttribute("LOGIN_USER",user1);
                response.getWriter().write("<script  language=\"javascript\">alert('登录成功');location.href='http://localhost:8080/'</script>");
            }else{
                response.getWriter().write("<script  language=\"javascript\">alert('账号被冻结，请联系管理员');location.href='http://localhost:8080/toLogin'</script>");
            }

         }
        response.getWriter().write("<script  language=\"javascript\">alert('用户名或者密码不正确');location.href='http://localhost:8080/toLogin'</script>");

    }
    /** 用户注销*/
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session){
        //注销，删除存在session中的登录用户的凭证(移除session中这个凭证的key)，回到登录页面
       session.removeAttribute("LOGIN_USER");
       return "redirect:/toLogin";

    }
    /** 后端*/
    /** 后端登录*/
  @RequestMapping(value = "/backLogin")
    public void backLogin(User user,HttpSession session, HttpServletResponse response) throws IOException {
      response.setContentType("text/html;charset=utf-8");
      //根据用户和密码判断该用户是否正确

      User user1=userService.backLogin(user);
      if(user1!=null){
          session.setAttribute("BACK_USER",user1);
          response.getWriter().write("<script language=\"javascript\">alert('后端登录成功');location.href='http://localhost:8080/backMain'</script>");
      }
      response.getWriter().write("<script language=\"javascript\">alert('后端登录失败');location.href='http://localhost:8080/backLogin'</script>");
  }
  /** 后端点击用户管理的时候，查询用户的集合，并进行分页展示*/
  @RequestMapping(value = "/selectUserList")
    public String selectUserList(Page page, ModelMap map){
      PageInfo<User> pageInfo = userService.getPage(page.getPageNum(), page.getPageSize());
         map.put("page",pageInfo);
         map.put("url","user/selectUserList");
      return "back/user/userList";
  }
  /** 添加用户*/
  @RequestMapping(value = "/addUser")
    public void addUser(User user,HttpServletResponse response) throws IOException {
      response.setContentType("text/html;charset=utf-8");
      user.setRegistDate(new Date());
      user.setLockstate(0);
      int result = userService.add(user);
      if(result>0){
          response.getWriter().write("<script language=\"javascript\">alert('添加用户成功');location.href='http://localhost:8080/user/selectUserList'</script>");
      }else{
          response.getWriter().write("<script language=\"javascript\">alert('添加用户失败');location.href='http://localhost:8080/user/selectUserList'</script>");
      }

  }
  /** 点击用户冻结或者解冻的按钮，修改用户的状态*/
  @RequestMapping(value = "/updateLockState")
    public void updateLockState(Integer userid,Integer lockstate,HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
      User user = userService.getById(userid);
      user.setLockstate(lockstate);
      int result = userService.update(user);
      if(result>0){
        response.getWriter().write("<script language=\"\">alert('修改用户状态成功');location.href='http://localhost:8080/user/selectUserList'</script>");
      }else{
          response.getWriter().write("<script language=\"\">alert('修改用户状态失败');location.href='http://localhost:8080/user/selectUserList'</script>");
      }


  }
}
