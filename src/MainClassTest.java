import org.junit.Test;

public class MainClassTest extends MainClass {
    @Test
    public void testGetLocalNumber() {
        int number = this.getLocalNumber();
        if (number == 14) {
            System.out.println("Test completed successfully");
        }
        else {
            System.out.println("Test Failed " + number + "!=14");
        }
    }
}
