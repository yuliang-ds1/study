package com.alachao.study.datastruct.avlTree;

/**
 * @Author yuliang-ds1
 * @Date 2019/4/8 16:43
 * @Description
 */
public class Tree {

    //二叉树的左子树
    private Tree lChild;
    //二叉树的右子树
    private Tree rChild;
    //给二叉树节点一个int型的数据
    private Integer value;
    //二叉树的高度
    public int height;




    public Tree getlChild() {
        return lChild;
    }

    public void setlChild(Tree lChild) {
        this.lChild = lChild;
    }

    public Tree getrChild() {
        return rChild;
    }

    public void setrChild(Tree rChild) {
        this.rChild = rChild;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }


}
