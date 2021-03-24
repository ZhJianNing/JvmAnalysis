package com.zjn.number;

/**
 * NumberTest
 *
 * @author zjn
 * @date 2021/3/15
 **/
public class NumberTest {

    public static void main(String[] args) {
/*     3.4 是双精度数，将双精度型（double）赋值给浮点型（float）属于下转型（down-casting，也称为窄化）会造成精度损失，
         因此需要强制类型转换float f =(float)3.4; 或者写成 float f =3.4F;
*/
//        float o = 3.4;//报错
        float a = 3.4F;
        float b = (float) 3.4;

        //对于 short s1 = 1; s1 = s1 + 1;由于 1 是 int 类型，因此 s1+1 运算结果也是 int型，需要强制转换类型才能赋值给 short 型。
        //而 short s1 = 1; s1 += 1;可以正确编译，因为 s1+= 1;相当于 s1 = (short(s1 + 1);其中有隐含的强制类型转换。
        short c = 1;
//        c = c + 1;//报错
        c += 1;
    }
}
