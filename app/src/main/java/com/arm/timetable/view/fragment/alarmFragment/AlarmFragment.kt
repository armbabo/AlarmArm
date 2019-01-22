package com.arm.timetable.view.fragment.alarmFragment

import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arm.timetable.BuildConfig
import com.arm.timetable.R
import com.arm.timetable.base.BaseFragment
import com.arm.timetable.extension.print
import com.arm.timetable.view.fragment.data.User
import com.arm.timetable.view.fragment.services.UsersNewService
import com.google.gson.GsonBuilder
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.zipWith
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.alarm_fragment.*
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

class AlarmFragment : BaseFragment<AlarmViewModel,AlarmView>(), AlarmView {

    private val clickOne : PublishSubject<String> = PublishSubject.create()
    private val clickTwo : PublishSubject<Int> = PublishSubject.create()

    private val viewModel: AlarmViewModel = get(parameters = { parametersOf(this) })
    private val alarmAdapter = AlarmAdapter()
    private var token : String = "mzPGboMrAgJFTeDmaVMSjWte"
    companion object {
        fun newInstance() = AlarmFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        alarmAdapter.setListItem(mutableListOf(Alarm(1),Alarm(1),Alarm(1)))
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity).apply {
                orientation = RecyclerView.VERTICAL
                reverseLayout = false
            }
            addItemDecoration(
                DividerItemDecoration(
                    activity,
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = alarmAdapter
        }

        val okHttpClient = OkHttpClient.Builder().cache(Cache(activity?.application!!.cacheDir, 10 * 1024 * 1024))
            .addInterceptor(LoggingInterceptor.Builder()
                .loggable(BuildConfig.DEBUG)
                .log(Platform.INFO)
                .setLevel(Level.BODY)
                .request("Request")
                .response("Response")
                .build())
            .addInterceptor(Interceptor { chain ->
                val request = chain.request()
                val newRequest = request.newBuilder()
                    .header("X-Bittoco-Token", token)
                    .build()
                return@Interceptor chain.proceed(newRequest)
            }).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://dev.bittoco.net/v1/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        val userService = retrofit.create(UsersNewService::class.java)

        //fake token
        btnFake.setOnClickListener { token = "mzPGboMrAgJFTeDmaVMSjWta" }
        //reset
        btnReset.setOnClickListener { token = "mzPGboMrAgJFTeDmaVMSjWte" }

        btnGetNewToken.setOnClickListener {
            getNewToken(userService).subscribe()
        }
        btnCallApi.setOnClickListener {
//            userService.getQuestion()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnNext {
//                    it.status.print()
//                }
//                .subscribe({
//                    Toast.makeText(activity,"ok",Toast.LENGTH_SHORT).show()
//                }) { t -> t.printStackTrace() }

//            runOk(userService)
            runTest(userService)

//            Observable.just(1,2,3,4)
////                .subscribeOn(Schedulers.io())
//                .doOnNext { "call api $it".print() }
//                .doOnNext {
//                    if ("stauts" != "200") {
//                        throw Throwable("401")
//                    }
//                }
//                    //có lỗi thì bay zô
//                .retryWhen { error ->
//                    val count = AtomicInteger()
//                    error
////                        .takeWhile({e -> counter.getAndIncrement() != 3})
//                        .concatMap { _ ->
//                        Observable.timer(2, TimeUnit.SECONDS)
//                            .concatMap { Observable.just("")
//                                .doOnNext { "get new token".print() }
//                                .doOnNext { "save token" }
//                            }
////                            .doOnNext { count.incrementAndGet().print("delay: ") }
////                            .doOnNext { throw Throwable("test error") }
//                    }
//                }
//                .doOnNext { "if error -> retry" }
//                .doOnNext { "retry 3 lan" }
//                .doOnNext { "het 3 lan -> error stop" }
//                .concatMap { Observable.just(it).doOnNext { Timber.e("concat "+it.toString()) } }
//                .doOnNext { Timber.e("done concat") }
//                .onErrorReturn { ex ->
//                    ex.printStackTrace()
//                    0
//                }
//                .subscribe()
        }
        btnAddAlarm.setOnClickListener {
//            goToFragment(AddAlarm.newInstance())

//                .connectTimeout(3, TimeUnit.MINUTES)
//                .readTimeout(3, TimeUnit.MINUTES)
//                .writeTimeout(3, TimeUnit.MINUTES)
        }
    }

