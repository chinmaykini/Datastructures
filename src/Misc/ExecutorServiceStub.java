package Misc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceStub {

	interface Callback<T> {
		void on (T progress,int val,long threadId);
	}
	public static void main(String agrs[]) throws InterruptedException, ExecutionException{
		ExecutorService pool = Executors.newFixedThreadPool(5);

		ExecutorService retrypool = Executors.newFixedThreadPool(5);
		List<Callable<String>> callableTasks = new ArrayList<>();
		List<Callable<String>> retrycallableTasks = new ArrayList<>();

		Callback<String> callback = new Callback<String>() {
			int totalProgress =0;
			synchronized public void on(String event,int val,long threadId) {
				System.out.print("Curr value : "+totalProgress+ " ");
				totalProgress++;
				System.out.print("New value : "+totalProgress+ " ");
				System.out.println( "Total Progress is "+ ( totalProgress/(150.0) * 100)+
						" Curr val passed "+val+ " passed by thread "+((threadId %5)+1));
			}
		};

		List<Integer> failedIds = new ArrayList<Integer>();

		for ( int i=0; i < 5; i ++) {
			Callable<String> callableTask =  new Callable<String>() {
				public String call() throws Exception{
					for(int j = 0; j < ((Thread.currentThread().getId()%5 )+1)*10; j++) {
						if( ((Thread.currentThread().getId()%5 ) == 2)
								|| (Thread.currentThread().getId()%5 ) == 3){
							throw new Exception(" Timeout ");
						}
						callback.on(Thread.currentThread()+"Task Progress => "+j+" / 10 ",j,Thread.currentThread().getId());
					}
					return "done";
				}
			};
			callableTasks.add(callableTask);
		}

		List<Future<String>> futures = pool.invokeAll(callableTasks);
		int currIndex=0;
		for( Future<?> val : futures){
			try {
				System.out.println("Result "+ val.isDone()+ " "+currIndex+" => "+val.get());
			}catch(Exception ex){
				failedIds.add(currIndex);

			}
			currIndex++;
		}
		pool.shutdown();


		for( Integer failedId : failedIds) {
			Callable<String> callableTask =  new Callable<String>() {
				public String call() throws Exception{
					for(int j = 0; j < (failedId+1)*10; j++) {
						callback.on(Thread.currentThread()+"Task Progress => "+j+" / 10 ",j,Thread.currentThread().getId());
					}
					return "done";
				}
			};

			retrycallableTasks.add(callableTask);
			List<Future<String>> futures2 = retrypool.invokeAll(retrycallableTasks);
			int newIndex=0;
			for( Future<?> newFuture : futures2){
				System.out.println("Result "+ newFuture.isDone()+ " "+newIndex+++" => "+newFuture.get());
			}


		}

		retrypool.shutdown();
	}


}

