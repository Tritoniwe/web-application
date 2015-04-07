package SpringTest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

/**
 * Created by Triton on 31.03.2015.
 */
public class DependencyInjectionServlet extends HttpServlet {
private static final String APP_CTX = "appContext";

    @Override
    public void init() throws ServletException {
        String appCtxPath = this.getServletContext().getInitParameter(APP_CTX);
        System.out.println("Load "+APP_CTX+" -> "+appCtxPath);

        if (appCtxPath==null){
            throw new ServletException();
        }

        try{
            ApplicationContext aplCtx = new ClassPathXmlApplicationContext(appCtxPath);
            List<Field> allFields= ClassFilter.collectFields(this.getClass(),DependencyInjectionServlet.class);
            List<Field> fields = ClassFilter.filterInjection(allFields);
            for (Field field:fields){
                field.setAccessible(true);
                Inject annot =field.getAnnotation(Inject.class);
                String beanName= annot.value();
                Object bean = aplCtx.getBean(beanName);
                if (bean==null){
                    throw new ServletException("There are no such bean name ->"+beanName);
                }
                field.set(this,bean);
            }
        }
        catch (Exception e){
            throw new ServletException("Can't inject from" + appCtxPath);

        }

        //super.init();
    }
}
