package com.zyc.opencv;

import java.io.File;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

//  
// Detects faces in an image, draws boxes around them, and writes the results  
// to "faceDetection.png".  
//  
public class DetectFaceDemo {
	public void run() {
		CaiJian caiJian = new CaiJian();
		System.out.println("\nRunning DetectFaceDemo");
		System.out.println(getClass().getResource("lbpcascade_frontalface.xml").getPath());
		// Create a face detector from the cascade file in the resources
		// directory.
		// CascadeClassifier faceDetector = new
		// CascadeClassifier(getClass().getResource("lbpcascade_frontalface.xml").getPath());
		// Mat image =
		// Highgui.imread(getClass().getResource("lena.png").getPath());
		// 注意：源程序的路径会多打印一个‘/’，因此总是出现如下错误
		/*
		 * Detected 0 faces Writing faceDetection.png libpng warning: Image
		 * width is zero in IHDR libpng warning: Image height is zero in IHDR
		 * libpng error: Invalid IHDR data
		 */
		// 因此，我们将第一个字符去掉
		String xmlfilePath = getClass().getResource("lbpcascade_frontalface.xml").getPath().substring(1);
		CascadeClassifier faceDetector = new CascadeClassifier(xmlfilePath);
		File file = new File("images/2.jpg");
		Mat image = Highgui.imread(file.getPath());
		// Mat image =
		// Highgui.imread(getClass().getResource("1.jpg").getPath().substring(1));
		// Detect faces in the image.
		// MatOfRect is a special container class for Rect.
		MatOfRect faceDetections = new MatOfRect();
		faceDetector.detectMultiScale(image, faceDetections);

		System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
		// Draw a bounding box around each face.
		for (Rect rect : faceDetections.toArray()) {
			// 显示面部的矩形区域
			// Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x
			// + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
			caiJian.caiJian(image, rect);
		}

		// Save the visualized detection.
		/*
		 * String filename = "faceDetection.png";
		 * System.out.println(String.format("Writing %s", filename));
		 * Highgui.imwrite(filename, image);
		 */
	}

	public Mat run(Mat image) {
		CaiJian caiJian = new CaiJian();
		String xmlfilePath = getClass().getResource("lbpcascade_frontalface.xml").getPath().substring(1);
		//String xmlfilePath = "c:\\lbpcascade_frontalface.xml";
		CascadeClassifier faceDetector = new CascadeClassifier(xmlfilePath);
		// Detect faces in the image.
		// MatOfRect is a special container class for Rect.
		MatOfRect faceDetections = new MatOfRect();
		faceDetector.detectMultiScale(image, faceDetections);
		// Draw a bounding box around each face.
		for (Rect rect : faceDetections.toArray()) {
			return caiJian.caiJian(image, rect);
		}
		return null;
	}
}
