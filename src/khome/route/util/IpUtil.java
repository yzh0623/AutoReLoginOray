package khome.route.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class IpUtil {

	public static String cacheIP = "127.0.0.1";

	// 获取公网ip
	public static String getPublicIP() throws IOException {
		String str = null;
		InputStream ins = null;

		StringBuffer webContent = new StringBuffer();

		try {
			// 连接外网网站工具
			URL url = new URL("http://1212.ip138.com/ic.asp");
			URLConnection con = url.openConnection();
			ins = con.getInputStream();
			// 得到内容
			BufferedReader bReader = new BufferedReader(new InputStreamReader(ins, "UTF-8"));

			while (null != (str = bReader.readLine())) {
				webContent.append(str);
			}
			// 解析结果并返回ip值
			int start = webContent.indexOf("[") + 1;
			int end = webContent.indexOf("]");
			return webContent.substring(start, end);
		} finally {
			if (null != ins) {
				ins.close();
			}
		}
	}

}
