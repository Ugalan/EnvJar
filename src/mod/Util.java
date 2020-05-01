package mod;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Util {
    public static String CalcStrExpVal(String expression) {
    	String result = null;
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("nashorn");

        try {
            result = String.valueOf(scriptEngine.eval(expression));
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        
        return result;
    }
}
