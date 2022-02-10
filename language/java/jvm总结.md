# JVM 总结

## 走进JVM

1. **HotSpot 是什么**

   Hotspot是一种JVM实现，由SUN公司开发。

   JVM还有BEA实现的JRockit，IBM的J9，后来BEA和SUN均被Oracle收购

2. **JDK 版本名称**

   从JDK 1.5开始，公开版本书写为JDK5，JDK6...，只有开发者版本才会使用1.5，1.6这种方式。

3. **JAVA的优点**

   * 结构严谨，面向对象
   * 相对安全的内存管理和访问机制
     * 相比于C语言，提供了内存动态分配，和垃圾收集技术。
   * 热点代码检测，运行时编译及优化

4. **JDK与JVM的关系**

   ![image-20220209203224677](images/image-20220209203224677.png)

   JDK主要包括包括以下三部分：

   * Java 程序设计语言
   * Java Virtual Machine
   * Java API 类库  包括Java SE API 和 Tools&Tools APIs。

   JRE主要包括以下部分：

   * Java SE API子集
   * Java Virtual Machine

5. Java SE 与 Java EE

   Java SE: 面向桌面级应用的平台，比如window程序，主要是Java 核心的API，即Java SE API。

   Java EE: 面向多层架构的企业应用(比如ERP，CRM)的平台，除了有Java SE API(包名为java开头，由于历史原因也有小部分以javax开头)，还有扩展API(通常包名以javax开头)。

6. JDK 主要版本历史

   1. 2004 年 JDK1.5发布，提供自动装箱，泛型，动态注解，枚举，可变长参数，foreach等特性，改进Java Memory Model (JMM)java 内存模型，提供concurrent并发包。

7. JVM 发展历史

   * Classic VM    JDK 1.3  前
   * Hotspot VM  JDK1.3
     * Hotspot VM 是Sun JDK和OpenJDK的默认虚拟机，使用范围最广。
     * 如其名，Hotspot指的就是其热点探测技术

