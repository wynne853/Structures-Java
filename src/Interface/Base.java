package Interface;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wynne
 */
public interface Base<T> {

    public boolean add(T object);
    public T delete(int position);
    public T delete(T object);
    public T searchFirst();
    public T searchLast();
    public T search(int position);
    public T replace(T object,int position);
    public int size();
    public int index(T object);
    public boolean toClear();
    public T[] group(int firstPosition, int lastPosition);
}
