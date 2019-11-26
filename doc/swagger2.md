- [Spring Boot集成swagger2生成接口文档](https://www.jianshu.com/p/a115c9367a59)

### @ApiModelProperty用法
- @ApiModelProperty()用于方法，字段； 表示对model属性的说明或者数据操作更改 
- value–字段说明 
- name–重写属性名字 
- dataType–重写属性类型 
- required–是否必填 
- example–举例说明 
- hidden–隐藏 
```
@ApiModel(value="user对象",description="用户对象user")
public class User implements Serializable{
    private static final long serialVersionUID = 1L;
     @ApiModelProperty(value="用户名",name="username",example="xingguo")
     private String username;
     @ApiModelProperty(value="状态",name="state",required=true)
      private Integer state;
      private String password;
      private String nickName;
      private Integer isDeleted;

      @ApiModelProperty(value="id数组",hidden=true)
      private String[] ids;
      private List<String> idList;
     //省略get/set
}
```

