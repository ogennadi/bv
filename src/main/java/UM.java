import java.util.ArrayList;
import java.util.HashMap;

public class UM {
    private static final int NUM_REGISTERS = 8;
    private static final int DEFAULT_VALUE = 0;
    private static final int ARRAY_INDEX = 1;
    private static final int ARRAY_AMENDMENT = 2;
    private static final int ADDITION = 3;
    private static final int MULTIPLICATION = 4;
    private static final int DIVISION = 5;
    private static final int NOT_AND = 6;
    private final ArrayList<Integer> registers;
    private final HashMap<Integer, Integer[]> arrays;
    private boolean running;

    public UM() {
        registers = new ArrayList<>();
        for (int i = 0; i < NUM_REGISTERS; i++) {
            registers.add(DEFAULT_VALUE);
        }

        arrays = new HashMap<>();
        running = true;
    }

    public void set(Integer register, Integer value) {
        registers.set(register, value);
    }

    public Integer get(Integer register) {
        return registers.get(register);
    }

    public void exec(Integer op, Integer a, Integer b, Integer c) {
        if (op == 7) {
            running = false;
        }else        if (op == NOT_AND) {
            registers.set(a, ~(registers.get(b) & registers.get(c)));
        } else if (op == DIVISION) {
            registers.set(a, registers.get(b) / registers.get(c));
        } else if (op == MULTIPLICATION) {
            registers.set(a, registers.get(b) * registers.get(c));
        } else if (op == ADDITION) {
            registers.set(a, registers.get(b) + registers.get(c));
        } else if (op == ARRAY_AMENDMENT) {
            Integer[] array = arrays.get(a);
            array[registers.get(b)] = registers.get(c);
        } else if (op == ARRAY_INDEX) {
            Integer offset = registers.get(c);
            registers.set(a, arrays.get(b)[offset]);
        } else {
            if (registers.get(c) != 0) {
                registers.set(a, registers.get(b));
            }
        }
    }

    public void setArray(Integer index, Integer... ints) {
        arrays.put(index, ints);
    }

    public Integer getArray(Integer index, Integer offset) {
        return arrays.get(index)[offset];
    }

    public boolean getRunning() {
        return running;
    }
}
