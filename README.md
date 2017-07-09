TAILOR:
Tailor is a java library used to execute thread in a batch system. Programmer can keep adding threads to queue and call 'notifyExecutioner', this will batch the threads and will execute.

Importing the Tailor Library.

1) Import the class 'Bobbin'
2) Create an instance of Bobbin, Bobbin is a singleton,
Bobbin b = Bobbin.getInstance();
3) use 'b.add(<new thread>);' to add threads to the queue.
4) After addding threads call notifyExecutioner() from the class Bobbin. It takes an interface 'ExecutionListener' as a parameter.
Ex : 

		b.notifyExecutioner(new ExecutionListener(){
			@Override
			public void executionCompleted(int count,int totalCount) {
				System.out.println("exec count "+count+" : total count is "+totalCount);
				
			}
		});

> The executionCompleted(int count,int totalCount) method of 'ExecutionListener' is called after executing one batch.

Methods in Bobbin to use,
1) setExecLimit(int limit)
2) getExecLimit() - returns an integer
3) add(Thread thread)
4) add(ArrayList<Thread> threadListParam,ExecutionListener executionListener) , if you call this method you dont have to call 'notifyExecutioner()'. Automatically the list of threads will be added to queue, and will be executed accordingly.
