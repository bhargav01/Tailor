package Tailor;
import java.util.*;

public class Bobbin{

	static Bobbin instance ;
	//Stack<Thread> threadList = new Stack<Thread>();
	Queue<Thread> threadListQueue = new LinkedList<>();
	int execLimit = 3;
	int executed = 0;
	int totalCount = 0;
	boolean started,ended;
	private Bobbin(){
		
	}
	public static Bobbin getInstance(){
		if(instance == null){
			instance = new Bobbin();
		}
		return instance;
	}
	public void setExecLimit(int limit){
		Logger.debug("Setting limit to "+limit);
		execLimit = limit;
	}
	public int getExecLimit(){
		return execLimit;
	}
	public void add(Thread thread){
		threadListQueue.add(thread);
		totalCount++;
		Logger.debug("Added thread to queue . Total count is "+totalCount);
	}
	public void add(ArrayList<Thread> threadListParam,ExecutionListener executionListener){
		for(Thread t : threadListParam){
			this.add(t);
		}
		Logger.debug("Added thread list to queue . Total count is "+totalCount);
		notifyExecutioner(executionListener);
	}
	public void notifyExecutioner(ExecutionListener executionListener){
		Thread t = new Thread() {
		    public void run() {
		    	if(!started){
					started = true;
					while(started){
					//	System.out.println("in while thread count is "+Thread.activeCount());
					FeedDog f = new FeedDog();
						try {
							int sum = f.setThreadList(getLimitedThreads());
							executed+=sum;
							executionListener.executionCompleted(executed, getTotalCount());
							if(isQueueEmpty()){
								started = false;
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
		    }
		};
		t.start();
	}
	private boolean isQueueEmpty() {
		if(threadListQueue.size() <= 0){
			Logger.debug("queue is empty");
			return true;
		}
		return false;
	}
	private ArrayList<Thread> getLimitedThreads(){
		ArrayList<Thread> tempThreadList = new ArrayList<Thread>();
		int iterCount = getExecLimit();
		if(threadListQueue.size() < iterCount){
			iterCount = threadListQueue.size();
		}
		for(int i=0;i<iterCount;i++){
			tempThreadList.add(threadListQueue.remove());
		}
		return tempThreadList;
	}
	private int getTotalCount(){
		return totalCount;
	}
	
}
