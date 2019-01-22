package asia.teqnological.bittoco.data.dto

import com.google.gson.annotations.SerializedName

open class BaseApi{
    @SerializedName("status")
    var status: String? = null
    @SerializedName("message", alternate = ["errors"])
    var message: String? = null

    val isChecking: Boolean
        get() {
            if (status.equals("0")) {
                return false
            }
            //status 401-001 : need verify, status 200 : verified
            return if (status.equals("401-001") || status.equals("200")){
                return true
            }
            else false
        }
}