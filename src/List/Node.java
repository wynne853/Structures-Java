/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package List;

/**
 *
 * @author wynne
 */
public class Node<T> {
    private T object;
    private Node nodeRight;
    private Node nodeLeft;
    
    public Node(T object, Node nodeRight, Node nodeLeft) {
        this.object = object;
        this.nodeRight = nodeRight;
        this.nodeLeft = nodeLeft;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public Node getNodeRight() {
        return nodeRight;
    }

    public void setNodeRight(Node nodeRight) {
        this.nodeRight = nodeRight;
    }

    public Node getNodeLeft() {
        return nodeLeft;
    }

    public void setNodeLeft(Node nodeLeft) {
        this.nodeLeft = nodeLeft;
    }
    
}
