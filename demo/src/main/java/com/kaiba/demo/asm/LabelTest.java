package com.kaiba.demo.asm;

/**
 * Created by luliru on 2017/7/25.
 */
public class LabelTest {

    static int inc(){
        int x;
        try {
            x = 1;
            return x;
        } catch (Exception e){
            x = 2;
            return x;
        } finally {
            x = 3;
        }
    }


//    static int inc();
//    descriptor: ()I
//            flags: ACC_STATIC
//            Code:
//            stack=1, locals=4, args_size=0
//            0: iconst_1
//            1: istore_0
//            2: iload_0
//            3: istore_1
//            4: iconst_3
//            5: istore_0
//            6: iload_1
//            7: ireturn
//            8: astore_1
//            9: iconst_2
//            10: istore_0
//            11: iload_0
//            12: istore_2
//            13: iconst_3
//            14: istore_0
//            15: iload_2
//            16: ireturn
//            17: astore_3
//            18: iconst_3
//            19: istore_0
//            20: aload_3
//            21: athrow
//            Exception table:
//            from    to  target type
//            0     4     8   Class java/lang/Exception
//            0     4    17   any
//            8    13    17   any
//            LineNumberTable:
//            line 11: 0
//            line 12: 2
//            line 17: 4
//            line 12: 6
//            line 13: 8
//            line 14: 9
//            line 15: 11
//            line 17: 13
//            line 15: 15
//            line 17: 17
//            LocalVariableTable:
//            Start  Length  Slot  Name   Signature
//            2       6     0     x   I
//            9       8     1     e   Ljava/lang/Exception;
//            11       6     0     x   I
//            20       2     0     x   I
//            StackMapTable: number_of_entries = 2
//            frame_type = 72 /* same_locals_1_stack_item */
//            stack = [ class java/lang/Exception ]
//        frame_type = 72 /* same_locals_1_stack_item */
//        stack = [ class java/lang/Throwable ]



    public int testThrowEx(int i) {
        if(i == 0) {
            throw new RuntimeException();
        }
        return i;
    }

//    stack=2, locals=2, args_size=2
//            0: iload_1
//         1: ifne          12
//            4: new           #3                  // class java/lang/RuntimeException
//            7: dup
//         8: invokespecial #4                  // Method java/lang/RuntimeException."<init>":()V
//            11: athrow
//        12: iload_1
//        13: ireturn
//    LineNumberTable:
//    line 80: 0
//    line 81: 4
//    line 83: 12
//    LocalVariableTable:
//    Start  Length  Slot  Name   Signature
//            0      14     0  this   Lcom/kaiba/demo/asm/LabelTest;
//            0      14     1     i   I

    public int testCatchEx(int i) {
        try {
            testThrowEx(i);
        }catch(RuntimeException e){
            i++;
        }catch(Exception e){
            i--;
        }
        return i;
    }

//    stack=2, locals=3, args_size=2
//            0: aload_0
//         1: iload_1
//         2: invokevirtual #5                  // Method testThrowEx:(I)I
//            5: pop
//         6: goto          20
//                 9: astore_2
//        10: iinc          1, 1
//            13: goto          20
//            16: astore_2
//        17: iinc          1, -1
//            20: iload_1
//        21: ireturn
//    Exception table:
//    from    to  target type
//             0     6     9   Class java/lang/RuntimeException
//             0     6    16   Class java/lang/Exception
//    LineNumberTable:
//    line 88: 0
//    line 93: 6
//    line 89: 9
//    line 90: 10
//    line 93: 13
//    line 91: 16
//    line 92: 17
//    line 94: 20
//    LocalVariableTable:
//    Start  Length  Slot  Name   Signature
//           10       3     2     e   Ljava/lang/RuntimeException;
//           17       3     2     e   Ljava/lang/Exception;
//            0      22     0  this   Lcom/kaiba/demo/asm/LabelTest;
//            0      22     1     i   I


    public int testCatchEx2(int i) {
        try {
            testThrowEx(i);
        }catch(RuntimeException e){
            i++;
        }catch(Exception e){
            i--;
        }finally {
            i++;
        }
        return i;
    }

//    stack=2, locals=4, args_size=2
//            0: aload_0
//         1: iload_1
//         2: invokevirtual #5                  // Method testThrowEx:(I)I
//            5: pop
//         6: iinc          1, 1
//            9: goto          38
//            12: astore_2
//        13: iinc          1, 1
//            16: iinc          1, 1
//            19: goto          38
//            22: astore_2
//        23: iinc          1, -1
//            26: iinc          1, 1
//            29: goto          38
//            32: astore_3
//        33: iinc          1, 1
//            36: aload_3
//        37: athrow
//        38: iload_1
//        39: ireturn
//    Exception table:
//    from    to  target type
//             0     6    12   Class java/lang/RuntimeException
//             0     6    22   Class java/lang/Exception
//             0     6    32   any
//            12    16    32   any
//            22    26    32   any
//    LineNumberTable:
//    line 99: 0
//    line 105: 6
//    line 106: 9
//    line 100: 12
//    line 101: 13
//    line 105: 16
//    line 106: 19
//    line 102: 22
//    line 103: 23
//    line 105: 26
//    line 106: 29
//    line 105: 32
//    line 107: 38
//    LocalVariableTable:
//    Start  Length  Slot  Name   Signature
//           13       3     2     e   Ljava/lang/RuntimeException;
//           23       3     2     e   Ljava/lang/Exception;
//            0      40     0  this   Lcom/kaiba/demo/asm/LabelTest;
//            0      40     1     i   I



    public static void main(String[] args){
        System.out.println(inc());
    }
}



