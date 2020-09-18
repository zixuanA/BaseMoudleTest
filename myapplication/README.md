# BusUtil
这个demo是个未完成的类eventbus demo
1. post方法没有实现
2. 没有实际运行过model里的代码（颅内编译是没有问题的，实际上emm我也不知道）

修复了eventbus的示例，现在从第二个activity 中返回时会改变第一个activity中的“hello world”

需要自己实现post方法，并且实现进程的确认：当在注解中给出的为main时应该先确认当前是否在主线程，若不在，则发送到主线程后再执行。
给出了确认当前是否为主线程的方法。

涉及 单例 观察者模式 注解 反射 线程间通信
