import java.util.ArrayList;

public class UM {
    private static final int NUM_REGISTERS = 8;
    private static final int DEFAULT_VALUE = 0;
    private ArrayList<Integer> registers;

    public UM() {
        registers = new ArrayList<Integer>();
        for (int i = 0; i < NUM_REGISTERS; i++) {
            registers.add(DEFAULT_VALUE);
        }
    }

    public void set(Integer register, Integer value) {
        registers.set(register, value);
    }

    public Integer get(Integer register) {
        return registers.get(register);
    }

    public void exec(Integer i, Integer a, Integer b, Integer c) {
        if(registers.get(c) != 0){
            registers.set(a, registers.get(b));
        }
    }
}
