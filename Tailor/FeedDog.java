package Tailor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FeedDog{

	public int setThreadList(ArrayList<Thread> threadList) throws InterruptedException, ExecutionException{
		ExecutorService executor = Executors.newCachedThreadPool();
		//System.out.println("before exe");
	//	System.out.println("here before exec thread count is "+Thread.activeCount());
		Set<Future<Integer>> futureSet = new HashSet<Future<Integer>>();
		  for (Thread t : threadList){
			  Future<Integer> f =  executor.submit(t,1);
			 // threadList.remove(t);
			  futureSet.add(f);
		  	}
		  int sum = 0;
		    for (Future<Integer> future : futureSet) {
		      sum += future.get();
		    }
		    executor.shutdown();
			 // System.out.println("after exec thread count is "+Thread.activeCount());
		    return sum;
		    
	}

}
