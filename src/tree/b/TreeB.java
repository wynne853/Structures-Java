/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree.b;

import Interface.Tree;
import Test.Car;
import java.util.ArrayList;

/**
 *
 * @author wynne
 */
public class TreeB<Index, T> implements Tree<Index, T> {

    private Node<Index, T> root;
    private int lenght = 0;
    private static TreeB structure = new TreeB();

    public static TreeB instance() {
        return TreeB.structure;
    }

    public boolean add(Index index, T object) {

        if (index == null || object == null) {
            return false;
        }

        Node<Index, T> newNode = new Node<>(index, object);

        if (this.root == null) {
            this.root = newNode;
            this.lenght++;

            return true;
        }

        Node<Index, T> aux = this.root;

        for (int i = 0; i < this.lenght; i++) {

            int compare = aux.compareTo(index);
            
            if (compare < 0) {
                if (aux.getSonRight() == null) {

                    aux.setSonRight(newNode);
                    newNode.setDad(aux);

                    this.lenght++;

                    return true;
                } else {
                    aux = aux.getSonRight();
                }
            } else if (compare >= 0) {
                if (aux.getSonLeft() == null) {

                    aux.setSonLeft(newNode);
                    newNode.setDad(aux);
                    this.lenght++;

                    return true;
                } else {
                    aux = aux.getSonLeft();
                }
            }
        }

        return false;
    }

    @Override
    public T delete(Index index) {

        Node<Index, T> node = findElement(index, this.root);

        if (node != null) {
            
            if (node.getSonLeft() != null && node.getSonRight() != null) {
            
                Node<Index, T> aux = this.findTheLeftMost(node.getSonRight());
                
                if (aux.getSonRight() != null) {
                    aux.getDad().setSonLeft(aux.getSonRight());
                    aux.getSonRight().setDad(aux.getDad());
                } else {
                    aux.getDad().setSonLeft(null);
                }
                
                aux.setDad(node.getDad());
                
                if(node.getDad() != null){          
                    if (node.getDad().getSonLeft().getIndex().equals(node.getIndex())) {
                        node.getDad().setSonLeft(aux);
                    } else {
                        node.getDad().setSonRight(aux);
                    }
                }
                aux.setSonLeft(node.getSonLeft());
                aux.setSonRight(node.getSonRight());

                node.getSonLeft().setDad(aux);
                node.getSonRight().setDad(aux);
                
            } else if (node.getSonLeft() == null && node.getSonRight() == null) {
                if(node.getDad() == null){
                    this.root = null;
                    
                    return node.getObject();
                }
                if (node.getDad().getSonLeft().getIndex().equals(node.getIndex())) {
                    node.getDad().setSonLeft(null);
                } else {
                    node.getDad().setSonRight(null);
                }
                
            } else {
                
                Node<Index, T> aux;
                
                if (node.getSonLeft() != null) {
                    aux = node.getSonLeft();
                } else {
                    aux = node.getSonRight();
                }
                
                aux.setDad(node.getDad());
                
                if(node.getDad() != null){
                    if (node.getDad().getSonLeft().getIndex().equals(node.getIndex())) {
                        node.getDad().setSonLeft(aux);
                    } else {
                        node.getDad().setSonRight(aux);
                    }
                }
            }

            node.setDad(null);
            node.setSonLeft(null);
            node.setSonRight(null);

            this.lenght--;

            return node.getObject();
        }

        return null;
    }

    @Override
    public T search(Index index) {
        
        Node<Index, T> node = this.findElement(index, this.root);
        System.out.println(((Car)node.getDad().getObject()).getValue());
        if(node == null){
            return null;
        }
        
        return node.getObject();
    }

    @Override
    public int size() {
        return this.lenght;
    }

    @Override
    public boolean isEmpty() {
        return this.lenght == 0;
    }

    @Override
    public T replace(Index index, T newObject) {
        Node<Index, T> node = this.findElement(index, this.root);
        T object = node.getObject();
        node.setObject(newObject);

        return object;
    }

