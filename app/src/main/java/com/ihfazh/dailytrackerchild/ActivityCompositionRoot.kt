package com.ihfazh.dailytrackerchild

import android.accounts.AccountManager
import android.app.Application
import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import com.ihfazh.dailytrackerchild.remote.ActualClient
import com.ihfazh.dailytrackerchild.remote.TokenHeader
import com.ihfazh.dailytrackerchild.utils.ChildrenCache
import com.ihfazh.dailytrackerchild.utils.DateProvider
import com.ihfazh.dailytrackerchild.utils.TokenCache
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import java.util.Locale

class ActivityCompositionRoot(context: Application) {
    val childrenCache: ChildrenCache = ChildrenCache(context.getSharedPreferences("childrenCache", Context.MODE_PRIVATE))
    val tokenCacheUtil = TokenCache()
    val accountManager = AccountManager.get(context)

    val dateProvider = DateProvider()


    private val ktor = HttpClient(OkHttp){

        install(HttpTimeout){
            socketTimeoutMillis = 120_000
        }

        install(ContentNegotiation){
            json(Json{
                ignoreUnknownKeys = true
                encodeDefaults = true
                isLenient = true
                allowSpecialFloatingPointValues = true
                allowStructuredMapKeys = true
                prettyPrint = false
                useArrayPolymorphism = false
            })
        }

        install(TokenHeader){
            tokenCache = tokenCacheUtil
        }
    }

    val client = ActualClient(ktor, tokenCacheUtil)
//    val client: DummyClient = DummyClient()

    val textToSpeech: TextToSpeech by lazy {
        TextToSpeech(context){
            if (it == TextToSpeech.SUCCESS)
                textToSpeech.language = Locale("in_ID")
            else
                Log.d("CompositionRoot", "text to speech error")
        }
    }

}