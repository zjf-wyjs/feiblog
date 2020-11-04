package plus.feifei.feiblog.qiniu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Data
public class UploadFileUtil {
    @Value("${feiblog.qiniu.AccessKey}")
    private String accessKey;

    @Value("${feiblog.qiniu.SecretKey}")
    private String secretKey;

    @Value("${feiblog.qiniu.Bucket}")
    private String bucket;
    @Value("${feiblog.qiniu.BucketUrl}")
    private String bucketUrl;

    /**
     * 保存图片
     * @param file
     * @return
     * @throws Exception
     */
    public String save(MultipartFile file) throws Exception {
        Configuration configuration = new Configuration(Region.huanan());
        configuration.useHttpsDomains=false;
        UploadManager uploadManager = new UploadManager(configuration);

        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = file.getOriginalFilename();
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        Response response = uploadManager.put(file.getInputStream(),key,upToken,null, null);
        //解析上传成功的结果
        JSONObject jsonObject = JSON.parseObject(response.bodyString());
        System.out.println(jsonObject.toJSONString());
        return jsonObject.getString("key");

    }
    /**
     * 删除七牛云图片
     */

}
