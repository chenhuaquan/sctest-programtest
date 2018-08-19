package com.sc.test.programtest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.tools.DiagnosticCollector;
import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CodeRunner {
	private static final Logger logger = LoggerFactory.getLogger(CodeRunner.class);
	private static Map<String, JavaFileObject> fileObjects = new ConcurrentHashMap<>();

	
	public String execute(List<String> codes, String input) throws IOException {
		StringBuffer sb = new StringBuffer();
		sb.append("public class MyStringOperation {\n");
		sb.append("    public String transform(String input) {\n");
		
		//replace code -> sb.append("        return input.concat(\"AAAA\");");
		for (String s : codes) {
		    sb.append(s);
		    
		}
		
		sb.append("    }\n");
		sb.append("}\n");	
		
		String code = sb.toString();

		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		DiagnosticCollector<JavaFileObject> collector = new DiagnosticCollector<>();
		JavaFileManager javaFileManager = new MyJavaFileManager(compiler.getStandardFileManager(collector, null, null));

		List<String> options = new ArrayList<>();
		options.add("-target");
		options.add("1.8");

		//ensure class name is provided
		Pattern CLASS_PATTERN = Pattern.compile("class\\s+([$_a-zA-Z][$_a-zA-Z0-9]*)\\s*");
		Matcher matcher = CLASS_PATTERN.matcher(code);
		String cls;
		if (matcher.find()) {
			cls = matcher.group(1);
		} else {
		    javaFileManager.close();
			throw new IllegalArgumentException("No such class name in " + code);
		}

		JavaFileObject javaFileObject = new MyJavaFileObject(cls, code);
		Boolean result = compiler
				.getTask(null, javaFileManager, collector, options, null, Arrays.asList(javaFileObject)).call();

		ClassLoader classloader = new MyClassLoader();

		Class<?> clazz = null;
		try {
			clazz = classloader.loadClass(cls);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Method method = null;

		try {
			// 准备测试数据
			method = clazz.getDeclaredMethod("transform", String.class);
			Object obj = clazz.newInstance();
			Object rslt = method.invoke(obj, input);
			
			// log the result
			logger.debug("transform({}) = {}", input, rslt);		
			
			return rslt.toString();
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} finally {
		    javaFileManager.close();
		}
		
		return "__UNDEFINED__";
	}

	public static class MyClassLoader extends ClassLoader {

		@Override
		protected Class<?> findClass(String name) throws ClassNotFoundException {
			JavaFileObject fileObject = fileObjects.get(name);
			if (fileObject != null) {
				byte[] bytes = ((MyJavaFileObject) fileObject).getCompiledBytes();
				return defineClass(name, bytes, 0, bytes.length);
			}
			try {
				return ClassLoader.getSystemClassLoader().loadClass(name);
			} catch (Exception e) {
				return super.findClass(name);
			}
		}
	}

	public static class MyJavaFileObject extends SimpleJavaFileObject {
		private String source;
		private ByteArrayOutputStream outPutStream;

		public MyJavaFileObject(String name, String source) {
			super(URI.create("String:///" + name + Kind.SOURCE.extension), Kind.SOURCE);
			this.source = source;
		}

		public MyJavaFileObject(String name, Kind kind) {
			super(URI.create("String:///" + name + kind.extension), kind);
			source = null;
		}

		@Override
		public CharSequence getCharContent(boolean ignoreEncodingErrors) {
			if (source == null) {
				throw new IllegalArgumentException("source == null");
			}
			return source;
		}

		@Override
		public OutputStream openOutputStream() throws IOException {
			outPutStream = new ByteArrayOutputStream();
			return outPutStream;
		}

		public byte[] getCompiledBytes() {
			return outPutStream.toByteArray();
		}
	}

	public static class MyJavaFileManager extends ForwardingJavaFileManager<JavaFileManager> {
		protected MyJavaFileManager(JavaFileManager fileManager) {
			super(fileManager);
		}

		@Override
		public JavaFileObject getJavaFileForInput(Location location, String className, JavaFileObject.Kind kind)
				throws IOException {
			JavaFileObject javaFileObject = fileObjects.get(className);
			if (javaFileObject == null) {
				super.getJavaFileForInput(location, className, kind);
			}
			return javaFileObject;
		}

		@Override
		public JavaFileObject getJavaFileForOutput(Location location, String qualifiedClassName,
				JavaFileObject.Kind kind, FileObject sibling) throws IOException {
			JavaFileObject javaFileObject = new MyJavaFileObject(qualifiedClassName, kind);
			fileObjects.put(qualifiedClassName, javaFileObject);
			return javaFileObject;
		}
	}
}
