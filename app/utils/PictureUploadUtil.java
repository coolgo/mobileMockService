package utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import javax.imageio.ImageIO;

import jsonvo.mobileVo.RichPostVo.ImageSize;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

import play.Logger;
import play.Play;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Request;
import com.ning.http.client.RequestBuilder;
import com.ning.http.client.Response;

/**
 * TODO:图片服务将使用nginx实现<br>
 * 这里需要配套改变
 * 
 * @author mayan
 * 
 */
public class PictureUploadUtil {

	private static final String PIC_SERVER_BASEURL = Play.configuration
			.getProperty("picserver.baseurl", "http://www.deach.net/picserver");

	public static String uploadFile(File file) {
		assert file != null;
		if (file.length() > PictureUploadUtil.getUploadLimit() * 1024 * 1024
				|| file.length() == 0l) {
			return null;
		}
		AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
		Request request = new RequestBuilder("POST")
				.setBody(file)
				.setHeader("extension",
						FilenameUtils.getExtension(file.getName()))
				.setFollowRedirects(true).setUrl(PIC_SERVER_BASEURL).build();
		try {

			Response response = asyncHttpClient.executeRequest(request).get();
			return response.getResponseBody();
		} catch (InterruptedException e) {
			Logger.error(e, e.getMessage());
			return null;
		} catch (ExecutionException e) {
			Logger.error(e, e.getMessage());
			return null;
		} catch (IOException e) {
			Logger.error(e, e.getMessage());
			return null;
		} finally {
			if (!asyncHttpClient.isClosed()) {
				asyncHttpClient.close();
			}
		}
	}

	public enum PicSizeType {
		small {

			@Override
			public int getWidth() {
				return 64;
			}

			@Override
			public int getHeight() {
				return 64;
			}

		},
		middle {

			@Override
			public int getWidth() {
				return 128;
			}

			@Override
			public int getHeight() {
				return 128;
			}

		},
		large {

			@Override
			public int getWidth() {
				return 500;
			}

			@Override
			public int getHeight() {
				return 500;
			}

		};

		abstract public int getWidth();

		abstract public int getHeight();
	}

	public static String getPictureFromPicServer(int width, int height,
			String defaultUrl) {
		if (!StringUtils.startsWithIgnoreCase(defaultUrl, PIC_SERVER_BASEURL)) {
			return defaultUrl;
		}
		String host = StringUtils.substringBeforeLast(defaultUrl, "/");
		String fileName = StringUtils.substringAfterLast(defaultUrl, "/");
		return host + "/" + width + "_" + height + "/" + fileName;
	}

	public static String getPictureFromPicServer(PicSizeType picSizeType,
			String defaultUrl) {
		return getPictureFromPicServer(picSizeType.getWidth(),
				picSizeType.getHeight(), defaultUrl);
	}

	/**
	 * 切割规则：<br>
	 * 将过长或过宽的图片进行切割<br>
	 * 如果过长:从顶部开始向下切割<br>
	 * 如果过宽:居中后向两边切<br>
	 * 
	 * @author WangLiang/王良(841369634@qq.com)
	 * 
	 * @throws IOException
	 */
	public static File cutPic(File avatarFile, int width, int height)
			throws IOException {
		BufferedImage image = ImageIO.read(avatarFile);

		// 等比拉伸
		int afterStretchingWidth = width;// 拉伸后的宽
		int afterStretchingheight = height;// 拉伸后的高
		int x = 0;// 切割的起始点：x坐标
		int y = 0;// 切割的起始点：y坐标

		// 获得拉伸后的新的高宽
		if (image.getHeight() > image.getWidth()) {
			afterStretchingheight = image.getHeight() * width
					/ image.getWidth();
		} else {
			afterStretchingWidth = image.getWidth() * height
					/ image.getHeight();
			// 当宽度比高度大时，从中间开始切割
			if (afterStretchingWidth > afterStretchingheight) {
				x = (width - afterStretchingWidth) / 2;
			}
		}

		return cutPic(avatarFile, image, width, height, x, y,
				afterStretchingWidth, afterStretchingheight);
	}

