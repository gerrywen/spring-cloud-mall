package com.mall.oms.service;

import com.mall.oms.pojo.Address;

import java.util.List;

/**
 * program: spring-cloud-mall->AddressService
 * description:
 * author: gerry
 * created: 2019-12-14 09:24
 **/
public interface AddressService {
    /**
     * 删除地址
     * @param addressId
     */
    void deleteAddress(Long addressId);

    /**
     * 更新地址
     * @param address
     */
    void updateAddressByUserId(Address address);

    /**
     * 查询地址
     * @return
     */
    List<Address> queryAddressByUserId();

    /**
     * 新增收货地址
     * @param address
     */
    void addAddressByUserId(Address address);

    /**
     * 根据地址id查询地址
     * @param addressId
     * @return
     */
    Address queryAddressById(Long addressId);
}
