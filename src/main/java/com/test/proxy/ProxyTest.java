package com.test.proxy;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {
    public interface ISender {
        public boolean send();
    }
    public class SmsSender implements ISender {
        @Override
        public boolean send() {
            return false;
        }
    }
    public class ProxySender implements ISender {
        private ISender sender;
        public ProxySender(ISender sender) {
            this.sender = sender;
        }
        @Override
        public boolean send() {
            System.out.println("before...");
            boolean target = sender.send();
            System.out.println("after...");
            return target;
        }
    }
    @Test
    public void testStaticProxy() {
        ISender sender = new ProxySender(new SmsSender());
        System.out.println("result: " + sender.send());
    }

    // jdk proxy
    public class JdkProxyHandler implements InvocationHandler {
        private Object target;
        public JdkProxyHandler(Object target) {
            this.target = target;
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("before...");
            Object targetResult = method.invoke(this.target, args);
            System.out.println("after...");
            return targetResult;
        }
    }
    @Test
    public void testJkdProxy() {
        ISender proxySender = (ISender) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                new Class[]{ISender.class},
                new JdkProxyHandler(new SmsSender()));
        System.out.println(proxySender);
        System.out.println(proxySender.getClass());
        System.out.println("result: " + proxySender.send());
    }

    // cglib proxy
    public class XSender {
        public XSender(){}
        public boolean send(){
            System.out.println("sending msg");
            return true;
        }
        public String echo(String info) {
            return info;
        }
    }
    public class CglibProxyInterceptor implements MethodInterceptor{
        private Enhancer enhancer = new Enhancer();
        public Object getProxy(Class c) {
            this.enhancer.setSuperclass(c);
            this.enhancer.setCallback(this);
            return this.enhancer.create();
        }
        @Override
        public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            System.out.println(method.getName());
            System.out.println("before...");
            Object targetResult = methodProxy.invokeSuper(object, args);
            System.out.println("after...");
            return targetResult;
        }
    }
    @Test
    public void testCglibProxy(){
        BSender bSender = (BSender) new CglibProxyInterceptor().getProxy(BSender.class);
        System.out.println(bSender.getClass());
        System.out.println("--------------------------------");
        System.out.println(bSender.send());
        System.out.println("--------------------------------");
        System.out.println(bSender.echo("hello"));
    }

    // javassist proxy
    public class JavassistProxyHandler implements MethodHandler {
        private ProxyFactory proxyFactory = new ProxyFactory();

        public Object getProxy(Class c) {
            proxyFactory.setSuperclass(c);
            Class<?> targetClass = proxyFactory.createClass();
            Object target = null;
            try {
                 target = targetClass.newInstance();
                ((ProxyObject)target).setHandler(this);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return target;
        }

        @Override
        public Object invoke(Object object, Method method, Method method1, Object[] args) throws Throwable {
            System.out.println("before...");
            Object targetResult = method1.invoke(object, args);
            System.out.println("after...");
            return targetResult;
        }
    }
    @Test
    public void testJavassistProxy() {
        BSender sender = (BSender) new JavassistProxyHandler().getProxy(BSender.class);
        System.out.println(sender.getClass());
        System.out.println("--------------------------------");
        System.out.println(sender.send());
        System.out.println("--------------------------------");
        System.out.println(sender.echo("world"));
    }
}
