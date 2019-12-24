package kasei.boot.web;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;


public class GenerallyTest {

    public interface Ggh{

        void pp(String str);
        static Ggh tt(){return t -> {}; }
    }

    @Test
    public void test(){


        SecurityAutoConfiguration a;
        UserDetailsServiceAutoConfiguration b;


        Ggh x = str -> System.out.println(str);

        x.pp("fqe");
        Ggh tt = Ggh.tt();
        tt.pp("dfas");


    }


}