8. 未来

   1. 模块化--[OSGi](https://www.ibm.com/docs/zh/radfws/9.6.1?topic=SSRTLW_9.6.1/com.ibm.aries.osgi.doc/topics/cosgi.htm)(面向Java的动态模块化规范，模块作为服务器的一部分来即插即用，而不是作为应用程序的一部分)

9. 64位虚拟机 指针膨胀

   64位虚拟机需要在内存上付出较大代价，原因在于指针膨胀以及数据类型对齐补白问题，比32位要额外增加10%到30%的内存消耗。

   解决方式：可以开启普通对象指针压缩功能，-XX:+ UseCompressedOops，但是不建议显式设置，建议由虚拟机的==Ergonomics==机制自动开启

10. OpenJDK vs Oracle JDK

    OpenJDK是sun的开源版本；Oracle是商业闭源版，包含比如从JRockit移植过来的Java Fligth Recorder等功能。



## 自动内存管理

### 虚拟机运行时数据区

1. 程序计数器 Program Counter Register

   * 内容：正在执行的字节码指令的地址
   * 线程私有
   * 唯一一个不会产生OOM的区域。

2. Java 虚拟机栈 Java Virtual Machine Stack 

   * 内容：Java方法栈帧

     * 栈帧里面存放局部变量表，操作数栈，动态链接，方法出口等信息。

       * 局部变量表：存放基本数据类型，对象引用，returnAddress类型(指向了一条字节码指令的地址)

         > 其中64bit的基本类型long和double占据两个局部变量空间（slot），其余均为1个。
         >
         > 局部变量表在编译之后就已经确定，方法运行时期不会改变局部变量表的大小。

   * 描述Java 方法执行的内存模型，每个方法在执行时都有一个栈帧，方法被调用和结束对应了入栈和出栈操作

   * 线程私有

   * 异常：

     * 如果线程请求的栈深度大于虚拟机允许的范围， 抛出StackOverflowError 异常
     * 大部分虚拟机都支持动态扩展虚拟机栈长度，如果拓展时无法申请到足够的内存，则抛出OutOfMemoryError

3. 本地方法栈 Native Method Stack

   * 内容：Native 方法栈帧
   * 有的虚拟机实现会把虚拟机栈与本地方法栈合而为一，比如SUN的Hotspot VM
   * 线程私有
   * 异常：StackOverflowError，OutOfMemoryError

4. 堆 Heap

   * 内容：对象实例和数组

     > 并不是所有的对象都在堆里面，由于JIT编译器的发展和逃逸技术的组件成熟，栈上分配，标量替换优化技术，已经不是绝对的了。

   * 线程共享

   * 细分：

     * 按分代：
       * 新生代
         * Eden
         * survivor1
         * survivor2
       * 老年代
     * 按内存分配
       * 线程共享的堆可能划分出多个线程==私有==的分配缓冲区 TLAB(Thread Local Allocation Buffer)

   * 异常：OutOfMemoryError

5. 方法区 Method Area

   * 内容：已被虚拟机加载的类信息(类名，修饰符，字段描述，方法描述)，常量，静态变量，即时编译器编译后的代码

   * 线程共享

   * 在JDK 8前被称为永久区(Permanent Generation)，其实不严谨。只是Hotsopt虚拟机将分代收集机制拓展到方法区而已，使得Hotspot的垃圾收集器可以像管理堆那样来管理方法区，省去了单独为方法区编写内存管理代码的工作。

     > 实际上的表现也证明这种设计不合理，
     >
     > * 其一，方法区很容易溢出，因为永久代有大小上限(-XX: MaxPermSize，而其他虚拟机实现比如JRockit没有限制(只要没有超过进程可用内存)，而且有极少数方法(比如]String.intern()](https://stackoverflow.com/questions/10578984/what-is-java-string-interning)方法会将内容相同的字符串引用指向到同一个对象)会导致不同虚拟机有不同的实现。
     >
     >   在Hotspot中，从Jdk 1.7开始，String.intern后的对象不再放在方法区，而将整个字符串常量池放在heap中
     >
     > * 其二，方法区的垃圾收集行为是比较少出现的，需要回收的主要是针对常量池的回收和对类型的卸载。而且回收条件是非常苛刻的，比如类型卸载。但是回收也是有必要的。
     >
     > 从JDK8开始，使用元空间MetaSpace代替。

   * 异常：OutOfMemoryError

   * 细分：

     * 运行时常量池 Runtime Constant Pool

       * class文件除了有类的版本，字段，方法，接口等描述信息，还有一项信息是常量池（constant Pool Table）。常量池里面存放编译期生成的各种字面量和符号引用，这部分内容将在类加载后进入方法区的运行时常量池中保存。

         > 不同虚拟机实现时，除了保存class文件中描述的符号引用，还会将翻译出来的直接引用也存储在运行时常量池中。

       * 运行时常量池具有动态性，即运行时也可能将新的常量放入，比如使用String.intern。

         > 不过从JDK1.7开始，字符串常量池不在堆中而不是方法区中。

6. 直接内存 Direct Memory

   并不是虚拟机运行时数据区的一部分，也不是虚拟机规范中定义的区域。但也会导致OutOfMemoryError的出现。

   JDK1.4引入了NIO(NEW Input/Output)，可以用一种基于通道Channel和缓冲区Buffer的IO方式，可以使用Native方法直接分配堆外内存，通过一个存储在堆中的DirectByteBuffer对象作为这块内存的引用来进行操作。这样做的好处是避免了Java堆和Native堆中的数据来回复制数据。

   直接内存不受Java堆的限制，但是既然是内存，肯定会受到本机总内存大小和处理器寻址空间的限制。

   如果忽略了这部分内存，那么在动态扩展时可能会出现OutOfMemoryError。



### Hotspot中对象的分配，布局与访问

#### 对象的创建

1. 虚拟机接收到new指令，虚拟机到运行时常量池查找对应类的符号引用，检查该符号引用代表的类是否被加载，解析，初始化，如果没有，先执行类加载过程。

2. 虚拟机为新对象分配内存。对于使用带有整理算法的收集器的区域，一般会使用指针碰撞的方式分配，对于标记-清除算法的收集器的区域，一般使用空闲列表的方式。

   > 对象所需的内存在类加载后便可完全确定。
   >
   > * 内存分配如何保证并发安全？
   >   * 方式1：虚拟机采用CAS+失败重试的机制保证操作的原子性。
   >   * 方式2：利用本地线程分配缓冲 (Thread Local Allocation Buffer) TLAB。哪个线程需要分配内存，就在该线程的TLAB上分配，当TLAB用完需要分配新的TLAB时，才需要同步锁定。这种方式可通过-XX: +/- UseTLAB参数来指定
   >
   

3. 内存分配完后，虚拟机将分配到的内存空间都初始化为零值(不包括对象头)。这个步骤使得对象的实例字段还未赋予初始值就可以直接使用，程序可以访问到这个对象这些字段的数据类型对应的零值。

   > 如果采用TLAB，可以提前到TLAB分配时进行。

4. 虚拟机对对象进行必要的设置，主要是对象头(Object Header)。比如该对象是哪个类的实例，如何才能找到类的元数据信息，对象的哈希吗，对象的GC分代年龄等信息。

   > 虚拟机当前的运行状态不同，对对象头的设置也会不同，比如是否启用偏向锁。

   > 从虚拟机的角度，上面的工作完成，一个新的对象已经产生了，但是从程序的角度，该对象才刚刚开始，<init>方法还没执行，所有字段都是零值。

5. 执行<init>方法，初始化对象。



#### 对象的内存布局

1. 对象头Header

   * 对象自身的运行时数据--Mark Word

     包括hashcode，GC分代年龄，锁状态标志，线程持有的锁，偏向线程ID，偏向时间戳等。

     32和64位虚拟机分别使用32位和64位固定长度的空间的bitmap结构记录。

     这些数据已经超过了32位或者64位，但是为了空间效率，对象处于不同的状态，Mark word中的数据是不同的。

     | 存储内容 29位                       | 标志位 2位(还有1位固定为0) | 状态             |
     | ----------------------------------- | -------------------------- | ---------------- |
     | 对象HashCode，对象分代年龄          | 01                         | 未锁定状态       |
     | 指向锁记录的指针                    | 00                         | 轻量级锁定       |
     | 指向重量级锁的指针                  | 10                         | 膨胀(重量级锁定) |
     | 空，不需要记录信息                  | 11                         | GC标记           |
     | 偏向线程ID,偏向时间戳，对象分代年龄 | 01                         | 可偏向           |

   * 类型指针

     指向该对象的类元数据的指针。虚拟机通过该指针判断是哪个类的实例。

     > 并不是所有的虚拟机实现都在对象数据上记录类型指针。也就是说查找对象的类元数据并不一定要经过对象本身。

   * 数组长度

     如果该对象是数组，对象头会记录数组长度。

2. 实例数据Instance Data

   对象各字段的内容。

   该部分的存储顺序遵循以下分配策略：

   1. 相同宽度的子弹分配到一起longs/doubles, ints, shorts/chars, byte/booleans, oops(Ordinary Object pointers)
   2. 父类的变量在子类之前
   3. 如果开启CompactField，子类中较窄的变量可能插入父类变量之中。

3. 对齐填充 Padding

​		起占位作用。Hotspot VM的自动内存管理系统要求对象的起始地址必须是8字节的整数倍。因此对象的大小也必须是8字节的整数倍。==**所以一个对象至少是8字节。**==



#### 对象的访问定位

java程序通过栈上的reference数据操作堆上的对象。

reference类型在虚拟机规范中只规定一个指向对象的引用，不一定是一个具体的地址，对象的寻址取决于虚拟机的实现。

有以下两种方式：

* 句柄

  java堆中划分出一块内存作为句柄池，reference中存储的是对象的句柄地址，句柄中存放了对象实例数据和类型数据的具体地址。

  > 好处是对象被移动时(GC时)，只需要修改句柄里面的具体地址，reference不需要修改。

* 直接指针

  reference中存储的是对象的实例数据的具体地址，对象的实例数据中存储了对象类型数据的指针。

  > 如果采用这种方式，对象头中就必须有类型指针。
  >
  > 好处是直接访问，速度更快。对象的访问时非常频繁的，可以节省非常可观的成本。
  >
  > Sun Hotspot采用这种方式。

​	





### OutOfMemoryError

#### 堆溢出 排查方法论

1. 重现：减少堆大小-Xms -Xmx，不断创建对象，并保证GC Roots到对象之间有可达的路径，避免垃圾回收机制清除对象。java.lang.OutOfMemoryError: Java heap space
2. 获取堆转储快照：可添加参数-XX:+HeapDumpOnOutOfMemoryError
3. 通过内存映像工具，比如Eclipse Memory Analyzer
   * 判断是内存泄漏还是内存溢出：如果大量对象是不需要的，很可能是对内存泄漏了。
   * 如果是内存泄漏，可查看泄漏对象到GC Roots的引用链，了解泄漏对象是通过什么路径与GC Roots相关联导致无法回收的。
   * 如果是内存溢出，在配置上调大堆内存，在代码上检查对象的生命周期是否过长，持有状态时间过长。



#### 虚拟机栈与本地方法栈溢出

在Hotspot中不区分这两个，栈容量由-Xss来指定。

* StackOverflowError：当栈深度大于虚拟机所允许的最大深度

  1. 重现：减少栈帧容量-Xss，构建死循环或者死递归；或者定义大量的局部变量。

     java.lang.StackOverflowError

  2. 加大栈帧或者检查代码

* OutOfMemoryError:   当虚拟机在拓展栈时无法申请到足够的内的空间

  1. 重现：增大栈帧容量-Xss，不断创建线程。

     java.lang.OutOfMemoryError: unable to create new native thread

  2. 减小栈帧或者减少堆内存，使得栈空间足够。



#### 方法区与运行时常量池

由于JDK1.7开始，字符串常量池在堆中，所以使用string.intern试图增大字符串常量池是不能导致方法区或者运行时常量池OOM的。方法区容量指定 -XX: PermSize=10M -XX: MaxPermSize=10M

1. 重现：运行时产生大量类去填满方法区，可通过jdk proxy或者cglib产生动态类。

   java.lang.OutOfMemoryError: PermGen space

   > 像spring，hibernate对类进行增强时都会使用到CGlib操作字节码生成动态类，另外基于JVM的动态语言，比如Groovy通常会持续地创建类来实现语言的动态性，还有大量JSP或者动态产生JSP的引用。（JSP会在运行时编译为Servlet），以及基于OSGI的应用，同一个类文件，被不同的加载器加载也视为不同的类。
   >
   > 一个类要被垃圾收集器回收掉，判定条件比较苛刻，在经常动态生成大量Class的引用，需要特别注意类的回收状况。

2. 检查类加载情况，类加载数量等。



#### 直接内存溢出

直接内存容量由-XX: MaxDirectMemorySize指定，如果没有指定，则默认与堆最大值(-Xmx)一致.

1. 重现：通过DirectByteBuffer分配内存或者通过Unsafe单例对象的allocateMemory()来分配。

   java.lang.OutOfMemory:
