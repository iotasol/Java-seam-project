package com.iotasol.common;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.acegisecurity.providers.encoding.Md5PasswordEncoder;
import org.apache.commons.fileupload.FileItem;

import com.iotasol.util.JSFUtil;

/**
 * 
 * @author Iotasol Technologies Pvt. Ltd.
 *
 */
public class CommonUtils {

	private static String url = null;

	public static final String DATE_FORMAT_DATE = "MMM dd,yyyy";
	public static final String DATE_FORMAT_TIME = "HH:mm";

	public static String getNowDate() {
		return new SimpleDateFormat(DATE_FORMAT_DATE).format(Calendar
				.getInstance().getTime());
	}

	public static boolean chkListEmpty(List list) {
		return list == null || list.isEmpty();
	}
	public static String checkNullSetOldorNewString(String stringNew,
			String StringOld) {
		return (stringNew == null) ? StringOld : stringNew;
	}

	public static String getFormatedDate(Date date) {
		return new SimpleDateFormat(DATE_FORMAT_DATE).format(date);
	}

	public static String getStringForDate(Date date) {
		if (date == null)
			return "";
		return (new SimpleDateFormat(DATE_FORMAT_DATE).format(date));
	}

	public static String getStringForDate(Date date, String stringFormat) {
		if (date == null)
			return "";
		if (stringFormat == null || stringFormat.equalsIgnoreCase(""))
			return getStringForDate(date);
		return (new SimpleDateFormat(stringFormat).format(date));
	}

	public static Date getDateForString(String date, String dateFormat)
			throws ParseException {
		if (date == null || date.equalsIgnoreCase(""))
			return null;
		if (dateFormat == null || dateFormat.equalsIgnoreCase(""))
			return getDateForString(date);
		DateFormat df = new SimpleDateFormat(dateFormat);
		Date convertedDate = df.parse(date);
		return convertedDate;
	}

	public static Date getDateForString(String date) throws ParseException {
		if (date == null)
			return null;
		DateFormat df = new SimpleDateFormat("MM/dd/yyy");
		Date convertedDate = df.parse(date);
		return convertedDate;
	}

	public static String getNowTime() {
		return new SimpleDateFormat(DATE_FORMAT_TIME).format(Calendar
				.getInstance().getTime());
	}

	public static String getEmptyStringForNull(String str) {
		return str == null ? "" : str;
	}

	public static Object getEmptyStringForNull(Object obj) {
		return obj == null ? "" : obj;
	}

	public static String checkNullSetOldorNew(String newString, String oldString) {
		return emptyString(newString) ? oldString : newString;
	}

	public static String trimValue(String str) {
		if (emptyString(str))
			return null;

		return str.trim();
	}

	public static Date checkNullSetOldorNewDate(Date newDate, Date oldDate) {
		return (newDate == null) ? oldDate : newDate;
	}

	public static boolean emptyString(String str) {
		return (str == null || str.trim().equals(""));
	}

	public static boolean notEmptyString(String str) {
		return !emptyString(str);
	}

	public static boolean checkListNullOrEmpty(List<?> lst) {
		return lst == null || lst.isEmpty();
	}

	public static final String encryptPassword(String password)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {

		Md5PasswordEncoder enc = new Md5PasswordEncoder();
		String encodedPassword = enc.encodePassword(password, null);
		return encodedPassword;
	}

	public static final void deleteIfNotEmpty(String imagePath) {
		if (emptyString(imagePath))
			return;
		deleteFile(ApplicationProperties.IMAGE_FOLDER + imagePath);
	}

	public static final boolean compareTwoString(String str1, String str2) {
		if (str1 == null)
			return false;

		return str1.equals(str2);
	}

	/**
	 * 
	 * @param digitsCount
	 * @return
	 */
	public static long generateRandom(int digitsCount) {
		if (digitsCount <= 0
				|| digitsCount > Long.valueOf(Long.MAX_VALUE).toString()
						.length()) {
			throw new IllegalArgumentException("Out of integer digits rang");
		}
		Random random = new Random();
		long rand = 0;
		int mulMask = 1;
		for (int i = 0; i < digitsCount - 1; i++) {
			int nextDigit = random.nextInt(10);
			rand = rand * 10 + nextDigit;
			mulMask *= 10;
		}
		int leftmostDigit = 0;
		do {
			leftmostDigit = random.nextInt(10);
		} while (leftmostDigit == 0);

		return leftmostDigit * mulMask + rand;
	}

	public static boolean chechNotNull(Object obj) {
		return obj != null;
	}

	public static String toString(Object obj) {
		return obj == null ? null : obj.toString();
	}

	public static boolean chechNull(Object obj) {
		return obj == null;
	}

	public static String extractUrl(HttpServletRequest request) {
		if (url == null) {
			url = request.getRequestURL().toString();
			url = url.substring(0, url.indexOf(request.getServletPath()) + 1);
		}
		return url;
	}

	public static String extractUrl() {
		return extractUrl(JSFUtil.getRequest());
	}

	public static String formatMessage(String str, Object[] args) {
		return new MessageFormat(str).format(args);
	}

	public static Long convertToLong(String str) {
		if (emptyString(str))
			return null;
		return Long.parseLong(str);
	}

	public static Integer convertToInt(String str) {
		if (emptyString(str))
			return null;
		return Integer.parseInt(str);
	}

	public static Float convertToFloat(String str) {
		if (emptyString(str))
			return null;
		return Float.parseFloat(str);
	}

	/**
	 * url is valid if its empty
	 * 
	 * @param myUrl
	 * @return
	 */
	public static boolean invalidateNonEmptyURL(String myUrl) {
		if (emptyString(myUrl))
			return false;

		try {
			new java.net.URI(myUrl);
		} catch (URISyntaxException e) {
			return true;
		}
		return false;
	}

	// public static String createThumbImage(String filePath, int width, int
	// height) {
	// String thumbPath = "thumb_" + filePath;
	// String outputFile = ApplicationProperties.IMAGE_FOLDER + thumbPath;
	// filePath = ApplicationProperties.IMAGE_FOLDER + filePath;
	//
	// try {
	// createThumbnail(filePath, width, height, 100, outputFile);
	// } catch (Exception e) {
	// throw new MakeAppGenericException(
	// "Not able to create thumnail file", e);
	// }
	// return thumbPath;
	// }

	// public static void createThumbImage1(String filePath, int width, int
	// height) {
	// int dot = filePath.indexOf(".");
	//
	// String ext = filePath.substring(dot + 1);
	// try {
	// File savedFile = new File(ApplicationProperties.IMAGE_FOLDER
	// + filePath);
	// BufferedImage src = ImageIO.read(savedFile);
	// filePath = filePath.replace("/", "/thumb_");
	// BufferedImage img = new BufferedImage(width, height,
	// BufferedImage.TYPE_INT_RGB);
	//
	// img.createGraphics().drawImage(
	// src.getScaledInstance(width, height, Image.SCALE_SMOOTH),
	// 0, 0, null);
	// ImageIO.write(img, ext, new File(ApplicationProperties.IMAGE_FOLDER
	// + filePath));
	// } catch (IOException e) {
	// throw new MakeAppGenericException(
	// "Not able to create thumnail file", e);
	// }
	//
	// }

	public static String getThumbNailPath(String imagePath) {
		if (emptyString(imagePath))
			return imagePath;
		return imagePath.replace("/", "/thumb_");
	}

	/**
	 * url is invalid if its empty
	 * 
	 * @param myUrl
	 * @return
	 */
	public static boolean invalidateEmptyURL(String myUrl) {
		if (emptyString(myUrl))
			return true;

		try {
			new java.net.URI(myUrl);
		} catch (URISyntaxException e) {
			return true;
		}
		return false;
	}

	public static void deleteFile(String file) {
		File f1 = new File(file);
		f1.delete();
	}

	public static String saveFile(FileItem item, String itemName, int IndexOf)
			throws Exception {
		Random generator = new Random();
		int r = Math.abs(generator.nextInt());
		String domainName = itemName.substring(IndexOf);
		String fileName = itemName.substring(0, IndexOf);
		String string = "_image_";
		if (domainName.equalsIgnoreCase(".pem")) {
			string = "_certificate_";
		} else if (domainName.equalsIgnoreCase(".csv")) {
			string = "_csv_";
		}

		String finalimage = fileName + string + r + domainName;
		File savedFile = new File(ApplicationProperties.IMAGE_FOLDER
				+ finalimage);
		item.write(savedFile);
		return finalimage;
	}

