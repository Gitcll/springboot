package com.softroad.controller;

import com.softroad.pojo.Users;
import com.softroad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;



    @RequestMapping("/user")
    public String SelectAll(Model model){
      List<Users> list =  userService.selectAllUser();
      model.addAttribute("list" ,list);
        return "index";

    }

    /**
     * 分页查询
     * @param model
     * @param curpage
     * @return
     */
    @RequestMapping("/page")
    public String pageSelect(Model model ,@RequestParam(value = "curpage",required = false) Integer curpage){

        if(null == curpage || curpage < 1){
            curpage = 1;
        }

        //每页显示的数据
        int pageSize = 3;

        //查询总数据
        int totalRows = userService.pageSize();

        //页码
        int totalPage = totalRows / pageSize;

        //页码余数
        int left = totalRows % pageSize;
        if(left  > 0){
            totalPage = totalPage + 1;
        }
        //页码数量大于总页码
        if(curpage > totalPage){
            curpage = totalPage;
        }
        //从哪里开始
        int start = (curpage - 1) * pageSize;

        //查询全部
        List<Users> list =  userService.selectAllUser();
        //分页查询
        List<Users> pageList = userService.pageSelect(start,pageSize);
        model.addAttribute("pageList" ,pageList);
        model.addAttribute("curpage",curpage);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("totalRows",totalRows);
        model.addAttribute("list" ,list);
        return "page";
    }

    /**
     * 删除用户信息
     * @param id
     * @return
     */
    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam(value = "id",required = false) Integer id){
        userService.deleteUser(id);
        return "redirect:/page";
    }

    /**
     * 修改用户信息
     * @param userid
     * @param model
     * @return
     */
    @RequestMapping("/updatePage")
    public String updateUser(@RequestParam(value = "userid",required = false) Integer userid,Model model){
        Users users =  userService.selectByUserId(userid);
        model.addAttribute("users",users);
        return "updatePage";
    }

    /**
     * 添加用户跳转页码
     * @return
     */
    @RequestMapping("/addUser")
    public String addUser(){
        return "updatePage";
    }

    /**
     * 添加用户
     * @param users
     * @return
     */
    @RequestMapping("/AddAndUpdateUser")
    public String AddAndUpdateUser(Users users){
        Integer userid =   users.getUserid();
        if(userid == null){
            userService.addUser(users);
        }else {
            userService.updateUser(users);
        }
        return "redirect:/page";
    }

    /**
     * 跳转查询页面
     * @return
     */
    @RequestMapping("/selectUser")
    public String selectUser(Model model,Users users){
        //查询总数据
        int totalRows = userService.pageSize();

        model.addAttribute("users",users);
        model.addAttribute("totalRows",totalRows);
        return "selectWidow";
    }

    /**
     * 通过用户ID查询
     * @param model
     * @param users
     * @return
     */
    @RequestMapping("/selectByIdUser")
    public String selectByIdUser(Model model,Users users) {
        //查询总数据
        int totalRows = userService.pageSize();
        //获取前台用户ID
        Integer userid = users.getUserid();
        //判断输入用户id是否正确,不存在返回当前页面
        if(userid > totalRows || userid < 1){
            return "selectWidow";
        }
        Users user =  userService.selectByUserId(userid);
        model.addAttribute("users",user);
        return "index";
    }

    @RequestMapping("/selectByNameUser")
    public String selectByNameUser(Model model,Users users) {

        String username = users.getUsername();
        List<Users> user = userService.selectByName(username);
        System.err.println(user);
        model.addAttribute("users",user);
        return "index";
    }
}
