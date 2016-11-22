import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Converter {
	boolean realm;
	boolean serialize;
	private String dir;
	
	private TreeSet<String> set = new TreeSet<>();

	public Converter(String dir, String packageName, String className, String json, boolean realm, boolean serialize) {
		JSONParser parser = new JSONParser();

		this.realm = realm;
		this.serialize = serialize;
		this.dir = dir;
		try {
			JSONObject jsonObject = (JSONObject) parser.parse(json);

			parse(packageName, className, jsonObject);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void parse(String packageName, String name, JSONObject jsonObject) {
		// TODO Auto-generated method stub
		
		this.set.add(name);
		Set set = jsonObject.entrySet();
		Iterator iterator = set.iterator();
		StringBuilder headerBuilder = new StringBuilder();
		StringBuilder methodBuilder = new StringBuilder();

		StringBuilder builder = new StringBuilder();
		headerBuilder.append("package " + packageName + ";\n\n");
		headerBuilder.append("import com.google.gson.annotations.Expose;\n");
		headerBuilder.append("import com.google.gson.annotations.SerializedName;\n\n");
		builder.append("public class " + name);
		if (realm) {
			headerBuilder.append("import io.realm.RealmObject;\n");
			builder.append(" extends RealmObject");
		}
		if (serialize) {
			headerBuilder.append("import java.io.Serializable;\n");
			builder.append(" implements Serializable");
		}
		builder.append("{\n\n");
		boolean isListImport = false;
		while (iterator.hasNext()) {
			Entry<String, Object> entry = (Entry<String, Object>) iterator.next();
			System.out.print(entry.getKey() + "-> ");

			String key = entry.getKey();
			// key = key.toLowerCase().charAt(0) + key.substring(1);
			String className = key.toUpperCase().charAt(0) + key.substring(1);
			String methodName = className;
			while(this.set.contains(className)){
				className+="_";
			}
			Object object = entry.getValue();
			builder.append("\t@SerializedName(\"ApiKey\")\n");
			builder.append("\t@Expose\n");
			if (object instanceof Long) {
				System.out.println("Integer");
				builder.append(String.format("\tprivate long %s;\n", key));
				addMethod("long", methodName, key, methodBuilder);
			} else if (object instanceof Double) {
				System.out.println("Double");
				builder.append(String.format("\tprivate double %s;\n", key));
				addMethod("double", methodName, key, methodBuilder);
			} else if (object instanceof String) {
				System.out.println("String");
				builder.append(String.format("\tprivate String %s;\n", key));
				addMethod("String", methodName, key, methodBuilder);

			} else if (object instanceof Boolean) {
				System.out.println("Boolean");
				builder.append(String.format("\tprivate boolean %s;\n", key));
				addMethod("boolean", methodName, key, methodBuilder);

			} else if (object instanceof List) {
				System.out.println("List");
				if (!isListImport) {
					isListImport = true;
					if (realm) {
						headerBuilder.append("import io.realm.RealmList;\n");
					} else {
						headerBuilder.append("import java.util.List;\n");
						headerBuilder.append("import java.util.ArrayList;\n");
					}
				}
				String type = realm ? "RealmList" : "List";
				builder.append(String.format("\tprivate %s<%s> %s=new %s<>();\n", type, className, key,
						realm ? "RealmList" : "ArrayList"));

				parse(packageName, className, (JSONArray) jsonObject.get(key));
				addMethod(type + "<" + className + ">", methodName, key, methodBuilder);
			} else {
				System.out.println("Object");
				builder.append(String.format("\tprivate %s %s;\n", className, key));
				parse(packageName, className, (JSONObject) jsonObject.get(key));
				addMethod(className, methodName, key, methodBuilder);
			}
		}

		builder.append("\n").append(methodBuilder.toString());
		builder.append("}");
		headerBuilder.append("\n").append(builder);
		System.out.println(headerBuilder.toString());

		File folder = new File(new File(dir), "generate");
		if (!folder.exists())
			folder.mkdirs();
		IO.getInstance().write(new File(folder, name + ".java"), headerBuilder);
	}

	private static void addMethod(String string, String string2, String key, StringBuilder builder) {
		// TODO Auto-generated method stub
		builder.append("\tpublic ").append(string).append(" get").append(string2)
				.append("(){\n\t\treturn " + key + ";\n\t}\n\n");
		builder.append("\tpublic void set").append(string2).append("(").append(string).append(" ").append(key)
				.append(")").append("{ \n\t\tthis." + key + "=" + key + ";\n\t}\n\n");
	}

	private void parse(String packageName, String name, JSONArray jsonArray) {
		// TODO Auto-generated method stub
		if (jsonArray != null)
			if (jsonArray.size() > 0) {
				parse(packageName, name, (JSONObject) jsonArray.get(0));
				return;
			}
		parse(packageName, name, new JSONObject());

	}
}
