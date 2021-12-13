import com.fasterxml.jackson.core.JsonProcessingException;
import config.HibernateConfig;
import model.Value;
import org.hibernate.Session;
import service.ThreadServiceAbstract;
import service.ThreadServiceImpl;

public class Main {
    static ThreadServiceAbstract threadService = new ThreadServiceImpl();
    private volatile static Integer count = 10;
    static Session session = HibernateConfig.sessionFactory().openSession();

    public static void main(String[] args) throws JsonProcessingException {
        threadService.saveNewEntity(new Value(true, false, new String[2]));
        threadService.saveNewEntity(new Value(true, true, new String[2]));
        threadService.saveNewEntity(new Value(false, true, new String[2]));
        threadService.saveNewEntity(new Value(true, false, new String[2]));
        threadService.saveNewEntity(new Value(true, true, new String[2]));
        threadService.saveNewEntity(new Value(false, true, new String[2]));
        threadService.saveNewEntity(new Value(false, false, new String[2]));
        threadService.saveNewEntity(new Value(true, false, new String[2]));
        threadService.saveNewEntity(new Value(true, true, new String[2]));
        threadService.saveNewEntity(new Value(false, true, new String[2]));

        ThreadA threadA = new ThreadA();
        threadA.start();
        ThreadB threadB = new ThreadB();
        threadB.start();
        session.close();
    }

    public static class ThreadA extends Thread {
        @Override
        public void run() {
            while (count > 1) {
                int c = 0;
                try {
                    c = threadService.parallelProcessingOfThreads("threadA");
                    Thread.sleep(100);
                } catch (JsonProcessingException | InterruptedException e) {
                    e.printStackTrace();
                }
                if (c == 1) {
                    count--;
                }
            }
        }
    }

    public static class ThreadB extends Thread {
        @Override
        public void run() {
            while (count > 1) {
                int c = 0;
                try {
                    c = threadService.parallelProcessingOfThreads("threadB");
                    Thread.sleep(100);
                } catch (JsonProcessingException | InterruptedException e) {
                    e.printStackTrace();
                }
                if (c == 1) {
                    count--;
                }
            }
        }
    }
}
