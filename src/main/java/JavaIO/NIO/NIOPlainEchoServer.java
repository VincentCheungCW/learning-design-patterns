package JavaIO.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 多路复用的、同步非堵塞
 * 通道与流的不同之处在于：通道是双向的，既可以从通道中读取数据，也可以写数据到通道，
 * 而流的读写通常是单向的，它只是在一个方向上移动.
 *
 * FileChannel
 * FileChannel 从文件中读取数据，也可以将数据写到文件中，FileChannel无法设置非堵塞模式，它总是运行在堵塞模式下，

 DatagramChannel
 DatagramChannel通过UDP读写网络中的数据

 SocketChannel
 SocketChannel  通过TCP读写网络中的数据

 ServerSocketChannel
 可以监听新进来的TCP连接，像web服务器那样，堆每一个新进来的连接都会创建一个SocketChannel

 多路复用器 Selector
 Selector 是Java NIO实现多路复用的基础，简单的讲，Selector 会不断地轮询注册在其上的 Channel，如果某个Channel 上面发生读或者写事件，这个Channel 就处于就绪状态，会被Selector轮询出来，然后通过 SelectionKey 可以获取就绪 Channel 的集合，进行后续的 I/O 操作。
 这样，一个单独的线程可以管理多个 Channel ，从而管理多个网络连接，跟 I/O多路复用模型思想一样。


 */
public class NIOPlainEchoServer {

    public void serve(int port) throws IOException {
        System.out.println("Listening for connections on port " + port);
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        ServerSocket ss = serverChannel.socket();
        InetSocketAddress address = new InetSocketAddress(port);
        //将ServerSocket绑定到指定的端口里
        ss.bind(address);
        serverChannel.configureBlocking(false);
        Selector selector = Selector.open();
        //将channel注册到Selector里，并说明让Selector关注的点，这里是关注建立连接这个事件
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            try {
                //阻塞等待就绪的Channel，即没有与客户端建立连接前就一直轮询
                selector.select();
            } catch (IOException ex) {
                ex.printStackTrace();
                //代码省略的部分是结合业务，正确处理异常的逻辑
                break;
            }
            //获取到Selector里所有就绪的SelectedKey实例,每将一个channel注册到一个selector就会产生一个SelectedKey
            Set<SelectionKey> readyKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = readyKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                //将就绪的SelectedKey从Selector中移除，因为马上就要去处理它，防止重复执行
                iterator.remove();
                try {
                    //若SelectedKey处于Acceptable状态
                    if (key.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) key
                                .channel();
                        //接受客户端的连接
                        SocketChannel client = server.accept();
                        System.out.println("Accepted connection from " + client);
                        client.configureBlocking(false);
                        //像selector注册socketchannel，主要关注读写，并传入一个ByteBuffer实例供读写缓存
                        client.register(selector, SelectionKey.OP_WRITE |
                                SelectionKey.OP_READ, ByteBuffer.allocate(100));
                    }
                    //若SelectedKey处于可读状态
                    if (key.isReadable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer output = (ByteBuffer) key.attachment();
                        //从channel里读取数据存入到ByteBuffer里面
                        client.read(output);
                    }
                    //若SelectedKey处于可写状态
                    if (key.isWritable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer output = (ByteBuffer) key.attachment();
                        output.flip();
                        //将ByteBuffer里的数据写入到channel里
                        client.write(output);
                        output.compact();
                    }
                } catch (IOException ex) {
                    key.cancel();
                    try {
                        key.channel().close();
                    } catch (IOException cex) {
                    }
                }
            }
        }
    }
}