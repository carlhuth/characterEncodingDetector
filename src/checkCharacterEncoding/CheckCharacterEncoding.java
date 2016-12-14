/**
 * 
 */
package checkCharacterEncoding;
import org.mozilla.universalchardet.UniversalDetector;
/**
 * @author huthc
 *
 */
public class CheckCharacterEncoding {

	public static void main(String[] args) throws java.io.IOException {
		byte[] buf = new byte[4096];
		if ( args == null || args[0] == null || args[0].length() == 0 ){
			System.out.println( "Please include a filename (example - checkCharacterEncoding [filename])" );
			return;
		}
		String fileName = args[0];
		final java.io.FileInputStream fis = new java.io.FileInputStream(fileName);
		final UniversalDetector detector = new UniversalDetector(null);
		int nread;
		while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
			detector.handleData(buf, 0, nread);
		}
		detector.dataEnd();
		
		final String encoding = detector.getDetectedCharset();
		final String msg = (encoding != null)?
				"Detected encoding = " + encoding : "No encoding detected.";
		System.out.println(msg);
		detector.reset();
		fis.close();
	}
}