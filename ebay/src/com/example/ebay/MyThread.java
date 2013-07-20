package com.example.ebay;

public class MyThread extends Thread {

  public static void main(String args[]) {
    MyThread m = new MyThread();
    MyThread m1 = new MyThread();
    MyThread m2 = new MyThread();
    Thread.currentThread().setPriority(7);
    MyThread m3 = new MyThread();
    MyThread m4 = new MyThread();
    MyThread m5 = new MyThread();

    m.start();
    System.out.println(m);
    System.out.println(m1);
    System.out.println(m2);

    System.out.println(m3);
    System.out.println(m4);
    System.out.println(m5);

  }

  // @Override
  // public String toString() {
  // // TODO Auto-generated method stub
  // return "hello";
  // }
}
