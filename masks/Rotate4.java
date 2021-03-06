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

package com.act365.sudoku.masks ;

import com.act365.sudoku.MaskFactory ;

/**
 * Rotate4 represents a mask with rotational symmetry of order 4. 
 */

public class Rotate4 extends MaskFactory {

    public Rotate4( int cellsInRow ) {
        super( cellsInRow );
    }
    
    /**
     * There are potentially two sectors:
     * sector[0] represents everything apart from the centre cell.
     * sector[1] represents the centre cell.
     */
    
    public int countSectors(){
        return 1 + cellsInRow % 2 ;    
    }
    
    public int allocateSectors( int filledCells ){
        if( nSectors == 2 ){
            if( filledCells % 4 == 1 ){
                sectorMin[0] = sectorMax[0] = ( filledCells - 1 )/ 4 ;
            } else {   
                if( filledCells % 4 != 0 ){
                    filledCells += 4 - filledCells % 4 ;
                }                
                sectorMin[0] = sectorMax[0] = filledCells / 4 ;
            }
            sectorMin[1] = sectorMax[1] = filledCells % 4 ;
            sectorSlots[0] = ( cellsInRow * cellsInRow - 1 )/ 4 ;
            sectorSlots[1] = 1 ;
        } else {
            if( filledCells % 4 != 0 ){
                filledCells += 4 - filledCells % 4 ;
            }
            sectorMin[0] = sectorMax[0] = filledCells / 4 ;
            sectorSlots[0] = cellsInRow * cellsInRow / 4 ;
        }        
        
        return filledCells ;
    }   

    public boolean areSectorsValid(){
        return true ;   
    }
    
    public void populateMask(){
        int i , j , k ;
        i = 0 ;
        while( i < cellsInRow ){
            j = 0 ;
            while( j < cellsInRow ){
                mask[i][j] = false ;
                ++ j ;
            }
            ++ i ;
        }
        i = j = k = 0 ;
        while( k < sectorBalls[0] ){
            j += g[0][k++];
            i += j /( cellsInRow / 2 );
            j = j % ( cellsInRow / 2 );
            mask[i][j] = mask[j][cellsInRow-1-i] = 
            mask[cellsInRow-1-i][cellsInRow-1-j] = mask[cellsInRow-1-j][i] = true ; 
            ++ j ; 
        }
        if( nSectors == 2 && sectorBalls[1] == 1 ){
            mask[(cellsInRow-1)/2][(cellsInRow-1)/2]= true ;
        }        
    }
}
