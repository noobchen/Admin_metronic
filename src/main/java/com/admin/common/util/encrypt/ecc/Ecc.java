package com.admin.common.util.encrypt.ecc;



import com.admin.common.util.math.MathUtils;
import com.admin.common.util.string.StringUtils;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: wy
 * Date: 12-8-10
 * Time: 上午11:52
 * To change this template use File | Settings | File Templates.
 */
public class Ecc implements Serializable {
    public E e;//椭圆曲线
    public Pare pare;//椭圆上的已知点
    public long privateKey;//7位速度变慢            私钥--随机
    public Pare publicKey;//公钥

    public Ecc() {
        super();
        Random rand = new Random();
        this.e = new E(BigInteger.probablePrime(20, rand).intValue(),rand.nextInt(40),rand.nextInt(40));
        this.privateKey = rand.nextInt(160);//7位速度变慢,私钥--随机
        this.pare = new Pare(rand.nextInt(10000000),rand.nextInt(10000000));
        this.publicKey = this.pare.multiply(privateKey);
    }

    public class E implements Serializable{// 表示椭圆曲线方程
        Long p;//模p的椭圆群
        Long a;
        Long b;

        public E(long p, long a, long b) {
            super();
            this.p = p;
            this.a = a;
            this.b = b;
        }

        public Long getP() {
            return p;
        }

        public void setP(Long p) {
            this.p = p;
        }

        public Long getA() {
            return a;
        }

        public void setA(Long a) {
            this.a = a;
        }

        public Long getB() {
            return b;
        }

        public void setB(Long b) {
            this.b = b;
        }
    }

    public class Message implements Serializable{//传送消息的最小单元
        Pare pa;
        Pare pb;

        public Message(Pare pa, Pare pb) {
            super();
            this.pa = pa;
            this.pb = pb;
        }

        public Pare getPa() {
            return pa;
        }

        public void setPa(Pare pa) {
            this.pa = pa;
        }

        public Pare getPb() {
            return pb;
        }

        public void setPb(Pare pb) {
            this.pb = pb;
        }

        public String toString() {
            return this.pa.toString() + " " + this.pb.toString();
        }
    }

    public class Pare implements Serializable{//椭圆曲线上的点(x,y)
        long x;
        long y;

        public Pare() {
            super();
        }

        public Pare(long x, long y) {
            super();

            this.x = x;
            this.y = y;
        }

        public long getX() {
            return x;
        }

        public void setX(long x) {
            this.x = x;
        }

        public long getY() {
            return y;
        }

        public void setY(long y) {
            this.y = y;
        }

        //加法
        public Pare add(Pare pare) {
            if (this.x == Integer.MAX_VALUE) {//为无穷大时O+P=P
                return pare;
            }
            Pare res = new Pare();
            if (this.y == pare.y && this.x == pare.x) {//相等时
                long d = moddivision(3 * this.x * this.x + e.a, e.p, 2 * this.y);

                res.x = d * d - 2 * this.x;
                res.x = mod(res.x, e.p);

                res.y = d * (this.x - res.x) - this.y;
                res.y = mod(res.y, e.p);
            } else if (pare.x - this.x != 0) {
                long d = moddivision(pare.y - this.y, e.p, pare.x - this.x);
                res.x = d * d - this.x - pare.x;
                res.x = mod(res.x, e.p);

                res.y = d * (this.x - res.x) - this.y;
                res.y = mod(res.y, e.p);
            } else {//P Q互逆,返回无穷大
                res.x = Integer.MAX_VALUE;
                res.y = Integer.MAX_VALUE;
            }

            return res;
        }

        //减法
        public Pare less(Pare p) {
            p.y *= -1;
            return add(p);
        }

        //乘法
        public Pare multiply(long num) {
            Pare p = new Pare(this.x, this.y);
            for (long i = 1; i < num; i++) {
                p = p.add(this);
            }
            return p;
        }

        //求余,解决负号问题
        public long mod(long a, long b) {
            a = a % b;
            while (a < 0) {
                a += b;
            }
            return a;
        }

        public long moddivision(long a, long b, long c) {
            a = mod(a, b);
            c = mod(c, b);
            a = a * MathUtils.exgcd(c, b);
            return mod(a, b);
        }

        public String toString() {
            return StringUtils.obox(StringUtils.long2hexStr(this.x), 4) + " " + StringUtils.obox(StringUtils.long2hexStr(this.y), 4);
        }
    }

    //加密
    public Message encryption(Pare g, Pare pbk, Pare word) {
        pbk = g.multiply(privateKey);//公钥
        int d = new Random().nextInt(1024);//随机数
        Pare dg = g.multiply(d);
        Pare dp = pbk.multiply(d);
        Pare send = word.add(dp);
        return new Message(dg, send);
    }

    public String encryption(Pare g, Pare pbk, String word) {
        StringBuffer sb = new StringBuffer();
        Pare[] words = Str2Pares(word);
        for (int i = 0; i < words.length; i++) {
            sb.append(encryption(g, pbk, words[i]).toString());
            sb.append(" ");
        }
        return sb.toString();
    }

    public String encryption(String word) {
        StringBuffer sb = new StringBuffer();
        Pare[] words = Str2Pares(word);
        for (int i = 0; i < words.length; i++) {
            sb.append(encryption(this.pare, this.publicKey, words[i]).toString());
            sb.append(" ");
        }
        return sb.toString();
    }

    //解密
    public Pare decryption(Message m) {
        Pare pab = m.pa.multiply(this.privateKey);
        Pare result = m.pb.less(pab);
        return result;
    }

    public String decryption(String s) {
        StringBuffer sb = new StringBuffer();
        Message[] mes = hexStr2Messages(s);
        for (int i = 0; i < mes.length; i++) {
            sb.append(decryption(mes[i]).toString());
        }
        return StringUtils.hexStr2Str(sb.toString().replace(" ", ""));
    }

    public static void print(Object o) {
        System.out.println(o);
    }

    //将字符串转换为   值对
    public Pare[] Str2Pares(String string) {

        Pare[] pares;
        if (string.length() % 2 != 0)
            pares = new Pare[string.length() / 2 + 1];
        else
            pares = new Pare[string.length() / 2];
        char[] chars = string.toCharArray();
        int i = 0;
        for (i = 0; i < string.length() / 2; i++) {
            pares[i] = new Pare(chars[i * 2], chars[i * 2 + 1]);
        }
        if (string.length() % 2 != 0)
            pares[i] = new Pare(chars[i * 2], 0);
        return pares;
    }

    //将值对转换成16进制字符串
    public String Pares2hexStr(Pare[] pares) {
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < pares.length; i++) {
            s.append(pares[i].toString());
        }
        return s.toString();
    }

    //将16进制字符串转为     消息串
    public Message[] hexStr2Messages(String s) {
        String[] ss = s.split(" ");
        Message[] mes = new Message[ss.length / 4];
        for (int i = 0; i < mes.length; i++) {
            long pax = StringUtils.hexStr2long(ss[i * 4]);
            long pay = StringUtils.hexStr2long(ss[i * 4 + 1]);
            long pbx = StringUtils.hexStr2long(ss[i * 4 + 2]);
            long pby = StringUtils.hexStr2long(ss[i * 4 + 3]);
            mes[i] = new Message(new Pare(pax, pay), new Pare(pbx, pby));
        }
        return mes;
    }

    //将消息串转为16进制字符串
    public String Messages2hexStr(Message[] mes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mes.length; i++) {
            sb.append(mes[i].toString());
            sb.append(" ");
        }
        return sb.toString();
    }
}
