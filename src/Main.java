import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static class ContainerThread extends Thread{
        private RamContainer ramContainerSecond;

        ContainerThread(RamContainer ramContainer){
            this.ramContainerSecond = ramContainer;
        }

        @Override
        public void run(){
            ramContainerSecond.put(11, new Employee("Dev", 32));
            ramContainerSecond.put(12, new Employee("Manager", 33));
            ramContainerSecond.get(1);
            ramContainerSecond.remove(2);
        }
    }


    public static void main(String[] args) {
        RamContainer ram = new RamContainer(3);
        ContainerThread containerThread = new ContainerThread(ram);
        containerThread.start();
        ram.put(1, new Employee("Manager", 25));
        ram.put(2, new Employee("Dev", 44));
        ram.put(3, new Employee("HR", 35));
        ram.put(4, new Employee("Manager", 29));
        ram.get(11);
        ram.get(2);
        System.out.println("Всего элемнтов(в оперативной памяти и на диске)" + ram.size());
    }

}
