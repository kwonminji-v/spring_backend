package com.study.jpa;

public class ValueMain {
    public static void main(String[] args) {

        int a = 10;
        int b = 10;

        System.out.println("a == b = " + (a == b));



        Address address1 = new Address("city", "street", "100-1000");
        Address address2 = new Address("city", "street", "100-1000");

        System.out.println("address2 == address1 = " + (address2 == address1));
        //java에서는 == 비교 자체가 레퍼런스 (참조값)를 비교하는데, 내부의 value가 동일하더라도, 현재 인스턴스 자체가 다름으로 참조값이 다른것으로 인식됩니다.



        a = 20;
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        /** 같은 인스턴스를 공유하는 상황 */
/*        int a = 10;
        int b = a; //b가 초기화될 때, a의 10의 value가 복사가 되어 b에 저장됨

        b = 20; //b value에 20을 넣으면 b의 값만 변하게 됨*/

        /** 기본 값 타입은 항상 값을 복사하게 되어, value가 공유되지 않습니다.
         * Integer같은 래퍼 클래스나 String 같은 특수한 클래스는 레퍼런스를 가지고 가기 때문에 참조값 공유가 가능한 객체이지만
         * 변경자체를 불가능하게 두었기때문에, 사이드이펙트가 발생하지 않아 개발 시 안전하게 개발이 가능합니다. */

    }
}
