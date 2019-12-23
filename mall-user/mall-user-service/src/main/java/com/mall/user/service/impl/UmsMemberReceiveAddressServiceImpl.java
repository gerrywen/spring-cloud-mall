package com.mall.user.service.impl;

import com.mall.admin.mapper.UmsMemberReceiveAddressMapper;
import com.mall.admin.model.UmsMemberReceiveAddress;
import com.mall.admin.model.UmsMemberReceiveAddressExample;
import com.mall.auth.entity.UserInfo;
import com.mall.common.base.constant.Constant;
import com.mall.user.interceptor.LoginInterceptor;
import com.mall.user.service.UmsMemberReceiveAddressService;
import com.mall.user.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 用户地址管理Service实现类
 * Created by macro on 2018/8/28.
 */
@Service
public class UmsMemberReceiveAddressServiceImpl implements UmsMemberReceiveAddressService {

    @Autowired
    private UmsMemberReceiveAddressMapper addressMapper;
    @Override
    public int add(UmsMemberReceiveAddress address) {
        //获取登录的用户
        UserInfo currentMember = LoginInterceptor.getLoginUser();
        address.setMemberId(currentMember.getId());
        int insert = addressMapper.insert(address);
        // 将当前地址设置为默认地址
        setDefaultAddress(address);
        return insert;
    }

    @Override
    public int delete(Long id) {
        //获取登录的用户
        UserInfo currentMember = LoginInterceptor.getLoginUser();
        UmsMemberReceiveAddressExample example = new UmsMemberReceiveAddressExample();
        example.createCriteria().andMemberIdEqualTo(currentMember.getId()).andIdEqualTo(id);
        return addressMapper.deleteByExample(example);
    }

    @Override
    public int update(Long id, UmsMemberReceiveAddress address) {
        address.setId(null);
        //获取登录的用户
        UserInfo currentMember = LoginInterceptor.getLoginUser();
        UmsMemberReceiveAddressExample example = new UmsMemberReceiveAddressExample();
        example.createCriteria().andMemberIdEqualTo(currentMember.getId()).andIdEqualTo(id);
        int i = addressMapper.updateByExampleSelective(address, example);
        // 将当前地址设置为默认地址
        setDefaultAddress(address);
        return i;
    }

    @Override
    public List<UmsMemberReceiveAddress> list() {
        //获取登录的用户
        UserInfo currentMember = LoginInterceptor.getLoginUser();
        UmsMemberReceiveAddressExample example = new UmsMemberReceiveAddressExample();
        example.createCriteria().andMemberIdEqualTo(currentMember.getId());
        return addressMapper.selectByExample(example);
    }

    @Override
    public UmsMemberReceiveAddress getItem(Long id) {
        //获取登录的用户
        UserInfo currentMember = LoginInterceptor.getLoginUser();
        UmsMemberReceiveAddressExample example = new UmsMemberReceiveAddressExample();
        example.createCriteria().andMemberIdEqualTo(currentMember.getId()).andIdEqualTo(id);
        List<UmsMemberReceiveAddress> addressList = addressMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(addressList)){
            return addressList.get(0);
        }
        return null;
    }

    /**
     * 将当前地址设置为默认地址
     * @param umsMemberReceiveAddress
     */
    private void setDefaultAddress(UmsMemberReceiveAddress umsMemberReceiveAddress) {
        if (umsMemberReceiveAddress.getDefaultStatus().equals(Constant.ADDRESS_DEFAULT_STATUS_YES)) {
            //如果将本地址设置为默认地址，那么该用户下的其他地址都应该是非默认地址
            List<UmsMemberReceiveAddress> addressList = list();
            addressList.forEach(addressTemp -> {
                if (addressTemp.getDefaultStatus().equals(Constant.ADDRESS_DEFAULT_STATUS_YES)){
                    addressTemp.setDefaultStatus(Constant.ADDRESS_DEFAULT_STATUS_NO);
                    this.addressMapper.updateByPrimaryKeySelective(addressTemp);
                }
            });
        }
    }
}