	/**
	 * 生成拉伸并切割后的图片文件
	 * 
	 * @author WangLiang/王良(841369634@qq.com)
	 * 
	 * @param avatarFile
	 *            源文件
	 * @param image
	 *            由源文件
	 * @param width
	 *            最终要生成的图片宽度
	 * @param height
	 *            最终要生成的图片高度
	 * @param afterStretchingWidth
	 *            拉伸后的宽
	 * @param afterStretchingheight
	 *            拉伸后的高
	 * @param x
	 *            切割起始点x坐标
	 * @param y
	 *            切割起始点y坐标
	 * @return 最终生成的图片文件
	 * @throws IOException
	 */
	public static File cutPic(File avatarFile, BufferedImage image, int width,
			int height, int x, int y, int afterStretchingWidth,
			int afterStretchingheight) throws IOException {
		if (image == null) {
			image = ImageIO.read(avatarFile);
		}
		BufferedImage destimage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		destimage.getGraphics().drawImage(
				image.getScaledInstance(afterStretchingWidth,
						afterStretchingheight, Image.SCALE_SMOOTH), x, y, null);

		ImageIO.write(destimage,
				FilenameUtils.getExtension(avatarFile.getName()), avatarFile);
		return avatarFile;
	}

	/**
	 * 将原图片按比例(ratio)拉伸后，再根据x、y进行切割，最终生成新图片
	 * 
	 * @author WangLiang/王良(841369634@qq.com)
	 * 
	 * @param fileUrl
	 *            此URL为带http://的绝对路径
	 * @param width
	 * @param height
	 * @param x
	 * @param y
	 * @param ratio
	 * @return
	 */
	public static String cutPic(String fileUrl, int width, int height,
			Integer x, Integer y, Double ratio) throws IOException {
		if (fileUrl == null || fileUrl.length() == 0) {
			return null;
		}

		if (x == null) {
			x = 0;
		}
		if (y == null) {
			y = 0;
		}
		if (ratio == null) {
			ratio = 1.0;
		}

		// 获取图片文件
		File file = getFileByUrl(fileUrl);
		if (file == null) {
			return null;
		}

		BufferedImage image = ImageIO.read(file);

		// 当不缩放、不偏移、原图片规格与要求的规格一致时，返回原文件路径
		if (x == 0 && y == 0 && ratio == 1 && width == image.getWidth()
				&& height == image.getHeight()) {
			file.delete();
			return fileUrl;
		}

		int afterStretchingWidth = (int) (image.getWidth() * ratio);
		int afterStretchingheight = (int) (image.getHeight() * ratio);

		File newPicFile = cutPic(file, image, width, height, x, y,
				afterStretchingWidth, afterStretchingheight);

		String newPicFileUrl = PictureUploadUtil.uploadFile(newPicFile);
		file.delete();

		return newPicFileUrl;
	}

	// private

	/**
	 * 根据地址获取图片（此地址可能是绝对地址，也可能是相对地址）
	 * 
	 * @author WangLiang/王良(841369634@qq.com)
	 * 
	 * @param fileUrl
	 * @return
	 */
	private static File getFileByUrl(String fileUrl) {
		String fileName = fileUrl.substring(fileUrl.lastIndexOf("/"));
		String fileSavePath = basePath + "/tmp/" + initFileName(fileName);
		boolean flag = downloadByUrl(fileUrl, fileSavePath);
		if (!flag) {
			return null;
		}
		return new File(fileSavePath);
	}

	private static final String basePath = System.getProperty("user.dir");

	/**
	 * 根据文件链接下载文件
	 */
	private static boolean downloadByUrl(String photoUrl, String fileSavePath) {
		try {
			URL url = new URL(photoUrl);

			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();

			DataInputStream in = new DataInputStream(
					connection.getInputStream());

			DataOutputStream out = new DataOutputStream(new FileOutputStream(
					fileSavePath));

			byte[] buffer = new byte[4096];
			int count = 0;

			while ((count = in.read(buffer)) > 0) {
				out.write(buffer, 0, count);
			}
			out.close();
			in.close();
			return true;
		} catch (Exception e) {
			Logger.error(e, e.getMessage());
			return false;
		}

	}

