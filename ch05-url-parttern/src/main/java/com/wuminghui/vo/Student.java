package com.wuminghui.vo;
//用来保存请求参数值的普通类
public class Student {
    //要求：属性名和请求中的参数名一样
    private String name;
    private Integer age;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("setName" + name);
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        System.out.println("setAge" + age);
        this.age = age;
    }

    public Student() {
        System.out.println("=======Student无参构造器=======");
    }

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
