package com.alachao.study.datastruct;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author yuliang-ds1
 * @Date 2019/4/8 15:49
 * @Description
 */
public class BinTree {


    private BinTree leftChild;//左孩子
    private BinTree rightChild;//右孩子
    private BinTree root;//根节点
    private Object data; //数据域
    private List<BinTree> datas;//存储所有的节点
    public BinTree(BinTree lChild, BinTree rChild, Object data) {
        super();
        this.leftChild = lChild;
        this.rightChild = rChild;
        this.data = data;
    }
    public BinTree(Object data) {
        this(null, null, data);
    }
    public BinTree() {
        super();
    }

    public void createTree(Object[] objs){
        datas=new ArrayList<BinTree>();
        for (Object object : objs) {
            datas.add(new BinTree(object));
        }
        root=datas.get(0);//将第一个作为根节点
        for (int parentIndex  = 0; parentIndex  < objs.length/2; parentIndex ++) {
            datas.get(parentIndex ).leftChild=datas.get(parentIndex *2+1);
            if(parentIndex *2+2<datas.size()){//避免偶数的时候 下标越界
                datas.get(parentIndex ).rightChild=datas.get(parentIndex *2+2);
            }
        }



    }
    //先序遍历
    public void preorder(BinTree root){
        if(root!=null){
            visit(root.getData());
            preorder(root.leftChild);
            preorder(root.rightChild);
        }

    }
    //中序遍历
    public void inorder(BinTree root){
        if(root!=null){
            inorder(root.leftChild);
            visit(root.getData());
            inorder(root.rightChild);
        }

    }
    //后序遍历
    public void afterorder(BinTree root){
        if(root!=null){
            afterorder(root.leftChild);
            afterorder(root.rightChild);
            visit(root.getData());
        }

    }
    private void visit(Object obj) {
        System.out.print(obj+" ");
    }
    public Object getData() {
        return data;
    }
    public BinTree getRoot() {
        return root;
    }





        public static void main(String[] args) {
            BinTree binTree=new BinTree();
            Object[] objs={1, 2, 3, 4, 5, 6, 7, 8, 9};
            binTree.createTree(objs);
    //		binTree.preorder(binTree.getRoot()); 先序遍历
    //		binTree.inorder(binTree.getRoot()); 中序遍历
            binTree.afterorder(binTree.getRoot()); //后序遍历
        }

    //先序遍历：2 4 8 9 5 3 6 7
    //中序遍历：4 9 2 5 1 6 3 7
    //后序遍历：9 4 5 2 6 7 3 1


}
