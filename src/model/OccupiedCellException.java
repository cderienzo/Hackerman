package com.mygdx.game.model;

import com.sun.corba.se.impl.orbutil.ObjectStreamClassUtil_1_3;

/**
 * Created by brothers on 03/06/2017.
 */
public class OccupiedCellException extends Exception {
    public OccupiedCellException()
    {
        super("The cell is occupied!");
    }
}
