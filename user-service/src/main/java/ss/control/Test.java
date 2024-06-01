package ss.control;

import org.aspectj.weaver.ast.Var;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Test implements Callable<String> {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Test test = new Test();
        FutureTask<String> futureTask = new FutureTask<>(test);
        Thread thread = new Thread(futureTask);
        thread.start();
        String s = futureTask.get();
        System.out.println(s);
    }
    
    @Override
    public String call() throws Exception {
        return "hello world";
    }
}
