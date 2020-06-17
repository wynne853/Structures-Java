/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package List;

import Interface.Base;

/**
 *
 * @author wynne
 */
public class LinkedList<T> implements Base<T> {

    private static LinkedList structe = new LinkedList();

    private Node<T> first = null;
    private Node<T> last = null;
    private int lenght = 0;
    
    
    public static LinkedList instance(){
        return LinkedList.structe;
    }
    
    public boolean addFirst(T object) {
        
        Node<T> newNode = new Node(object,this.first,null);
        
        if(this.first == null){
            this.first = newNode;
            this.last = newNode;
        }
        
        this.first.setNodeLeft(newNode);
        this.first = newNode;
        this.lenght++;
        
        return true;
    }
    
    public boolean addLast(T object) {
        return this.add(object);
    }
    
    @Override
    public boolean add(T object) {
        Node<T> newNode = new Node(object,null,this.last);
        
        if(this.first == null){
            this.first = newNode;
            this.last = newNode;
        }
        
        this.last.setNodeRight(newNode);
        this.last = newNode;
        
        this.lenght++;
        
        return true;
    }

    public T deleteFirst() {
        
        Node<T> node = this.first;
        
        if(this.first.getNodeRight() != null ){
            this.first = this.first.getNodeRight();
            this.first.setNodeLeft(null);
            node.setNodeRight(null);
        }else{
            this.first = null;
        }
        
        
        
        this.lenght--;
        
        return node.getObject();
    }

    public T deleteLast() {      
        Node<T> node = this.last;
        
        if(this.last.getNodeLeft() != null){
            this.last = this.last.getNodeLeft();
            this.last.setNodeRight(null);
            node.setNodeRight(null);
        }else{
            this.last = null;
            this.first = null;
        }
        this.lenght--;
        
        return node.getObject();
    }
    
    @Override
    public T delete(int position) {
        if(validPosition(position)){
            if(position == 0){
                return this.deleteFirst();
            }

            if(position == this.lenght - 1){
                return this.deleteLast();
            }

           Node<T> aux = this.find(position);
           if(aux != null){
                if(aux.getNodeLeft() != null){
                    aux.getNodeLeft().setNodeRight(aux.getNodeRight());
                }
                if(aux.getNodeRight() != null){
                    aux.getNodeRight().setNodeLeft(aux.getNodeLeft());
                }
                aux.setNodeLeft(null);
                aux.setNodeRight(null);

                this.lenght--;

                return aux.getObject();
           }
       }
       return null;
    }
    
    @Override
    public T delete(T object){
        return this.delete(index(object));
    }
    
    @Override
    public T searchFirst() {
        return this.first.getObject();
    }

    @Override
    public T searchLast() {
        return this.last.getObject();
    }

    @Override
    public T search(int position) {
        T item = null;
        if(this.validPosition(position)){
            Node<T> node = this.find(position);

            if(node == null){
                return null;
            }
            item = node.getObject();
        }   
        return item;
    }

    @Override
    public T replace(T object, int position) {
        T item = null;
        
        if(this.validPosition(position)){
            Node<T> aux = this.find(position);
            item = aux.getObject();
            aux.setObject(object);
        }
        
        return item;
    }

    @Override
    public int size() {
        return this.lenght;
    }

    @Override
    public int index(T object) {
        Node<T> aux = this.first;
        
        for(int i = 0; i < this.lenght; i++){
            if(object.equals(aux.getObject())){
                return i;
            }else{
                aux = aux.getNodeRight();
            }
        }
        
        return -1;
    }

    @Override
    public boolean toClear() {
        this.first = null;
        this.last = null;
        this.lenght = 0;
        
        return true;
    }

    @Override
    public T[] group(int firstPosition, int lastPosition) {
        T[] array  = null;
        
        if(this.validPosition(lastPosition) && this.validPosition(firstPosition) && lastPosition > firstPosition){
            array = (T[]) new Object[lastPosition - firstPosition];
            Node<T> aux = this.find(firstPosition);
            for(int i = 0; i < array.length; i++){
                array[i] = aux.getObject();
                aux = aux.getNodeRight();
            }
        }
        
        return array;
    }
    
    private Node<T> find(int position){
        
        Node<T> aux;
        
        if(position > this.lenght || position < 0){
            return null;
        }
        
        if((this.lenght/2) >= position){
            aux = this.first;
            for(int i = 0; i < position; i++){
                aux = aux.getNodeRight();
            }
        }else{
            aux = this.last;
            for(int i = this.lenght - 1; i > position; i--){
                aux = aux.getNodeLeft();
            }        
        }
        
        return aux;
    }
    
    private boolean validPosition(int position) {
        return position >= 0 && position <= this.lenght;
    }
}
