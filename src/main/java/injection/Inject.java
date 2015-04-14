package injection;

import java.lang.annotation.*;

/**
 * Created by Triton on 31.03.2015.
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {
    public String value();
}
