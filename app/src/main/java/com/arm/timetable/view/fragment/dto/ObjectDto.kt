package asia.teqnological.bittoco.data.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by apple on 10/14/15.
 */
class ObjectDto<T> : BaseApiDto() {
    @SerializedName("data")
    var data: T? = null
}
