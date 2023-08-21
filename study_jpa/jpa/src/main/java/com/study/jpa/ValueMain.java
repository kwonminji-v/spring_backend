package com.study.jpa;

public class ValueMain {
    public static void main(String[] args) {

        Integer a = Integer.valueOf(10);
        Integer b = a; // 이 땐, a의 value가 복사되어 넘어가는 것이 아니라 주소값(참조값)이 전달됩니다.

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
