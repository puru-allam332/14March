package com.utils.in;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfigFileClass {
	static Properties prop;
public static Properties ReadConfigFileMethod() throws IOException
{
	File file =new File("./src/test/resources/config/config.properties");
	FileInputStream fis=new FileInputStream(file);
	prop=new Properties();
	prop.load(fis);
	return prop;
}
}
