package com.example.order.util;

import com.example.order.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chencj
 * @description Excel工具类
 * @date 2020/6/30
 */
@Slf4j
public class PoiUtil {
    private final static String xls = "xls";
    private final static String xlsx = "xlsx";
    public static List<UserDTO> readExcel(InputStream io, String fileName) throws IOException {
        checkFile(fileName);
        Workbook workbook = getWorkBook(io,fileName);
        List<UserDTO> userDTOList=new ArrayList<>();
        if(workbook != null){
            for(int sheetNum = 0;sheetNum < workbook.getNumberOfSheets();sheetNum++){
                Sheet sheet = workbook.getSheetAt(sheetNum);
                List<UserDTO> list=getField(sheet);
                userDTOList.addAll(list);
            }
            workbook.close();
        }
        return userDTOList;
    }
    public static List<UserDTO> getField(Sheet sheet) {
        if(sheet != null){
            int lastRowNum = sheet.getLastRowNum();
            int id= 0,name= 0,email= 0,sex=0,role=0,group=0;
            Row row=sheet.getRow(0);
            int lastCellNum= row.getLastCellNum();
            for(int i=0;i<=lastCellNum;i++) {
                if(row.getCell(i)==null) {
                    continue;
                }
                String ss=row.getCell(i).toString().trim();
                if(ss.equals("工号")){
                    id=i;
                }
                else if(ss.equals("姓名")){
                    name=i;
                }
                else if(ss.equals("邮箱")){
                    email=i;
                }
                else if(ss.equals("性别")){
                    sex=i;
                }
                else if(ss.equals("角色")){
                    role=i;
                }
                else if(ss.equals("分组")){
                    group=i;
                }
            }
            List<UserDTO> userDTOList=new ArrayList<>();
            for(int rowNum = 1;rowNum <=lastRowNum;rowNum++){
                row = sheet.getRow(rowNum);
                if(row == null||row.getCell(1)==null){
                    break;
                }
                UserDTO userDTO=new UserDTO();
                if(row.getCell(id)!=null){
                    row.getCell(id).setCellType(Cell.CELL_TYPE_STRING);
                    userDTO.setUserId(row.getCell(id).toString().trim());
                }
                if(row.getCell(name)!=null){
                    userDTO.setUserName(row.getCell(name).toString().trim());
                }
                if(row.getCell(email)!=null){
                    userDTO.setUserEmail(row.getCell(email).toString().trim());
                }
                if(row.getCell(sex)!=null){
                    userDTO.setUserSex(row.getCell(sex).toString().trim());
                }
                if(row.getCell(role)!=null){
                    userDTO.setUserRole(row.getCell(role).toString().trim());
                }
                if(row.getCell(group)!=null){
                    userDTO.setUserGroup(row.getCell(group).toString().trim());
                }
                userDTO.setUserPassword("123");
                userDTOList.add(userDTO);
            }
            return userDTOList;
        }
        return null;
    }
    public static void checkFile(String fileName) throws IOException{
        fileName = fileName.substring(fileName.lastIndexOf("."));
        if(!fileName.endsWith(xls) && !fileName.endsWith(xlsx)){
            throw new IOException(fileName + "文件格式错误");
        }
    }
    public static Workbook getWorkBook(InputStream io,String fileName) {
        Workbook workbook = null;
        try {
            if (fileName.endsWith(xls)) {
                //2003
                workbook = new HSSFWorkbook(io);
            } else if (fileName.endsWith(xlsx)) {
                //2007
                workbook = new XSSFWorkbook(io);
            }
        } catch (IOException e) {
            log.warn("解析错误");
        }
        return workbook;
    }
}
