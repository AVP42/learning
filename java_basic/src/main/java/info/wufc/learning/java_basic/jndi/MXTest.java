package info.wufc.learning.java_basic.jndi;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * @author fuchang.wu@foxmail.com
 * @since 2022-01-23 17:29
 */
public class MXTest {
    public static void main(String[] args) throws NamingException {
        Hashtable env = new Hashtable();
        env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
        env.put("java.naming.provider.url", "dns://baidu.com");
        // 创建环境对象
        InitialDirContext dirContext = new InitialDirContext(env);
        // 读取环境对象的属性
        Attributes attrs = dirContext.getAttributes("jeffcorp.com", new String[]{"MX"});
        for(NamingEnumeration ae = attrs.getAll(); ae!=null && ae.hasMoreElements();){
            Attribute attr = (Attribute) ae.next();
            NamingEnumeration<?> e = attr.getAll();
            while(e.hasMoreElements()){
                String element = e.nextElement().toString();
                System.out.println(element);
            }
        }
    }
}
