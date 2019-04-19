package com.luliru.practice.groovy;

import javax.script.*;
import java.util.Date;

public class ScriptEngineManagerTest {

    public static void main(String[] args) throws ScriptException, NoSuchMethodException {
        test_2();
    }

    private static void test_1() throws ScriptException, NoSuchMethodException {
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("groovy");

        String fact = "def factorial(n, msg) { println(msg);return n == 1 ? 1 : n * factorial(n - 1, msg);}";
        engine.eval(fact);

        Invocable inv = (Invocable) engine;
        Object[] params = {new Integer(5), "ssss"};
        Object result = inv.invokeFunction("factorial", params);
        System.out.println(result);

        Bindings binding = engine.createBindings();
        binding.put("date", new Date());
        engine.eval("def getTime(){return date.getTime();}", binding);
        engine.eval("def sayHello(name,age){return 'Hello,I am ' + name + ',age ' + age;}");
        Object time = inv.invokeFunction("getTime", null);
        System.out.println((Long) time);
        String message = (String) inv.invokeFunction("sayHello", "zhangsan", new Integer(12));
        System.out.println(message);
    }

    private static void test_2() throws ScriptException, NoSuchMethodException {
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("groovy");
        String HelloLanguage = "def hello(language) {return \"Hello $language\"}";
        engine.eval(HelloLanguage);
        Invocable inv = (Invocable) engine;
        Object[] params = {new String("Groovy")};
        Object result = inv.invokeFunction("hello", params);
        assert result.equals("Hello Groovy");
        System.err.println(result);
    }
}
