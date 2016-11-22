import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringBuilder builder = IO.getInstance().read(new File("data.json"));
		JSONParser parser = new JSONParser();

		try {
			JSONObject jsonObject = (JSONObject) parser.parse(builder.toString());

			parse("Data", jsonObject);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void parse(String name, JSONObject jsonObject) {
		// TODO Auto-generated method stub
		Set set = jsonObject.entrySet();
		Iterator iterator = set.iterator();
		StringBuilder builder = new StringBuilder();
		StringBuilder methodBuilder = new StringBuilder();
//		builder.append("import java.util.ArrayList;\n");
//		builder.append("import java.util.List;\n");
		builder.append("import io.realm.RealmList;\n");
		builder.append("import io.realm.RealmObject;\n");
		builder.append("public class " + name + " extends RealmObject{\n\n");
		while (iterator.hasNext()) {
			Entry<String, Object> entry = (Entry<String, Object>) iterator.next();
			System.out.print(entry.getKey() + "-> ");

			String key = entry.getKey();
			//key = key.toLowerCase().charAt(0) + key.substring(1);
			String className = key.toUpperCase().charAt(0) + key.substring(1);
			Object object = entry.getValue();
			if (object instanceof Long) {
				System.out.println("Integer");
				builder.append(String.format("\tprivate long %s;\n", key));
				addMethod("long", className, key, methodBuilder);
			} else if (object instanceof Double) {
				System.out.println("Double");
				builder.append(String.format("\tprivate double %s;\n", key));
				addMethod("double", className, key, methodBuilder);
			} else if (object instanceof String) {
				System.out.println("String");
				builder.append(String.format("\tprivate String %s;\n", key));
				addMethod("String", className, key, methodBuilder);

			} else if (object instanceof Boolean) {
				System.out.println("Boolean");
				builder.append(String.format("\tprivate boolean %s;\n", key));
				addMethod("boolean", className, key, methodBuilder);

			} else if (object instanceof List) {
				System.out.println("List");
				builder.append(String.format("\tprivate RealmList<%s> %s=new RealmList<>();\n", className, key));
				parse(className, (JSONArray) jsonObject.get(key));
				addMethod("RealmList<" + className + ">", className, key, methodBuilder);
			} else {
				System.out.println("Object");
				builder.append(String.format("\tprivate %s %s;\n", className, key));
				parse(className, (JSONObject) jsonObject.get(key));
				addMethod(className, className, key, methodBuilder);
			}
		}
		builder.append("\n").append(methodBuilder.toString());
		builder.append("}");

		System.out.println(builder.toString());

		File folder = new File("generate");
		if (!folder.exists())
			folder.mkdirs();
		IO.getInstance().write(new File(folder, name + ".java"), builder);
	}

	private static void addMethod(String string, String string2, String key, StringBuilder builder) {
		// TODO Auto-generated method stub
		builder.append("\tpublic ").append(string).append(" get").append(string2)
				.append("(){\n\t\treturn " + key + ";\n\t}\n\n");
		builder.append("\tpublic void set").append(string2).append("(").append(string).append(" ").append(key)
				.append(")").append("{ \n\t\tthis." + key + "=" + key + ";\n\t}\n\n");
	}

	private static void parse(String name, JSONArray jsonArray) {
		// TODO Auto-generated method stub
		if (jsonArray != null)
			if (jsonArray.size() > 0) {
				parse(name, (JSONObject) jsonArray.get(0));
				return;
			}
		parse(name, new JSONObject());

	}

}
