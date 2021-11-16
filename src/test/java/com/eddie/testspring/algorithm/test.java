    package com.eddie.testspring.algorithm;

    /**
     * @author Eddie.Liao
     * @description: TODO
     * @date 2021/10/30 12:01 上午
     */
    public class test {// 本题为考试多行输入输出规范示例，无需提交，不计分。

            public static void main(String[] args) {

                int treeNum = 5;
                int deathNum = 3;
                int[] deathArr = new int[deathNum];
                deathArr[0] = 0;
                deathArr[1] = 2;
                deathArr[2] = 4;
                //deathArr[3] = 7;


                int maxLiveTree = 0;
                for(int j = 0;j<deathNum ;j++){
                    int addNum = 1;
                    int tempNum = 0; //临时存活中最大的连续存活的数
                    for(int i = deathArr[j]+1; i <= treeNum; i++){//遍历存死的数

                        boolean isAlive = true;
                        for(int a = 1;a< deathNum ;a++){ //遍历死的数
                            if(i  == deathArr[a]){
                                isAlive = false;
                                break;
                            }
                        }
                        if(isAlive){
                            tempNum ++;
                        }else{
                            if(addNum > 0){

                                addNum--;
                                tempNum++;
                            }else{
                                break;
                            }
                        }


                    }

                    if(tempNum > maxLiveTree){
                        maxLiveTree = tempNum;
                    }
                }

                System.out.println(maxLiveTree);
            }

    }
