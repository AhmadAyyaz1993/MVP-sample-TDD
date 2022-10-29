package evonative.app.com.sadapaytest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.GsonBuilder
import evonative.app.com.sadapaytest.api.ReposApi
import evonative.app.com.sadapaytest.data.repository.ReposRepositroy
import evonative.app.com.sadapaytest.utils.MockResponseFile
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@RunWith(JUnit4::class)
class ApiTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private val server = MockWebServer()
    private lateinit var repository: ReposRepositroy
    private lateinit var mockedResponse: String
    private val gson = GsonBuilder()
        .setLenient()
        .create()
    @Before
    fun init() {
        server.start(8000)
        val BASE_URL = server.url("/").toString()

        val service = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build().create(ReposApi::class.java)
        repository = ReposRepositroy(service)
    }
    @Test
    fun Endpoint_Not_Exist_Should_Not_Return_Data_And_Return_Error(){

        server.enqueue(
            MockResponse()
                .setResponseCode(404)
        )
        val response = runBlocking { repository.fetchRepos() }
        val code = response.code
        Assert.assertTrue(code == 404)
        Assert.assertNull(response.data)
        Assert.assertNotNull(response.message)
    }

    @Test
    fun Server_Down_Should_Not_Return_Data_And_Return_Error(){

        server.enqueue(
            MockResponse()
                .setResponseCode(500)
        )
        val response = runBlocking { repository.fetchRepos() }
        val code = response.code
        Assert.assertTrue(code == 500)
        Assert.assertNull(response.data)
        Assert.assertNotNull(response.message)
    }

    @Test
    fun Server_Returning_No_Content_Should_Show_Error() {

        server.enqueue(
            MockResponse()
                .setResponseCode(204)
        )
        val response = runBlocking { repository.fetchRepos() }
        val code = response.code
        Assert.assertTrue(code == 204)
        Assert.assertNull(response.data)
        Assert.assertNotNull(response.message)

    }

    @Test
    fun Server_Returning_Empty_Body_Should_Show_Error() {

        server.enqueue(
            MockResponse()
                .setResponseCode(123)
                .setBody("")
        )
        val response = runBlocking { repository.fetchRepos() }
        val code = response.code
        Assert.assertTrue(code == 123)
        Assert.assertNull(response.data)
        Assert.assertNotNull(response.message)

    }


    @Test
    fun Server_Returning_Success_With_Failure_Message(){
        mockedResponse = MockResponseFile("reposApi/success_with_failure_message.json").content
        server.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(mockedResponse)
        )
        val response = runBlocking { repository.fetchRepos() }
        val code = response.code

        Assert.assertTrue(code == 200)
        Assert.assertNull(response.data)
        Assert.assertNotNull(response.message)

    }

    @Test
    fun Server_Returning_Success_With_0_Items(){
        mockedResponse = MockResponseFile("reposApi/success_With_0_Items.json").content
        server.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(mockedResponse)
        )
        val response = runBlocking { repository.fetchRepos() }
        val code = response.code

        Assert.assertTrue(code == 200)
        Assert.assertNotNull(response.data)
        Assert.assertTrue(response.data!!.total_count == 0)
        Assert.assertTrue(response.data!!.items.size == 0)

    }

    @Test
    fun Server_Returning_Success_With_Items_Greater_Than_0(){
        mockedResponse = MockResponseFile("reposApi/success_Greater_Than_0.json").content
        server.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(mockedResponse)
        )
        val response = runBlocking { repository.fetchRepos() }
        val code = response.code

        Assert.assertTrue(code == 200)
        Assert.assertNotNull(response.data)
        Assert.assertTrue(response.data!!.total_count > 0)
        Assert.assertTrue(response.data!!.items.size > 0)

    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}

