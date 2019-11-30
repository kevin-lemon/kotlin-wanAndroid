package com.lemon.wanandroid.Utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
//dimens适配生成器
public class GenerateDimen {

    public static void genDimen() {

        StringBuilder swdef = new StringBuilder();
        PrintWriter out;

        try {

            swdef.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "<resources>");

            double value;
            for (int i = 0; i < 300; i++) {
                //这里控制对应转换的值，如果是标准尺寸就一对一转换
                value = (i + 1) * 1;
                value = Math.round(value * 100) / 100;
                swdef.append("<dimen name=\"mdp" + (i + 1) + "\">" + -value + "dp</dimen>\r\n");
            }
            double value1;
            for (int i = 0; i < 50; i++) {
                //这里控制对应转换的值，如果是标准尺寸就一对一转换
                value1 = (i + 1) * 1;
                value1 = Math.round(value1 * 100) / 100;
                swdef.append("<dimen name=\"sp" + (i + 1) + "\">" + value1 + "sp</dimen>\r\n");
            }
            double value2;
            for (int i = 0; i < 500; i++) {
                //这里控制对应转换的值，如果是标准尺寸就一对一转换
                value2 = (i + 1) * 1;
                value2 = Math.round(value2 * 100) / 100;
                swdef.append("<dimen name=\"dp" + (i + 1) + "\">" + value2 + "dp</dimen>\r\n");
            }
            swdef.append("</resources>");

            //这里是文件名，1 注意修改 sw 后面的值，和转换值一一对应  2 文件夹和文件要先创建好否则要代码创建
            String filedef = "./app/src/main/res/values/dimens.xml";


            out = new PrintWriter(new BufferedWriter(new FileWriter(filedef)));

            out.println(swdef.toString());


            out.close();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

        }

    }


    public static void main(String[] args) {

        genDimen();

    }

}