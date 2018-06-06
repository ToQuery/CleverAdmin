package com.toquery.cleverweb.semaphore;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author toquery
 * @version 1
 */
public class ResourceUser implements Runnable{


    private ResourceManage resourceManage;
    private int userId;
    public ResourceUser(ResourceManage resourceManage, int userId) {
        this.resourceManage = resourceManage;
        this.userId = userId;
    }
    public void run(){
        System.out.print("userId:"+userId+"准备使用资源...\n");
        try {
            resourceManage.useResource(userId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("userId:"+userId+"使用资源完毕...\n");
    }

    public static void main(String[] args){
        System.out.println(LocalDateTime.now());
        ResourceManage resourceManage = new ResourceManage();
        Thread[] threads = new Thread[100];
        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(new ResourceUser(resourceManage,i));//创建多个资源使用者
            threads[i] = thread;
        }
        for(int i = 0; i < 1000; i++){
            Thread thread = threads[i];
            try {
                thread.start();//启动线程
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println(LocalDateTime.now());
    }
}
