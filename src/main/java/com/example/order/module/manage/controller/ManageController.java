package com.example.order.module.manage.controller;

import com.example.order.dto.UserDTO;
import com.example.order.module.user.service.UserService;
import com.example.order.module.user.service.UserServiceImpl;
import com.example.order.util.NormUtil;
import com.example.order.util.PoiUtil;
import com.example.order.util.ResultInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author Chencj
 * @description 管理员控制
 * @date 2020/6/30
 */
@Slf4j
@Api(tags = "管理员api")
@Controller
public class ManageController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserService userService;

    @PostMapping("/uploadUerExcel")
    @ApiOperation(value = "上传员工excel",httpMethod = "POST")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "file",value = "excel文件",dataType = "MultipartFile")
    )
    @ResponseBody
    public ResultInfo uploadUserExcle(@RequestParam("file") MultipartFile file) throws IOException {
        ResultInfo resultInfo;
        if(file==null){
            resultInfo=ResultInfo.failure();
            resultInfo.setMsg("文件不存在");
            return resultInfo;
        }
        log.info("fileName is {}",file.getOriginalFilename());
        List<UserDTO> userDTOList= PoiUtil.readExcel(file.getInputStream(),file.getOriginalFilename());
        for (UserDTO user:userDTOList
             ) {
            user = NormUtil.normUtil(user);
            user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        }
        this.userService.insertAllUser(userDTOList);
        resultInfo=ResultInfo.success();
        resultInfo.setData(userDTOList);
        return resultInfo;
    }

    @GetMapping("/findOne")
    @ApiOperation(value = "查询单个员工",httpMethod = "GET")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "员工工号",dataType = "String")
    )
    @ResponseBody
    public ResultInfo findOne(@RequestParam("id")String id){
        ResultInfo resultInfo=ResultInfo.success();
        UserDTO userDTO=this.userService.getUserById(id);
        resultInfo.setData(userDTO);
        return resultInfo;
    }

    @GetMapping("/findAll")
    @ApiOperation(value = "查询所有员工",httpMethod = "GET")
    @ResponseBody
    public ResultInfo findAll(){
        ResultInfo resultInfo=ResultInfo.success();
        List<UserDTO> userDTOList=userService.getAllUser();
        resultInfo.setData(userDTOList);
        return resultInfo;
    }

    public static void main(String[] args) {
        String p=System.getProperty("user.dir");
        String sepa = java.io.File.separator;
        System.out.println(p);
    }
}
