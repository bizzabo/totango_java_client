import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Totango {

	private static final String UTC = "UTC";

	private static final String ISO8601_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

	private static final Logger logger = LoggerFactory.getLogger(Totango.class);

	private static String serviceId;

	private static final String TOTANGO_BASE_URL = "https://sdr.totango.com/pixel.gif?sdr_s=";

	private static final String ACCOUNT_ID = "&sdr_o";
	private static final String USER = "&sdr_u=";
	private static final String ACTIVITY = "&sdr_a=";
	private static final String MODULE = "&sdr_m=";
	private static final String DISPLAY_NAME = "&sdr_odn=";

	public static void serviceId(String serviceId) {
		Totango.serviceId = serviceId;
	}

	public static Track track(String account) {
		return new Track(account);
	}

	public static Track track(String account, String user) {
		return new Track(account, user);
	}

	static class Track {

		private static final String UTF8 = "UTF8";
		private Map<String, String> attributes = new HashMap<>();

		private String account;
		private String user;
		private String activity;
		private String module;
		private String displayName;

		public Track(String account) {
			this.account = account;
		}

		public Track(String account, String user) {
			this(account);
			this.user = user;
		}

		public Track activity(String activity) {
			this.activity = activity;
			return this;
		}

		public Track displayName(String displayName) {
			this.displayName = displayName;
			return this;
		}

		public Track module(String module) {
			this.module = module;
			return this;
		}

		public Track attribute(String key, Number value) {
			if (key != null && value != null) {
				attributes.put(key, value.toString());
			}
			return this;
		}
		public Track attribute(String key, Boolean value) {
			if (key != null && value != null) {
				attributes.put(key, value.toString());
			}
			return this;
		}

		public Track attribute(String key, String value) {
			if (key != null && value != null) {
				attributes.put(key, value);
			}
			return this;
		}
		public Track attribute(String key, Date value) {
			if (key != null && value != null) {
				attributes.put(key, getISO8601StringForDate(value));
			}
			return this;
		}

		public void send() {
			StringBuilder builder = new StringBuilder(TOTANGO_BASE_URL);
			builder.append(Totango.serviceId).append(ACCOUNT_ID).append('=').append(account);
			try {
				if (user != null) {
					builder.append(USER).append(URLEncoder.encode(user, UTF8));
				}
				if (activity != null) {
					builder.append(ACTIVITY).append(URLEncoder.encode(activity, UTF8));
				}
				if (module != null) {
					builder.append(MODULE).append(URLEncoder.encode(module, UTF8));
				}
				if (displayName != null) {
					builder.append(DISPLAY_NAME).append(URLEncoder.encode(displayName, UTF8));
				}
				for (Map.Entry<String, String> attribute : attributes.entrySet()) {
					builder.append(ACCOUNT_ID).append('.');
					builder.append(URLEncoder.encode(attribute.getKey(), UTF8)).append('=').append(URLEncoder.encode(attribute.getValue(), UTF8));
				}
				String url = builder.toString();
				logger.info("sending totango sdr: {}", url);
				new URL(url).getContent();
			} catch (UnsupportedEncodingException e) {
				logger.error("missing encoding", e);
			} catch (Exception e) {
				logger.error("failed to send totango record: {}",toString(), e);
				throw new RuntimeException();
			}
		}

		@Override
		public String toString()
		{
			return String.format("sdr [ account=%s, user=%s, activity=%s, module=%s, displayName=%s, attributes=%s]", account, user, activity, module, displayName,attributes);
		}
		
	}
	
	public static String getISO8601StringForDate(Date date) {
		if (date != null){
			DateFormat dateFormat = new SimpleDateFormat(ISO8601_FORMAT, Locale.US);
			dateFormat.setTimeZone(TimeZone.getTimeZone(UTC));
			return dateFormat.format(date);
		}
		return null;
	}
	
	

}
