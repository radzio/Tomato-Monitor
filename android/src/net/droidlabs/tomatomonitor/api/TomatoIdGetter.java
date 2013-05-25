package api;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Radek Piekarz
 * Date: 07.07.12
 * Time: 11:16
 */
public class TomatoIdGetter
{

    private static String regexp = "(?i)(_http_id=)(.+?)('></script>)";
    private static Pattern pattern = Pattern.compile(regexp);

    /**
     *
     * @param content
     * @return string
     */
    public static String getId(String content)
    {
         Matcher matches = pattern.matcher(content);
         matches.find();
         return matches.group(2);
    }
}