    class ErrorToken(message: String?) : Throwable(message)

    /**
     * @param conditionReFresh điều kiện để reload
     * @param times số lần thử lại
     * @param delayTime thời gian chờ gọi, MILLISECONDS
     * @param obGetNewValue Observable để gọi value nào cần dc update để reload lại
     */
    fun <T> Observable<T>.reloadWithNewValue(
        conditionReFresh: (T) -> Boolean
        , times: Int
        , delayTime: Long
        , obGetNewValue: () -> Observable<*>
    ): Observable<T> {
        return this.doOnNext {
            if (conditionReFresh.invoke(it)) {
                "het han token".print()
                throw Throwable("error token")
            }
        }
            .retryWhen {
                it.zipWith(Observable.range(1, times))//số lần thử lại
                    .concatMap { pair ->
                        Observable.timer(delayTime, TimeUnit.MILLISECONDS)// thời gian chờ
                            .concatMap {
                                obGetNewValue.invoke()
                                    .doOnNext { throw Throwable("new token error") }
                                    .subscribeOn(Schedulers.io())
                            }
//                            .doOnNext { "new token".print("lan ${pair.second}") }
                            .doOnNext { if (pair.second >= times) throw ErrorToken("max count") }
                    }
            }
//            .onErrorReturn { t ->
//                if (t.message == "max count") {
//                    "retry ca $times lan".print()
//                    return@onErrorReturn data.invoke(blockingFirst())
//                }
//                else {
//                    return@onErrorReturn data.invoke(blockingFirst())
//                }
//            }
    }

    private fun runTest(userService: UsersNewService) {
        userService.getQuestion()
            .subscribeOn(Schedulers.io())
            .doOnNext { "----call api----".print() }
            .reloadWithNewValue({it.status == 401},3,2000,{
                getNewToken(userService)
            })
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                if (it is ErrorToken) {
                    "ErrorToken qua luot".print()
                }
                else {
                    "error api".print()
                }
            }
            .subscribe({
                "finish".print()
            },{ t ->
                t.printStackTrace()
                "xong nhung co loi".print()
            }
                ,{ "onComplete".print() })
    }

    private fun runOk(userService: UsersNewService) {
        userService.getQuestion()
            .subscribeOn(Schedulers.io())
            .doOnNext { "call api".print() }
            .concatMap {
                it.status.print()
                if (it.status == 401) {
                    getNewToken(userService).doOnNext {
                        token = it
                        throw Throwable("401")
                    }
                }
                else {
                    Observable.just(it)
                }
            }
//                .retryWhen { Observable.timer(2,TimeUnit.SECONDS)
//                    .concatMap { Observable.just("").doOnNext { "get new token".print() } }}
////                    .switchMap { getNewToken(userService) }.doOnNext { token = it } }
            .retry { t1, t2 ->
                SystemClock.sleep(2000)
                return@retry t1 < 5
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Toast.makeText(activity,"ok",Toast.LENGTH_SHORT).show()
            }) { t -> t.printStackTrace() }
    }

    fun getNewToken(userService: UsersNewService): Observable<String> {
        return userService.signIn(User(email = "arm6@aa.bb",password = "11223344"))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.data!!.auth_token }
    }

    override fun render(alarmViewState: AlarmViewState) {
        Timber.e(alarmViewState.toString())
    }

    override fun navigation(navigationAlarm: AlarmView.NavigationAlarm) {
        when(navigationAlarm) {
            AlarmView.NavigationAlarm.ONE -> {
                Timber.e("1")
            }
            else -> {
                Timber.e("2")
            }
        }
    }

    override fun clickOne(): Maybe<String> {
        return clickOne.firstElement()
    }

    override fun clickTwo(): Maybe<Int> {
        return clickTwo.firstElement()
    }

    override fun createViewModel(): AlarmViewModel {
        return viewModel
    }

    override fun layoutResource(): Int {
       return R.layout.alarm_fragment
    }
}
