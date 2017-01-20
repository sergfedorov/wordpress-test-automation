package tmp;

import java.io.IOException;
import java.util.ArrayList;

public class PolygonMain {

    public static void main(String args[]) throws IOException {

        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(5);
        arrayList.add(5);
        /*arrayList.add(5);
        arrayList.add(5);
        arrayList.add(5);
        arrayList.add(5);*/
        /*String res = PolygonV3.isTriangleCorrectByCoordinates(arrayList);
        System.out.println(res);*/


        System.out.println(PolygonV3.isAllElementsAreTheSame(arrayList));

    }

}
