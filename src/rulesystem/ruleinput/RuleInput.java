package rulesystem.ruleinput;

import java.io.Serializable;
import rulesystem.ruleinput.value.InputDataType;

public abstract class RuleInput implements Serializable {

    protected RuleInputMetaData metaData;
    protected String rawInput;

    public static RuleInput createRuleInput(
        int id, int ruleSystemId, String name, int priority, RuleType ruleType, 
        InputDataType dataType, String value) throws Exception {

        value = value == null ? "" : value;

        RuleInput r;
        switch (ruleType) {
            case VALUE:
                r = new ValueInput(id, ruleSystemId, name, priority, dataType, value);
                break;
            case RANGE:
                r  = new RangeInput(id, ruleSystemId, name, priority, dataType, value);
                break;
            default:
                return null;
        }
        
        r.rawInput = value;

        return r;
    }

    public abstract boolean evaluate(String value) throws Exception;

    public abstract boolean isConflicting(RuleInput input) throws Exception;

    public final String getRawValue() {
        return this.rawInput;
    }

    public int getId() {
        return this.metaData.getId();
    }

    public int getRuleSystemId() {
        return this.metaData.getRuleSystemId();
    }

    public String getName() {
        return this.metaData.getName();
    }

    public int getPriority() {
        return this.metaData.getPriority();
    }

    public RuleType getDataType() {
        return this.metaData.getRuleType();
    }
}
