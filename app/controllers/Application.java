package controllers;

import java.util.ArrayList;
import java.util.List;

import play.Logger;
import play.Play;
import play.test.Fixtures;
import play.vfs.VirtualFile;
import cn.bran.play.JapidController;

public class Application extends JapidController {

	public static void index() {
		renderJapid("", loadedYml);
	}

	private static List<String> loadedYml = new ArrayList<String>();
	private static final String confpath = "/conf/";
	private static boolean isProcessing = false;

	public static void fillData(Boolean clean) {
		if (isProcessing) {
			renderJapidWith("Application/index.html", "系统正在加载中",
					new ArrayList<String>());
		}

		if (clean) {
			Fixtures.deleteDatabase();
		}
		isProcessing = true;
		loadedYml = new ArrayList<String>();
		putYMLToDB(Play.configuration.getProperty("mockdata.location"));
		isProcessing = false;
		renderJapidWith("Application/index.html", "", loadedYml);
	}

	public static void clearDb() {
		Fixtures.deleteDatabase();
		loadedYml = new ArrayList<String>();
		isProcessing = false;
		renderJapidWith("Application/index.html", "", loadedYml);
	}

	protected static void putYMLToDB(String rootPath) {
		String path = Play.applicationPath + confpath + rootPath;
		List<VirtualFile> list = VirtualFile.open(path).list();

		for (VirtualFile f : list) {
			if (f.isDirectory()) {
				putYMLToDB(rootPath + "/" + f.getName());
			} else {
				if (f.getName().contains("yml")) {
					String ymlpath = rootPath + "/" + f.getName();
					System.out.println("ymlPath:" + ymlpath);
					Fixtures.loadModels(ymlpath);
					loadedYml.add(f.getName());
					Logger.debug("load file:" + f.getName());
				}
			}
		}
	}

	public static void mockservice() {
		renderJapid();
	}
}