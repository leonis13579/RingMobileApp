package ru.realityfamily.ringapp.Network;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import ru.realityfamily.ringapp.Models.CloudObject;

public interface RestController {

    @Headers({
            "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiI1ZmNmNDFhZDhmMDljMDc4ZWNlMWYxZDEiLCJzdWIiOiI1ZmJkZWYxMjhmMDljMDg5NzRkZWVjM2UiLCJncnAiOiI1ZmJkZWYxMjhmMDljMDdkZDNkZWVjM2QiLCJsaWMiOnRydWUsInVzZyI6ImFwaSIsImZ1bGwiOmZhbHNlLCJyaWdodHMiOjEuNSwiaWF0IjoxNjA3NDE4Mjg1LCJleHAiOjE2MDk5NjY4MDB9.vrrhk-wh97B98iYiAcDoYfDEZziyFQ-OVVWXc1JqIck",
            "Content-type: application/json"
    })
    @GET("/api/v1/objects/")
    Call<List<CloudObject>> getAllObjects();

    @Headers({
            "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiI1ZmNmNDFhZDhmMDljMDc4ZWNlMWYxZDEiLCJzdWIiOiI1ZmJkZWYxMjhmMDljMDg5NzRkZWVjM2UiLCJncnAiOiI1ZmJkZWYxMjhmMDljMDdkZDNkZWVjM2QiLCJsaWMiOnRydWUsInVzZyI6ImFwaSIsImZ1bGwiOmZhbHNlLCJyaWdodHMiOjEuNSwiaWF0IjoxNjA3NDE4Mjg1LCJleHAiOjE2MDk5NjY4MDB9.vrrhk-wh97B98iYiAcDoYfDEZziyFQ-OVVWXc1JqIck",
            "Content-type: application/json"
    })
    @POST("/api/v1/objects/{id}/commands/dynamic-on")
    Call<ResponseBody> ringOn(@Path("id") String id);

    @Headers({
            "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiI1ZmNmNDFhZDhmMDljMDc4ZWNlMWYxZDEiLCJzdWIiOiI1ZmJkZWYxMjhmMDljMDg5NzRkZWVjM2UiLCJncnAiOiI1ZmJkZWYxMjhmMDljMDdkZDNkZWVjM2QiLCJsaWMiOnRydWUsInVzZyI6ImFwaSIsImZ1bGwiOmZhbHNlLCJyaWdodHMiOjEuNSwiaWF0IjoxNjA3NDE4Mjg1LCJleHAiOjE2MDk5NjY4MDB9.vrrhk-wh97B98iYiAcDoYfDEZziyFQ-OVVWXc1JqIck",
            "Content-type: application/json"
    })
    @POST("/api/v1/objects/{id}/commands/dynamic-off")
    Call<ResponseBody> ringOff(@Path("id") String id);

    @Headers({
            "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiI1ZmNmNDFhZDhmMDljMDc4ZWNlMWYxZDEiLCJzdWIiOiI1ZmJkZWYxMjhmMDljMDg5NzRkZWVjM2UiLCJncnAiOiI1ZmJkZWYxMjhmMDljMDdkZDNkZWVjM2QiLCJsaWMiOnRydWUsInVzZyI6ImFwaSIsImZ1bGwiOmZhbHNlLCJyaWdodHMiOjEuNSwiaWF0IjoxNjA3NDE4Mjg1LCJleHAiOjE2MDk5NjY4MDB9.vrrhk-wh97B98iYiAcDoYfDEZziyFQ-OVVWXc1JqIck",
            "Content-type: application/json"
    })
    @POST("/api/v1/objects/{id}/commands/notify-on")
    Call<ResponseBody> notifyOn(@Path("id") String id);

    @Headers({
            "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiI1ZmNmNDFhZDhmMDljMDc4ZWNlMWYxZDEiLCJzdWIiOiI1ZmJkZWYxMjhmMDljMDg5NzRkZWVjM2UiLCJncnAiOiI1ZmJkZWYxMjhmMDljMDdkZDNkZWVjM2QiLCJsaWMiOnRydWUsInVzZyI6ImFwaSIsImZ1bGwiOmZhbHNlLCJyaWdodHMiOjEuNSwiaWF0IjoxNjA3NDE4Mjg1LCJleHAiOjE2MDk5NjY4MDB9.vrrhk-wh97B98iYiAcDoYfDEZziyFQ-OVVWXc1JqIck",
            "Content-type: application/json"
    })
    @POST("/api/v1/objects/{id}/commands/notify-off")
    Call<ResponseBody> notifyOff(@Path("id") String id);
}
