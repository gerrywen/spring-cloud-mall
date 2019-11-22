package com.mall.item.web;

import com.mall.item.dto.PageDto;
import com.mall.item.po.Brand;
import com.mall.item.service.BrandService;
import com.mall.item.vo.PageResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("brand")
public class BrandController {

    @Autowired
    private BrandService brandService;


    /**
     * 分页查询品牌
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @param key
     * @return
     */
    @GetMapping("page")
    public ResponseEntity<PageResultVo<Brand>> queryBrandByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
            @RequestParam(value = "key", required = false) String key)
    {
        PageDto queryByPageAo = new PageDto(page,rows,sortBy,desc,key);
        PageResultVo<Brand> result = this.brandService.queryBrandByPage(queryByPageAo);
        if(result == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/index")
    public String index() {
        return "brand index";
    }

    @GetMapping("/info")
    public String info() {
        return "brand info";
    }

    @GetMapping("/{id}")
    public String queryById(@PathVariable("id") Long id) {
        return "id:" + id;
    }

}
