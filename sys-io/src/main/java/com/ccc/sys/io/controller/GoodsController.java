package com.ccc.sys.io.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ccc.sys.io.Vo.GoodsVo;
import com.ccc.sys.io.Vo.ProviderVo;
import com.ccc.sys.io.commons.baseResult.BaseResult;
import com.ccc.sys.io.commons.constants.Constants;
import com.ccc.sys.io.commons.utils.DataGridView;
import com.ccc.sys.io.commons.utils.UploadFileUtils;
import com.ccc.sys.io.domain.Goods;
import com.ccc.sys.io.domain.Provider;
import com.ccc.sys.io.service.GoodsService;
import com.ccc.sys.io.service.ProviderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <a>Title:GoodsController</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/18 13:42
 * @Version 1.0.0
 */
@RestController
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private ProviderService providerService;


    /**
     * 查询所有
     *
     * @param goodsVo
     * @return
     */
    @RequestMapping(value = "loadAllGoods")
    public DataGridView loadAllGoods(GoodsVo goodsVo) {
        IPage<Goods> page = new Page<>(goodsVo.getPage(), goodsVo.getLimit());
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(goodsVo.getProviderid() != null && goodsVo.getProviderid() != 0, "providerid", goodsVo.getProviderid());
        queryWrapper.like(StringUtils.isNoneBlank(goodsVo.getGoodsname()), "goodsname", goodsVo.getGoodsname());
        queryWrapper.like(StringUtils.isNoneBlank(goodsVo.getProductcode()), "prodectcode", goodsVo.getProductcode());
        queryWrapper.like(StringUtils.isNoneBlank(goodsVo.getPromitcode()), "promitcode", goodsVo.getPromitcode());
        queryWrapper.like(StringUtils.isNoneBlank(goodsVo.getDescription()), "description", goodsVo.getDescription());
        queryWrapper.like(StringUtils.isNoneBlank(goodsVo.getSize()), "size", goodsVo.getSize());
        this.goodsService.page(page, queryWrapper);
        List<Goods> records = page.getRecords();
        for (Goods goods : records) {
            Provider provider = this.providerService.getById(goods.getProviderid());
            if (null != provider) {
                goods.setProvidername(provider.getProvidername());
            }
        }
        return new DataGridView(page.getTotal(), records);
    }


    /**
     * 添加
     *
     * @param goodsVo
     * @return
     */
    @RequestMapping(value = "addGoods")
    public BaseResult addGoods(GoodsVo goodsVo) {
        try {
//            分两种情况上传 1. 默认图片  2. 自定义图片
//            同时 在上传过程中还要去掉 _temp 后缀
            if (goodsVo.getGoodsimg() != null && goodsVo.getGoodsimg().endsWith("_temp")) {
                String newName = UploadFileUtils.renameFile(goodsVo.getGoodsimg());
                goodsVo.setGoodsimg(newName);
            }
            this.goodsService.save(goodsVo);
            return BaseResult.success(Constants.ADD_MESSAGE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail(Constants.ADD_MESSAGE_FAIL);
        }
    }

    /**
     * 更新
     *
     * @param goodsVo
     * @return
     */
    @RequestMapping(value = "updateGoods")
    public BaseResult updateGoods(GoodsVo goodsVo) {
        try {
//            如果不是默认图片 则做处理
            if (!(goodsVo.getGoodsimg() != null && goodsVo.getGoodsimg().equals(Constants.DEFAULT_IMAGES))) {
//                再判断是不是后缀名为 _temp 如果是则去掉改后缀
                if (goodsVo.getGoodsimg().endsWith("_temp")) {
                    String newName = UploadFileUtils.renameFile(goodsVo.getGoodsimg());
                    goodsVo.setGoodsimg(newName);
//                    并删除原来的图片
                    String oldPaht = this.goodsService.getById(goodsVo.getId()).getGoodsimg();
                    if (!oldPaht.equals(Constants.DEFAULT_IMAGES)) {
                        UploadFileUtils.removeFileByPath(oldPaht);
                    }
                }
            }
            this.goodsService.updateById(goodsVo);
            return BaseResult.success(Constants.UPDATE_MESSAGE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail(Constants.UPDATE_MESSAGE_FAIL);
        }
    }

    /**
     * 单个删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "deleteGoods")
    public BaseResult deleteGoods(Integer id, String goodsimg) {
        try {
            if (!goodsimg.equals(Constants.DEFAULT_IMAGES)) {
                UploadFileUtils.removeFileByPath(goodsimg);
            }
            this.goodsService.removeById(id);
            return BaseResult.success(Constants.DELECE_MESSAGE_SUCCESS, Constants.STATUS_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail(Constants.DELECE_MESSAGE_FAIL, Constants.STATS_FAIL);
        }
    }


    @RequestMapping(value = "loadAllGoodsrForSelect")
    public DataGridView loadAllGoodsForSelect() {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("available", Constants.AVALIABLE_TRUE);
        List<Goods> list = this.goodsService.list(queryWrapper);
        return new DataGridView(list);
    }
}
