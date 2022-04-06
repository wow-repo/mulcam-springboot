package com.rubypaper.polymorphism;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TVUser {
	public static void main(String[] args) {		
//        TV tv = new SamsungTV();
//        tv.powerOn();
//        tv.volumeUp();
//        tv.volumeDown();
//        tv.powerOff();
        
        // 1. 스프링 컨테이너를 구동한다. 
//        GenericXmlApplicationContext container = 
//            new GenericXmlApplicationContext("context.xml");
        AnnotationConfigApplicationContext container = 
                new AnnotationConfigApplicationContext(TVConfiguration.class);
        
        // 2. 테스트할 객체를 Lookup한다.
        TV tv = (TV) container.getBean("tv"); 
        
        // 3. Lookup한 객체를 테스트한다. 
        tv.powerOn();
        tv.volumeUp();
        tv.volumeDown();
        tv.powerOff();        
        
        // 4. 스프링 컨테이너를 종료한다.
        container.close();
    }
}
