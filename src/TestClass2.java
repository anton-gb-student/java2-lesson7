public class TestClass2 {

    @BeforeSuite
    public void beforeSuite () {
        System.out.println("Before Suite");
    }

    @MyTest(priority = 9)
    public void test9 () {
        System.out.println("Test. Priority = 9");
    }

    @MyTest(priority = 2)
    public void test2 () {
        System.out.println("Test. Priority = 2");
    }

    @MyTest(priority = 7)
    public void test7 () {
        System.out.println("Test. Priority = 7");
    }

    @MyTest
    public void test () {
        System.out.println("Test. Priority = default");
    }

    @MyTest
    public void test5 () {
        System.out.println("Test. Priority = 5");
    }

    @AfterSuite
    public void afterSuite () {
        System.out.println("After Suite");
    }

    @AfterSuite
    public void afterSuite2 () {
        System.out.println("After Suite");
    }
}