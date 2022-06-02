package com.example.demo.okhttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import okhttp3.Request
import okhttp3.*
import okio.Utf8
import java.io.File
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private val baseUrl = "https://console-mock.apipost.cn/app/mock/project/a050bb0d-28d9-48fd-c7db-b2953f7f1caf/api/demo/login"
    private val userInterceptor:Interceptor by lazy {
        Interceptor {
            it.proceed(it.request())
        }
    }
    private val client:OkHttpClient by lazy {
        OkHttpClient.Builder()
                .connectTimeout(5000,TimeUnit.MILLISECONDS)
                .addInterceptor(userInterceptor)
                .build()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.form).setOnClickListener { lifecycleScope.launch(IO) { formTest() } }
        findViewById<Button>(R.id.postString).setOnClickListener { lifecycleScope.launch(IO) { postString() }  }
        findViewById<Button>(R.id.postFile).setOnClickListener { lifecycleScope.launch(IO) { postFile() } }
        findViewById<Button>(R.id.getFile).setOnClickListener { lifecycleScope.launch(IO) { getFile() } }
        findViewById<Button>(R.id.form_and_File).setOnClickListener { lifecycleScope.launch(IO){formAndPostFile()}}

    }
    private fun request(vararg requestBody : RequestBody){
        val request = Request.Builder().apply {
            requestBody.forEach {
                post(it)
            }
        }.url(baseUrl).build()
        val response = client.newCall(request).execute()
        printR(response.body()?.bytes())
    }
    private fun requestGet(url:String){
        val request = Request.Builder().get().url(url).build()
        val response = client.newCall(request).execute()
        printR(response.body()?.bytes())
    }
    private fun formTest(){
        val formBodyBuilder:FormBody.Builder = FormBody.Builder()
                .add("username","admin")
                .add("password","123456");//步骤2：配置网络请求body
        request(formBodyBuilder.build())
    }
    private fun postString(){
        val rd = RequestBody.create(MediaType.parse("text/plain;charset=utf-8"),"string used to post to web server!")
        request(rd)
    }
    private fun postFile(){
        val file = File("1.png")
        val rd = RequestBody.create(MediaType.parse("application/octet-stream"),file)
        request(rd)
    }
    private fun getFile(){
        requestGet("https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png")
    }
    private fun formAndPostFile(){
        val formBodyBuilder:FormBody.Builder = FormBody.Builder()
                .add("username","admin")
                .add("password","123456");//步骤2：配置网络请求body
        val file = File("1.png")
        val  rdFile = RequestBody.create(MediaType.parse("application/octet-stream"),  file);
        request(formBodyBuilder.build(),rdFile)
    }
    fun printR(t: ByteArray?){
        System.out.println(t?.let { String(it, Charset.forName("UTF-8")) })
    }
}