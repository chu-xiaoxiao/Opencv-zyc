package com.zyc.opencv;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.features2d.*;

public class Sift {
	public static void main(String[] args) {
		Sift sift = new Sift();
		sift.getSift();
	}
	public void getSift() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat test_mat = Highgui.imread("images/1.jpg");
		Mat desc = new Mat();
		FeatureDetector fd = FeatureDetector.create(FeatureDetector.SIFT);
		MatOfKeyPoint mkp = new MatOfKeyPoint();
		fd.detect(test_mat, mkp);
		List<KeyPoint> points = new ArrayList<KeyPoint>();
		points=mkp.toList();
		/*for(KeyPoint temp:points){
			Core.circle(test_mat, temp.pt,4,new Scalar(0, 255, 0));
			System.out.println(temp.pt);
			System.out.println(temp.size);
		}*/
		for(int i = 0;i<mkp.get(0, 0).length;i++){
			System.out.println(mkp.get(0, 0)[i]);
		}
		DescriptorExtractor de = DescriptorExtractor.create(DescriptorExtractor.SIFT);
		de.compute(test_mat, mkp, desc);// 提取sift特征
		Highgui.imwrite("images/sucess_sift.jpg", test_mat);
		
		System.out.println(desc.cols());
		System.out.println(desc.rows());
	}
	public Mat getSift(Mat test_mat) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat desc = new Mat();
		FeatureDetector fd = FeatureDetector.create(FeatureDetector.SIFT);
		MatOfKeyPoint mkp = new MatOfKeyPoint();
		fd.detect(test_mat, mkp);
		/*List<KeyPoint> points = new ArrayList<KeyPoint>();
		points=mkp.toList();
		System.out.println(points.get(1).pt);*/
		/*	    for(KeyPoint temp:points){
			Core.circle(test_mat, temp.pt,4,new Scalar(0, 255, 0));
			System.out.println(temp.pt);
		}*/
		DescriptorExtractor de = DescriptorExtractor.create(DescriptorExtractor.SIFT);
		de.compute(test_mat, new MatOfKeyPoint(new KeyPoint(test_mat.width()/2,test_mat.height()/2,test_mat.width())), desc);// 提取sift特征
		System.out.println(desc.cols());
		System.out.println(desc.rows());
		
		return desc;
	}
}
