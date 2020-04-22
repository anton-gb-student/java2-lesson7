import java.lang.reflect.Method;
import java.util.*;

public class Main {
    static List<Method> before = new ArrayList<>();
    static List<Method> after = new ArrayList<>();
    static ArrayList<Method> tests = new ArrayList<>();
    static Comparator<Method> comparator = new Comparator<Method>() {
        @Override
        public int compare(Method o1, Method o2) {
            return o1.getAnnotation(MyTest.class).priority() - o2.getAnnotation(MyTest.class).priority();
        }
    };

    public static void start (Class testClass) throws Exception {
        Object testObj = testClass.getConstructor(null).newInstance();
        for (Method m : testClass.getDeclaredMethods()) {
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                before.add(m);
            } else if (m.isAnnotationPresent(AfterSuite.class)) {
                after.add(m);
            } else if (m.isAnnotationPresent(MyTest.class)) {
                tests.add(m);
                Collections.sort(tests, comparator);
            }
        }
        if (before.size()>1 || after.size()>1) {
            throw new RuntimeException ("Too many before's or after's");
        } else {
            before.get(0).invoke(testObj);
            for (Method m : tests) {
                m.invoke(testObj);
            }
            after.get(0).invoke(testObj);
        }
    }

    public static void main(String[] args) {

        try {
            start(TestClass.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            start(TestClass2.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
