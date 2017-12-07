package ops.inventory.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Various utilities for processing http stream data that can be replaced
 * some library somewhere but whatever I could not find them.
 */
public class HttpStringUtils {
    private static final int LINEFEED = 13;
    private static final int NEWLINE = 10;
    
    /**
     * From the given stream, returns the characters up to but not including 
     * LINEFEED/NEWLINE; either of those alone are not considered as line-ends.
     * 
     * @param is input
     * @return the line
     * @throws IOException
     */
    public static String readLine(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        int next;
        boolean linefeed = false;
        while ((next=is.read()) != -1) {
            if (next==LINEFEED) {
                linefeed = true;
            }
            else if (next==NEWLINE && linefeed) {
                break;
            }
            else {
                if (linefeed) {
                    sb.appendCodePoint(LINEFEED);
                    linefeed = false;
                }
                sb.appendCodePoint(next);
            }
        }
        return sb.toString();
    }
    
    /**
     * Copy chars from the giving input stream to the given output stream, stopping when <code>until</code>
     * is found on the input stream. The <code>until</code> is not written to the output stream.
     * @param is source
     * @param collect target
     * @param until terminating string
     * @return number of chars written to <code>collect</code>
     * @throws IOException
     */
    static int readUntil(InputStream is, ByteArrayOutputStream collect, String until) throws IOException {
        collect.reset();
        int nextChar;
        int matchPos = 0;
        while ((nextChar = is.read()) != -1) {
            if (nextChar == until.codePointAt(matchPos)) {
                if (++matchPos >= until.length()) {
                    matchPos = 0;
                    break;  // terminating string is matched; we're done
                }
            }
            else {
                if (matchPos > 0) {  // false marker! Need to reprocess chars
                    is.reset();
                    nextChar = is.read();  // will be be until.charAt(0);
                    matchPos = 0;
                }
                is.mark(until.length());
                collect.write(nextChar);
            }
        }
        // Empty the stream of any pending match chars
        for (int i=0; i<matchPos; i++) {
            collect.write(is.read());
        }
        return collect.size();
    }
    
    /**
     * Returns value as in "key=value" which is embedded in the given text line,
     * ignoring spaces and false suffixes.
     * @param line to be searched for key=value pairs
     * @param key to match
     * @return value if key found in <code>line</code>
     */
    public static String valueOfKey(String line, String key) {
        int pos = 0;
        
        if (line==null || key==null) return null;
        
        while (true) {
            pos = line.indexOf(key,pos);
            if (pos<0) return null;
            if (pos>0 && Character.isLetterOrDigit(line.charAt(pos-1))) {pos++;continue;}
            pos = line.indexOf('=', pos);
            if (pos >= 0) {
                pos++;
                int endx = line.indexOf(';',pos);
                if (endx < 0) endx = line.length();
                return dequote(line.substring(pos,endx).trim());
            }
        }
    }
    
    private static String dequote(String s) {
        if (s != null) {
            int len = s.length();
            if (len>1 && s.charAt(0) == '"' && s.charAt(len-1) == '"') {
                s = s.substring(1,len-1);
            }
        }
        return s;
    }
    
    /**
     * Copy characters from the given input to output streams, discard terminating characters and determine 
     * if there are more parts in input stream that remain to be processed.
     * @param is source
     * @param boundary terminating marker
     * @param os destination
     * @return true iff there are more parts
     * @throws Exception
     */
    public static boolean ingestNextBodyPart(InputStream is, String boundary, ByteArrayOutputStream os) throws Exception {
        /*int count = */readUntil(is,os,boundary);
        int c1 = is.read();
        int c2 = is.read();
        if (c1=='-' && c2=='-') {  // Per HTTP spec the last boundary ends with these
            is.close();
            return false;
        }
        return true; // else assume read chars are \r\n
    }
    
    /**
     * Matches key-value, and excludes any whitespace around the key or value.
     * @param key to match
     * @param kvs list of strings each of form "foo=bar", " foo =  bar  ", etc.
     * @return
     */
    public static String readKeyValue(String key, String[]kvs) {
        for (String kv: kvs) {
            int eqx = kv.indexOf('=');
            if (eqx > 0) {
                String x = kv.substring(0,eqx).trim();
                if (key.equalsIgnoreCase(x)) {
                    return kv.substring(eqx+1).trim();
                }
            }
        }
        return null;
    }
}
