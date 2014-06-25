package org.pvemu.mapeditor.handler;

import java.util.ArrayList;
import java.util.List;
import org.pvemu.mapeditor.data.Change;
import org.pvemu.mapeditor.handler.changeaction.ChangeAction;
import org.pvemu.mapeditor.handler.changeaction.ChangeActions;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class ChangeHandler {
    final private List<Change> changes;
    private int current;

    public ChangeHandler(List<Change> changes) {
        this.changes = changes;
        current = changes.size() - 1;
    }

    public ChangeHandler() {
        changes = new ArrayList<>();
        current = -1;
    }
    
    public void addChange(Change change){
        ChangeAction action = ChangeActions.getAction(change.getAction());
        
        if(action == null){
            System.err.println("Cannot found action " + change.getAction());
            return;
        }
        
        action.perform(change);
        
        ++current;
        
        if(current == changes.size())
            changes.add(change);
        else{
            List<Change> tmp = changes.subList(0, current);
            tmp.add(change);
            changes.clear();
            changes.addAll(tmp);
        }
        
        System.out.println(action.label(change));
    }
    
    public void undo(){
        if(current <= 0)
            return;
        
        --current;
        Change change = changes.get(current);
        ChangeAction action = ChangeActions.getAction(change.getAction());
        
        if(action == null){
            System.err.println("Cannot found action " + change.getAction());
            return;
        }
        
        action.undo(change);
    }
    
    public void redo(){
        if(current >= changes.size() || current < 0)
            return;
        
        ++current;
        
        Change change = changes.get(current);
        ChangeAction action = ChangeActions.getAction(change.getAction());
        
        if(action == null){
            System.err.println("Cannot found action " + change.getAction());
            return;
        }
        
        action.perform(change);
        
        ++current;
    }
}
