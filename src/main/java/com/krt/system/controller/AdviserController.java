package com.krt.system.controller;

import com.krt.common.bean.ReturnBean;
import com.krt.common.entity.LayuiTable;
import com.krt.system.entity.Adviser;
import com.krt.system.service.AdviserService;
import com.krt.system.service.mpi.AdviserServicempi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author 黄宗滨
 * @Description   顾问资源的操作
 * @Date  2019/5/28
 **/
@Controller
public class AdviserController {

    Logger logger= LoggerFactory.getLogger(getClass());
    @Resource
   private AdviserService adviserService;

    /**
     *   资源列表调转
     * @return
     */
    @GetMapping("system/adviserlist")
    public  String adviserlist(){
        return  "system/adviserAction/adviserList";
    }

    /**
     *   查询列表
     * @param para
     * @return
     */
    @PostMapping("system/adviserlist")
    @ResponseBody
    public LayuiTable adviserlist(@RequestParam Map para){
        LayuiTable layuiTable=new LayuiTable();
        layuiTable=adviserService.selectAdviserList(para);
        return layuiTable;
    }

    /**
     * 添加资源用户页面调转
     */
    @GetMapping("system/adviserAdd")
    public String advicerAdd(){
        return "system/adviserAction/adviserAdd";
    }

    /**
     * 添加资源用户添加
     */
    @PostMapping("system/adviserAdd")
    @ResponseBody
    public ReturnBean insetrAdviser(Adviser adviser){
        ReturnBean rb;
        try {
            adviserService.insetrAdviser(adviser);
            rb=ReturnBean.ok();
        }catch (Exception e){
            e.printStackTrace();
            rb=ReturnBean.error("添加失败");
        }
        return rb;
    }

    /**
     * 删除
     */
    @PostMapping("system/adviserDelet")
    @ResponseBody
    public ReturnBean deletAdviser(int id){
        ReturnBean rb;
        try {
            adviserService.deletAdviser(id);
            rb=ReturnBean.ok();
        }catch (Exception e){
            e.printStackTrace();
            rb=ReturnBean.error("删除失败");
        }
        return rb;
    }

    /**
     * 修改页面跳转
     */
    @GetMapping("system/adviserEdit")
    public  String adviserEdit(HttpServletRequest request,int id){
        Map<Object, Object> map = new HashMap<>();
        map=adviserService.selectAdviserone(id);
        System.out.println("slect====+++map+++===="+map);
        request.setAttribute("map",map);
        return  "system/adviserAction/adviserEdit";
    }

    /**
     * 修改页面 确定
     */

    @PostMapping("system/adviserEdit")
    @ResponseBody
    public  ReturnBean updateAdiviser(Adviser adviser){
        System.out.println("adviser======"+adviser);
        ReturnBean rb;
        try {
            adviserService.updateAdiviser(adviser);
            rb=ReturnBean.ok();
        }catch (Exception e){
            e.printStackTrace();
            rb=ReturnBean.error("修改用户失败");
        }
        return rb;
    }

    /**
     * 审批状态设置
     */
    @PostMapping("system/adviserStatus")
    @ResponseBody
    public  ReturnBean updateStatus(int id){
        ReturnBean rb;
        try {
            adviserService.updateStatus(id);
            rb=ReturnBean.ok();
        }catch (Exception e){
            e.printStackTrace();
            rb=ReturnBean.error("更改状态失败");
        }
        return rb;
    }
}
