package com.ccc.sys.io.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ccc.sys.io.Vo.DeptVo;
import com.ccc.sys.io.commons.baseResult.BaseResult;
import com.ccc.sys.io.commons.constants.Constants;
import com.ccc.sys.io.commons.tree.TreeNode;
import com.ccc.sys.io.commons.utils.DataGridView;
import com.ccc.sys.io.domain.Dept;
import com.ccc.sys.io.service.DeptService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * <a>Title:DeptController</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/12 15:15
 * @Version 1.0.0
 */
@RestController
@RequestMapping(value = "/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 部门管理
     *
     * @param deptVo
     * @return
     */
    @RequestMapping(value = "loadDeptManagerLeftTreeJson")
    public DataGridView loadDeptManagerLeftTreeJson(DeptVo deptVo) {
        List<Dept> list = this.deptService.list();
        List<TreeNode> treeNodes = new ArrayList<>();
        for (Dept dept : list) {
            Boolean spared = dept.getOpen() == 1 ? true : false;
            treeNodes.add(new TreeNode(dept.getId(), dept.getPid(), dept.getTitle(), spared));
        }
        return new DataGridView(treeNodes);
    }

    /**
     * 查询数据
     *
     * @param deptVo
     * @return
     */
    @RequestMapping(value = "loadAllDept")
    public DataGridView loadAllDept(DeptVo deptVo) {
        IPage<Dept> page = new Page<>(deptVo.getPage(), deptVo.getLimit());
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(deptVo.getTitle()), "title", deptVo.getTitle());
        queryWrapper.like(StringUtils.isNotBlank(deptVo.getAddress()), "address", deptVo.getAddress());
        queryWrapper.like(StringUtils.isNotBlank(deptVo.getRemark()), "remark", deptVo.getRemark());
        queryWrapper.eq(deptVo.getId() != null, "id", deptVo.getId()).or().eq(deptVo.getId() != null, "pid", deptVo.getId());
//           正序排序
        queryWrapper.orderByAsc("ordernum");
        this.deptService.page(page, queryWrapper);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 添加
     *
     * @param deptVo
     * @return
     */
    @RequestMapping(value = "addDept")
    public BaseResult addDept(DeptVo deptVo) {
        try {
            deptVo.setCreatetime(new Date());
            this.deptService.save(deptVo);
            return BaseResult.success(Constants.ADD_MESSAGE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail(Constants.ADD_MESSAGE_FAIL);
        }
    }

    /**
     * 加载最大排序码
     *
     * @return
     */
    @RequestMapping(value = "loadDeptMaxOrderNum")
    public Map<String, Object> loadDeptMaxOrderNum() {
        Map<String, Object> map = new HashMap<String, Object>();
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
//        倒叙查到最大的那个
        queryWrapper.orderByDesc("ordernum");
        IPage<Dept> page = new Page<>(1,1);
        List<Dept> list = this.deptService.page(page,queryWrapper).getRecords();
//        如果有，取出第一个
        if (list.size() > 0) {
            map.put("value", list.get(0).getOrdernum() + 1);
        } else {
//            如果没有，赋值为 1
            map.put("value", 1);
        }
        return map;
    }

    /**
     * 更新用户管理信息
     *
     * @param deptVo
     * @return
     */
    @RequestMapping(value = "updateDept")
    public BaseResult updateDept(DeptVo deptVo) {
        try {
            this.deptService.updateById(deptVo);
            return BaseResult.success(Constants.UPDATE_MESSAGE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail(Constants.UPDATE_MESSAGE_FAIL);
        }
    }

    /**
     * 查询当前部门有无子部门
     *
     * @param deptVo
     * @return
     */
    @RequestMapping(value = "checkDeptHasChildrenNode")
    public Map<String, Object> checkDeptHasChildrenNode(DeptVo deptVo) {
        Map<String, Object> map = new HashMap<String, Object>();

        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid",deptVo.getId());
        List<Dept> list = this.deptService.list(queryWrapper);
        if (list.size() > 0) {
            map.put("value", true);
        } else {
            map.put("value", false);
        }
        return map;
    }

    /**
     * 删除部门
     *
     * @param deptVo
     * @return
     */
    @RequestMapping(value = "deleteDept")
    public BaseResult deleteDept(DeptVo deptVo) {
        try {
            this.deptService.removeById(deptVo.getId());
            return BaseResult.success(Constants.DELECE_MESSAGE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail(Constants.DELECE_MESSAGE_FAIL);
        }
    }


}
