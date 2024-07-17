import java.lang.reflect.Method;

public class LogExecutionTimeAspect {
    public static void logExecutionTime(Object obj, Method method, Object[] args) throws Exception {
        long start = System.currentTimeMillis();
        method.invoke(obj, args);
        long end = System.currentTimeMillis();
        System.out.println("Execution time of " + method.getName() + " is: " + (end - start) + "ms");
    }
}

