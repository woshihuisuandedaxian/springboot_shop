package com.qf.controller;

import com.qf.entity.Address;
import com.qf.entity.User;
import com.qf.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author ZouXianTao
 * @Date2019/12/30
 */
@Controller
@RequestMapping(value = "/address")
public class AddressController {
    @Autowired
    private IAddressService addressService;
    @RequestMapping(value = "/addAddress")
    public void addAddress(Address address, HttpServletResponse response, HttpSession session){
        response.setContentType("text/html;charset=utf-8");
        User user = (User) session.getAttribute("LOGIN_USER");
        if(user!=null){
            address.setUserid(user.getId());
            int result = addressService.add(address);
            if(result>0){
                try {
                    response.getWriter().write("<script language=\"\">alert('添加地址成功');location.href='http://localhost:8080/order/toOrder'</script>");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    response.getWriter().write("<script language=\"\">alert('添加地址失败');location.href='http://localhost:8080/order/toOrder'</script>");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }else{
            try {
                response.getWriter().write("<script language=\"\">alert('请先登录');location.href='http://localhost:8080/toLogin'</script>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }




    }
}
