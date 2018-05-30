package `in`.yeng.user.API

import `in`.yeng.user.Models.Requests.NewsandUpdatesRequest
import `in`.yeng.user.Models.Requests.SyllabusRequest
import `in`.yeng.user.Models.Responses.NewsandUpdatesResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {

    val BASE_URL = "https://www.yengapp.com"

    val client: Retrofit
        get() = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()


    /*

    Execute input blocks with (MutableList<String>, MutableList<String>) argument

    Usage:

    APIClient.getSyllabusList("id") { a, b ->
        process(a, b);   //
    }


    */
    fun getSyllabusList(id: String, func: (MutableList<String>, MutableList<String>) -> Unit) {
        doAsync {
            val syllabusService = APIClient.client.create(SyllabusRequest::class.java)
            val call = syllabusService.getSyllabusList(id)
            val result = call.execute().body()
            val syllabusArray: MutableList<String> = result?.children as MutableList<String>
            val filesArray: MutableList<String> = result?.files as MutableList<String>
            uiThread { func(syllabusArray, filesArray) }
        }
    }

    /*

    Execute input blocks with a List of items

    Usage:

    APIClient.getNews {
        process(it) //'it' is List<NewsandUpdatesResponse>
    }


 */
    fun getNews(func: (List<NewsandUpdatesResponse>) -> Unit) {
        doAsync {
            val NewsService = APIClient.client.create(NewsandUpdatesRequest::class.java)
            val call = NewsService.getNews()
            val result = call.execute().body()
            val newsList = result
            uiThread { newsList?.let { func(newsList) } }
        }
    }
}
