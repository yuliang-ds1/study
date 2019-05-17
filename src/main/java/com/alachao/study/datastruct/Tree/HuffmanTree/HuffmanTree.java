package com.alachao.study.datastruct.Tree.HuffmanTree;

/**
 * @Author yuliang-ds1
 * @Date 2019/4/10 15:44
 * @Description
 */
import java.util.*;
/*---------------------
作者：hoxis
来源：CSDN
原文：https://blog.csdn.net/bruce_6/article/details/38656413
版权声明：本文为博主原创文章，转载请附上博文链接！
以上代码中的关键步骤包括：
（1）对list集合中所有节点进行排序；

（2）找出list集合中权值最小的两个节点；

（3）以权值最小的两个节点作为子节点创建新节点；

（4）从list集合中删除权值最小的两个节点，将新节点添加到list集合中

程序采用循环不断地执行上面的步骤，直到list集合中只剩下一个节点，最后剩下的这个节点就是哈夫曼树的根节点
---------------------
作者：hoxis
来源：CSDN
原文：https://blog.csdn.net/bruce_6/article/details/38656413
版权声明：本文为博主原创文章，转载请附上博文链接！*/

public class HuffmanTree {

    public static class Node<E> {
        E data;
        double weight;
        Node leftChild;
        Node rightChild;

        public Node(E data, double weight) {
            super();
            this.data = data;
            this.weight = weight;
        }

        public String toString() {
            return "Node[data=" + data + ", weight=" + weight + "]";
        }
    }

    public static void main(String[] args) {
        List<Node> nodes = new ArrayList<Node>();

        nodes.add(new Node("A", 40.0));
        nodes.add(new Node("B", 8.0));
        nodes.add(new Node("C", 10.0));
        nodes.add(new Node("D", 30.0));
        nodes.add(new Node("E", 10.0));
        nodes.add(new Node("F", 2.0));

        Node root = HuffmanTree.createTree(nodes);

        //System.out.println(breadthFirst(root));

        depthFirst(root);


        //depthFirstSearch(root);

        //depthTraversal(root);



    }

    /**
     * 构造哈夫曼树
     *
     * @param nodes
     *            节点集合
     * @return 构造出来的哈夫曼树的根节点
     */
    private static Node createTree(List<Node> nodes) {

        // 只要nodes数组中还有2个以上的节点
        while (nodes.size() > 1) {
            quickSort(nodes);
            //获取权值最小的两个节点
            Node left = nodes.get(nodes.size()-1);
            Node right = nodes.get(nodes.size()-2);

            //生成新节点，新节点的权值为两个子节点的权值之和
            Node parent = new Node(null, left.weight + right.weight);

            //让新节点作为两个权值最小节点的父节点
            parent.leftChild = left;
            parent.rightChild = right;

            //删除权值最小的两个节点
            nodes.remove(nodes.size()-1);
            nodes.remove(nodes.size()-1);

            //将新节点加入到集合中
            nodes.add(parent);
        }

        return nodes.get(0);
    }



    public static void quickSort(List<Node> nodes){
        subSort(nodes, 0, nodes.size()-1);
    }

    /**
     * 实现快速排序算法，用于对节点进行排序
     *
     * @param nodes
     * @param start
     * @param end
     */
    private static void subSort(List<Node> nodes, int start, int end) {
        if (start < end) {

            // 以第一个元素作为分界值
            Node base = nodes.get(start);
            // i从左边搜索，搜索大于分界值的元素的索引
            int i = start;
            // j从右边开始搜索，搜索小于分界值的元素的索引
            int j = end + 1;
            while (true) {
                // 找到大于分界值的元素的索引，或者i已经到了end处
                while (i < end && nodes.get(++i).weight >= base.weight)
                    ;
                // 找到小于分界值的元素的索引，或者j已经到了start处
                while (j > start && nodes.get(--j).weight <= base.weight)
                    ;

                if (i < j) {
                    System.out.println("node:"+nodes.get(i)+"+++"+nodes.get(j));
                    swap(nodes, i, j);
                    System.out.println("node:"+nodes.get(i)+"+++"+nodes.get(j));
                } else {
                    break;
                }
            }

            swap(nodes, start, j);

            //递归左边子序列
            subSort(nodes, start, j - 1);
            //递归右边子序列
            subSort(nodes, j + 1, end);
        }
    }


