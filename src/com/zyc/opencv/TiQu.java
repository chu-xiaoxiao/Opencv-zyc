package com.zyc.opencv;

import org.opencv.core.Mat;

public class TiQu {
	public double tiqu(Mat temp,String temp2){
		double sum =0.0;
		System.out.println("识别中。。。");
		System.out.println("========================");
		String string = temp.dump().replace("[","");
		string = string.replaceAll("]","");
		String[]  strs = string.split(",");
		double[] d = new double[128];
		int i=0;
		for(String s:strs){
			if(!s.equals("")){
				d[i]=Double.parseDouble(s);
				i++;
			}
		}
		String string1 = temp2.replace("[", "");
		string1 = string1.replaceAll("]","");
		String[]  strs1 = string1.split(",");
		double[] d1 = new double[128];
		int i1=0;
		for(String s:strs1){
			if(!s.equals("")){
				d1[i1]=Double.parseDouble(s);
				i1++;
			}
		}
		
		for(int j=0;j<128;j++){
			System.out.println(d[j]+"\t"+d1[j]);
			sum+=Math.sqrt(Math.abs(Math.pow(d[j], 2)-Math.pow(d1[j], 2)));
		}
		System.out.println("计算结果:"+sum);
		System.out.println("========================");
		return sum;
	}
}
