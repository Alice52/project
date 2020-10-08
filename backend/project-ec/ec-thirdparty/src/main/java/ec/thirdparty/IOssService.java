package ec.thirdparty;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author zack <br>
 * @create 2020-10-11 11:28 <br>
 * @project project-ec <br>
 */
public interface IOssService {

  /**
   * Upload file to oss server directly.
   *
   * @param name
   * @param in
   * @return uri
   */
  @Deprecated
  String upload(String name, InputStream in);

  /**
   * Get policy and signature info, then send post request to host with signature info, <br>
   * it will put object to oss.
   *
   * @return Map contains signature info for front end upload.
   */
  Map<String, String> signature() throws UnsupportedEncodingException;
}
