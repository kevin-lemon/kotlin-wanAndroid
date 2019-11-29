package com.example.hepan.adaptation;

/**
 * Created by hepan on 2018/5/25.
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

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
            for (int i = 0; i < 500; i++) {
                //这里控制对应转换的值，如果是标准尺寸就一对一转换
                value = (i + 1) * 1;
                value = Math.round(value * 100) / 100;
                swdef.append("<dimen name=\"dp" + (i + 1) + "\">" + value + "dp</dimen>\r\n");
            }
            swdef.append("</resources>");

            //这里是文件名，1 注意修改 sw 后面的值，和转换值一一对应  2 文件夹和文件要先创建好否则要代码创建
            String filedef = "./app/src/main/res/values-sw380dp/dimens.xml";


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