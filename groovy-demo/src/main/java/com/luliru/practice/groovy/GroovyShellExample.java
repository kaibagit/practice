package com.luliru.practice.groovy;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;

public class GroovyShellExample {

    public static void main(String args[]) {
        parse();
    }

    public static void parse(){
        Binding groovyBinding = new Binding();
        groovyBinding.setVariable("a", 1);
        groovyBinding.setVariable("b", 2);
        GroovyShell groovyShell = new GroovyShell(groovyBinding);

        String scriptContent = "def sum = a+b;\n" + "sum";

        Script script = groovyShell.parse(scriptContent);
        System.out.println(script.run());
    }

    public static void binding(){
        Binding binding = new Binding();
        binding.setVariable("x", 10);
        binding.setVariable("language", "Groovy");

        GroovyShell shell = new GroovyShell(binding);
        Object value = shell.evaluate("println \"Welcome to $language\"; y = x * 2; z = x * 3; return x ");

        System.err.println(value +", " + value.equals(10));
        System.err.println(binding.getVariable("y") +", " + binding.getVariable("y").equals(20));
        System.err.println(binding.getVariable("z") +", " + binding.getVariable("z").equals(30));
    }
}
