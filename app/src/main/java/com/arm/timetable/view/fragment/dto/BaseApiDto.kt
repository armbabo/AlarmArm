package asia.teqnological.bittoco.data.dto

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by apple on 10/8/15.
 */
open class BaseApiDto {
    @SerializedName("current_time")
    var currentTime: Date? = null
    @SerializedName("status")
    var status: Int = 0
    @SerializedName("message", alternate = ["errors"])
    var message: String? = null

    val isSuccess: Boolean
        get() {
            if (status == 0) {
                return false
            }
            return if (status == STATUS_SUCCESS_201 || status == STATUS_SUCCESS_200){
                return true
            }
            else false
        }

    companion object {
        val STATUS_SUCCESS_201 = 201
        val STATUS_SUCCESS_200 = 200
    }
}
