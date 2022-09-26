package com.zd.tools.project.generator.analysis.process;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.zd.tools.project.generator.model.AbstractModule;
import com.zd.tools.project.generator.model.Project;

import java.util.ArrayList;
import java.util.List;

public class WrapTreeUtil {

    /*构建树型结构,多个根节点*/
    public static void buildTree(Project project, List<AbstractModule> list){
        for(AbstractModule item: getRootNode(project, list)){
            recursiveTree(item, list);
        }
    }

    private static List<AbstractModule> getRootNode(Project project,  List<AbstractModule> list){
        List<AbstractModule> roots = new ArrayList<>();
        for(AbstractModule item: list){
            if( StrUtil.isEmpty(item.getWrapBy()) ){
                item.setBasePath(project.getBasePath() + project.getName() + "-" + item.getName());
                roots.add(item);
            }
        }
        return roots;
    }

    /*构建树型结构，单个根节点*/
    private static void recursiveTree( AbstractModule parent, List<AbstractModule> list){
        for(AbstractModule item: list){
            if(ObjectUtil.equal(parent.getName(), item.getWrapBy())){
                item.setBasePath(parent.getBasePath());
                recursiveTree(item, list);
            }
        }
    }
}
