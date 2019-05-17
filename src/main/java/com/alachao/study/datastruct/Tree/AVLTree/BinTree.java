package com.alachao.study.datastruct.Tree.AVLTree;


import com.alachao.study.datastruct.Tree.BinaryTree.Tree;
//
/**
 * @Author yuliang-ds1
 * @Date 2019/4/9 15:38
 * @Description https://blog.csdn.net/javazejian/article/details/53892797
 */
public class BinTree {

    /** The tree root. */
    public TreeNode root;


    /**
     * 左左单旋转(LL旋转) w变为x的根结点, x变为w的右子树
     * @param x
     * @return
     */
    private TreeNode   singleRotateLeft(TreeNode x){
        //把w结点旋转为根结点
        TreeNode  w=x.leftNode;
        //同时w的右子树变为x的左子树
        x.leftNode=w.rightNode;
        //x变为w的右子树
        w.rightNode=x;
        x.height=Math.max(height(x.leftNode),height(x.rightNode))+1;
        w.height=Math.max(height(w.leftNode),height(x))+1;
        return w;
    }

    /**
     * 右右单旋转(RR旋转) x变为w的根结点, w变为x的左子树
     * @return
     */
    private TreeNode   singleRotateRight(TreeNode w){
        TreeNode x=w.rightNode;
        w.rightNode=x.leftNode;
        x.leftNode=w;
        x.height=Math.max(height(x.rightNode),w.height)+1;
        w.height=Math.max(height(w.leftNode),height(w.leftNode))+1;
        //返回新的根结点
        return x;


    }


    public TreeNode doubleRotateLeft(TreeNode x){
        //w先进行RR旋转
       x.leftNode=singleRotateRight(x.leftNode);
        //再进行x的LL旋转
       return  singleRotateLeft(x);
    }


    public TreeNode doubleRotateRight(TreeNode w) {
        //先进行LL旋转
        w.rightNode = singleRotateLeft(w.rightNode);
        //再进行RR旋转
        return singleRotateRight(w);
    }
    /**
     * @param p
     * @return
     */
    public int height(TreeNode p){
        return p == null ? -1 : p.height;
    }



    public void insert(int data) {
        this.root=insert(data,root);
    }

    private TreeNode insert(int data , TreeNode p){
        //说明已没有孩子结点,可以创建新结点插入了.
        if(p==null){
            p=new TreeNode(data);
        }

       int result= data-p.data;

        if(result<0){
            p.leftNode = insert(data, p.leftNode);
            if(height(p.leftNode)-height(p.rightNode)==2){
                if(data-p.leftNode.data<0){
                   p=singleRotateLeft(p);
                }else{
                    p=doubleRotateLeft(p);
                }

            }

        }else if(result>0){
            p.rightNode=insert(data,p.rightNode);
            if(height(p.rightNode)-height(p.leftNode)==2) {
                if (data - p.rightNode.data < 0) {
                    p = doubleRotateRight(p);
                } else {
                    p = singleRotateRight(p);
                }
            }


        }else;

        //重新计算各个结点的高度
        p.height = Math.max( height( p.leftNode ), height( p.rightNode ) ) + 1;
        return p;//返回根结点
    }


    public void remove(int data){
        this.root=remove(data,root);
    }

    public TreeNode remove(int data, TreeNode p){
        if(p==null){
            return null;
        }
        int result= data-p.data;
        if(result<0){
            p.leftNode=remove(data,p.leftNode);

            if(height(p.rightNode)-height(p.leftNode)==2){

                TreeNode currentNode = p.rightNode;
                if(height(currentNode.leftNode)>height(currentNode.rightNode)){
                    p=doubleRotateRight(p);
                }else {
                    p=singleRotateRight(p);
                }

            }

        }else if(result>0){
            p.rightNode=remove(data,p.rightNode);
            if(height(p.leftNode)-height(p.rightNode)==2) {
                TreeNode currentNode = p.leftNode;
                if(height(currentNode.leftNode)>height(currentNode.rightNode)){
                    p=singleRotateLeft(p);
                }else{
                    p=doubleRotateLeft(p);
                }

            }

        }else if(p.leftNode!=null&&p.rightNode!=null){
            //已找到需要删除的元素,并且要删除的结点拥有两个子节点
            //寻找替换结点
            p.data=findMin(p.rightNode).data;
            //移除用于替换的结点
            p.rightNode = remove( p.data, p.rightNode );

        }else{
            //只有一个孩子结点或者只是叶子结点的情况
            p=(p.leftNode!=null)? p.leftNode:p.rightNode;
        }

        //更新高度值
        if(p!=null) p.height = Math.max( height( p.leftNode ), height( p.rightNode ) ) + 1;

        return p;
    }

