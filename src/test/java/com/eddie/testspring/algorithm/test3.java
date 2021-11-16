    package com.eddie.testspring.algorithm;

    import java.util.ArrayList;

    /**
     * @author Eddie.Liao
     * @description: TODO
     * @date 2021/10/30 12:01 上午
     */
    public class test3 {// 本题为考试多行输入输出规范示例，无需提交，不计分。

        public void main(String[] args) {

            int num = 3;

            int count = 7;
            ArrayList a = new ArrayList();
            a.add(3);
            a.add(4);
            a.add(7);
            int temp = 0;
            int total = 0;
            total +=this.backTracking(a, 1,count);

        }

        public int backTracking(ArrayList a,  int level,int count ){
            level ++;
            if(level >=a.size() ){
                return level;
            }

            for (int i = 0;i<a.size();i++) {
                if((Integer)a.get(i) + (Integer)a.get(i + 1) >count){
                    this.backTracking(a, level,count);
                }
            }
            return level;
        }

    }
