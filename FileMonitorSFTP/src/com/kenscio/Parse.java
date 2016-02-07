package com.kenscio;

import java.io.InputStream;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

public class Parse {
	public static boolean parse(InputStream is) {
		boolean flag = true;
		JsonParser parser = Json.createParser(is);
		String name = null;
		int age = 0;
		int sal = 0;
		while (parser.hasNext()) {
			JsonParser.Event event = parser.next();
			switch (event) {
			case START_ARRAY: {
				break;
			}
			case START_OBJECT: {
				name = null;
				age = 0;
				sal = 0;
				break;
			}

			case END_OBJECT: {
				if (sal > 0 && age > 0 && name != null) {
					Database.insert(name, age, sal);
				} else {
					System.out.println("values missing");
					return flag = false;
				}
				break;

			}
			case END_ARRAY:{
				break;
			}
			case KEY_NAME: {
				if (parser.getString().equals("name")) {
					event = parser.next();
					if (event == Event.VALUE_STRING) {
						name = parser.getString();
					} else {
						System.out.println("invalid data type for attrivute name");
						return flag = false;
					}
				} else if (parser.getString().equals("age")) {
					event = parser.next();
					if (event == Event.VALUE_NUMBER) {
						age = parser.getInt();
					} else {
						System.out.println("invalid data type for attrivute age");
						return flag = false;
					}
				} else if (parser.getString().equals("sal")) {
					event = parser.next();
					if (event == Event.VALUE_NUMBER) {
						sal = parser.getInt();
					} else {
						System.out.println("invalid data type for attrivute sal");
						return flag = false;
					}
				} else {
					System.out.println("Invalid entrey in the json file");
					return flag = false;
				}
				break;

			}
			default: {
				System.out.println("json file is not in the proper format. Please check again");
				return flag = false;
			}

			}

		}
		return flag;
	}

}
