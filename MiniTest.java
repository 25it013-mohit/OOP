import java.lang.annotation.*;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Run {
}

class MyTests {

    @Run
    public void testOne() {
        System.out.println("Test One executed");
    }

    @Run
    public void testTwo() {
        System.out.println("Test Two executed");
    }

    public void normalMethod() {
        System.out.println("Normal method executed");
    }

    @Run
    public void testThree() {
        System.out.println("Test Three executed");
    }
}

public class MiniTest {

    public static void main(String[] args) throws Exception {
        MyTests obj = new MyTests();
        int count = 0;

        for (Method method : obj.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Run.class)
                    && method.getParameterCount() == 0) {
                method.invoke(obj);
                count++;
            }
        }

        System.out.println("Total methods executed: " + count);
    }
}