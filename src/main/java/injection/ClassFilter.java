package injection;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Triton on 31.03.2015.
 */
public class ClassFilter {

    public static List<Field> collectFields(Class <?> clazz, Class<?> upperBound){
        ArrayList <Field> result = new ArrayList<>();
        Class<?> current = clazz;
        while (current!=upperBound){
            result.addAll(Arrays.asList(current.getDeclaredFields()));
            current=current.getSuperclass();
        }

        return result;
    }



    public static List<Field> filterInjection(List<Field> allFields){
        ArrayList <Field> result = new ArrayList<>();

        for (Field field : allFields){
            Inject annotation = field.getAnnotation(Inject.class);
            if(annotation!= null){
                result.add(field);
            }
        }
        return result;
    }
}
