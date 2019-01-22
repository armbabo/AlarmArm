package com.arm.timetable.view.fragment.data

import com.google.gson.annotations.SerializedName

/**
 * Created by Admin on 1/20/2018.
 */
//"status": "200",
//"message": "OK",
//"data": [
//{
//    "id": 10,
//    "content": "仮想通貨について知っていますか？？",
//    "q_type": "main",
//    "answers": [
//    {
//        "id": 25,
//        "content": "知らない"
//    },
//    {
//        "id": 26,
//        "content": "知ってる"
//    }
//    ],
//    "image_url": "https://s3-ap-northeast-1.amazonaws.com/bittoco.common/robo/image_robo_01.png",
//    "total_question": 5
//}
//]
//}
data class QuestionAnswer(val rank: Double? = 0.0,
                          @SerializedName("id") val id: Int? = -1,
                          @SerializedName("total_question") val total_question: Int? = -1,
                          @SerializedName("content") val content: String? = "",
                          @SerializedName("q_type") val q_type: String? = "",
                          @SerializedName("image_url") val image_url: String? = "") {

}