public class MyServiceImpl implements MyService {
    @LogExecutionTime
    public void performTask() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Task performed");
    }
}

