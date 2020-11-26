public class MainClass {
    public int getLocalNumber() {
        return 14;
    }

    private int class_number() {
        return 20;
    }

    public int getClassNumber() {
        return this.class_number();
    }
}
