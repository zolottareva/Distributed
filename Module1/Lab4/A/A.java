package A;

import A.Instructions.ReaderInstruction;
import A.Instructions.WriterInstruction;
import java.util.Random;

public class A {
    private static final String fileName = "database.txt";

    public static void main(String... args) {
        ReadWriteLock lock = new ReadWriteLock();
        Reader reader = new Reader(fileName, lock);
        Writer writer = new Writer(fileName, lock);

        try {
            System.out.println("Status of adding operation: " +
                    writer.changeFile(WriterInstruction.ADD, "Name" + new Random().nextInt(100),
                            "11" + (new Random().nextInt(8999) + 1000)));
            System.out.println("Name founded: " +
                    reader.completeSearch(ReaderInstruction.FIND_NAME_BY_NUMBER, "2"));
            System.out.println("Number founded: " +
                    reader.completeSearch(ReaderInstruction.FIND_NUMBER_BY_NAME, "Name6"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
