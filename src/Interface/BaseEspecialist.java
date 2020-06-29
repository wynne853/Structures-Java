/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

/**
 *
 * @author wynne
 */
public interface BaseEspecialist<T> {
    
    public boolean add(T object);
    public T delete();
    public T searchFirst();
    public T searchLast();
    public T search(int position);
    public int size();
    public int index(T object);
    public boolean toClear();
}
