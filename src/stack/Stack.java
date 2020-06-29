/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stack;

import List.LinkedList;
import Interface.BaseEspecialist;

/**
 *
 * @author wynne
 */
public class Stack<T> implements BaseEspecialist<T> {

    private LinkedList<T> list = LinkedList.instance();
    private static Stack structe = new Stack();
    
    public static Stack instance(){
        return Stack.structe;
    }
    
    @Override
    public boolean add(T object) {
        return this.list.addLast(object);
    }

    @Override
    public T delete() {
        return this.list.deleteLast();
    }

    @Override
    public T searchFirst() {
        return this.list.searchFirst();
    }

    @Override
    public T searchLast() {
        return this.list.searchLast();
    }

    @Override
    public T search(int position) {
        return this.list.search(position);
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public int index(T object) {
        return this.list.index(object);
    }

    @Override
    public boolean toClear() {
        return this.list.toClear();
    }
    
}