	/**
	 * 初始化文件名，为了不重名
	 */
	private static String initFileName(String fileName) {
		return new SimpleDateFormat("yyyyMMdd-HHmmssSSS").format(new Date())
				+ "-" + (int) (10000 * Math.random()) + "."
				+ FilenameUtils.getExtension(fileName);
	}

	/**
	 * 用于等比缩放图片 <br>
	 * 【(x,y),(x,y*h/w),(x*w/h,y)】
	 * 
	 * @author coolgo(winerdaxian@163.com)
	 * @param file
	 * @param x
	 * @param y
	 * @return
	 * @throws IOException
	 */
	public static File cutPicReduce(File file, int x, int y) throws IOException {
		BufferedImage bufferedImage = ImageIO.read(file);
		int imgW = bufferedImage.getWidth();
		int imgH = bufferedImage.getHeight();
		// 图片小于制定要求
		if (x >= imgW && y >= imgH) {
			return file;
		} else if (imgW >= imgH) {
			y = y * imgH / imgW;
		} else {
			x = x * imgW / imgH;
		}
		return cutPic(file, bufferedImage, x, y, 0, 0, x, y);
	}

	private static int getUploadLimit() {
		return Integer.parseInt(Play.configuration.getProperty(
				"file.UploadLimit", "30"));
	}

	/**
	 * 
	 * 通过upload控件上传文件 <br>
	 * 支持图片和文件自动选择上传，即，自动选择文件和图片服务器
	 * 
	 * @author coolgo
	 * @param fileName
	 * @return
	 */
	public static String uploadByUploadWidget(String fileName)
			throws IOException {
		play.mvc.Http.Request request = play.mvc.Http.Request.current();
		File file = new File(PictureUploadUtil.getTempFileName(fileName));
		InputStream is = null;
		try {
			if (request.isNew) {
				is = request.body;
				FileUtils.copyInputStreamToFile(is, file);
				if (file == null || !file.exists() || file.length() == 0l
						|| file.length() > getUploadLimit() * 1024 * 1024) {
					return null;
				}
				return PictureUploadUtil.uploadFile(file);
			}
		} catch (IOException e) {
			Logger.error(e, "file upload is fail", fileName);
			throw e;
		} finally {
			if (is != null) {
				is.close();
			}
			FileUtils.deleteQuietly(file);
		}
		return null;
	}

	/**
	 * 获取临时的随机文件名
	 * 
	 * @author coolgo
	 * @param fileName
	 * @return
	 */
	private static String getTempFileName(String fileName) {
		final String basePath = "tmp\\";
		String randomHead = RandomStringUtils.random(8);
		return basePath + randomHead + fileName;
	}

	/**
	 * 
	 * @author coolgo(winerdaxian@163.com)
	 * 
	 * @param file
	 * @return
	 */
	public static ImageSize getImageSizeFromImage(File file) {
		ImageSize size = new ImageSize();
		try {
			BufferedImage bufferedImage = ImageIO.read(file);
			size.hight = bufferedImage.getHeight();
			size.width = bufferedImage.getWidth();
		} catch (IOException e) {
			Logger.error(e, e.getMessage(), "获取图片大小失败");
		}
		return size;
	}

	public static String uploadFileWithPngImage(File file) {
		String fileUrl = null;
		File tempFile = null;
		try {
			tempFile = new File(PictureUploadUtil.getTempFileName("temp.png"));
			FileUtils.copyFile(file, tempFile);
			fileUrl = uploadFile(tempFile);
		} catch (IOException e) {
			Logger.error(e, e.getMessage(), "get pngimage file is wrong");
		} finally {
			FileUtils.deleteQuietly(tempFile);
		}
		return fileUrl;
	}
}
