package com.mall.admin.base.service;

import com.mall.admin.base.bo.SysLogBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class SysLogService {
    public boolean save(SysLogBO sysLogBO){
        // 这里就不做具体实现了
        log.info("---------记录日志---------");
        log.info(sysLogBO.getParams());
        log.info("---------记录日志---------");
        return true;
    }
}
