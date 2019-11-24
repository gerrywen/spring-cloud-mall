package com.mall.item.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.item.dto.PageDto;
import com.mall.item.mapper.BrandMapper;
import com.mall.item.pojo.Brand;
import com.mall.item.service.BrandService;
import com.mall.item.vo.PageResultVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public PageResultVo<Brand> queryBrandByPage(PageDto pageDto) {
        /**
         * 1.分页
         */
        PageHelper.startPage(pageDto.getPage(), pageDto.getRows());

        /**
         *  2.排序
         */
        Example example = new Example(Brand.class);
        if (StringUtils.isNotBlank(pageDto.getSortBy())) {
            example.setOrderByClause(pageDto.getSortBy() + (pageDto.getDesc() ? " DESC" : " ASC"));
        }

        /**
         * 3.查询
         */
        if (StringUtils.isNoneBlank(pageDto.getKey())) {
            example.createCriteria().orLike("name", pageDto.getKey() + "%").orEqualTo("letter", pageDto.getKey().toUpperCase());
        }

        List<Brand> list = this.brandMapper.selectByExample(example);
        /**
         * 4.创建PageInfo
         */
        PageInfo<Brand> pageInfo = new PageInfo<>(list);
        /**
         * 5.返回分页结果
         */
        return new PageResultVo<>(pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getList());
    }

    /**
     * 品牌新增
     * @param brand
     * @param categories
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveBrand(Brand brand, List<Long> categories) {
        // 删除品牌分类
        deleteByBrandIdInCategoryBrand(brand.getId());
        // 新增品牌信息
        this.brandMapper.insertSelective(brand);
        // 新增品牌和分类中间表
        for (Long cid : categories) {
            this.brandMapper.insertCategoryBrand(cid,brand.getId());
        }
    }

    /**
     * 品牌更新
     * @param brand
     * @param categories
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBrand(Brand brand, List<Long> categories) {
        //删除原来的数据
        deleteByBrandIdInCategoryBrand(brand.getId());

        // 修改品牌信息
        this.brandMapper.updateByPrimaryKeySelective(brand);

        //维护品牌和分类中间表
        for (Long cid : categories) {
            //System.out.println("cid:"+cid+",bid:"+brand.getId());
            this.brandMapper.insertCategoryBrand(cid, brand.getId());
        }
    }

    /**
     * 品牌删除
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBrand(Long id) {
        //删除品牌信息
        this.brandMapper.deleteByPrimaryKey(id);

        //维护中间表
        this.brandMapper.deleteByBrandIdInCategoryBrand(id);
    }

    /**
     * 删除中间表中的数据
     * @param bid
     */
    @Override
    public void deleteByBrandIdInCategoryBrand(Long bid) {
        this.brandMapper.deleteByBrandIdInCategoryBrand(bid);
    }

    /**
     * 根据category id查询brand
     * @param cid
     * @return
     */
    @Override
    public List<Brand> queryBrandByCategoryId(Long cid) {
        return this.brandMapper.queryBrandByCategoryId(cid);
    }

    /**
     * 根据品牌id集合查询品牌信息
     * @param ids
     * @return
     */
    @Override
    public List<Brand> queryBrandByBrandIds(List<Long> ids) {
        return this.brandMapper.selectByIdList(ids);
    }
}
