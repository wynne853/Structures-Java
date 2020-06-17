/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Queue;

import List.LinkedList;
import Interface.BasEspecialist;
/**
 *
 * @author wynne
 */
public class Queue<T> implements BasEspecialist<T>{

    private LinkedList<T> list = LinkedList.instance();
    private static Queue structe = new Queue();
    
    public static Queue instance(){
        return Queue.structe;
    }
    
    @Override
    public boolean add(T object) {
        return this.list.addFirst(object);
    }

    @Override
    public T delete() {
        return this.list.deleteFirst();
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
        return this.index(object);
    }

    @Override
    public boolean toClear() {
        return toClear();
    }

}
