package tmp;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BankTest {

    public static int whatIsMyRate(int balance){
        if(balance >= 0 && balance < 100)
            return 3;
        else if(balance >= 100 && balance < 1000)
            return 5;
        else if (balance >= 1000 && balance < 1000000)
            return 7;
        else if (balance >= 1000000)
            return -1;
        else
            return 0;
    }

    @Test
    public void test1(){
        int myRate = whatIsMyRate(-100);
        Assert.assertEquals(myRate, 0);
    }

    @Test
    public void test11(){
        int myRate = whatIsMyRate(-1);
        Assert.assertEquals(myRate, 0);
    }

    @Test
    public void test2(){
        int myRate = whatIsMyRate(0);
        Assert.assertEquals(myRate, 3);
    }

    @Test
    public void test3(){
        int myRate = whatIsMyRate(55);
        Assert.assertEquals(myRate, 3);
    }

    @Test
    public void test33(){
        int myRate = whatIsMyRate(99);
        Assert.assertEquals(myRate, 3);
    }

    @Test
    public void test4(){
        int myRate = whatIsMyRate(100);
        Assert.assertEquals(myRate, 5);
    }

    @Test
    public void test44(){
        int myRate = whatIsMyRate(650);
        Assert.assertEquals(myRate, 5);
    }

    @Test
    public void test6(){
        int myRate = whatIsMyRate(999);
        Assert.assertEquals(myRate, 5);
    }

    @Test
    public void test7(){
        int myRate = whatIsMyRate(1000);
        Assert.assertEquals(myRate, 7);
    }

    @Test
    public void test8(){
        int myRate = whatIsMyRate(1000);
        Assert.assertEquals(myRate, 7);
    }


}
