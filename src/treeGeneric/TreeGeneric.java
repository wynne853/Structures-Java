package treeGeneric;

import Interface.Tree;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author wynne
 */
public class TreeGeneric<Index, T> implements Tree<Index, T> {

    private GenericNode<Index, T> root;
    private int lenght = 0;
    private static TreeGeneric structure = new TreeGeneric();


    public static TreeGeneric instance() {
        return TreeGeneric.structure;
    }

    public boolean add(Index index, T object, Index indexDad) {

        if (indexDad == null) {
            if (this.root != null) {
                return false;
            }
            this.root = new GenericNode<>(index, object, null);
            this.lenght++;

            return true;
        }

        GenericNode<Index, T> dad = this.findElement(indexDad, this.root);
        GenericNode<Index, T> newNode = new GenericNode<>(index, object, dad);
        if(dad != null && dad.getChieldren() != null){
            dad.getChieldren().add(newNode);
        }
        this.lenght++;

        return true;
    }

    @Override
    public T delete(Index index) {
        GenericNode<Index, T> node = this.findElement(index, this.root);

        if (node != null) {
            if (node.getDad() == null) {
                this.root = null;
                this.lenght = 0;
            } else {
                node.setDad(null);
                if(node.getChieldren().size() == 0){
                    this.lenght--;
                }else{
                    ArrayList<T> vector = new ArrayList<>();
                    PreOrderSearch(node,vector);
                    this.lenght -= (vector.size() - 1);
                }
            }
            return node.getObject();
        }

        return null;
    }

    @Override
    public T search(Index index) {
        GenericNode<Index, T> node = this.findElement(index, this.root);

        if (node == null) {
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
        if (this.lenght == 0) {
            return true;
        }
        return false;
    }

    @Override
    public T replace(Index index, T newObject) {
        GenericNode<Index, T> node = this.findElement(index, this.root);
        T object = null;
        if(node != null){
            object = node.getObject();
            node.setObject(newObject);
        }
        return object;
    }

    @Override
    public T parent(Index index){
        
        T object = null;
        
        if(index != null && !this.isRoot(index)){
            GenericNode<Index, T> element = this.findElement(index, this.root);
            if(element != null){
                object = element.getDad().getObject();
            }
        }
        
        return object; 
    }

    @Override
    public ArrayList<T> children(Index index) {
        
        if (index == null) {
            return null;
        }
        
        ArrayList<T> chieldren = new ArrayList<>();
        
        ArrayList<GenericNode<Index, T>> nodeChieldren = findElement(index, this.root).getChieldren();
        
        for (int i = 0; i < nodeChieldren.size(); i++) {
            chieldren.add(nodeChieldren.get(i).getObject());
        }

        return chieldren;
    }

    @Override
    public boolean isInternal(Index index) {
        
        if(index == null){
            return false;
        }
        
        GenericNode<Index, T> element = findElement(index, this.root);
        
        if(element == null){
            return false;
        }
        
        return element.getChieldren().size() != 0;
    }

    @Override
    public boolean isExternal(Index index) {
        if(index == null){
            return false;
        }
        
        GenericNode<Index, T> element = findElement(index, this.root);
        
        if(element == null){
            return false;
        }
        
        return  element.getChieldren().size()== 0;
    }

    @Override
    public int heightNode(Index index) {
        GenericNode<Index, T> node = findElement(index, this.root);
        int heightNode = 0;

        while (node.getDad() != null) {
            node = node.getDad();
            heightNode++;
        }

        return heightNode;
    }

    @Override
    public boolean isRoot(Index index) {
        if (index == null || this.root == null) {
            return false;
        }
        return index.equals(this.root.getIndex());
    }

    @Override
    public boolean toClear() {

        this.root = null;
        this.lenght = 0;

        return true;
    }

    @Override
    public ArrayList<T> goThrough(int type) {
        
        ArrayList<T> vector = null;
        
        if (this.lenght != 0 && type > 0 && type < 3) {
            
            vector = new ArrayList<>();
            
            switch (type) {
                case 1:
                    PreOrderSearch(this.root,vector);
                    break;
                case 2:
                    PosOrderSearch(this.root,vector);
                    break;
            }

        }
        
        return vector;
    }

    private void PreOrderSearch(GenericNode<Index, T> node,ArrayList<T> vector) {
                
        vector.add(node.getObject());
                
        if (node.getChieldren() != null) {
            for (int i = 0; i < node.getChieldren().size(); i++) {
                GenericNode<Index, T> aux = node.getChieldren().get(i);
                PreOrderSearch(aux,vector);
            }
        }
    }

    private void PosOrderSearch(GenericNode<Index, T> node,ArrayList<T> vector) {

        if (node.getChieldren() != null) {
            for (int i = 0; i < node.getChieldren().size(); i++) {
                GenericNode<Index, T> aux = node.getChieldren().get(i);
                PreOrderSearch(aux,vector);
            }
        }
        
        vector.add(node.getObject());        

    }

    private GenericNode<Index, T> findElement(Index index, GenericNode<Index, T> node) {

        GenericNode<Index, T> element = null;

        if (node == null) {
            return null;
        }

        if (node.getIndex().equals(index)) {
            return node;
        }

        if (node.getChieldren().size() != 0) {

            for (int i = 0; i < node.getChieldren().size(); i++) {

                GenericNode<Index, T> aux = node.getChieldren().get(i);

                element = findElement(index, aux);

                if (element != null) {
                    return element;
                }
            }
        }

        return element;
    }

}
