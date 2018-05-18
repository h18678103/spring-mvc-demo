package binarytree;

import java.util.LinkedList;
/*
 * 测试二叉树的  创建、遍历
 * 二叉树结构如下
 *            A
 *       B         C
 *    D     E   F     G
 *  H   I        J
 */
/**
 * @author huqinsong
 * @date 2018/5/17
 */
public class TestBinaryTree {
    public static void main(String[] args) {
        BinaryTree<Character> binaryTree = new BinaryTree<>();

        //输入ABDH##I##E##CF#J##G##（#用null代替）
        LinkedList<Character> tree = new LinkedList<>();
        tree.add('A');
        tree.add('B');
        tree.add('D');
        tree.add('H');
        tree.add(null);
        tree.add(null);
        tree.add('I');
        tree.add(null);
        tree.add(null);
        tree.add('E');
        tree.add(null);
        tree.add(null);
        tree.add('C');
        tree.add('F');
        tree.add(null);
        tree.add('J');
        tree.add(null);
        tree.add(null);
        tree.add('G');
        tree.add(null);
        tree.add(null);

        TreeNode<Character> root = binaryTree.creatBinaryPre(tree);

        //先序遍历（递归）
        binaryTree.PrintBinaryTreePreRecur(root);
        System.out.println();
        //中序遍历（递归）
        binaryTree.PrintBinaryTreeMidRecur(root);
        System.out.println();
        //后序遍历（递归）
        binaryTree.PrintBinaryTreeBacRecur(root);
        System.out.println();

        //先序遍历（非递归）
        binaryTree.PrintBinaryTreePreUnrecur(root);System.out.println();
        //中序遍历（非递归）
        binaryTree.PrintBinaryTreeMidUnrecur(root);System.out.println();
        //后序遍历（非递归）
        binaryTree.PrintBinaryTreeBacUnrecur(root);System.out.println();
        //层次遍历（非递归）
        binaryTree.PrintBinaryTreeLayerUnrecur(root);System.out.println();
    }
}
