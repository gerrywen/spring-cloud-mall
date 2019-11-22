package com.mall.pojo;

import com.google.gson.Gson;
import com.mall.ao.PageAo;
import com.mall.item.dto.PageDto;

public class PageAo2PageDtoConverter {
    public static PageDto convert(PageAo pageAo)
    {
        // Gson提供了fromJson() 和toJson() 两个直接用于解析和生成的方法，前者实现反序列化，后者实现了序列化；
        // 同时每个方法都提供了重载方法
        // https://www.cnblogs.com/qinxu/p/9504412.html
        Gson gson = new Gson();
        PageDto pageDto = new PageDto();
        pageDto.setPage(pageAo.getPage());
        pageDto.setRows(pageAo.getRows());
        pageDto.setKey(pageAo.getKey());
        pageDto.setDesc(pageAo.getDesc());
        pageDto.setSortBy(pageAo.getSortBy());
        return pageDto;
    }
}
