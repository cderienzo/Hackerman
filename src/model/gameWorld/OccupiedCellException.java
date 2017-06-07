package model.gameWorld;

import com.sun.corba.se.impl.orbutil.ObjectStreamClassUtil_1_3;

/**
 * Created by brothers on 03/06/2017.
 */
public class OccupiedCellException extends RuntimeException {
    public OccupiedCellException()
    {
        super("The cell is occupied!");
    }
}
