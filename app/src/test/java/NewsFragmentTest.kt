import com.mathieukh.tutopsychop13.activity.news.NewsFragment
import org.junit.Test

class NewsFragmentTest {

    @Test
    fun isNewsFragmentSingleton() {
        val n = NewsFragment.newInstance()
        val n2 = NewsFragment.newInstance()
        assert(n == n2)
    }

}