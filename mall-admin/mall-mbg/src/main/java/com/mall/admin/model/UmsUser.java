package com.mall.admin.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UmsUser implements Serializable {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "创建用户")
    private String createUser;

    @ApiModelProperty(value = "资源编号(相当于uuid)")
    private String resourceId;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "修改用户")
    private String updateUser;

    @ApiModelProperty(value = "审核状态")
    private Boolean verifyStatus;

    @ApiModelProperty(value = "记录版本")
    private Integer version;

    @ApiModelProperty(value = "用户类型:1=手机号注册,2=微信")
    private Boolean userType;

    @ApiModelProperty(value = "用户帐号")
    private String account;

    @ApiModelProperty(value = "真实姓名")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "密码盐")
    private String salt;

    @ApiModelProperty(value = "电子邮箱")
    private String email;

    @ApiModelProperty(value = "入伍时间")
    private Date entryTime;

    @ApiModelProperty(value = "生日")
    private Date birthday;

    @ApiModelProperty(value = "性别:0=保密,1=男,2=女")
    private Boolean gender;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "等级")
    private Boolean level;

    @ApiModelProperty(value = "余额")
    private BigDecimal money;

    @ApiModelProperty(value = "积分")
    private Integer score;

    @ApiModelProperty(value = "上次登录时间")
    private Date lastedTime;

    @ApiModelProperty(value = "登录时间")
    private Date loginTime;

    @ApiModelProperty(value = "登陆次数")
    private Integer loginCount;

    @ApiModelProperty(value = "登录IP")
    private String loginIp;

    @ApiModelProperty(value = "帐号锁定时间")
    private Date lockoutTime;

    @ApiModelProperty(value = "密码重试次数:超过10次提示联系管理员")
    private Boolean loginFailure;

    @ApiModelProperty(value = "密码最后修改时间")
    private Date changedTime;

    @ApiModelProperty(value = "允修改帐号的次数:每修改一次帐号减1，大0前允许修改")
    private Boolean changeAccount;

    @ApiModelProperty(value = "所属连队")
    private String company;

    @ApiModelProperty(value = "证件类型:1=身份证")
    private Boolean cardType;

    @ApiModelProperty(value = "证件号码")
    private String cardNo;

    @ApiModelProperty(value = "证件Url")
    private String cardUrl;

    @ApiModelProperty(value = "证件反面Url")
    private String cardBackUrl;

    @ApiModelProperty(value = "Token")
    private String token;

    @ApiModelProperty(value = "备注:审核意见填此")
    private String memo;

    @ApiModelProperty(value = "审核状态:1=审核通过(正常),0=待审核,2=审核失败,9=禁用")
    private Boolean status;

    @ApiModelProperty(value = "是否已被锁定:0=否,1=是")
    private Boolean isLockedOut;

    @ApiModelProperty(value = "是否专家:0=否,1=是")
    private Boolean isExpert;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Boolean getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(Boolean verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getUserType() {
        return userType;
    }

    public void setUserType(Boolean userType) {
        this.userType = userType;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Boolean getLevel() {
        return level;
    }

    public void setLevel(Boolean level) {
        this.level = level;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getLastedTime() {
        return lastedTime;
    }

    public void setLastedTime(Date lastedTime) {
        this.lastedTime = lastedTime;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLockoutTime() {
        return lockoutTime;
    }

    public void setLockoutTime(Date lockoutTime) {
        this.lockoutTime = lockoutTime;
    }

    public Boolean getLoginFailure() {
        return loginFailure;
    }

    public void setLoginFailure(Boolean loginFailure) {
        this.loginFailure = loginFailure;
    }

    public Date getChangedTime() {
        return changedTime;
    }

    public void setChangedTime(Date changedTime) {
        this.changedTime = changedTime;
    }

    public Boolean getChangeAccount() {
        return changeAccount;
    }

    public void setChangeAccount(Boolean changeAccount) {
        this.changeAccount = changeAccount;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Boolean getCardType() {
        return cardType;
    }

    public void setCardType(Boolean cardType) {
        this.cardType = cardType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardUrl() {
        return cardUrl;
    }

    public void setCardUrl(String cardUrl) {
        this.cardUrl = cardUrl;
    }

    public String getCardBackUrl() {
        return cardBackUrl;
    }

    public void setCardBackUrl(String cardBackUrl) {
        this.cardBackUrl = cardBackUrl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getIsLockedOut() {
        return isLockedOut;
    }

    public void setIsLockedOut(Boolean isLockedOut) {
        this.isLockedOut = isLockedOut;
    }

    public Boolean getIsExpert() {
        return isExpert;
    }

    public void setIsExpert(Boolean isExpert) {
        this.isExpert = isExpert;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createTime=").append(createTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", resourceId=").append(resourceId);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", verifyStatus=").append(verifyStatus);
        sb.append(", version=").append(version);
        sb.append(", userType=").append(userType);
        sb.append(", account=").append(account);
        sb.append(", username=").append(username);
        sb.append(", nickname=").append(nickname);
        sb.append(", password=").append(password);
        sb.append(", salt=").append(salt);
        sb.append(", email=").append(email);
        sb.append(", entryTime=").append(entryTime);
        sb.append(", birthday=").append(birthday);
        sb.append(", gender=").append(gender);
        sb.append(", mobile=").append(mobile);
        sb.append(", avatar=").append(avatar);
        sb.append(", level=").append(level);
        sb.append(", money=").append(money);
        sb.append(", score=").append(score);
        sb.append(", lastedTime=").append(lastedTime);
        sb.append(", loginTime=").append(loginTime);
        sb.append(", loginCount=").append(loginCount);
        sb.append(", loginIp=").append(loginIp);
        sb.append(", lockoutTime=").append(lockoutTime);
        sb.append(", loginFailure=").append(loginFailure);
        sb.append(", changedTime=").append(changedTime);
        sb.append(", changeAccount=").append(changeAccount);
        sb.append(", company=").append(company);
        sb.append(", cardType=").append(cardType);
        sb.append(", cardNo=").append(cardNo);
        sb.append(", cardUrl=").append(cardUrl);
        sb.append(", cardBackUrl=").append(cardBackUrl);
        sb.append(", token=").append(token);
        sb.append(", memo=").append(memo);
        sb.append(", status=").append(status);
        sb.append(", isLockedOut=").append(isLockedOut);
        sb.append(", isExpert=").append(isExpert);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}