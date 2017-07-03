package com.zyc.opencv;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.Core;
import org.opencv.core.Rect;
import org.opencv.core.Size;

public class CaiJian {

	public void caiJian() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat image = Highgui.imread(getClass().getResource("1.jpg").getPath().substring(1));

		Rect rect = new Rect(new Point(10,10), new Point(40, 40));

		Mat roi_img = new Mat(image, rect);
		Mat tmp_img = new Mat();

		roi_img.copyTo(tmp_img);
		Highgui.imwrite("sucess1.jpg", roi_img);
		roi_img.eye(200, 200, roi_img.type());
		Highgui.imwrite("sucess1200*200.jpg", roi_img);
		// Highgui.imwrite(filename + i + "_" + j + ".jpg", tmp_img);
	}
	
	public Mat caiJian(Mat image,Rect rect) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);


		Mat roi_img = new Mat(image, rect);
		Mat tmp_img = new Mat();
		
		roi_img.copyTo(tmp_img);
		 Size dsize = new Size(200,200);
		//roi_img.create(200, 200, roi_img.type());
		Imgproc.resize(roi_img, roi_img,dsize );
		return roi_img;
		
		// Highgui.imwrite(filename + i + "_" + j + ".jpg", tmp_img);
	}
}
