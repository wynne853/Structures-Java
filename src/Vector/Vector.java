/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vector;

import Interface.Base;

/**
 *
 * @author wynne
 */
public class Vector<T> implements Base<T>{

    private T[] array ;
    private int lastPosition = -1;
    private static Vector structe = new Vector();

    public Vector() {
        this(10);
    }
    
    public Vector(int length) {
        this.array =  (T[]) new Object[length];
    }
    
    public static Vector instance(){
        return Vector.structe;
    }
    
    public boolean addFirst(T object) {
        return this.add(object,0);   
    }
    
    public boolean addLast(T object) {
        return this.add(object);   
    }

    @Override
    public boolean add(T object) {
        return this.add(object,this.lastPosition + 1 );
    }

    public T deleteFirst() {
        return this.delete(0);
    }
    
    @Override
    public T delete(T object){
        return this.delete(index(object));
    }
    
    public T deleteLast() {
        return this.delete(this.lastPosition);
    }

    @Override
    public T delete(int position) {
        
        if(!validPosition(position)){
            return null;
        }
             
        T item = this.array[position];
        this.goToLeft(position);
        this.lastPosition--;
        
        return item;
    }

    @Override
    public T searchFirst() {
        return this.search(0);
    }

    @Override
    public T searchLast() {
        return this.search(this.lastPosition);
    }

    @Override
    public T search(int position) {
        
        if(!this.validPosition(position)){
            return null;
        }
        
        return this.array[position];
    }

    @Override
    public T replace(T object,int position){
        
        if(!this.validPosition(position) && object != null){
            return null;
        }
        
        T item = this.array[position];
        this.array[position] = object;
        
        return item;
    }
    
    @Override
    public int size() {
        return this.lastPosition + 1 ;
    }

    @Override
    public int index(T object){
        for(int i=0;i<this.lastPosition;i++){
            if(object.equals(this.array[i])){
                return i;
            }
        }
        
        return -1;
    }
    
    @Override
    public T[] group(int firstPosition, int lastPosition) {
        T[] arrayReturn  = null;
        
        if(this.validPosition(lastPosition) && this.validPosition(firstPosition) && lastPosition > firstPosition){
            arrayReturn = (T[]) new Object[lastPosition - firstPosition];
            
            for(int i = 0,index = firstPosition; i < arrayReturn.length; i++, index++){
                arrayReturn[i] = this.array[index];
            }
        }
        
        return arrayReturn;
    }
    
    @Override
    public boolean toClear() {
        this.array = (T[]) new Object[10];
        this.lastPosition = -1;
        return true;
    }

    private boolean add(T object, int position) {
       
        if(position < -1 || object == null){
            return false;
        }
        
        if(this.array.length - 1 <= this.lastPosition ) { 
            this.increasesArray();
        }
        
        if(this.array[position] != null){
   
            this.goToRight(position);
        }

        this.array[position] = object;
        
        this.lastPosition++;
        
        return true;
    }
    
    private void increasesArray() {
        T[] newArray = (T[]) new Object[2*this.array.length];
        
        for(int i=0;i<this.array.length;i++){
            newArray[i] = this.array[i];
        }
        
        this.array = newArray ;
    }

    private void goToRight(int position) {
        for(int i = this.lastPosition; i >= position;i--){
            this.array[i + 1 ] = this.array[i];  
        }
    }
    
    private void goToLeft(int position) {
        for(int i = position; i <= this.lastPosition;i++){
            this.array[i] = this.array[i + 1];
        }
    }

    private boolean validPosition(int position) {
        return position >= 0 && position <= this.lastPosition;
    }

}
