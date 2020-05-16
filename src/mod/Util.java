package mod;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Util {
	public static void FindElement(){
		
	}
	
	/***
	 * 计算字符串表达式的值
	 * @param expression
	 * @return
	 */
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