    public boolean contains(int num){

        return contains(root,num);

    }

    public boolean contains(TreeNode node,int num){
        if(null==node)return  false;

        if(num<node.data){
            return contains(node.leftNode,num);
        }else if(num>node.data){
            return contains(node.rightNode,num);
        }else{
            return true;
        }
    }


    public  int findMin(){
        return findMin(root).data;
    }


    private TreeNode findMin(TreeNode p){
        if (p==null)//结束条件
            return null;
        else if (p.leftNode==null)//如果没有左结点,那么t就是最小的
            return p;
        return findMin(p.leftNode);
    }


    public int findMax() {
        return findMax(root).data;
    }


    public TreeNode findMax(TreeNode node) {
        if(null==node){
            return null;
        }else if(node.rightNode==null){
            return node;
        }
        return findMax(node.rightNode);
    }

    public void printTree(TreeNode node){
        if(node!=null) {
            printTree(node.leftNode);
            System.out.println(node.data);
            printTree(node.rightNode);
        }
    }

    //https://blog.csdn.net/qq_40772692/article/details/79343914
    //先根遍历 按照根节点->左子树->右子树的顺序访问二叉树
    public  String preOrder(){
        String sb=preOrder(root);
        if(sb.length()>0){
            //去掉尾部","号
            sb=sb.substring(0,sb.length()-1);
        }
        return sb;

    }

    //先根遍历
    public String preOrder(TreeNode subtree){
        StringBuilder sb =new StringBuilder();
        if (subtree!=null) {
            //先访问根结点
            sb.append(subtree.data).append(",");
            //访问左子树
            sb.append(preOrder(subtree.leftNode));
            //访问右子树
            sb.append(preOrder(subtree.rightNode));
        }
        return sb.toString();
    }

    //中根遍历 中序遍历：按照左子树->根节点->右子树的顺序访问
    public  String inOrder(){
        String sb=inOrder(root);
        if(sb.length()>0){
            //去掉尾部","号
            sb=sb.substring(0,sb.length()-1);
        }
        return sb;
    }

    //中根遍历
    public String inOrder(TreeNode node){
        StringBuilder sb =new StringBuilder();
        if (node!=null) {
            //访问左子树
            sb.append(inOrder(node.leftNode));
            //先访问根结点
            sb.append(node.data).append(",");
            //访问右子树
            sb.append(inOrder(node.rightNode));
        }
        return sb.toString();
    }

    //后根遍历 遍历顺序规则为【左右根】
    public  String postOrder(){
        String sb=postOrder(root);
        if(sb.length()>0){
            //去掉尾部","号
            sb=sb.substring(0,sb.length()-1);
        }
        return sb;
    }

    public String postOrder(TreeNode node){

        StringBuilder sb =new StringBuilder();
        if (node!=null) {
            //访问右子树
            sb.append(postOrder(node.rightNode));
            ///访问右子树
            sb.append(postOrder(node.leftNode));
            //先访问根结点
            sb.append(node.data).append(",");

        }
        return sb.toString();
    }



    /**
     * 测试
     * @param arg
     */
    public  static void main(String arg[]){

        BinTree avlTree=new BinTree();

        for (int i = 1; i <18 ; i++) {
            avlTree.insert(i);
        }

        avlTree.printTree(avlTree.root);
        //删除11,8以触发旋转平衡操作
        avlTree.remove(11);
        avlTree.remove(8);

        System.out.println("================");

        avlTree.printTree(avlTree.root);

        System.out.println("findMin:"+avlTree.findMin());

        System.out.println("findMax:"+avlTree.findMax());

        System.out.println("15 exist or not : " + avlTree.contains(11) );


        //先根遍历:9,4,2,1,3,6,5,7,14,12,10,13,16,15,17
        //中根遍历:4,2,1,3,6,5,7,9,14,12,10,13,16,15,17
        //后根遍历:14,12,10,13,16,15,17,9,4,2,1,3,6,5,7
        System.out.println("先根遍历:"+avlTree.preOrder());

        System.out.println("中根遍历:"+avlTree.inOrder());

        System.out.println("后根遍历:"+avlTree.postOrder());

        //先根遍历:9,4,2,1,3,6,5,7,14,12,10,13,16,15,17
        //中根遍历:1,2,3,4,5,6,7,9,10,12,13,14,15,16,17
        //后根遍历:1,3,2,5,7,6,4,10,13,12,15,17,16,14,9

        //先根遍历:9,4,2,1,3,6,5,7,14,12,10,13,16,15,17
        //中根遍历:1,2,3,4,5,6,7,9,10,12,13,14,15,16,17
        //后根遍历:17,15,16,13,10,12,14,7,5,6,3,1,2,4,9
    }
}
