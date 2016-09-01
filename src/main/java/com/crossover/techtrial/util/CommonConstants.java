package com.crossover.techtrial.util;

import org.springframework.stereotype.Service;

@Service("commonConstants")
public class CommonConstants {
	public static int GRID_ROWS = 16; 
	public static int GRID_COLUMNS = 16;
	public static int SHOT_ATTEMPS = 5;
	public static char BULLET = '*'; 
	public static char WINGER = 'W'; 
	public static char ANGLE = 'L'; 
	public static char A_CLASS = 'A'; 
	public static char B_CLASS = 'B'; 
	public static char S_CLASS = 'S'; 
	public static String ENV = "dev";
	public static String GAME_ID = "match"; 
	public static String PLAYER_ID = "xebialabs-2";
	public static String PLAYER = "Cesar Chavez";
	public static String HOSTNAME = "localhost";
	public static String PORT = "8080";
	public static String SCHEMA_TYPE = "oracle";	
	public static String REQUEST_SVC = "";	
    public static String JAVAPROCESS_HEAPMEMORY = "128M";
    public static String JAVAPROCESS_NONHEAPMEMORY = "90M";
    public static Boolean HACK_SHOT_ATTEMPS = false;
    public static Boolean RANDOM_SHOTS = false;
	public static Boolean WINGER_DISABLE = false; 
	public static Boolean ANGLE_DISABLE = false; 
	public static Boolean A_CLASS_DISABLE = false; 
	public static Boolean B_CLASS_DISABLE = false; 
	public static Boolean S_CLASS_DISABLE = false; 
	public static String RECEIVE_REQUEST = "receive"; 
	public static String FIRE_REQUEST = "fire";
	public static String NEWGAME_REQUEST = "newgame";
	public static String STATUS_REQUEST = "status";
    public static String STANDARD_GAME_RULES = "standard";
    public static String X_SHOT_GAME_RULES = "-shot";
	public static String SUPER_CHARGE_GAME_RULES = "super-charge"; 
	public static String DESPERATION_GAME_RULES = "desperation"; 
}
