public class MainThreads {
	
	private static int sum = 0;
	private static long start;
	private static long end;
	private static long totalTime;
	
	private static int[] array = new int[2000000];
	
	public static void main(String[] args){
		
			for(int i = 0; i < array.length; i++) {
				array[i] = (int)(Math.random()*10+1);
			}

		Thread thread1 = new Thread (new Runnable() {
			@Override
			public void run() {
				
				start = System.nanoTime();
				for (int i : array) {
					sum += i;
				}
				System.out.println(sum);
				end = System.nanoTime();
				totalTime = ((end - start) / 1000000);
				System.out.println(totalTime + " milliseconds");
			}
		});
		
		Thread thread2 = new Thread (new Runnable() {
			@Override
			public void run() {
				for (int i : array) {
					sum += i;
				}
				System.out.println(sum);
				end = System.nanoTime();
				totalTime = ((end - start) / 1000000);
				System.out.println(totalTime + " milliseconds");
			}
		});
		
		thread1.start();
		thread2.start();

		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