	public static String copyFile(String fileName) throws Exception {
		Random generator = new Random();
		int r = Math.abs(generator.nextInt());
		int indexOf = fileName.indexOf(".");
		String domainName = fileName.substring(indexOf);
		String itemName = fileName.substring(0, indexOf);
		String finalimage = itemName + r + domainName;

		File savedFile = new File(ApplicationProperties.IMAGE_FOLDER
				+ finalimage);
		File originalFile = new File(ApplicationProperties.IMAGE_FOLDER
				+ fileName);
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
				originalFile), 4096);
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(savedFile), 4096);
		int theChar;
		while ((theChar = bis.read()) != -1) {
			bos.write(theChar);
		}
		bos.close();
		bis.close();
		return finalimage;
	}

	/**
	 * This method is to be used in conjugation with the RichFileUpload Tag and
	 * is to be called inside the event listener to save the file that is
	 * uploaded
	 * 
	 * @return
	 */
	public static String saveFile(byte[] bytes, String fileName)
			throws FileNotFoundException, IOException {
		// // Reading the temp file into file input stream image
		// FileInputStream fis = new FileInputStream(fileToBeSaved);
		// ByteArrayOutputStream bos = new ByteArrayOutputStream();
		// byte[] buf = new byte[1024];
		//
		// //Readying the image into output stream
		// for (int readNum; (readNum = fis.read(buf)) != -1;)
		// {
		// bos.write(buf, 0, readNum);
		// }

		InputStream inputStream = new ByteArrayInputStream(bytes);
		// bufferedImage is the RenderedImage to be written
		BufferedImage bufferedImage = ImageIO.read(inputStream);

		Random generator = new Random();
		int r = Math.abs(generator.nextInt());
		String finalFileName = r + fileName;
		File finalImage = new File(ApplicationProperties.IMAGE_FOLDER
				+ finalFileName);
		// Writing the file to the local dir
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
		ImageIO.write(bufferedImage, formatName, finalImage);

		// Closing the streams
		// bos.close();
		// fis.close();
		// Deleting the temporary file
		// fileToBeSaved.delete();
		// returning the saved image name
		return finalFileName;
	}

	/**
	 * This method loads the Image and returns bytes array. This method is
	 * called in conjugation with rich file upload element to reRender the image
	 */
	public static byte[] loadImageInBytes(String savedFileName)
			throws FileNotFoundException, IOException {
		// Creating the new file object
		File tempFile = new File(ApplicationProperties.IMAGE_FOLDER
				+ savedFileName);
		// Reading the temp file into file input stream image
		FileInputStream fis = new FileInputStream(tempFile);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		// Readying the image into output stream
		for (int readNum; (readNum = fis.read(buf)) != -1;) {
			bos.write(buf, 0, readNum);
		}
		// Returning the byte array
		return bos.toByteArray();
	}

	/**
	 * This method returns corresponding string for boolean value If Boolean
	 * value is true(1), then it returns string "TRUE" If Boolean value is
	 * false(0), then it returns string "FALSE"
	 * 
	 * @param Boolean
	 * @return String
	 */
	public static String convertBooleanToString(boolean flag) {
		return flag ? "TRUE" : "FALSE";
	}

	/**
	 * 
	 * This method checks if a String contains only numbers
	 * 
	 */
	public static boolean containsOnlyNumbers(String str) {
		// It can't contain only numbers if it's null or empty...
		if (str == null || str.length() == 0)
			return false;

		for (int i = 0; i < str.length(); i++) {
			// If we find a non-digit character we return false.
			if (!Character.isDigit(str.charAt(i)))
				return false;
		}
		return true;
	}

	public static boolean containsOnlyDecimal(String str) {
		// It can't contain only numbers if it's null or empty...
		if (str == null || str.length() == 0)
			return false;

		for (int i = 0; i < str.length(); i++) {
			// If we find a non-digit character we return false.
			if (!Character.isDigit(str.charAt(i)))
				if (str.charAt(i) != '.')
					return false;
		}
		return true;
	}

	public static boolean invalidEmail(String email) {
		// Input the string for validation

		String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		// Set the email pattern string
		Pattern p = Pattern.compile(EMAIL_PATTERN);

		// Match the given string with the pattern
		Matcher m = p.matcher(email);

		// check whether match is found
		return !m.matches();
	}

	public static void main(String args[]) {
		try {
			String url = "http://www.fubers.com/pages/gamePlay.seam";
			System.out.println(url.substring(0, url.lastIndexOf("/")));
			System.out.println(CommonUtils.encryptPassword("demo123"));
			System.out.println(URLEncoder.encode(url, "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean isNumeric(String str)
	{
	    if (!Character.isDigit(str.charAt(0)) && str.charAt(0) != '-') return false;

	    for (char c : str.substring(1).toCharArray())
	    {
	        if (!Character.isDigit(c)) return false;
	    }
	    return true;
	}


}