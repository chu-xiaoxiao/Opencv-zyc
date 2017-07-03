package com.zyc.opencv;

import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class TiQuback {
	public double tiqu(Mat temp,Mat temp2){
	    temp.convertTo(temp, CvType.CV_32S);
	    int size = (int) (temp.total() * temp.channels());
	    int[] temp11 = new int[size];
	    
	    
	    temp2.convertTo(temp2, CvType.CV_32S);
	    int size2 = (int) (temp2.total() * temp2.channels());
	    int[] temp21 = new int[size2];
	    
	    double sum = 0;
	    
	for(int i=Math.min(temp.rows(), temp2.rows());i>0;i--){
			for(int j=Math.min(temp.cols(), temp2.cols());j>0;j--){
				   System.out.print(temp.get(i, j, temp11)+"\t");
				   System.out.println(temp2.get(i, j, temp21));
				   sum+=Math.pow(temp.get(i, j, temp11)-temp2.get(i, j, temp21),2);
			}
		}
		System.out.println(sum);
		return 1;
	}
}
