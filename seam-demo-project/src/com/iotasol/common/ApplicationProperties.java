package com.iotasol.common;

import java.io.InputStream;
import java.util.Properties;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

/**
 * 
 * @author Iotasol Technologies Pvt. Ltd.
 *
 */
@Name("applicationProperties")
@Scope(ScopeType.APPLICATION)
public class ApplicationProperties {
	static {
		getProps();
	}
	public static String IMAGE_FOLDER;
	public static String folderName;
	public static String CSV_NAME;
	public static String FORGOT_PASSWORD_SH_PATH;
	public static String api_id;
	public static String SECRET_KEY;
	public static String API_KEY;
	public static String APP_URL;
	public static String ERROR_URL;
	public static String DATABASE_URL;
	public static String viewgallery_url;
	public static String sharefriends_url;
	public static String gamePlay_url;
	public static String flashGame_url;
	public static String registerUser_url;
	public static String forgotPassword_url;
	public static String changePassword_url;
	public static String myProfile_url;

	public static void getProps() {
		String propertyfile = "com//iotasol//resource//ApplicationProperties.properties";
		Properties props = new Properties();
		ClassLoader loader = ApplicationProperties.class.getClassLoader();
		InputStream istream = loader.getResourceAsStream(propertyfile);
		try {
			props.load(istream);
			ApplicationProperties.IMAGE_FOLDER = props
					.getProperty("imagesFolder");
			ApplicationProperties.folderName = props.getProperty("folderName");
			ApplicationProperties.CSV_NAME = props.getProperty("csv_name");
			ApplicationProperties.FORGOT_PASSWORD_SH_PATH = props
					.getProperty("forgot.password.sh");
			
			ApplicationProperties.api_id = props.getProperty("api_id");
			ApplicationProperties.SECRET_KEY = props.getProperty("secret_key");
			ApplicationProperties.API_KEY = props.getProperty("api_key");
			ApplicationProperties.APP_URL = props.getProperty("app_url");
			ApplicationProperties.ERROR_URL = props.getProperty("error_url");
			ApplicationProperties.DATABASE_URL = props
					.getProperty("database_url");
			ApplicationProperties.viewgallery_url = props
					.getProperty("viewgallery_url");
			ApplicationProperties.sharefriends_url = props
					.getProperty("sharefriends_url");
			ApplicationProperties.gamePlay_url=props.getProperty("gamePlay");
			ApplicationProperties.flashGame_url=props.getProperty("flash_game");
			ApplicationProperties.registerUser_url=props.getProperty("register_user");
			ApplicationProperties.forgotPassword_url=props.getProperty("forgot_password");
			ApplicationProperties.changePassword_url=props.getProperty("change_password");
			ApplicationProperties.myProfile_url=props.getProperty("my_profile");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * @return the registerUser_url
	 */
	public String getRegisterUser_url() {
		return registerUser_url;
	}



	/**
	 * @return the forgotPassword_url
	 */
	public String getForgotPassword_url() {
		return forgotPassword_url;
	}



	/**
	 * @return the changePassword_url
	 */
	public String getChangePassword_url() {
		return changePassword_url;
	}



	/**
	 * @return the myProfile_url
	 */
	public String getMyProfile_url() {
		return myProfile_url;
	}



	/**
	 * @return the viewgallery_url
	 */
	public String getViewgallery_url() {
		return viewgallery_url;
	}
	/**
	 * @return the sharefriends_url
	 */
	public String getSharefriends_url() {
		return sharefriends_url;
	}
	/**
	 * @return the aPI_ID
	 */
	public String getApi_id() {
		return api_id;
	}
	/**
	 * @return the folderName
	 */
	public String getFolderName() {
		return folderName;
	}

	/**
	 * @return the gamePlay_url
	 */
	public String getGamePlay_url() {
		return gamePlay_url;
	}

	/**
	 * @return the flashGame_url
	 */
	public String getFlashGame_url() {
		return flashGame_url;
	}
}