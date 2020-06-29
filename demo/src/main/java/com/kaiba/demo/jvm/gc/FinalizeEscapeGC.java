package com.kaiba.demo.jvm.gc;

/**
 * 模拟对象在生死边缘拯救自己的过程
 * @author Lijian
 */
public class FinalizeEscapeGC {

    public static FinalizeEscapeGC SAVE_HOOK = null; //没有引用链，很危险很可能被回收
    public void isAlive() {
        System.out.println("I am still alive :)");
    }

    //重写finalize()方法：有必要执行finalize()方法，放入F-Quece队列
    //VM 创建的线程Finalizer会执行finalize()方法，这是对象唯一一次自我救赎机会
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        FinalizeEscapeGC.SAVE_HOOK = this;//真正的自我救赎，重新在finalize()中建立引用链
    }
    public static void main(String[] args) throws Throwable{
        SAVE_HOOK = new FinalizeEscapeGC();

        //第一次救赎
        SAVE_HOOK = null;//不存在引用链，引起GC注意将会第一次标记
        System.gc();
        Thread.sleep(500);//Finalzier方法优先级低，所以先暂定0.5m。等待它
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();//还活着
        }else {
            System.out.println("No, I am dead :(");
        }
        //不等于null证明活过来了
        System.out.println(SAVE_HOOK);

        //再一次救赎：活过来之后再失去引用链，再次尝试自我拯救
        //但是finalize()方法只能被系统调用一次，结果是失败的
        SAVE_HOOK = null;//活过来之后再失去引用链
        System.gc();
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        }else {
            System.out.println("No, I am dead!");
        }
    }

}
