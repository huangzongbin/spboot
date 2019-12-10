package com.krt.system.service.mpi;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.krt.common.entity.LayuiTable;
import com.krt.common.util.ShiroUtil;
import com.krt.system.entity.Adviser;
import com.krt.system.mapper.AdviserMapper;
import com.krt.system.service.AdviserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 黄宗滨
 * @Description
 * @Date 2019/5/28
 **/
@Service
public class AdviserServicempi implements AdviserService {

    @Resource
    private AdviserMapper adviserMapper;

    @Override
    public LayuiTable selectAdviserList(Map para) {

        LayuiTable layuiTable=new LayuiTable();
        int page=Integer.parseInt(para.get("page")+"");
        int limit=Integer.parseInt(para.get("limit")+"");
        page=(page-1)*limit;
        para.put("page",page);
        para.put("limit",limit);
        List list=new ArrayList();
        list=adviserMapper.selectAdviserList(para);
        int size=adviserMapper.selectAdviserCount();
        layuiTable.setCount(size);
        layuiTable.setData(list);
        return layuiTable;
    }

    /**
     * 用于添加
     */
    @Override
    public void insetrAdviser(Adviser adviser){
        Map currentUser = ShiroUtil.getCurrentUser();
        adviser.setInsertName((String) currentUser.get("name"));
        adviserMapper.insetrAdviser(adviser);
    }

    /**
     *删除
     */
    @Override
    public void deletAdviser(int id){
        adviserMapper.deletAdviser(id);
    }


    /**
     * 查询单个用户信息
     */
    @Override
    public  Map selectAdviserone(int id){
        Map map =new HashMap();
        map=adviserMapper.selectAdciserone(id);
        return map;
    }


    /**
     * 修改用户
     */
    @Override
    public  void updateAdiviser(Adviser adviser){
        adviserMapper.updateAdiviser(adviser);
    }

    /**
     * 更改转态失败
     */
    @Override
    public  void updateStatus(int id){
        adviserMapper.updateStatus(id);
    }

}

