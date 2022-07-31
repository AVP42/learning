# 应用层

### 1. HTTP 协议

[HTTP](https://httpwg.org/specs/)，HyperText Transfer Protocol 超文本传输层协议

* HTTP 协议版本

  * HTTP 1.0 

    * [标准] RFC [1945](https://datatracker.ietf.org/doc/html/rfc1945)
    * 缺点：
      * 默认情况下，无法复用连接。可以指定非标准的General头部Connection: keep-alive来请求复用。

  * HTTP 1.1

    * 标准：最新为2014年发布的RFC [7230](https://datatracker.ietf.org/doc/rfc7230/)-7237

    * 特性: [Google 开发者文档](https://developers.google.com/web/fundamentals/performance/http2/)

      * 默认支持==**持久连接**(persistent connection)==，不需要使用Connection:keep-alive字段了。同一个域名，大多数浏览器允许建立6个持久连接。
      * 支持==**管道**(pipeline)==,  同一个TCP连接中发送多次请求，服务器按照顺序执行并返回。客户端可根据Content-Length头字段知道响应的长度
      * 支持==**分块传输编码**==(Chunked transfer encoding), 在未知响应的长度的情况下，采用流模式，每读取到一块数据，就发送一块，而不是缓存到最后再发送。通过Transfer-Encoding: chunked 开启。每个chunked数据块有一个16进制的数字表明该块的长度，最后一块为0
      * 支持请求到同一台服务器上的不同域名的网站(适应虚拟主机的兴起)。通过==**Host头字段**==来实现。
      * 支持请求部分资源。通过==**Range头字段**==实现。
      * 支持更多的请求方法HEAD, PUT, PATCH, DELETE, OPTIONS, TRACE
      * 支持更多的错误响应码，比如414为URL过长， 服务器无法处理，410为资源已经被永久删除。
      * 更加灵活的缓存处理，比如Entity-tag, If-Unmodified-Since, If-Match, If-None-Match 等可供选择的缓存头来控制缓存策略。
      
    * 缺点：
    
      * 允许复用连接，且支持管道，但是数据通信是按照次序的，可能因为前面的比较忙，导致整体够很慢，造成“队头堵塞”（Head-of-line blocking）
    
        > 为了避免这个问题，只有两种方法：一是减少请求数，二是同时多开持久连接。这导致了很多的网页优化技巧，比如合并脚本和样式表、将图片嵌入CSS代码、域名分片（domain sharding）等等。如果HTTP协议设计得更好一些，这些额外的工作是可以避免的。
  
* [HTTP 2](https://http2.github.io/)
  
  * 标准: 最新为2015年发布的RFC [7540](https://www.rfc-editor.org/rfc/rfc7540.html)-7541
  
  * 特性：
  
    前身是Google的SPDY协议
  
      * 是一个**二进制协议**，头信息和数据体都是二进制的，称为“帧”(Frame)，对应头信息帧(Header）和数据体帧(Data), 还可以定义其他额外的帧。
  
        > HTTP/1.1 版的头信息肯定是文本（ASCII编码），数据体可以是文本，也可以是二进制。
        >
        > 如果使用文本实现这种功能，解析数据将会变得非常麻烦，二进制解析则方便得多
  
    ![image-20211121220925619](images/image-20211121220925619.png)
  
    ![image-20211121225022201](images/image-20211121225022201.png)
  
    ![image-20211121224602345](images/image-20211121224602345.png)
  
    ![image-20211121224619140](images/image-20211121224619140.png)
  
    * 多工复用(Multiplexing)--**-双向实时通讯**
  
      客户端和服务器均可同时发送多条请求，且不必按照顺序一一对应，避免“队头堵塞”
  
      ![image-20211121225455959](images/image-20211121225455959.png)
  
    * **数据流(Streaming)** 
  
      * 一个请求对应的所有数据包，形成数据流。每个数据流都有独有的数据流ID。
  
        * 客户端的数据流id为奇数。服务器的数据流id为偶数。
  
      * 支持取消数据流，通过发送信号(RST_STREAM帧)。*HTTP 1.1只能通过关闭TCP连接来取消数据传输。*
      
    * **支持设置数据流的优先级**
    
  
  ![image-20211121225044210](images/image-20211121225044210.png)
  
  * **头信息压缩(Header compression)**
    
    由于HTTP是无状态的，所以每次请求都附带上所有的上下文信息，因此，请求中的很多字段都是重复的，比如Cookie和User-Agent, 即浪费带宽，也影响速度。
    
    头信息压缩机制**一方面会在发送前对头信息使用gzip或者compress压缩；**另一方面，**客户端和服务器均维护一张信息表，所有字段都会存入该表**，生成索引号。以后就只发送索引号。
    
    > 使用HPACK压缩算法，用**Huffman算法**对标头字段进行编码。
    >
    > [静态**索引表**](https://httpwg.org/specs/rfc7541.html#static.table.definition)，标记常见的header的内存了，index是固定的。动态**索引表**，初始为空，在特定的连接内交换进行更新。
    
    
  
  ![image-20211121225253289](images/image-20211121225253289.png)
  
  ![image-20211121230545671](images/image-20211121230545671.png)
  
  ![image-20211121230724063](images/image-20211121230724063.png)
  
  * **服务器推送(Server Push)**
    
    允许服务器未经请求，主动向客户端发送资源。比如服务器端可以提前将后续可能会请求的静态资源直接发送给客户端，减少了请求数。
    
      ![image-20211121225433080](images/image-20211121225433080.png)




* HTTP 消息结构

  ![image-20211121173256570](images/image-20211121173256570.png)

* 头部

  ![image-20211121221016009](images/image-20211121221016009.png)

  ![image-20211121221100541](images/image-20211121221100541.png)

  * 通用头部：客户端和服务端都可以使用的头部

    ![image-20211120203257705](images/image-20211120203257705.png)

  * 请求头部：

    ![image-20211121224059502](images/image-20211121224059502.png)

  * 响应头部：

    ![image-20211121224149114](images/image-20211121224149114.png)

  * 实体头部：

    ![image-20211121224207510](images/image-20211121224207510.png)



* HTTP [请求方法](https://www.rfc-editor.org/rfc/rfc7231.html#page-24)

  * 概述：

    * HTTP 标准请求方法并不是针对具体资源的，不像[REST](https://roy.gbiv.com/pubs/dissertation/top.htm)(Replisentational State Transfer)表述性状态转移

  * 方法列表

    > Y:  yes    N: no   C: conditinally  M: may

    | 方法                                                   | 描述                                                         | 安全 | 幂等 | 可缓存 | 请求实体 | 响应实体 | 功能语义                                                     |
    | ------------------------------------------------------ | ------------------------------------------------------------ | ---- | ---- | ------ | -------- | -------- | ------------------------------------------------------------ |
    | GET                                                    | Transfer a current representation of the target resource。   | Y    | Y    | Y      | N        | Y        | 获取目标资源的首选方法，关注性能的优化。                     |
    | HEAD                                                   | Same as GET, but only transfer the status line and header section | Y    | Y    | Y      | N        | N        | 类似GET，除了服务器不能返回响应实体                          |
    | POST                                                   | Perform resource-specific processing on the request payload  | N    | N    | C      | Y        | Y        | 基于请求实体的数据进行资源处理。比如提交表单数据进行处理；新建资源。 |
    | PUT                                                    | Replace all current representations of the target resource with the request payload. | N    | Y    | N      | Y        | N        | 使用请求实体的数据替换现有资源,不存在则新建。                |
    | DELETE                                                 | Remove all current representations of the target resource    | N    | Y    | N      | M        | M        | 删除现有资源                                                 |
    | CONNECT                                                | Establish a tunnel to the server identified by the target resource | N    | N    | N      | N        | Y        | 创建隧道。客户端与代理服务器握手之后发送connect请求到目标服务器，建立隧道。 |
    | OPTIONS                                                | Describe the communication options for the target resource   | Y    | Y    | N      | N        | Y        | 获取目的资源所支持的通信选项<br />客户端可以对特定的 URL 使用 OPTIONS 方法，也可以对整站（通过将 URL 设置为“*”）使用该方法 |
    | TRACE                                                  | Perform a message loop-back test along the path to the target resource. | Y    | Y    | N      | N        | Y        | 实现沿通向目标资源的路径的消息环回（loop-back）测试          |
    | [PATCH](https://datatracker.ietf.org/doc/html/rfc5789) | A set of changes applied to representations of the target resource | N    | N    | C      | Y        | N        | 部分修改资源                                                 |

    > Safe: 安全，是指方法语义上是只读的，不会改变server的状态。
    >
    > Idempotent: 幂等，是指一次请求造成对server资源影响与多次相同请求是一样的。safe方法是只读的，所以一定是幂等的。
    >
    > Cacheable: 可缓存的，是指响应可以被缓存起来。POST, PATCH如果指定了Context_Locationt头部以及刷新策略（比如Expires和Cache-Control请求头），则可以支持Cacheable；但是大多数实现只支持GET和HEAD。

    > 上述方法中请求实体为N，仅仅表示没有明确的语义，携带的request payload可能会被忽略，甚至拒绝。
    >
    > 在HTTP 响应码中，针对不同的请求方法，根据请求的结果有不同的响应码，比如GET请求特定的响应码才会触发到缓存的生效。

* 请求方法其他说明

  * [PUT 请求](https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Methods/PUT)

    * 响应码：200-OK  201-Created   204-No Content

  * DELETE请求

    * 响应码：200-OK  201-Created   204-No Content

  * OPTIONS 请求：

    * 时机：

      * 有复杂请求
        * 简单请求：
          * 请求方法是GET、HEAD或者POST
          * 请求方法是POST时，Content-Type必须是application/x-www-form-urlencoded, multipart/form-data或着text/plain中的一个值。
          * 请求头中没有自定义header
      * 发生跨域

    * 用途

      * 检查服务器或者资源所支持的HTTP method，通过响应头部的Allow来识别

        ```shell
        curl -X OPTIONS http://example.org -i
        
        HTTP/1.1 200 OK
        Allow: OPTIONS, GET, HEAD, POST
        Cache-Control: max-age=604800
        Date: Thu, 13 Oct 2016 11:45:00 GMT
        Expires: Thu, 20 Oct 2016 11:45:00 GMT
        Server: EOS (lax004/2813)
        x-ec-custom-error: 1
        Content-Length: 0
        ```

      * [CORS](https://developer.mozilla.org/zh-CN/docs/Web/HTTP/CORS) 跨源资源共享的预检请求

        ```shell
        OPTIONS /resources/post-here/ HTTP/1.1
        Host: bar.other
        Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
        Accept-Language: en-us,en;q=0.5
        Accept-Encoding: gzip,deflate
        Accept-Charset: ISO-8859-1,utf-8;q=0.7,*;q=0.7
        Connection: keep-alive
        Origin: http://foo.example
        Access-Control-Request-Method: POST  // 允许跨域的请求方法
        Access-Control-Request-Headers: X-PINGOTHER, Content-Type // 允许跨域的请求头
        ```



* GET与POST的区别 [^1]
  * [**语义**]GET请求表示获取资源；POST表示基于请求实体的数据进行资源处理(主要是新增，追加等操作)

    * GET请求在HTTP0.9就已经存在了，POST在HTTP1.0引入。

  * [**安全**]GET是安全的，不会改变server数据；POST是不安全的。

  * [**幂等**]GET请求是幂等的；POST不幂等的。

  * [**可缓存**]GET是可缓存的; POST默认不可缓存，除非手动设置。

  * [**参数位置**]GET请求参数放在URL上；POST通过request body传输请求参数

    > GET请求也可以有request Body，但是没有明确的语义，elasticSearch 的api就有使用到。
    >
    > 旧规范要求服务器忽略GET请求携带的RequestBody，但是2014年新规范不再要求。所以有些服务器可能可以解析，有些则不能，甚至可能拒绝

    * [内容类型]URI上的参数只能使用ASCII码，而请求实体上可以指定很多媒体类型
    * [编码]URI上的参数仅仅支持URL编码，而请求实体上的编码则可以自定义
    * [参数安全]URL上的参数会被浏览器显示和缓存(比如保存在历史记录中)，更加容易造成CSRF(cross-site request forgery跨站请求伪造)[^2]
    * [参数长度]URI上的参数长度通常会收到限制，[不同的实现不一样, Chrome browser为2M](http://net-informations.com/q/mis/len.html)，而请求实体上不会。



### HTTPS

HTTPS 即HTTP over SSL/TLS。

SSL (Secure Socket Layer) 安全套接层，TLS(Transport Layer Security Protocol) 传输层安全协议。

* **TSL 1.2** 

  * 标准：[RFC5246](https://datatracker.ietf.org/doc/html/rfc5246#section-7.4.9)  [RFC6066](https://datatracker.ietf.org/doc/html/rfc6066)  [RFC5077](https://datatracker.ietf.org/doc/rfc5077/)
  * 作用：
    * 身份认证 **Authentication**
      * 通过数字证书进行身份认证，防止中间人攻击
    * 数据私密性  **Encryption**
      * 通过对称密钥进行数据加密，密钥只有客户端和服务端有，防止别人窥探
    * 数据完整性 **Integrity**
      * 通过摘要算法对报文计算，接受方可以校验摘要值，防止数据被篡改
  * 组成
    * Record Layer
    * Handshake Protocol

* HTTPS 建立连接过程

  主要分为以下阶段：

  1. 协商加密算法

  2. 服务器鉴别

  3. 会话密钥计算

     对于Client，Client将之前握手的消息进行Hash生成**摘要**， 基于**随机数A**使用**协商的算法**生成**会话密钥**,  使用会话密钥加密的摘要就是Finished中的data。

     ​				并利用Server传过来的RSA public key 进行加密产生预备-主密钥(**premaster Key**)。

     对于Server,   Server先使用私钥解密**premaster key**得到随机数A，同样使用**协商的算法**生成**会话密钥**，使用会话秘钥验证data。

     > 双方除了推导出相同的session Key，还会推导出HMAC key，用于保护数据完整性。

     client在收到server的信息后，也会对摘要信息进行确认。

  4. 安全数据完整传输

     完整性依赖协商的算法套件中的消息摘要算法(比如SHA256)来生成MAC(message authentication code)

     ![image-20211122233735041](images/image-20211122233735041.png)

  数据流转如下：

  【HTTP over TLS 1.2】

  ![img](images/165d13841574fac9~tplv-t2oaga2asx-watermark.awebp)

  【HTTP 2 over TLS 1.2】

  ![image-20211122212651162](images/image-20211122212651162.png)

  ```sequence
  Client-Server: Client Hello \n (包含TLS版本，加密套件,压缩算法等)
  Server->Client: Server Hello \n (包含选用的加密算法)
  Server->Client: Certificate、Certificate Status、Server Key Exchange、Server Hello Done \n(数字证书、证书状态、额外参数、结束)
  Client->Client: 利用CA机构公开发布\n的RSA公钥验证证书
  Client->Server: Client Key Exchange、Change Cipher Spec、Finished\n(pre-master-key、摘要密文)
  Server->Client: New Session Ticket、Change Cipher Spec, Finished\n(session ticket、摘要密文)
  Client->Server: Application Data\n(使用会话密钥加密的数据，并使用摘要算法校验完整性)
  ```

* **Client Hello** 

  Client 连接server的第一条消息。包括支持的TLS版本，加密算法，压缩算法等

  ![image-20211123233612076](images/image-20211123233612076.png)

  ![image-20211123233636039](images/image-20211123233636039.png)

* **Server Hello**

  Server 返回选用的加密算法等

  ![image-20211123233704011](images/image-20211123233704011.png)

* **Certificate**

  返回Server的证书链Certificate Chain

  ![image-20211123233929385](images/image-20211123233929385.png)

* **Certificate Status**

  Client 为了避免下载CRL(证书注销列表)或者通过OCSP(Online Certificate Status Protocol) 实时查询证书带来的往返开销，要求server直接带上。但是server可以拒绝返回。

  > 只有Client Hello的extension中包含status_request才会发送该消息给Client

  [拓展阅读: CRL(Certificate Revocation List) 证书注销列表 与 OCSP(Online)](https://www.cfca.com.cn/20150811/101230759.html)

* **Server Key Exchange**

  如果Certificate 消息无法承载所有能够让客户端交换premaster secrete的数据，就会发送该消息。

  该消息包含Diffie-Hellman密钥协商协议的参数(比如D-H public key)等，有了这些参数，客户端才能完成Key exchange。

  > 该消息只有指定的算法才需要发送，比如DHE_RSA； 而RSA, DH-RSA不需要。

  ![image-20211123234039944](images/image-20211123234039944.png)

* **Server Hello Done**

  该消息表示Server Hello的结束，server将会进入等待client的状态。

  Client端收到该消息之后，开始验证数字证书。

* **Client Key Exchange**

  Client产生**随机数A**，并利用Server传过来的RSA public key 进行加密产生**预备-主密钥(premaster Key)**。该消息主要携带的就是

  > 如果商定的算法是DHE_RSA，则使用D-H 参数中的public key

  ![image-20211123234220920](images/image-20211123234220920.png)

* **New Session Ticket**  *RFC 5077*

  TLS的拓展功能，session ticket 包含了有状态的会话信息。

  如果Client Hello中的extension中包含session_ticket，Server就会创建ticket发送给Client.

  ![image-20211123234253995](images/image-20211123234253995.png)

* **Change Cipher Spec**

  表示加密策略的发生了改变，后续的内容都是使用商定的**会话密钥**来通信。

* **Finished**

  用于接收方可以识别Change Cipher Spec消息已经完成。这个消息是第一个使用会话密钥加密的消息。

  Finished中的data是Client或者Server将之前握手的消息先生成摘要，然后使用会话密钥加密的内容。

  > wireshark 有些版本显示为 Encrypted Handshake，是一个消息。

  

* HTTP涉及的加密技术
  * 对称加密
  * 非对称加密
  * 摘要算法
  * 数据证书

## 其他面试题

* **怎么知道 HTTP 的报文长度**

  当响应消息中存在 Content-Length 字段时，我们可以直接根据这个值来判断数据是否接收完成，例如客户端向服务器请求一个静态页面或者一张图片时，服务器能够很清楚的知道请求内容的大小，因此可以通过消息首部字段 Content- Length 来告诉客户端需要接收多少数据，但是如果服务器预先不知道请求内容的大小，例如加载动态页面的时候，就需要使用 Transfer-Encoding: chunked 的方式来代替 Content-Length。

  分块传输编码（Chunked transfer encoding）是 HTTP/1.1 中引入的一种数据传输机制，其允许 HTTP 由服务器发送给客户端的数据可以分成多个部分，当数据分解成一系列数据块发送时，服务器就可以发送数据而不需要预先知道发送内容的总大小，每一个分块包含十六进制的长度值和数据，最后一个分块长度值为0，表示实体结束，客户机可以以此为标志确认数据已经接收完毕


* **GET 的长度限制是多少**

  HTTP 中的 GET 方法是通过 URL 传递数据的，而 URL 本身并没有对数据的长度进行限制，真正限制 GET 长度的是浏览器，例如 IE 浏览器对 URL 的最大限制为 2000多个字符，大概 2KB左右，像 Chrome, FireFox 等浏览器能支持的 URL 字符数更多，其中 FireFox 中 URL 最大长度限制为 65536 个字符，Chrome 浏览器中 URL 最大长度限制为 8182 个字符。并且这个长度不是只针对数据部分，而是针对整个 URL 而言，在这之中，不同的服务器同样影响 URL 的最大长度限制。因此对于特定的浏览器，GET的长度限制不同。

  由于 POST 方法请求参数在请求主体中，理论上讲，post 方法是没有大小限制的，而真正起限制作用的是服务器处理程序的处理能力。
  
* **为什么需要CA认证的证书/ 客户端为什么信任第三方证书**

  为了防止中间人攻击。

  对于客户端，通过全世界都信任的机构，客户端拿到证书之后就会校验是否合法；

  即时中间人拿着一个合法的证书拦截客户端的请求，由于中间人只有公钥，没有私钥，所以无法解密客户端的premaster secret，无法拿到那个**秘密的随机数**。

  ```sequence
  客户端->中间人:1.请求被中间人拦截
  中间人->客户端:2.返回中间人的证书
  客户端->中间人:3.客户端向中间人发送数据,利用客户端和中间人协商的会话密钥
  中间人->中间人:4.中间人解密客户端数据
  中间人->服务器:5.中间人利用解密数据冒充客户端与服务器建立连接
  服务器->中间人:6.服务器向中间人发送数据，利用服务器与中间人协商的会话密钥
  中间人->中间人:7:中间人解密服务器数据
  中间人->客户端:8:返回被篡改的数据给客户端。
  
  ```



* **客户端(浏览器)如何校验证书的合法性**

  * 证书内容包含：颁发机构，**公钥**，域名，有效期，公司信息，指纹等

    ![image-20211123221551915](images/image-20211123221551915.png)

    ![image-20211123221605918](images/image-20211123221605918.png)

    ![image-20211123221618311](images/image-20211123221618311.png)

    ![image-20211123221633623](images/image-20211123221633623.png)

  * 证书合法的依据：认证机构要是权威的，是大家都认可的

  * [浏览器如何验证证书的合法性](https://www.zhihu.com/question/37370216)：

    * 验证证书的**来源**，即证书是否有系统或者浏览器"受信任的根证书颁发机构"颁发。
    
    * 验证证书**内容**，比如域名是否匹配，有效期等信息是否正确
    
    * 验证证书是否**[被篡改](https://www.zhihu.com/question/34873227/answer/518086565)**
    
      操作系统和浏览器都会在本地存储权威机构的**根证书**，浏览器利用根证书上的公钥进行解密，对比hash值。
    
      > 如果客户端收到的证书是多级的，比如下面的情况。
      >
      > 浏览器中没有安装SHA-256-G2中间证书，就需要向CA机构请求该中间证书。
      >
      > 利用Root CA的公钥解密中间证书，验证中间证书。
      >
      > 利用SHA-256-G2的公钥解密baidu.com证书，验证baidu.com证书。
      >
      > ![image-20211127215312947](images/image-20211127215312947.png)
    
    * 验证证书是否**被吊销**，通过下载CRL(Certificate Revocation List)证书吊销列表或者使用OCSP(Online Certificate Status Protocol)在线证书状态协议进行查询(可以减少与CA服务器的交互)

* **只有认证机构可以生成证书吗**

  如果不是认证机构签发的证书，浏览器会提示不安全。

  但是不会限制网站不能访问，像fiddle就是通过让浏览器信任它的证书，从而来代理请求的。

  以前的12306需要我们手动安装证书来实现HTTPS访问。

* **本地随机数被窃取了怎么办**

  HTTPS的证书验证是由非对称加密来完成的，但是后续的数据传输是采用对称加密的，其中客户独当生成的随机数是关键，这个随机数是保存在本地的。

  HTTPS可以保证传输过程即时被拿到了密封，由于没有私钥无法解密，随机数不会因此丢失。

  但是客户端本地的安全，不是HTTPS来保证的，是另外一个安全范畴，应对的措施有安装杀毒软件，反木马，修复漏洞等。

* **HTTPS  是如何被抓包的**

  HTTPS是加密的，所以一般情况下，抓包工具拿到的是加密后的数据，无法直接查看。

  * 方式1：构建中间人网络

    但是浏览器只会提醒安全风险，如果用户授权，依然可以访问。

  我们可以组建中间人网络，抓包工具就是中间人的代理，像fiddler作为一个中间人，安装证书到系统或者浏览器，我们就相当于信任这个证书，那么中间人就可以实现了。

  * 方式2：获取随机数生成相同的密钥。比如，wireshark
    * 方法1：利用服务器的SSL证书私钥，导入wireshark，wireshark解密premaster srecet得到随机数，生成会话密钥。这种方式适合在服务器端使用wireshark。
    * 方法2：由于随机数就是在本地，在premaster secret log 文件中有，我们可以在wireshark指定log文件的位置(SSLKEYLOGFILE环境变量指向的文件夹)，通过读取就可以生成会话密钥。

  虽然HTTPS还是被抓包，这种情况下是我们授权的，风险是一致的。其他情况，则要采用应用级的安全防护，例如采用私有的对称加密，同时做好移动端的防反编译加固，防止本地算法被破解。

* **GET 的长度限制是多少**

  主要是URL的限制，header没有限制。

  [chrome](https://chromium.googlesource.com/chromium/src/+/refs/heads/main/docs/security/url_display_guidelines/url_display_guidelines.md#URL-Length)是2M；[IE](https://support.microsoft.com/en-us/topic/maximum-url-length-is-2-083-characters-in-internet-explorer-174e7c8a-6666-f4e0-6fd6-908b53c12246)是2048 characters

  ![image-20211124202653582](images/image-20211124202653582.png)

  ![image-20211124203333509](images/image-20211124203333509.png)

* **HTTP 与 HTTPs 区别**

  HTTP：超文本传输协议，使用TCP作为运输层协议，工作在80端口，通过安全套接字(socket)访问tcp，采用明文传输。性能好但安全性差。

  HTTPs: HTTP over SSL, 在TCP之上使用传输层安全协议TLS来完成**身份认证，安全传输，数据完整性校验**，工作在443端口，采用密文传输。性能下降但安全性好。

* **HTTPS 的加密方式**

  采用非对称加密协商秘密数，之后双方都通过秘密数生成会话密钥，进行对称加密传输数据。

  为了保证非对称加密不受到中间人攻击，采用了证书来进行身份认证。

* **长连接与短连接分别用在哪些场景**

  * 长连接：操作频繁，客户端较少的点对点通讯，比如即时通讯，网络游戏
  * 短连接：客户端很多的情况，比如web网站

* **HTTP是无状态协议，如何保存用户状态**

  * 对于单机应用，基于Session实现会话保存

  * 对于分布式应用，使用中间件保存有状态数据，比如redis

  * 基于Cookie实现会话保持，服务端在响应头中设置Set-Cookie字段，用来保存客户端信息。客户端会根据不同的生命周期创建不同的Cookie，从而实现状态保存。

    此方式相当于是在浏览器保存会话状态，虽然能减轻服务器压力，但是不安全，而且每次都要携带大量数据，占用带宽。

  * 使用URL参数来实现会话保持，如果cookie被禁用可以考虑这种方式，反证要么是浏览器保存，传递到服务器，就是服务器保存，生成索引给到浏览器。

* HTTP状态码

  | 分类 | 分类描述                                     |
  | ---- | -------------------------------------------- |
  | 1xx  | 指示信息--表示请求正在处理                   |
  | 2xx  | 成功--表示请求已经被处理完毕                 |
  | 3xx  | 重定向---表示请求需要额外的操作              |
  | 4xx  | 客户端错误--请求有语法错误，或者请求无法实现 |
  | 5xx  | 服务器错误---服务器处理请求出现错误          |

  常见：

  * 301和302的区别

    301--永久移动。浏览器会自动定向到返回的新URL，并且后续的请求都应使用新的URL。

    302--临时移动。浏览器会跳转到新的URL，但是后续请求应继续使用URI，因为资源只是临时移动。

  ![image-20211124212514535](images/image-20211124212514535.png)

* HTTP 1.0 与 HTTP1.1的区别

  [跳到该位置](###1. HTTP 协议)

* HTTP 1.x 与 HTTP2的区别

  * ==二进制传输==  header 和 body 均转成帧，一个请求的所有帧构成数据流，数据流具有id和优先级，数据流与数据流之间具有依赖关系。
  * ==多路复用== 客户端和服务端都可以同时发送和接收数据，实现全双工通信，**缓解**“队头堵塞”问题，通过流id来识别是哪个请求的数据。
  * ==头部压缩==  对header进行压缩，而且客户端和服务器协商维护静态索引表和动态索引表，减少header数据量的占比。
  * ==服务器推送==，服务器可在未经请求的情况向客户端推送数据，客户端可在退出服务时发送复位帧来取消服务端的推送。

* **TLS 1.2 与TLS 1.3的区别**

* **HTTP 3的特性**

  [HTTP/3: Performance Improvements](https://www.smashingmagazine.com/2021/08/http3-performance-improvements-part2/)

  [如何看待HTTP3](https://www.zhihu.com/question/302412059)

  ![image-20211125213020005](images/image-20211125213020005.png)

  ![image-20211125213746217](images/image-20211125213746217.png)

  > HTTP3的挑战在于全球大部分手机，电脑终端都是使用私有ip，都需要使用NAT设备来完成私有IP与全球IP的转换。NAT设备会记忆用户的通信状态，一旦完成了通信，NAT设备会释放这些记忆。
  >
  > 对于面向连接的TCP的HTTP，HTTPS传输，NAT设备可以根据TCP报头上的SYN/FIN状态位，知道连接的开会与结束。
  >
  > 但是对于基于UDP的HTTP/3，NAT虽然知道连接什么时候开始，但是无法知道流量什么时候结束。
  >
  > 一种解决方式是在QUIC的头部可以模仿TCP的SYN/FIN位，但是这需要升级全球的NAT设备的软件。
  >
  > 还可以让QUIC周期性发送keepAlive信息，刷新NAT设备的记忆。

  * **HTTP 2存在的问题** 

    * 基于TCP传输协议

      * **队头阻塞**：HTTP2 多路复用的副作用，多个请求复用一个连接，如果TCP丢包了，就会阻塞TCP连接中的所有请求。

        > 由于TCP是**字节流协议**，TCP层必须保证接收到的数据是完整且有序的。如果序号较低的分组在传输中丢失了，即时序列号高的分组已经接收到了，由于无法解析，会被视为阻塞。
        >
        > 如果丢包比较多，性能将比HTTP1.1还要差，毕竟至多可以有6个连接。

        ![image-20211125213136342](images/image-20211125213136342.png)

      * **TCP需要3次握手，如果需要使用TLS，则要需要增加握手次数**。

        > RTT Round Trip Time 往返时间  如果客户端与服务器比较近，RTT<10ms, 美国到中国差不多200ms以上。
        >
        > TCP + TLS 需要3个RTT，如下图

        ![image-20211125212843374](images/image-20211125212843374.png)

        

      * **网络迁移需要重新连接**：TCP连接是由四元组(src ip, src port, dest ip, dest port)确定的，如果ip或者port发生了变化，就需要TCP和TLS重新握手，不利于移动设备切换场景，比如手机从4G网络切换到WIFI，出现的卡顿。

      * TCP慢启动: TCP 刚开始启动不会使用全部带宽发送数据(因为可能会使得整条网络过载)

    * 基于HPACK压缩头部：这要求传输过程有序，所以无法彻底解决“队头堵塞”的问题。

      > 因为动态表是由时序的，如果出现了首次的请求出现了丢包，后续的请求无法解码出HPACK头部，因为对方还没有建立好动态表，因此后续的请求会阻塞到首次请求中丢失的数据包重传过来。

  * **QUIC**   

    QUIC(Quick UDP Internet  Connections) 协议，即快速UDP网络连接 

    * 基于UDP的应用层协议

      > UDP 简单不可靠的传输层协议，udp数据报之间时无需的，也没有依赖关系。

    * QUIC集成了TCP的**连接管理**，**拥塞窗口**和流量**控制**的网络特性，使得传输变得可靠，不必担心丢包。

    * 集成了TLS 1.3协议，使得传输变得安全。

    * 提供类似HTTP/2的多路复用功能。

    QUIC如何解决上述问题：

    * **无队头阻塞**：同样采用多路复用，但是对于不同的请求，数据报都有对应的流id，某个流的数据丢包了，只会影响这个请求的数据，其他请求的数据只要被完整读取到就可以读取到数据。也就是说不会因为底层协议的限制而使得stream之间存在依赖。

      ![image-20211125215504107](images/image-20211125215504107.png)

    * **更少的握手RTT**：对于HTTPs来说，TCP和TLS是分成的，牵着属于传输层，后者属于openSSL实现的表示层，难以合并在一起，所以必须先后进行握手。

      QUIC改变了这点，QUIC包含了TLS1.3，在建立连接的过程中，自己的帧中会携带TLS的信息，仅需要**1个RTT**就可以[同时]完成建立连接和密钥协商。甚至在第二次连接的时候，应用数据可以和QUIC握手信息(连接信息和TLS信息)一起发送，实现0RTT的效果。

      ![image-20211125215744528](images/image-20211125215744528.png)

    * **连接迁移**：QUIC没有使用四元组来绑定连接，而是使用**连接ID**，握手的作用就是**确认连接id**。

      客户端和服务器可以各自选择一组ID来标记自己，网络切换之后，只要连接id和TLS密钥等上下文信息一样，就可以无缝复用连接，不会产生卡顿感，实现了连接迁移。

    * 向前纠错，为了减少由于丢包导致的重传次数过多，每个数据包携带除了本身内容外的部分其他数据报的数据，通过牺牲单个包的有效数据量来换取更少的重传次数。

  * **HTTP/3** 

    * **更简单的二进制帧结构**，类似HTTP2, 不过HTTP/3复用QUIC的流，不需要再定义stream，所以结构上也比较简单

      ![image-20211125222442625](images/image-20211125222442625.png)

    * **采用QPACK头部压缩算法**，与HTTP/2中的HPACK类似，也采用了静态表，动态表以及Huffman编码。静态表的表项从HTTP2的61个增加到91个。由于QUIC基于UDP，多个请求的数据流不再有依赖关系，那么QPACK如何来解决动态表的问题呢。QPACK 使用两个特殊的单向流，传输应用数据的时候是双向流。

      * QPACK Encoder Stream，用于将一个字典(key-value)传递给对方，比如不存在于静态表中的header
      * QPACK Decoder Stream, 用于响应给对方，刚才发送的字典已经更新，后续就可以用这个字典来编码了。

      这两个特殊的单向流就是用来同步双方的动态表，编码方收到解码方更新确认之后，才会使用动态表编码HTTP头部。



* **DNS的作用和原理**

  [全面理解DNS及HTTPDNS](https://juejin.cn/post/6844903987796246542)

  Domain Name System, 域名系统，是一种能够将主机名转换为IP地址的目录服务。

  域名空间采用树形层次结构，服务器采用分布式部署。

  1. 根域名服务器：共13个，大部分位于北美洲

  2. 顶级域名服务器：com, org, edu等以及所有国家的顶级域名(比如cn,uk,ca,jp)
  3. 权威DNS服务器：具有公共可访问的组织结构(比如WEB服务器和邮件服务器)，这些域名由这些组织的全文DNS服务器来保存，并且解析为IP地址。

  > 本地DNS服务器，每个ISP(网络服务提供商)，都有自己的本地DNS服务器。
  >
  > 公共DNS是本地DNS服务器的一种特地，是提供给全网的递归DNS服务网。
  >
  > 常见的有阿里云的223.5.5.5，国内的114.114.114.114，google的8.8.8.8
  >
  > 当主机与某个ISP相连的时候，该ISP提供一台主机的IP地址，该主机具有一台或者多态本地DNS服务器的IP地址。
  >
  > 当主机发起请求的时候，请求被发送到本地DNS服务器，继而转发到DNS服务器层次结构中。

  域名解析记录的方式

  * A记录：Address,用来指定域名对应的ip, 可以将多个域名解析到1个地址
  * CNAME记录：Canonical Name, 别名解析。为域名设置别名。
  * MIX记录: Mail Exchange 将域名下的邮件域名指向邮件域名服务器。
  * NS记录：为某个域名指定DNS解析服务器
  * TXT记录：为主机名或者域名设置说明

  域名解析查询的两种方式

  1. 递归：主机向本地域名服务器查询通常采用这种方式，如果在本地DNS服务器找不到，本地DNS就会向根服务器查询，而不是让主机来向根服务器查询。

     > 所以本地DNS服务器也称为递归DNS

  2. 迭代：本地DNS服务器向根域名服务器通常采用这种方式。本地DNS收到上一次请求的服务器的响应之后，得到下一步应该到哪个服务器去查询，直到找到所需要的IP地址。

  ```mermaid
  flowchart LR
  	主机A --1-->  本地DNS服务器
  	本地DNS服务器 <--2--> 根域名服务器
  	本地DNS服务器 <--3--> 顶级域名服务器
  	本地DNS服务器 <--4--> 权威域名服务器1
  	本地DNS服务器 <--5--> 权威域名服务器2
  	本地DNS服务器 --6--> 主机A
  	主机A --7--> 主机B
  ```

  > 1. 主机是如何知道DNS服务器的地址的？
  >
  >    采用DHCP动态获取，或者手工配置。
  >
  >    DHCP （Dynamic Host Configuration Protocol）动态主机配置协议，用于大型的局域网中，集中管理和分配IP地址，使得网络环境中的主机动态获得IP地址，Gateway地址和DNS服务器地址，提高地址的使用率。
  >
  > 2. 智能DNS
  >
  >    智能DNS是全局负载均衡GSLB的一种应用，实现互联网不同地域的服务器间的流量调配，保证用户的请求能被距离最近或者服务质量更好的服务器来处理，保证服务质量。
  >
  >    比如baidu.com的权威域名服务器可能就是一个智能DNS，会根据请求的线路(电信，移动，联通)，地域，服务器负载等选择一个合适的ip地址返回给LocalDNS.

  实操：使用dig来查询

  ```shell
  # DNS lookup utility  获取A记录
  $ dig +trace www.baidu.com
  #  通过8.8.8.8公共DNS查询
  $ dig @8.8.8.8 www.baidu.com +trace
  
  ; <<>> DiG 9.11.3-1ubuntu1.1-Ubuntu <<>> @8.8.8.8 www.baidu.com +trace
  ; (1 server found)
  ;; global options: +cmd
  .			85788	IN	NS	a.root-servers.net.
  .			85788	IN	NS	b.root-servers.net.
  .			85788	IN	NS	c.root-servers.net.
  .			85788	IN	NS	d.root-servers.net.
  .			85788	IN	NS	e.root-servers.net.
  .			85788	IN	NS	f.root-servers.net.
  .			85788	IN	NS	g.root-servers.net.
  .			85788	IN	NS	h.root-servers.net.
  .			85788	IN	NS	i.root-servers.net.
  .			85788	IN	NS	j.root-servers.net.
  .			85788	IN	NS	k.root-servers.net.
  .			85788	IN	NS	l.root-servers.net.
  .			85788	IN	NS	m.root-servers.net.
  .			85788	IN	RRSIG	NS 8 0 518400 20211210050000 20211127040000 14748 . e0yNGuaHzKT2q0dyw6u23Z3zz/yaLQdDGLPNKD8sWVUdLOBJNbPaZvhs Z48a9qsfQVSUUyNP5tlr+02NUUbfIqgFLzyFYadkajoh7e3ujUTt2M2n P1BlSjOma8FcU/kQ/XqB/3io8bGy9l+nr6hiL7rKcJp6oUEKT7TZ8Swq hUOBgPaZRHnOCcGo5JGS3TB48VhHEL9LGANWMoyqI6UuxyI+jkwlF1mr MecSbgLCaUVpXm0qhnpyl8e7Es2nqIueJzgCudvGnmrQjqrXXFnWgweX 6Y96oK6crX3R7l9Yhd+lwEvrIh+ChSLZaqO0bZGzrRoK05DZ0KFmUfi9 626D4g==
  ;; Received 525 bytes from 8.8.8.8#53(8.8.8.8) in 40 ms
  
  com.			172800	IN	NS	a.gtld-servers.net.
  com.			172800	IN	NS	b.gtld-servers.net.
  com.			172800	IN	NS	c.gtld-servers.net.
  com.			172800	IN	NS	d.gtld-servers.net.
  com.			172800	IN	NS	e.gtld-servers.net.
  com.			172800	IN	NS	f.gtld-servers.net.
  com.			172800	IN	NS	g.gtld-servers.net.
  com.			172800	IN	NS	h.gtld-servers.net.
  com.			172800	IN	NS	i.gtld-servers.net.
  com.			172800	IN	NS	j.gtld-servers.net.
  com.			172800	IN	NS	k.gtld-servers.net.
  com.			172800	IN	NS	l.gtld-servers.net.
  com.			172800	IN	NS	m.gtld-servers.net.
  com.			86400	IN	DS	30909 8 2 E2D3C916F6DEEAC73294E8268FB5885044A833FC5459588F4A9184CF C41A5766
  com.			86400	IN	RRSIG	DS 8 1 86400 20211210050000 20211127040000 14748 . g3qXDbFVheLdJcAwfI4hPi73Brk5OcvSr/Ln/jGM5ddHRrbTnn/oRjS6 FulmAas1a8IKPOAD/ZfVcYCo5HKWDh/K1NZOnPOUcR7VqCAcxvvI0E8s KMOxv/tfPLG8dLmVfcQ1GsqXW0KaXRitxyiR+Ml3NSGC4zHdILIkYFto lXqMl2UlSWH8cUrRLumYm/Q60qyzu1goyw0yJMA7kdFqFJ2kIBMBxDYa fidDVS5MHh4vdrQeGA/vT3z2lQYyfgp0HaRCzj/GIucAqyNcUYTdNJpN a0eDUck8MvJmGxznCp20B/V+q/Ojxli0l6Ni5N2tUkQc0tqBm9YOhoNb HEcKng==
  ;; Received 1173 bytes from 198.97.190.53#53(h.root-servers.net) in 179 ms
  
  baidu.com.		172800	IN	NS	ns2.baidu.com.
  baidu.com.		172800	IN	NS	ns3.baidu.com.
  baidu.com.		172800	IN	NS	ns4.baidu.com.
  baidu.com.		172800	IN	NS	ns1.baidu.com.
  baidu.com.		172800	IN	NS	ns7.baidu.com.
  CK0POJMG874LJREF7EFN8430QVIT8BSM.com. 86400 IN NSEC3 1 1 0 - CK0Q1GIN43N1ARRC9OSM6QPQR81H5M9A  NS SOA RRSIG DNSKEY NSEC3PARAM
  CK0POJMG874LJREF7EFN8430QVIT8BSM.com. 86400 IN RRSIG NSEC3 8 2 86400 20211202052333 20211125041333 15549 com. l/dK9oZWkkfDgTCmc1cyZGPS9DTzBOAKFBFtkwXY21/2+MdmQT5gHTy6 qqKdLN4UFUqruT/ll0DPiBpHsLa6d/Vg2oMUFZtRLxXgI69FfTqIfFIY ibmPOwA+NybLS3OhngIB9AIjL6/xYvolM5T7MTNZdcpw8FuiLyDVptbT rYgBpJm26r3AIzDDdop0Cb6I5rdz5HSIHV5atIEzaJvFOQ==
  HPVU6NQB275TGI2CDHPDMVDOJC9LNG86.com. 86400 IN NSEC3 1 1 0 - HPVV8SARM2LDLRBTVC5EP1CUB1EF7LOP  NS DS RRSIG
  HPVU6NQB275TGI2CDHPDMVDOJC9LNG86.com. 86400 IN RRSIG NSEC3 8 2 86400 20211203053218 20211126042218 15549 com. UZJwdnNU/viL4XRBmTph1AeFuDRuhXVWZ0P1CwXStmPcpAP3cFJD4cK6 cFTDeRSTNo0kic0nGfI8HpTxki6g1d9pvE7XmcNmJCKG7YjiKfdqOUgE 4BYpX1Jn/iF3sxdJfkMozHOmTyW8tl2Gwis5zU66fv6t5TAJqQT8hb6X zgldnHf3phBi1MAlDgnv4R7SZ+/dGe9K/tSIT+4tLy8tFw==
  ;; Received 761 bytes from 192.41.162.30#53(l.gtld-servers.net) in 176 ms
  
  www.baidu.com.		1200	IN	CNAME	www.a.shifen.com.
  a.shifen.com.		1200	IN	NS	ns4.a.shifen.com.
  a.shifen.com.		1200	IN	NS	ns5.a.shifen.com.
  a.shifen.com.		1200	IN	NS	ns2.a.shifen.com.
  a.shifen.com.		1200	IN	NS	ns3.a.shifen.com.
  a.shifen.com.		1200	IN	NS	ns1.a.shifen.com.
  ;; Received 239 bytes from 14.215.178.80#53(ns4.baidu.com) in 43 ms
  
  
  ```

  ```shell
  #可以看到www.baidu.com CNAME到www.a.shifen.com,然后我们再查询www.a.shifen.com
  $ dig @8.8.8.8 www.a.shifen.com +trace
  ....
  www.a.shifen.com.	300	IN	A	110.242.68.3
  www.a.shifen.com.	300	IN	A	110.242.68.4
  a.shifen.com.		1200	IN	NS	ns1.a.shifen.com.
  a.shifen.com.		1200	IN	NS	ns2.a.shifen.com.
  a.shifen.com.		1200	IN	NS	ns3.a.shifen.com.
  a.shifen.com.		1200	IN	NS	ns4.a.shifen.com.
  a.shifen.com.		1200	IN	NS	ns5.a.shifen.com.
  ;; Received 275 bytes from 112.80.255.253#53(ns3.a.shifen.com) in 24 ms
  
  ```

  ```shell
  # 简单查询DNS
  $ nslookup ww.baidu.com
  
  Server:		127.0.0.53
  Address:	127.0.0.53#53
  
  Non-authoritative answer:
  www.baidu.com	canonical name = www.a.shifen.com.
  Name:	www.a.shifen.com
  Address: 110.242.68.3
  Name:	www.a.shifen.com
  Address: 110.242.68.4
  
  ```

  ```shell
  # 更简单的查询DNS
  $ host www.baidu.com
  www.baidu.com is an alias for www.a.shifen.com.
  www.a.shifen.com has address 110.242.68.3
  www.a.shifen.com has address 110.242.68.4
  ```

  

* **DNS传输层使用的是什么协议**。

  DNS是引用层协议，采用53端口。

  解析域名的时候(客户端向DNS服务器查询域名)使用UDP，因为返回的内容一般不会超过UDP报文的最大长度(512字节)，而且不需要TCP3次握手，4次挥手，降低资源使用，提高响应速度，需要域名解析器(客户端)和域名服务器执行处理超时与重传，来保证可靠性。

  当主域名服务器中的域名信息发生变化，需要进行区域传送(向辅助域名服务器传送变化的数据)的时候，使用TCP协议，因为同步的数据量一般会比一次请求/应答的数据量要大，TCP允许的报文长度更长，为了保证数据正确，使用TCP进行传输。

* **什么是DNS劫持**

  ![image-20211127125017139](images/image-20211127125017139.png)

  ![image-20211127125053159](images/image-20211127125053159.png)

  

  根据劫持的位置不同，可以分为以下几种

  * 客户端DNS劫持：

    * 比如通过木马病毒入侵PC，篡改DNS配置(hosts文件，DNS服务器地址，DNS缓存等)
    * 比如通过路由器漏洞或者破击路由器管理帐号入侵路由器，篡改DNS配置
    * 企业代理设备，针对企业内部场景做DNS劫持

  * DNS解析路径劫持：在客户端与DNS服务器网络通信时，拦截请求，返回虚假的解析记录。

    * 比如入侵DNS服务器，监听DNS的53端口进行拦截

  * 篡改DNS权威记录：黑客通过非法入侵DNS权威记录管理帐号，直接修改DNS记录行为。

    ​	比如利用以下步骤：

    ① 获取要劫持的域名信息：攻击者首先会访问域名查询站点查询要劫持的域名信息。

    ② 控制域名相应的 E-MAIL 账号：在获取到域名信息后，攻击者通过暴力破解或者专门的方法破解公司注册域名时使用的 E-mail 账号所对应的密码。更高级的攻击者甚至能够直接对 E-mail 进行信息窃取。

    ③ 修改注册信息：当攻击者破解了 E-MAIL 后，会利用相关的更改功能修改该域名的注册信息，包括域名拥有者信息，DNS 服务器信息等。

    ④ 使用 E-MAIL 收发确认函：在修改完注册信息后，攻击者在 E-mail 真正拥有者之前收到修改域名注册信息的相关确认信息，并回复确认修改文件，待网络公司恢复已成功修改信件后，攻击者便成功完成 DNS 劫持。	




  如何处理DNS劫持：

  *  对于个人：
    * 可以通过配置host文件指定ip进行访问，
    * 可以指定公共的，正常的域名服务器进行解析，比如首选google的8.8.8.8或者国内的114.114.114.114
    * 如果DNS被污染了，可以采用VPN或者域名远程解析，越过缓存了错误记录的服务器

  * 对于服务提供方：需要支持监控DNS是否被劫持；使用DNS over TLS 或者 DNS over HTTPs加密技术对主机到递归服务器之间DNS劫持的影响

    * HTTPDNS：使用HTTP与DNS服务器进行交互，代替传统的基于UDP的DNS协议。请求直接发送到HTTPDNS服务器，绕过了运营商的LocalDNS

      ![image-20211127143819256](images/image-20211127143819256.png)

  

  

  

* Socket 套接字有哪些

  * Socket是应用进程之间双向通信的端点的抽象。包括应用进程id，源端口，目标端口，源ip，目标ip，简称5元组。
  * 套接字主要有以下类型：
    * 流套接字SOCKET_STREAM：基于TCP协议，用于面向连接，可靠的数据传输服务。底层的TCP保证了数据无差错，无重复，并按照顺序接收，应用进行不需要进行相应的处理了。
    * 数据报套接字SOCKET_DGRAM：基于UDP协议，用于无连接的UDP服务应用，不保证数据传输的可靠性，也无法保证对端能够按照顺序接收到数据。底层UDP协议不会处理超时，丢包等问题，需要应用程序处理。
    * 原始套接字SOCKET_RAW: 流套接字与数据报套接字只能读取TCP和UDP协议的数据，如果需要传输非传输层数据包(比如ping命令发出的网络层ICMP协议数据包) 或者遇到操作系统无法处理的数据报，就需要建立原始套接字来发送

* URI 与 URL的区别

  * 【定义】
    * URI: Uniform Resource Idenfier 统一资源标识符，用于标识一个资源，允许用户对资源进行操作
    * URL: Uniform Resource Locator  统一资源定位符，用于找到一个互联网资源
      * 格式：[协议类型]://[凭证信息]@[服务器地址]:[端口号]/[资源层级UNIX文件路径] [文件名] ?[查询]#[片段ID]
      * 实例：
        * file:///home/username/RomeoAndJuliet.pdf   
        *  http://username:password@192.168.1.101/novels/love-topic?name=RomeoAndJuliet.pef#chapter_one  
    * URN: Uniform Resource Name 统一资源名称，在命名空间中使用名称来标识一个资源
      * 格式：[命名空间]:[命名空间的内部编码]
      * 实例：urn: ISBN 0-486-27557-4 《罗密欧与朱丽叶》的某个特定的版本
  *  【联系】
    * URI是一个**抽象的概念**。URL和URN都是一种URI的**表现形式**，一个资源可以同时有URL和URN。
    * URN没有流行起来，所以大部分URI都以URL形式呈现。
  * 【通俗】URI定义一个资源，URL包含了如何去操作这个资源的信息，URN则定义了一个资源的在某个命名空间下的唯一名称。

* fidder，charlse 抓包原理

  * fidder 通过中间人代理，对于https，提供代理的证书让系统或者浏览器信任
  * charlse 通过网卡流量，对于https，通过服务器的私钥或者客户端的秘密数(随机数)Client_Random 生成同样的会话密钥。

* 如果访问一个网站很慢，怎么排查与解决

  思路：根据请求的链路进行排查

  * 客户端排查：网速是否正常，带宽是否被占用，cpu，内存，磁盘等资源情况。
  * 链路排查：
    * 通过ping命令查看服务器RTT时间和丢包(好机房不超过1%)情况，RTT是否够小，够稳定(不稳定，说明路由不稳定)。对比其他网站的情况。
    * 如果同一个地域时快时慢，甚至打不开，可以怀疑空间不稳定，更换双线空间或者多线空间
    * 如果不同地域打开的速度不一样，可能是网络线路的问题，比如电信线路访问到了使用联通线路的服务器。
    * DNS是否耗时过长
  * 服务器排查：服务器资源情况，带宽，cpu，内存，磁盘，连接数等等。
  * 程序实现排查：
    * 网页设计问题
    * 接口实现问题
    * 静态资源优化问题：使用CDN等等
    * 等等

* 应用层其他协议：

  * FTP:  File Transfer Protocol 文件传输协议，数据端口20(主动方)，控制端口21

    * FTP (标准)：在网络上进行文件传输的一套标准协议，使用C/S模式，基于TCP，提供交互式访问，双向传输。
    * TFTP(Trival File Transfer Protocol) 简单文件传输协议，使用C/S模式，基于UDP，不支持交互，没有列目录，不能进行身份验证

  * SMTP：端口25

    Simple Mail Transfer Protocol 简单邮件传输协议，在网络上进行邮件收发的一套标准协议。基于TCP

  * DHCP: 

    Dynamic Host Configuration Protocol 动态主机配置协议，是一个局域网的网络协议。用于局域网内自动分配ip地址给用户，也用于管理员对所有电脑的中央管理。

  * SNMP 

    Simple Network Management Protocol 简单网络管理协议，支持网络管理，用于监听网络上的设备。

* **DHCP 动态主机配置协议**

  连接到互联网的计算机的协议软件需要配置以下参数：

  * 主机IP
  * 子网掩码
  * 默认路由器IP
  * DNS服务器IP

  互联网普遍使用DHCP来实现即插即用。

  DHCP使用客户端(68端口)-服务器(67端口)方式，采用请求-应答机制工作。基于UDP。

  ![image-20211127194603439](images/image-20211127194603439.png)

  ![image-20211127195606114](images/image-20211127195606114.png)

  ```sequence
  主机A->DHCP服务器:【广播】DHCPDISCOVERY
  DHCP服务器->主机A: DHCPOFFER
  主机A->DHCP服务器: DHCPREQUEST
  DHCP服务器->主机A: DHCPACK
  ```

  ![image-20211127173506421](images/image-20211127173506421.png)

  ![image-20211127174707622](images/image-20211127174707622.png)

  工作流程：

   1. [广播] 主机A发送DHCPDISCOVERY报文

      src--0.0.0.0 ,  dest：255.255.255.255 , 

       源MAC地址：主机A的MAC地址，目标MAC地址：FF:FF:FF:FF:FF:FF

      > 主机A的报文会先到交换机上，根据目标MAC，交换机会广播到所有的交换机端口。
      >
      > 局域网上，二层广播MAC地址为FF:FF:FF:FF:FF:FF， 三层广播目标IP地址为255:255:255:255

  2. DHCP服务器响应DHCPOFFER报文，包含申请都的IP，子网掩码，默认路由的IP，DNS服务器地址。

     src--DHCP的IP，  dest:主机A申请到的IP

     源MAC地址：DHCP服务器的MAC地址，目标MAC地址：主机A的MAC地址

     先在数据库中查找，如果没有找到，则在IP地址池中取一个地址分配给主机。

     > 通常路由器就是DHCP的服务器。
     >
     > 交换机根据MAC，找到主机A的端口，转发给A。
     >
     > 如果网络中有多个DHCP服务器，则客户端会收到多个DHCPOFFER

  3. 主机A发送DHCPREQUEST报文

     > 主机A选择一个offer，并向发出该offer的服务器发出请求报文

  4. DHCP服务器响应DHCPACK报文

     > DHCP服务器响应确认报文，主机A收到之后就可以使用临时IP地址了
     >
     > DHCP分配的IP地址具有租用期，客户端根据服务器提供的租用期T，设置0.5T 和 0.85T计时器，用于请求更新租用期

  5. 客户端发送DHCPREQUEST报文更新租用期。
  6. DHCP服务器发送DHCPACK报文返回新的租用期；或者返回DHCPNACK, 告知客户端立即停用原来的IP地址，并重新申请IP地址。
  7. DHCP客户端可以提前终止租用期，通过发送DHCPRELEASE报文，来释放当前ip地址。

* **ARP协议**  -- Address Resovle Protocol 地址解析协议

  ARP协议是网络层协议，ARP分组封装在物理网路的帧中传输。

  互联网通信需要使用到两个地址：

  * IP地址----网络层地址

  * MAC地址----数据链路层地址  6个十六进制

    由各种各样的网络，对应的硬件地址也非常不同，需要抽象出一个中间层，屏蔽硬件地址，这就是IP地址的作用。

    > 广播地址: 每个比特都是1
    >
    > 单播地址: 第一个字节的最低位是0
    >
    > 组播地址: 第一个字节的最低位是1

  ARP协议的作用在于根据IP地址来解析出其MAC地址。

  每个主机都有一个ARP高速缓存(ARP Cache), 维护了局域网上个主机和路由器IP地址-->MAC地址的映射表，每一个表项都有对应的TTL。

  主机在发送IP数据报时，都需要在ARP Cache中找到IP地址对应的硬件地址，将硬件地址写入MAC数据帧中。

  如果ARP Cache中没有对应的表项，ARP进程会在本局域网广播发送一个ARP请求分组，收到响应分组后，将得到的IP地址-->硬件地址映射写入ARP Cache

  ![image-20211127203226045](images/image-20211127203226045.png)

  > 注意映射表中的MAC地址并非就是IP地址所在机器的MAC地址，而是数据帧下一站所要到达的机器的MAC地址。
  >
  > 如果所要找的主机和源主机不在一个局域网上，就会找到一个位于本局域网上的某个路由器MAC地址。
  >
  > 后续的分组将会发送到这个路由器，由这个路由器将分组转发到下一个网络。
  >
  > 路由器不会转发ARP请求。

  request报文：源IP, 源MAC, 目标IP, 目标MAC(000000)

  response报文: 源IP, 源MAC, 目标IP, 目标MAC

  ![img](images/6a3e93691f5047e9f93bd97b2abf11d9_720w.jpg)

  > MAC地址为0是报文上的值，物理帧的MAC地址才是广播地址，所以可以广播。

  * 实操

    ```shell
    # 查看arp表
    arp -a
    ```

    ![image-20211127210640543](images/image-20211127210640543.png)

  

  

* **浏览器地址让输入一个URL之后回车，背后有什么技术步骤和流程**

  [参考1](https://www.cnblogs.com/lepeCoder/p/15147895.html) [参考2](https://www.zhihu.com/question/34873227/answer/518086565)

  该问题有非常多问法 ：

  * 用户输入网址到显示对应页面的全过程

  详细过程：

  * 从协议的角度---然后讲述协议的过程

       1. 通过DHCP协议配置协议软件参数(ip地址，子网掩码，默认路由地址，默认DNS服务器地址)

          > 默认如有地址将会设置到IP路由表中

       2. 通过ARP协议获取默认网关的MAC地址(交换机)

          > 先查询ARP cache

       3. 通过DNS协议获取URL中域名解析后的IP地址

          > 先查询内存的DNS cache，硬盘中的hosts文件

       4. 通过TCP协议，经3次握手建立连接

       5. 通过TLS协议进行身份认证与密钥协商（如果使用HTTPS）

       6. 通过HTTP协议，进行应用数据对称加密传输

          > accept: 从TCP的Accept队列中取出一个连接
          >
          > read：从socket中读取数据，包括等待读就绪与读取数据
          >
          > decode：解码
          >
          > process：逻辑处理
          >
          > endcode：编码
          >
          > send：向socket写入数据，包括等待写就绪和写入数据

  * 从HTTP报文的链路来看

    1. 主机上的浏览器发起
    2. 交换机  NAT 转换
    3. 路由器
    4. 负载均衡LB
    5. 网关
    6. 服务器

  * 如果要包括浏览器渲染的话

    浏览器根据回应开始显示页面，先将html解析成**DOM树**，然后解析CSS文件成**渲染树**，之后开始布局并将渲染树绘制到屏幕中。
    
    
    
    https://cloud.tencent.com/developer/article/1793846
  
  * 数据传输的角度，基于5层模型
  
    * 应用层：
  
      * 浏览器解析URL，封装HTTP请求
      * DNS解析域名获取目标服务器的IP地址
  
    * 传输层：
  
      * 建立TCP连接，进行TLS握手
      * 将应用层报文基于字节流封装成报文段，加上包含5元组等信息的TCPheader，交给下层
  
    * 网络层
  
      * 将TCP报文段加上包含原地址，目标地址等信息的IP协议头，交给下层
      * 利用ARP协议根据ip地址获取MAC地址
  
    * 数据链路层
  
      * 在IP分组上添加包含**MAC地址的帧首部**，和校验用的**帧尾部FCS**
  
    * 物理层
  
      * 将**帧**转化为比特流送到调制解调器，将**数字信号**转化为**模拟信号**，在物理媒体上传输
  
      经过路由器的转发到达服务器
  
      服务器一层层解析，最终将HTTP请求报文交给进程
  
      进程响应后已相同的方式返回给浏览器
  
      浏览器收到响应后解析渲染界面。
  
      完成请求后，TCP断开连接。
  
  
  
  









待整理

https://www.zhihu.com/question/34873227/answer/518086565

TCP 缓冲区 和变量



* 主机，交换机，路由器，

* ip路由表

  route print

* ![image-20211127223627809](images/image-20211127223627809.png)

* NAT 设备  -- 内网与外网是如何通信的





### 附录：

* 参考资料
  * [HTTP github repository](https://httpwg.org/)
  * [HTTP2 github repository](https://github.com/http2)
  * [HTTP datatracker](https://datatracker.ietf.org/doc/rfc7230/)
  * [REST 论文](https://roy.gbiv.com/pubs/dissertation/rest_arch_style.htm#sec_5_2)
  * [HTTP2 -- Google 开发者文档](https://developers.google.com/web/fundamentals/performance/http2/)
  * https://www.ruanyifeng.com/blog/2010/02/url_encoding.html
  * 《HTTP 权威指南》
  * [【强烈推荐】火狐的开发者帮助](https://developer.mozilla.org/zh-TW/docs/Web/HTTP/Methods/POST)
  * [阮一峰-HTTP 协议入门](https://www.ruanyifeng.com/blog/2016/08/http.html)
  * [blog-TLS](https://hpbn.co/transport-layer-security-tls/)
  * [wireshark-tls-handshake](https://www.catchpoint.com/blog/wireshark-tls-handshake)
  * [wireshark-tls-handshake-CSDN](https://blog.csdn.net/yetugeng/article/details/100554653)
  * [博客-HTTPS原理](https://netsecurity.51cto.com/art/201912/607179.htm)
  * [博客-HTTPS原理2](https://juejin.cn/post/6844903679036932104)
  *  [Wireshark解密HTTPS流量的两种方法](https://www.cnblogs.com/yurang/p/11505741.html)
  * [网络代理原理]( https://imququ.com/post/web-proxy.html)

---

[^1]:[RFC 8132](https://www.rfc-editor.org/rfc/rfc8132.html)有提到GET和POST的一些不同点, 还有一个[误区](https://zhuanlan.zhihu.com/p/25028045)是将POST 请求发送时分成两个packet的问题，这仅仅是利用了Expect 请求头和100 continue响应码的关系，不同浏览器和服务器的实现不一样导致的。
[^2]:CSRF(Corss-site request forgery) 跨站请求伪造，利用服务器对浏览器的信任，在已信任的网页中嵌入脚本，比如 <img src="http://www.examplebank.com/withdraw?account=Alice&amount=1000&for=Badman">，一旦点击，服务器认为就是用户的意图。XSS (Cross-site scrpt) 跨站脚本，属于代码注入，利用了浏览器对服务器的信任。比如将一段`<script>alert(“hey!you are attacked”)</script>`植入网页中，比如评论，当别人流浪到这个评论时，浏览器加载之后就会触发，改变里面的js代码就可以获取到你的cookie等信息。
```

```