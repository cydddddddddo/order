package com.example.order.module.menu.controller;

import com.example.order.dto.Menu;
import com.example.order.dto.UserDTO;
import com.example.order.module.menu.service.MenuServiceImpl;
import com.example.order.util.Constast;
import com.example.order.util.ResultInfo;
import com.example.order.util.TreeNode;
import com.example.order.util.TreeNodeBuilder;
import com.example.order.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cy
 * @data 2020/6/30 - 15:01
 */
@Controller
public class MenuController extends BaseController {

    @Autowired
    private MenuServiceImpl menuService;

    @ResponseBody
    @RequestMapping("menu/loadIndexLeftMenuJson")
    public ResultInfo getMenuList(){
        UserDTO user = this.getCurrentUser();
        List<Menu> list = null;
        switch (user.getUserRole()){
            case "ROLE_man":
                list = menuService.getMenuByType(1,1);
                break;
            case "ROLE_lea":
                list = menuService.getMenuByType(1,2);
                break;
            default:
                list = menuService.getMenuByType(1,3);
                break;
        }
        List<TreeNode> treeNodes = new ArrayList<>();
        for (Menu p :
                list) {
            Integer id=p.getId();
            Integer pid=p.getPid();
            String title=p.getTitle();
            String icon=p.getIcon();
            String href=p.getHref();
            Boolean spread=(p.getOpen().equals(Constast.OPEN_TRUE))?true:false;
            treeNodes.add(new TreeNode(id,pid,title,icon,href,spread));
        }

        List<TreeNode> list2 = TreeNodeBuilder.build(treeNodes,1);


        return new ResultInfo(String.valueOf(list.size()),"0",list2);
    }
}