    /**
     * 将指定集合中的i和j索引处的元素交换
     *
     * @param nodes
     * @param i
     * @param j
     */
    private static void swap(List<Node> nodes, int i, int j) {
        Node tmp;
        tmp = nodes.get(i);
        nodes.set(i, nodes.get(j));
        nodes.set(j, tmp);
    }


    //广度优先遍历
    public static List<Node> breadthFirst(Node root){
        Queue<Node> queue = new ArrayDeque<Node>();
        List<Node> list = new ArrayList<Node>();

        if(root!=null){
            //将根元素加入“队列”
            queue.offer(root);
        }

        while(!queue.isEmpty()){
            //将该队列的“队尾”元素加入到list中
            list.add(queue.peek());
            Node p = queue.poll();

            //如果左子节点不为null，将它加入到队列
            if(p.leftChild != null){
                queue.offer(p.leftChild);
            }

            //如果右子节点不为null，将它加入到队列
            if(p.rightChild != null){
                queue.offer(p.rightChild);
            }
        }

        return list;
    }


    //https://blog.csdn.net/weixin_42289193/article/details/81741756
    //https://blog.csdn.net/weixin_39912556/article/details/82852749
    public static void  depthFirst(Node root){
        Stack<Node> stack = new Stack<>();
        if(root!=null){
            //将根元素加入“队列”
            stack.push(root);
        }
        while(!stack.isEmpty()){
            //将该队列的“队尾”元素加入到list中
            Node p = stack.pop();
            System.out.println("pop:"+p.data+" "+p.weight);
            //如果右子节点不为null，将它加入到队列
            if(p.rightChild != null){
                stack.push(p.rightChild);
            }
            //如果左子节点不为null，将它加入到队列
            if(p.leftChild != null){
                stack.push(p.leftChild);
            }
        }
    }



    //广度优先遍历是使用队列实现的
    public void BroadFirstSearch(Node nodeHead) {
        if(nodeHead==null) {
            return;
        }
        Queue<Node> myQueue=new LinkedList<>();
        myQueue.add(nodeHead);
        while(!myQueue.isEmpty()) {
            Node node=myQueue.poll();
            System.out.print(node.data+" ");
            if(null!=node.leftChild) {
                myQueue.add(node.leftChild);    //深度优先遍历，我们在这里采用每一行从左到右遍历
            }
            if(null!=node.rightChild) {
                myQueue.add(node.rightChild);
            }

        }
    }

    //深度优先遍历
    public static void depthFirstSearch(Node nodeHead) {
        if(nodeHead==null) {
            return;
        }
        Stack<Node> myStack=new Stack<Node>();
        myStack.add(nodeHead);
        while(!myStack.isEmpty()) {
            Node node=myStack.pop();    //弹出栈顶元素
            System.out.print(node.data+" ");
            if(node.rightChild!=null) {
                myStack.push(node.rightChild);    //深度优先遍历，先遍历左边，后遍历右边,栈先进后出
            }
            if(node.leftChild!=null) {
                myStack.push(node.leftChild);
            }
        }

    }


    public static void depthTraversal(Node node){
        if(node==null){
            System.out.print("empty tree");
            return;
        }
        Stack<Node> stack = new Stack<Node>();
        stack.push(node);
        while(!stack.isEmpty()){
            Node rnode = stack.pop();
            System.out.print(rnode.data);
            if(rnode.rightChild!=null){
                stack.push(rnode.rightChild);
            }
            if(rnode.leftChild!=null){
                stack.push(rnode.leftChild);
            }
        }
    }



    public void levelOrderTraversal(Node node) {
        if (node == null) {
            System.out.print("empty tree");
            return;
        }
        ArrayDeque<Node> deque = new ArrayDeque<Node>();
        deque.add(node);
        while (!deque.isEmpty()) {
            Node rnode = deque.remove();
            System.out.print(rnode.weight + "  ");
            if (rnode.leftChild != null) {
                deque.add(rnode.leftChild);
            }
            if (rnode.rightChild != null) {
                deque.add(rnode.rightChild);
            }
        }

    }

/*---------------------
    作者：TingWang7916
    来源：CSDN
    原文：https://blog.csdn.net/weixin_42289193/article/details/81741756
    版权声明：本文为博主原创文章，转载请附上博文链接！*/

}
