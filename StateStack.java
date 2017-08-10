/*
 * Su Doku Solver
 * 
 * Copyright (C) act365.com August 2005
 * 
 * Web site: http://act365.com/sudoku
 * E-mail: developers@act365.com
 * 
 * The Su Doku Solver solves Su Doku problems - see http://www.sudoku.com.
 * 
 * This program is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by the Free 
 * Software Foundation; either version 2 of the License, or (at your option) 
 * any later version.
 *  
 * This program is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General 
 * Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with 
 * this program; if not, write to the Free Software Foundation, Inc., 
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */

package com.act365.sudoku ;

//import java.util.Vector ;
import java.util.*;

/**
 * StateStack stores state grids in a dynamically-expanding vector.
 * The class should be used for memory-intensive state grids that
 * are rarely references (so that performance isn't critical), such
 * as LinearSystemState.
 * <p>
 * Modified by John Clark (jc) as part of Java 4 Homework 2.  The modification
 * replaced all usage of the Vector class with the ArrayList class.
 * 
 * @param <E> the type of the object to be stored
 * @author joclark
 * 
 */
public class StateStack<E> extends ArrayList<E> {
	//jc: Modified StateStack to now inherit from ArrayList, rather than Vector
	//Previous class declaration: public class StateStack extends Vector {

    int nMovesStored ;
    
    int[] moves ;
    
    /**
     * Creates a StateStack to store at most maxMoves moves. 
     * 
     * @param maxMoves maximum number of elements to be in the ArrayList
     */

    public StateStack( int maxMoves ){
        nMovesStored = 0 ;
        moves = new int[maxMoves];
        // jc: Replace Vector method setSize with ArrayList method ensureCapacity
        //setSize( maxMoves );
        ensureCapacity(maxMoves);
    }
    
    /**
     * Adds a state grid to the thread.
     * 
     * @param obj the ArrayList element being pushed onto the stack
     * @param nMoves thread position from which state should be read
     * @see com.act365.sudoku.IState#pushState(int)
     */
    // jc: Updated pushState to allow a generic obj to be pushed onto the stack
    //public void pushState( Object obj , int nMoves ) {
    public void pushState( E obj , int nMoves ) {
        int i = 0 ;
        while( i < nMovesStored && moves[i] != nMoves ){
            ++ i ;   
        }
        if( i < nMovesStored ){
        	// jc: Replace Vector method setElementAt with ArrayList method set
            //setElementAt( obj , i ); 
        	set(i, obj);  
        } else {
        	// jc: Replace Vector method addElement with ArrayList method add
            //addElement( obj );
        	add(obj);
            moves[nMovesStored++] = nMoves ;
        }
    }    
       
    /**
     * Unwinds a state grid from the thread.
     * 
     * @param nMoves thread position from which state should be read
     * @return the ArrayList element at the specified location
     * @see com.act365.sudoku.IState#popState(int)
     */
    public Object popState( int nMoves ) {
        int i = 0 ;
        while( i < nMovesStored && moves[i] != nMoves ){
            ++ i ;   
        }
        if( i < nMovesStored ){
        	// jc: Replace Vector method elementAt with ArrayList method get
            //return elementAt( i );
            return get(i);
        } else {
            return null ;   
        }
    }
}
