/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treeGeneric;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author wynne
 */
class GenericNode<Index,T> {
    
    private Index index;
    private T object;
    private GenericNode<Index,T> dad;
    private ArrayList<GenericNode<Index,T>> chieldren = new ArrayList<>();

    public GenericNode(Index index, T object, GenericNode<Index,T> dad) {
        this.index = index;
        this.object = object;
        this.dad = dad;

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

    public GenericNode<Index,T> getDad() {
        return dad;
    }

    public void setDad(GenericNode<Index,T> dad) {
        this.dad = dad;
    }

    public ArrayList<GenericNode<Index,T>> getChieldren() {
        return chieldren;
    }

    public void setChieldren(ArrayList<GenericNode<Index,T>> chieldren) {
        this.chieldren = chieldren;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GenericNode<?, ?> other = (GenericNode<?, ?>) obj;
        if (!Objects.equals(this.index, other.index)) {
            return false;
        }
        return true;
    }


       
}
