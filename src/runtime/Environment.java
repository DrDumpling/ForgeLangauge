package runtime;

import java.util.HashMap;
import java.util.Objects;

public class Environment {
    Object returnedValue;
    HashMap<String, Object> heldVariables;

    public Environment() {
        this.heldVariables = new HashMap<>();
    }

    public <T> void addVariable(String variableName, T addedVariable) {
        this.heldVariables.put(variableName, addedVariable);
    }

    public <T> T getVariable(String variableName) {
        if(!heldVariables.containsKey(variableName)) {
            throw new RuntimeException(variableName + " does not exist");
        }
        return (T) heldVariables.get(variableName);
    }

    public boolean hasReturned() {
        return this.returnedValue != null;
    }

    public void returnValue(Object returnedValue) {
        if(Objects.isNull(returnedValue)) throw new RuntimeException("Cannot return null");
        this.returnedValue = returnedValue;
    }

    public Object getReturnedValue() {
        return this.returnedValue;
    }

    @Override
    public String toString() {
        return heldVariables.toString();
    }
}
