import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class lowAITest   {

    @Test
    public void start() {
        assertEquals(3+1, 1+3,"asd");

    }

    @Test
    public void testAutoAttack(){
        lowAI ai = new lowAI(new Hero[]{});
       // assertEquals(ai.autoAttck(new Hero()),null);
    }
}