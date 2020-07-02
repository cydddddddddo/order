package com.example.order.module.manage.controller;

import com.example.order.dto.MealDTO;
import com.example.order.module.user.service.MealService;
import com.example.order.util.Path;
import com.example.order.util.ResultInfo;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author Chencj
 * @description 菜品管理
 * @date 2020/6/30
 */
@RestController
@Api(tags = "菜品api")
@RequestMapping("/meal")
public class MealController {
    @Resource
    private MealService mealService;

    @ApiOperation(value = "查询所有菜品",  httpMethod = "GET")
    @GetMapping("/findAll")
    public ResultInfo finAll(){
        ResultInfo resultInfo=ResultInfo.success();
        List<MealDTO> mealDTOList=mealService.findAll();
        resultInfo.setData(mealDTOList);
        return resultInfo;
    }
    @ApiOperation(value = "插入菜品",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "菜品图片", dataType = "MultipartFile"),
            @ApiImplicitParam(name = "name", value = "菜名", dataType = "String"),
            @ApiImplicitParam(name = "description", value = "菜品描述", dataType = "String")
            })
    @PostMapping("/insert")
    public ResultInfo insert(@RequestParam("file") MultipartFile file,@RequestParam("name")String name,@RequestParam("description")String description) throws IOException {
        String id= UUID.randomUUID().toString().replaceAll("-","");
        String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        File mealFile=new File(Path.derectory+Path.separator+"picture"+Path.separator+id+suffix);
        if(!mealFile.exists()){
            mealFile.mkdirs();
        }
        file.transferTo(mealFile);
        MealDTO mealDTO=new MealDTO();
        mealDTO.setId(id);
        mealDTO.setDescription(description);
        mealDTO.setName(name);
        mealDTO.setStatus("1");
        mealDTO.setPicture("picture"+Path.separator+id+suffix);
        mealService.insert(mealDTO);
        return ResultInfo.success();
    }
    @ApiOperation(value = "删除菜品",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "菜品id集合", dataType = "List")
    })
    @PostMapping("/delete")
    public ResultInfo deleteById(@RequestParam("id")List<String> ids){
        for(String id:ids) {
            MealDTO mealDTO = mealService.findOne(id);
            File file = new File(Path.derectory + Path.separator + mealDTO.getPicture());
            file.delete();
            mealService.deleteById(id);
        }
        return ResultInfo.success();
    }

    @ApiOperation(value = "更细菜品文字信息",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "菜品id", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "菜名", dataType = "String"),
            @ApiImplicitParam(name = "description", value = "菜品描述", dataType = "String")
    })
    @PostMapping("/update")
    public ResultInfo update(@RequestParam("name")String name,@RequestParam("description")String description,@RequestParam("id")String id) throws IOException {
        MealDTO mealDTO=mealService.findOne(id);
        mealDTO.setDescription(description);
        mealDTO.setName(name);
        mealService.update(mealDTO);
        return ResultInfo.success();
    }
    @ApiOperation(value = "更细菜品状态",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "菜品id", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "菜品状态 0:失效 1: 有效", dataType = "String")
    })
    @PostMapping("/updateStatus")
    public ResultInfo update(@RequestParam("status")String status,@RequestParam("id")String id) {
        mealService.updateStatusById(id,status);
        return ResultInfo.success();
    }

    @ApiOperation(value = "更换菜品图片",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "菜品图片", dataType = "MultipartFile"),
            @ApiImplicitParam(name = "id", value = "菜品id", dataType = "String"),
    })
    @PostMapping("/resetPicture")
    public ResultInfo resetPicture(@RequestParam("file")MultipartFile file,@RequestParam("id")String id) throws IOException {
        ResultInfo resultInfo;
        MealDTO mealDTO=mealService.findOne(id);
        if(mealDTO==null){
            resultInfo=ResultInfo.failure();
            resultInfo.setMsg("更换失败");
            return resultInfo;
        }
        String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        File mealFile=new File(Path.derectory+Path.separator+"picture"+Path.separator+id+suffix);
        if(mealFile.exists()){
            file.transferTo(mealFile);
        }else{
            file.transferTo(mealFile);
            File oldFile=new File(Path.derectory+Path.separator+mealDTO.getPicture());
            oldFile.delete();
            mealDTO.setPicture("picture"+Path.separator+id+suffix);
            mealService.update(mealDTO);
        }
        return ResultInfo.success();
    }
}
