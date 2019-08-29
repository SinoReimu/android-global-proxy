package com.github.megatronking.netbare.sample

import android.util.Log
import com.github.megatronking.netbare.http.*
import com.github.megatronking.netbare.injector.InjectorCallback
import com.github.megatronking.netbare.injector.SimpleHttpInjector
import com.github.megatronking.netbare.stream.BufferStream
import java.nio.ByteBuffer
import java.nio.CharBuffer
import java.nio.charset.Charset
import java.util.*

/**
 * 注入器范例1：替换百度首页logo
 *
 * 启动NetBare服务后，用浏览器App打开百度首页，Logo将会被替换成此sample项目raw目录下的图片。
 * 注意：如果浏览器有图片缓存，记得先把缓存清理掉！
 *
 * @author Megatron King
 * @since 2018/12/30 00:18
 */
class BaiduLogoInjector : SimpleHttpInjector() {

    override fun sniffRequest(request: HttpRequest): Boolean {
        return true
    }


    fun getString(buffer: ByteBuffer): String {
        var charset: Charset? = null
        var charBuffer: CharBuffer? = null
        try {
            charset = Charset.defaultCharset()
            // charBuffer = decoder.decode(buffer);//用这个的话，只能输出来一次结果，第二次显示为空
            charBuffer = charset!!.decode(buffer.asReadOnlyBuffer())
            return charBuffer!!.toString()
        } catch (ex: Exception) {
            ex.printStackTrace()
            return ""
        }

    }
    override fun onRequestInject(header: HttpRequestHeaderPart, callback: InjectorCallback) {
        //header.toBuffer().toString()
        Log.i("tagg", "final package:"  + getString( header.toBuffer()))
        callback.onFinished(header)
    }

    override fun onRequestInject(request: HttpRequest, body: HttpBody, callback: InjectorCallback) {
        callback.onFinished(body)
    }




}