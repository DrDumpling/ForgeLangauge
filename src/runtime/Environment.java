package runtime;

import java.util.HashMap;

public class Environment {
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
}
