package mod;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/***
 * ȫ���쳣����
 * ʹ�÷�����CaughtExceptionHandler.getInstance().init();
 */
public class CaughtExceptionHandler implements Thread.UncaughtExceptionHandler{ 
	private Thread.UncaughtExceptionHandler defaultHandler = null;
    private static CaughtExceptionHandler caughtExceptionHandler = null;
    
    private CaughtExceptionHandler(){    	
    }
 
    public static CaughtExceptionHandler getInstance(){ 
        if (caughtExceptionHandler == null){ 
            synchronized (CaughtExceptionHandler.class){ 
                if (caughtExceptionHandler == null){
                    caughtExceptionHandler = new CaughtExceptionHandler();
                }
            }
        }
 
        return  caughtExceptionHandler;
    }
 
    public void init(){
    	defaultHandler = Thread.getDefaultUncaughtExceptionHandler(); // ��׿��Ŀ��Ĭ�ϵĲ����������ĵ���jar������Ŀ��û����Ϊnull
    	Thread.setDefaultUncaughtExceptionHandler(this);
    }
    
    public void continueThrows(Throwable throwable) throws Throwable{
    	throw throwable;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable){
    	StackTraceElement ele = throwable.getStackTrace()[0];
        System.out.println(String.format("className��%s����methodName��%s����fileName��%s����lineNumber��%s����message��%s��"
        		, ele.getClassName(), ele.getMethodName(), ele.getFileName(), ele.getLineNumber(), throwable.getMessage()));
        
        if (throwable.getMessage().contains("flag")){
			try {
				Class<?> exceptionClass = Class.forName(ele.getClassName());
				Method method = exceptionClass.getMethod(ele.getMethodName());
				method.invoke(throwable.getClass()); // method.invoke(exceptionClass.newInstance());
			}
			catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
        }
        else {
        	// defaultHandler.uncaughtException(thread, throwable);
        	throw new RuntimeException(throwable);
        }
    }
}