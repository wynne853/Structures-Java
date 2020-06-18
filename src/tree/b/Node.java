/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree.b;

/**
 *
 * @author wynne
 */
public class Node<Index,T> {
    
    private Index index;
    private T object;
    private Node<Index,T> dad;
    private Node<Index,T> sonLeft;
    private Node<Index,T> sonRight;

    public Node(Index index, T object, Node<Index, T> dad, Node<Index, T> sonLeft, Node<Index, T> sonRight) {
        this.index = index;
        this.object = object;
        this.dad = dad;
        this.sonLeft = sonLeft;
        this.sonRight = sonRight;
    }

    public Node(Index index, T object) {
        this.index = index;
        this.object = object;
    }

    public Index getIndex() {
        return index;
    }

    public void setIndex(Index index) {
        this.index = index;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public Node<Index, T> getDad() {
        return dad;
    }

    public void setDad(Node<Index, T> dad) {
        this.dad = dad;
    }

    public Node<Index, T> getSonLeft() {
        return sonLeft;
    }

    public void setSonLeft(Node<Index, T> sonLeft) {
        this.sonLeft = sonLeft;
    }

    public Node<Index, T> getSonRight() {
        return sonRight;
    }

    public void setSonRight(Node<Index, T> sonRight) {
        this.sonRight = sonRight;
    }
    
    public int compareTo(Index index){
        return this.index.toString().compareTo(index.toString());
    } 
    
}
