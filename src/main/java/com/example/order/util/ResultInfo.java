package com.example.order.util;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Chencj
 * @description
 * @date 2020/2/7
 */

//zcj
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@ApiModel(value="基础返回类",description="基础返回类")
public class ResultInfo {
    @ApiModelProperty(example="0")
    private String code;
    @ApiModelProperty(example="提示信息")
//=======
public class ResultInfo {
    private String code;

    private String msg;
    private Object data;

    public static final ResultInfo CHANGE_ERROR=new ResultInfo(Constast.ERROR,"修改失败");
    public static final ResultInfo  LOGIN_SUCCESS=new ResultInfo(Constast.OK, "登陆成功");
    public static final ResultInfo  LOGIN_ERROR_PASS=new ResultInfo(Constast.ERROR, "登陆失败,密码错误");
    public static final ResultInfo  LOGIN_ERROR_CODE=new ResultInfo(Constast.ERROR, "登陆失败,验证码错误");
    public static final ResultInfo  LOGIN_ERROR_NULL=new ResultInfo(Constast.ERROR,"登陆失败，用户未注册");
    public static final ResultInfo  LOGIN_ERROR_ROLE=new ResultInfo(Constast.ERROR,"登陆失败，请检查身份信息");
    public static final ResultInfo DELETE_SUCCESS=new ResultInfo(Constast.OK,"删除成功");
    public static final ResultInfo DELETE_FAILED=new ResultInfo(Constast.ERROR,"删除失败");

    public static final ResultInfo AMEND_SUCCESS=new ResultInfo(Constast.OK,"提交成功！");
    public static final ResultInfo AMEND_FAILED=new ResultInfo(Constast.ERROR,"提交失败，请联系管理员。");

    public static final ResultInfo CHANGE_SUCCESS=new ResultInfo(Constast.OK,"修改成功。");
    public static final ResultInfo CHANGE_FAILED=new ResultInfo(Constast.ERROR,"修改失败，请联系管理员。");
    public static final ResultInfo CHANGE_OLDPWD_FAILED=new ResultInfo(Constast.ERROR,"修改失败，旧密码错误。");
    public static final ResultInfo CHANGE_NEWPWD_FAILED=new ResultInfo(Constast.NEW_REAL,"修改失败，前后密码一致。");
    public static final ResultInfo CHANGE_REALPWD_FAILED=new ResultInfo(Constast.NEW_REAL,"修改失败，确认密码不一致。");
    //public static final ResultObj CHANGE_NULL_FAILED=new ResultObj(Constast.NEW_REAL,"修改失败，不能为空。");

    public static final ResultInfo VERIFY_SUCCESS=new ResultInfo(Constast.OK,"密码正确，解锁成功");
    public static final ResultInfo VERIFY_FAILED=new ResultInfo(Constast.ERROR,"密码错误，解锁失败");

    public static final ResultInfo AMEND_USER_SUCCESS = new ResultInfo(Constast.OK,"修改成功");
    public static final ResultInfo AMEND_USER_FAILED = new ResultInfo(Constast.ERROR,"修改失败");
    public static final ResultInfo AMEND_ROLE_FAILED = new ResultInfo(Constast.ERROR,"权限格式错误");

    public static ResultInfo success() {
        return ResultInfo.builder().code("0").msg("ok").build();
    }

    public static ResultInfo failure() {
        return ResultInfo.builder().code("1").msg("failure").build();
    }

    ResultInfo(String code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
