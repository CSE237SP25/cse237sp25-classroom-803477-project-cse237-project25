package banking;

public class Option {
    private String optionName;
    private Runnable optionCall;

    public Option(String optionName, Runnable optionCall) {
        this.optionName = optionName;
        this.optionCall = optionCall;
    }

    public boolean execute() {
        if (optionCall != null) {
            optionCall.run();
            return true;
        }
        return false;
    }

    public String getOptionName() {
        return optionName;
    }
}