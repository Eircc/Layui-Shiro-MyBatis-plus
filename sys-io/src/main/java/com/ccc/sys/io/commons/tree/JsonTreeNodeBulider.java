package com.ccc.sys.io.commons.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * <a>Title:JsonTreeNodeBulider</a>
 * <a>Author：<a>
 * <a>Description：<a>
 *
 * @Author ccc
 * @Date 2020/3/10 17:23
 * @Version 1.0.0
 */
public class JsonTreeNodeBulider {
    /**
     * 将没有层级关系的集合变成有层级关系的
     *
     * @param treeNodes
     * @param topPId
     * @return
     */
    public static List<TreeNode> build(List<TreeNode> treeNodes, Integer topPId) {
        List<TreeNode> nodes = new ArrayList<>();
        for (TreeNode n1 : treeNodes) {
            if (n1.getPid() == topPId) {
//                如果相等，就将当前值丢到nodes中
                nodes.add(n1);
            }
//            找n1的子节点
            for (TreeNode n2 : treeNodes) {
                if (n1.getId() == n2.getPid()) {
//                    如果相等，就将当前值作为子节点丢到n1中
                    n1.getChildren().add(n2);
                }
            }
        }
//        将nodes丢出去
        return nodes;
    }
}
