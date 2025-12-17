package org.teju;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class App 
{
    public static void main( String[] args )
    {
        //creating the object using XML config
        ApplicationContext context= new ClassPathXmlApplicationContext("applicationContext.xml");
//        Developer obj= (Developer) context.getBean("developer");
        Developer obj= context.getBean("developer", Developer.class);
       // obj.setAge(21);
        System.out.println(obj.getAge());






        obj.code();
    }
}
