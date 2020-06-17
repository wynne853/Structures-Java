/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Vector.Vector;
import java.util.ArrayList;

/**
 *
 * @author wynne
 */
public interface Tree<Index, T> {

    //remover elemento
    public T delete(Index index);
    //pegar elemento

    public T search(Index index);
    // Retorna a quantidade de nós da árvore

    public int size();
    //retorna se a árvore está vazia

    public boolean isEmpty();
    // Substitui o elemento armazenado em determinado nó

    public T replace(Index index, T newObject);
    // retorna o pai de um nó

    public T parent(Index index);
    //retorna o nivel da arvore

    public ArrayList<T> children(Index index);
    // retorna verdadeiro se o nó é interno

    public boolean isInternal(Index index);
    // retorna verdadeiro se o nó é externo

    public boolean isExternal(Index index);
    //altura do elemento na arvore 

    public int heightNode(Index index);
    // retorna verdadeiro se o nó é raiz

    public boolean isRoot(Index index);
    //limpar estrutura

    public boolean toClear();
    //percorrer a arvore
    
    public ArrayList<T> goThrough(int type);
}
