    package com.eddie.testspring.algorithm;

    /**
     * @author Eddie.Liao
     * @description: TODO
     * @date 2021/10/30 12:01 上午
     */
    public class test2 {// 本题为考试多行输入输出规范示例，无需提交，不计分。

        public static void main(String[] args) {


            int count = 8;
            int[] a = new int[6];
            a[0] = 3;
            a[1] = 1;
            a[2] = 5;
            a[3] = 7;
            a[4] = 9;
//            a[5] = 9;
            int temp = 0;
            for(int i = 0;i<a.length;i++){
                for(int j = i+1;j<a.length;j++){
                    if(a[i] > count){
                        temp++;
                    }else if(a[i]+a[j] == count){
                        temp++;
                    }
                }
            }
            System.out.println(temp);
        }

    }
