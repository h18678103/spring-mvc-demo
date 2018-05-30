import java.util.Base64;

/**
 * @author huqinsong
 * @date 2018/5/28
 */
public enum Calculator {
    //加法运算
    ADD("+"){
        public int exec(int a,int b){
            return a+b;
        }
    },
    //减法运算
    SUB("-"){
        public int exec(int a,int b){
            return a - b;
        }
    };
    String value = "";
    //定义成员值类型
    Calculator(String _value){
        this.value = _value;
    }
    //获得枚举成员的值
    public String getValue(){
        return this.value;
    }
    //声明一个抽象函数
    public abstract int exec(int a,int b);

    public int exec2(int a,int b){
        if ("+".equals(this.value)){
            return a+b;
        }else if ("-".equals(this.value)){
            return a-b;
        }
        return a +b;
    }

    public static void main(String[] args) {
        int exec = Calculator.ADD.exec2(1, 2);
        System.out.println(exec);
        exec = Calculator.SUB.exec2(1, 2);
        System.out.println(exec);

        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decode = decoder.decode("YToyOntzOjU6ImludHJvIjtzOjM6Im5ldyI7czoxODoic2VhcmNoX2VuY29kZV90aW1lIjtpOjEzOTU4ODY5OTU7fQ==");
        System.out.println(new String(decode));

        String s = "asdfsd的歌诗图安尔碘“；";
        byte[] encode = Base64.getEncoder().encode(s.getBytes());
        System.out.println(new String(encode));
    }
}
