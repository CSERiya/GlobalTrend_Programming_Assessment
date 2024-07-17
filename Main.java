public class Main {
    public static void main(String[] args) {
        MyService service = new MyServiceImpl();
        MyService proxyService = ProxyHandler.createProxy(service);
        
        proxyService.performTask();
    }
}

