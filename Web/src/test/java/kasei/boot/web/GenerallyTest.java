package kasei.boot.web;

import org.junit.jupiter.api.Test;


public class GenerallyTest {

    public interface Ggh{

        void pp(String str);
        static Ggh tt(){return t -> {}; }
    }

    @Test
    public void test(){




        Ggh x = str -> System.out.println(str);

        x.pp("fqe");
        Ggh tt = Ggh.tt();
        tt.pp("dfas");


    }


}
