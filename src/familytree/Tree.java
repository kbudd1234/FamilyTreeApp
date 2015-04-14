/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package familytree;

/**
 *
 * @author kevinbudd
 */
public class Tree<E> {
    public E root;
    public Tree<E> father;
    public Tree<E> mother;
    
    public Tree(E e) {
        root = e;
    }
    
    
}
