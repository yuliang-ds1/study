package com.alachao.study.datastruct.Tree.AVLTree;

/**
 * @Author yuliang-ds1
 * @Date 2019/4/9 15:31
 * @Description
 */
public class TreeNode {

    public TreeNode leftNode;

    public TreeNode rightNode;

    public int  data;

    public int height;

    public TreeNode(TreeNode leftNode,TreeNode rightNode,int data,int height){
        this.leftNode=leftNode;
        this.rightNode=rightNode;
        this.data=data;
        this.height=height;
    }

    public TreeNode(TreeNode leftNode,TreeNode rightNode,int data){
        this(leftNode,rightNode,data,0);
    }

    public TreeNode(int data){
        this(null,null,data);
    }


    public TreeNode () {
    }
}
