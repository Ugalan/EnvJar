package mod;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Util {
	public static void FindElement(){
		
	}
	
	/***
	 * �����ַ������ʽ��ֵ
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
    
    public boolean CalcStrExpValEx(String expression) {
    	String[] exprs = expression.split("&&");
    	for (int i=0; i<exprs.length; i++){
    		String[] comp = exprs[i].split("<|>");
    		int lNum = Integer.valueOf(comp[0]);
    		int rNum = Integer.valueOf(comp[1]);
    		if (!((exprs[i].contains("<") && lNum < rNum) || (exprs[i].contains(">") && lNum > rNum))){
    			return false;
    		} 
    	}
    	
    	return true;
    }
}
