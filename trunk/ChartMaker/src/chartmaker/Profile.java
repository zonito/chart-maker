package chartmaker;

import com.google.wave.api.ProfileServlet;

@SuppressWarnings("serial")
public class Profile extends ProfileServlet {

	@Override
	public String getRobotName() {
		return "Chart-Maker";
	}

	@Override
	public String getRobotAvatarUrl() {
		return "http://definebot.appspot.com/images/chart.png";
	}

	@Override
	public String getRobotProfilePageUrl() {
		return "http://www.voizle.com/";
	}

}
