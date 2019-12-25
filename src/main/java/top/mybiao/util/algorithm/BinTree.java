package top.mybiao.util.algorithm;

import java.util.LinkedList;
import java.util.Objects;

/**
 * 二叉树实现，遍历
 * @param <T>
 */
public class BinTree<T extends Comparable<T>>{
    //根节点
    Node<T> root;
    //节点个数
    int size;

    static class Node<T>{
        T data; //数据节点
        Node<T> left;   //左孩子
        Node<T> right;  //右孩子
        Node(T t){
            this.data = t;
            this.left=null;
            this.right=null;
        }
    }

    /**
     * 前序遍历，递归方式
     * @param root 根节点
     */
    private static <T> void leftRange(Node<T> root){
        if(root==null){
            return;
        }
        System.out.print(root.data+" ");
        leftRange(root.left);
        leftRange(root.right);
    }


    /**
     * 非递归遍历，需要使用栈，在左孩子不存在时，获得父节点，再访问右孩子
     * @param root 根节点
     * @param <T>
     */
    private static <T> void leftRange1(Node<T> root){
        LinkedList<Node> list=new LinkedList<>();
        Node<T> pNode = root;
        while (pNode!=null||!list.isEmpty()){
            if(pNode!=null){
                System.out.print(pNode.data+" ");
                list.push(pNode);
                pNode = pNode.left;
            }else {
                pNode = list.pop();
                pNode=pNode.right;
            }
        }
    }

    /**
     * 中序遍历，左子树->根->右子树
     * @param root
     * @param <T>
     */
    private static <T> void midRange(Node<T> root){
        if(Objects.isNull(root)) return;
        midRange(root.left);
        System.out.print(root.data+" ");
        midRange(root.right);
    }

    /**
     * 中序遍历非递归实现
     * @param root
     * @param <T>
     */
    private static <T> void midRange1(Node<T> root){
        LinkedList<Node> list=new LinkedList<>();
        Node<T> pNode = root;
        while (pNode!=null||!list.isEmpty()){
            if(pNode!=null){
                list.push(pNode);
                pNode = pNode.left;
            }else {
                pNode = list.pop();
                System.out.print(pNode.data+" ");
                pNode=pNode.right;
            }
        }
    }

    /**
     * 递归后序遍历
     * @param root
     * @param <T>
     */
    public static <T> void behindRange(Node<T> root){
        if(Objects.isNull(root)) return;
        behindRange(root.left);
        behindRange(root.right);
        System.out.print(root.data+" ");
    }

    /**
     * 非递归后序遍历
     * @param root
     * @param <T>
     */
    public static <T> void behindRange1(Node<T> root){
        LinkedList<Node<T>> stack=new LinkedList();
        Node<T> tmpRoot = root;
        Node<T> tmpLast = null;
        while (tmpRoot!=null||!stack.isEmpty()){
            while (tmpRoot!=null){
                stack.push(tmpRoot);
                tmpRoot = tmpRoot.left;
            }
            tmpRoot = stack.peek();
            if (tmpRoot.right==null||tmpRoot.right==tmpLast){
                System.out.print(tmpRoot.data+ " ");
                stack.pop();
                tmpLast=tmpRoot;
                tmpRoot = null;
            }else {
                tmpRoot = tmpRoot.right;
            }
        }
    }

    public static void main(String[] args) {
        /**
         *                  root(20)
         *                /         \
         *          left1(10)       right1(54)
         *          /     \            /
         * left11(75) right12(765) left12(123)
         *
         * 75->765->10->123->54->20
         */
        Node<Integer> root = new Node<>(20);
        Node<Integer> left1 = new Node<>(10);
        Node<Integer> right1 = new Node<>(54);
        Node<Integer> left11 = new Node<>(75);
        Node<Integer> right12 = new Node<>(765);
        Node<Integer> left12 = new Node<>(123);
        root.left= left1;
        root.right = right1;
        left1.left = left11;
        left1.right = right12;
        right1.left = left12;
        //前序遍历 20->10->75->765->54->123
//        leftRange(root);
        System.out.print("先序遍历：");
        leftRange1(root);
        System.out.println();
        //中序遍历 75->10->765->20->123->54
//        midRange(root);
        System.out.print("中序遍历：");
        midRange1(root);
        System.out.println();
        //后序遍历 75->765->10->123->54->20
//        behindRange(root);
        System.out.print("后序遍历：");
        behindRange1(root);
    }
}
