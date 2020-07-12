package utils;

import java.lang.annotation.*;

public class CSVAnnotation {

	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface CSVFileParameters {
		String path();
		String delimiter();
	}
}