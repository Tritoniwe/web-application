package aspects;


import core.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.WeakHashMap;

public class UsersCache {
    private static WeakHashMap<String, ArrayList<User>> map = new WeakHashMap<>(10);

    public Object checkCache(ProceedingJoinPoint joinPoint) throws Throwable {
        Method m = ((MethodSignature) joinPoint.getSignature()).getMethod();
        String methodName = m.getName();


        if (methodName.equals("searchUsers")) {
            Object[] args = joinPoint.getArgs();
            String arguments = "" + args[0] + args[1];
            if (map.containsKey(arguments)) {
                System.out.println("Taken from local cache");
                return map.get(arguments);
            } else {
                ArrayList<User> list = (ArrayList<User>) joinPoint.proceed();
                map.put(arguments, list);
                System.out.println("Taken from DB");
                return list;
            }
        } else if (methodName.equals("changeUserData") || methodName.equals("addUser") || methodName.equals("deleteUser")) {
            map = new WeakHashMap<>(10);
            return joinPoint.proceed();
        } else return joinPoint.proceed();
    }

}
