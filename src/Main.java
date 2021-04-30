public class Main {
    public static class ContainerThread extends Thread{
        private RamContainer ramContainerSecond;

        ContainerThread(RamContainer ramContainer){
            this.ramContainerSecond = ramContainer;
        }

        @Override
        public void run(){
            tPrint("Второй");
            ramContainerSecond.put(11, new Employee("Dev", 32));
            tPrint("Второй");
            ramContainerSecond.put(12, new Employee("Manager", 33));
            tPrint("Второй");
            ramContainerSecond.get(1);
            tPrint("Второй");
            ramContainerSecond.remove(2);
        }
    }


    public static void main(String[] args) {
        RamContainer ram = new RamContainer(3);
        ContainerThread containerThread = new ContainerThread(ram);
        containerThread.start();
        tPrint("Первый");
        ram.put(1, new Employee("Manager", 25));
        tPrint("Первый");
        ram.put(2, new Employee("Dev", 44));
        tPrint("Первый");
        ram.put(3, new Employee("HR", 35));
        tPrint("Первый");
        ram.put(4, new Employee("Manager", 29));
        tPrint("Первый");
        ram.get(11);
        tPrint("Первый");
        ram.get(2);
        System.out.println(ram.size());

    }

    public synchronized static void tPrint(String str){
        System.out.print(str + " поток: ");
    }
}
