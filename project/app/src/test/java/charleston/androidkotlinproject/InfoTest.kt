package charleston.androidkotlinproject

import charleston.androidkotlinproject.data.domain.Info
import charleston.androidkotlinproject.data.remote.features.info.InfoManager
import io.reactivex.observers.TestObserver
import io.reactivex.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Test
import javax.inject.Inject
import kotlin.test.assertTrue

/**
 * Created by charleston on 20/11/17.
 */
class InfoTest {

    @Inject
    lateinit var manager: InfoManager

    @Before
    fun setup() {
        DaggerTestComponent.builder().build().inject(this)
    }

    @Test
    fun testList() {
        val testSubscriber = TestObserver<List<Info>>()

        manager.findAll().subscribe(testSubscriber)

        testSubscriber.assertNoErrors()
        testSubscriber.assertNoTimeout()
        testSubscriber.assertSubscribed()

        testSubscriber.assertOf {
            val response = it.values()[0]
            assertTrue { response.isNotEmpty() }
        }
    }

    @Test
    fun testDetail() {
        val id = "5a123cc464754f5c115243d6"

        val testSubscriber = TestObserver<Info>()

        manager.findById(id).subscribe(testSubscriber)

        testSubscriber.assertNoErrors()
        testSubscriber.assertNoTimeout()
        testSubscriber.assertSubscribed()

        testSubscriber.assertOf {
            val response = it.values()[0]
            assertTrue { response != null }
        }
    }
}