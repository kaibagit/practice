package me.luliru.practice.maven.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.util.List;

/**
 * MyPackageMojo
 * Created by luliru on 2020-03-11.
 */
@Mojo(name="mypackage",defaultPhase= LifecyclePhase.PACKAGE)
public class MyPackageMojo extends AbstractMojo {

    @Parameter
    private String msg;

    @Parameter
    private List<String> options;

    @Parameter(property = "args")
    private String args;


    public void execute() throws MojoExecutionException, MojoFailureException {
        System.out.println("hello sogoucloud:"+msg);
        System.out.println("hello sogoucloud:"+options);
        System.out.println("hello sogoucloud:"+args);
    }

}