package com.project;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



@SpringBootTest
class ProjectApplicationTests {
    static class test{
        public String a = "156";
    }

    static class test2 extends test{
        public String b = "189";
    }
    @Test
    void contextLoads() {
        test2 t2 = new test2();
        System.out.println(t2.a);
    }


}