    @Override
    public T parent(Index index) {
        Node<Index,T> node = findElement(index, this.root);
        if(node == null || node.getDad() == null){
            return null;
        }
        
        return node.getDad().getObject();
    }

    @Override
    public ArrayList<T> children(Index index) {
        
        ArrayList<T> children = new ArrayList<>();
        Node<Index, T> node = this.findElement(index, this.root);

        if (node != null) {
            
            if (node.getSonLeft() != null) {
                children.add(node.getSonLeft().getObject());
            }
            if (node.getSonRight() != null) {
                children.add(node.getSonRight().getObject());
            }
        }
        
        return children;
    }

    @Override
    public boolean isInternal(Index index) {
        Node<Index, T> node = findElement(index, this.root);

        return node != null && (node.getSonLeft() != null || node.getSonRight() != null);
    }

    @Override
    public boolean isExternal(Index index) {
        Node<Index, T> node = findElement(index, this.root);

        return node != null && node.getSonLeft() == null && node.getSonRight() == null;
    }

    @Override
    public int heightNode(Index index) {
        Node<Index, T> node = findElement(index, this.root);
        int heightNode = 0;

        while (node.getDad() != null) {
            node = node.getDad();
            heightNode++;
        }

        return heightNode;
    }

    @Override
    public boolean isRoot(Index index) {
        return index.equals(this.root.getIndex());
    }
    
    @Override
    public ArrayList<T> goThrough(int type) {
        
        ArrayList<T> vector = new ArrayList<>();
        
        if (this.lenght != 0 && type > 0 && type < 4) {
                        
            switch (type) {
                case 1:
                    this.PreOrderSearch(0, this.root,vector);
                    break;
                case 2:
                    this.PosOrderSearch(0, this.root,vector);
                    break;
                case 3:
                    this.InOrderSearch(0, this.root,vector);
                    break;
            }

        }
        
        return vector;
    }
    
    private void PreOrderSearch(int indexVector, Node<Index, T> node,ArrayList<T> vector) {

        vector.add(node.getObject());

        if (node.getSonLeft() != null) {
            this.PreOrderSearch(indexVector++, node.getSonLeft(),vector);
        }

        if (node.getSonRight() != null) {
            this.PreOrderSearch(indexVector++, node.getSonRight(),vector);
        }

    }

    private void PosOrderSearch(int indexVector, Node<Index, T> node,ArrayList<T> vector) {

        if (node.getSonLeft() != null) {
            this.PosOrderSearch(indexVector++, node.getSonLeft(),vector);
        }

        if (node.getSonRight() != null) {
            this.PosOrderSearch(indexVector++, node.getSonRight(),vector);
        }

        vector.add(node.getObject());

    }

    private void InOrderSearch(int indexVector, Node<Index, T> node,ArrayList<T> vector) {

        if (node.getSonLeft() != null) {
            this.InOrderSearch(indexVector++, node.getSonLeft(),vector);
        }

        vector.add(node.getObject());

        if (node.getSonRight() != null) {
            this.InOrderSearch(indexVector++, node.getSonRight(),vector);
        }

    }

    private Node<Index, T> findElement(Index index, Node<Index, T> node) {

        Node<Index, T> element = null;

        if (node.getIndex().equals(index)) {
            return node;
        }

        if (node.getSonLeft() != null) {
            element = findElement(index, node.getSonLeft());
            if (element != null) {
                return element;
            }
        }

        if (node.getSonRight() != null) {
            element = findElement(index, node.getSonRight());
        }

        return element;
    }

    private Node<Index, T> findTheLeftMost(Node<Index, T> node) {

        if (node.getSonLeft() != null) {
            return findTheLeftMost(node.getSonLeft());
        } else {
            return node;
        }
    }

    @Override
    public boolean toClear() {
        
        this.lenght = 0;
        this.root = null;
        
        return true;
    }

}
