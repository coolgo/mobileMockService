package japidviews._javatags;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;

import play.Play;
import play.mvc.Http.Request;
import cn.bran.japid.util.WebUtils;

/**
 * @author Administrator
 * 
 */
public class CommonUtils {

	public static boolean checkMenuIsOpened(List<Long> menus, Long menuId) {
		if (menus == null) {
			return false;
		}
		for (long mId : menus) {
			if (mId == menuId) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param date
	 * @return 该帖子据页面显示经过时间<br>
	 *         updated by mayan
	 */
	public static String getPassTime(Date date) {
		Date now = new Date();
		if (now.before(date)) {
			return "";
		}
		long delta = (now.getTime() - date.getTime()) / 1000;
		if (delta < 60) {
			return delta + "秒前";
		}
		if (delta < 60 * 60) {
			long minutes = delta / 60;
			return minutes + "分钟前";
		}
		if (delta < 24 * 60 * 60) {
			long hours = delta / (60 * 60);
			return hours + "小时前";
		}
		if (delta < 4 * 24 * 60 * 60) {
			long days = delta / (24 * 60 * 60);
			return days + "天前";
		}
		return formatDate(date, "yyyy-MM-dd");
	}

	public static String formatDate(Date date, String pattern) {
		return WebUtils.fastformat(date, pattern);
	}

	public static String getBaseUrl() {
		Request current = Request.current();
		if (current == null) {
			String appBaseUrl = Play.configuration.getProperty(
					"application.baseUrl", "application.baseUrl");
			if (appBaseUrl.endsWith("/")) {
				appBaseUrl = appBaseUrl.substring(0, appBaseUrl.length() - 1);
			}
			return appBaseUrl;
		} else {
			return current.getBase();
		}
	}

	/**
	 * 处理请求url中“&”符号的转义 <br>
	 * 注： 通过JapidPlayAdapter生成的，两个以上（包括两个）要用该方法进行转义<br>
	 * 
	 * @author coolgo
	 * @param url
	 * @return
	 */
	public static String convertUrlDealWithLink_amp(String url) {
		return url.replaceAll("&amp;", "&");
	}

	/**
	 * 获取date所在时间的某个星期中某天的开始时间
	 * 
	 * @author coolgo
	 * @param date
	 * @param dayOfWeek
	 * @return
	 */
	public static Date getFirstDayInCertainWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		if (date != null) {
			cal.setTime(date);
		}
		int dateWeek = cal.get(Calendar.DAY_OF_WEEK);
		int amount = Calendar.MONDAY - dateWeek;
		if (dateWeek == Calendar.SUNDAY) { /* 当天是sunday, 往前推一个星期 */
			Date tempDate1 = DateUtils.addWeeks(date, -1);
			return DateUtils.addDays(tempDate1, amount);
		} else {
			return DateUtils.addDays(date, amount);
		}
	}

	/**
	 * @param date
	 * @return 中文星期
	 * @author xuyz (timely.xyz@163.com)
	 */
	public static final String getCNWeekDay(Date date) {
		final String[] weekDays = { "", "星期日", "星期一", "星期二", "星期三", "星期四",
				"星期五", "星期六" };
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return weekDays[c.get(Calendar.DAY_OF_WEEK)];
	}

}
