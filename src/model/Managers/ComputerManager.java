package model.Managers;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by cderienzo on 6/4/2017.
 */
public class ComputerManager {
    private LinkedList<Computer> computers;
    private Door myDoor;
    private int currentComputer;

    public ComputerManager(Door door, LinkedList<Computer> computers) {
        if(computers != null && door != null){
            this.computers = computers;
            this.myDoor = door;
            currentComputer = 0;
            computers.get(currentComputer).setOn();
        }
    }

    public void updateComputers() {
        if(computers.get(currentComputer).isHacked()){
            if(currentComputer == computers.size()) {
                openDoor();
            }
            else{
                currentComputer++;
                computers.get(currentComputer).setOn();
            }
        }
    }

    public void openDoor() {
        myDoor.setOpen();
    }

}