package com.pod.airbnb.navigation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.pod.airbnb.HostingActivity
import com.pod.airbnb.R
import com.pod.airbnb.navigation.viewpage.ViewpageAdapt
import com.pod.airbnb.navigation.viewpage.ViewpageAdapter
import com.pod.airbnb.navigation.viewpage.ViewpagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.view.*

class SearchFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_search, container, false)
        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.pageWidth)
        val screenWidth = resources.displayMetrics.widthPixels
        val offsetPx = screenWidth - pageMarginPx - pagerWidth

        // search 프래그먼트의 첫번째 뷰페이저
        view.viewPager_onBoarding.setPageTransformer { page, position ->
            page.translationX = position * -offsetPx
        }

        view.viewPager_onBoarding.offscreenPageLimit = 1
        view.viewPager_onBoarding.adapter = ViewpageAdapter(getImgList(), getTitleList(), getContentList())
        view.viewPager_onBoarding.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        // search 프래그먼트의 두번째 뷰페이저
        view.viewPager_onBoarding_1.setPageTransformer { page, position ->
            page.translationX = position * -offsetPx
        }

        view.viewPager_onBoarding_1.offscreenPageLimit = 1
        view.viewPager_onBoarding_1.adapter = ViewpagerAdapter(getImgList_1(), getContentList_1())
        view.viewPager_onBoarding_1.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        // search 프래그먼트의 세번째 뷰페이저
        view.viewPager_onBoarding_2.setPageTransformer { page, position ->
            page.translationX = position * -offsetPx
        }

        view.viewPager_onBoarding_2.offscreenPageLimit = 1
        view.viewPager_onBoarding_2.adapter = ViewpageAdapt(getImgList_2(), getTitleList_2(), getContentList_2())
        view.viewPager_onBoarding_2.orientation = ViewPager2.ORIENTATION_HORIZONTAL



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        search_btn.setOnClickListener {
            val fragment: Fragment = TravelFragment()
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.main_content, fragment)?.commit()

        }

        flex_search.setOnClickListener {
            startActivity(Intent(context, HostingActivity::class.java))
        }

        viewPager_onBoarding.setOnClickListener {
            startActivity(Intent(context, HostingActivity::class.java))
        }

        viewPager_onBoarding_1.setOnClickListener {
            startActivity(Intent(context, HostingActivity::class.java))
        }

        viewPager_onBoarding_2.setOnClickListener {
            startActivity(Intent(context, HostingActivity::class.java))
        }

    }

    private fun getImgList(): ArrayList<Int>{
        return arrayListOf<Int>(R.drawable.seoul, R.drawable.busan, R.drawable.yangyang, R.drawable.sokcho, R.drawable.daegu, R.drawable.jeju)
    }

    private fun getTitleList(): ArrayList<String>{
        return arrayListOf<String>("서울", "부산", "양양군", "속초시", "대구", "제주도(Jeju)")
    }

    private fun getContentList(): ArrayList<String>{
        return arrayListOf<String>("차로 30분 거리", "차로 4.5시간 거리", "차로 3.5시간 거리", "차로 4시간 거리", "차로 4시간 거리", "차로 13시간 거리")
    }

    private fun getImgList_1(): ArrayList<Int>{
        return arrayListOf<Int>(R.drawable.nature, R.drawable.unique, R.drawable.home, R.drawable.pet)
    }

    private fun getContentList_1(): ArrayList<String>{
        return arrayListOf<String>("자연생활을 만끽할 수 있는 숙소", "독특한 공간", "집 전체", "반려동물 동반 가능")
    }

    private fun getImgList_2(): ArrayList<Int>{
        return arrayListOf<Int>(R.drawable.experience, R.drawable.online, R.drawable.recommend)
    }

    private fun getTitleList_2(): ArrayList<String>{
        return arrayListOf<String>("체험", "온라인 체험", "추천 컬렉션: 여행 본능을 깨우는 체험")
    }

    private fun getContentList_2(): ArrayList<String>{
        return arrayListOf<String>("가까운 곳에서 즐길 수 있는 잊지 못할 체험을 찾아보세요.", "호스트와 실시간으로 소통하면서 액티비티를 즐겨보세요.", "온라인 체험으로 집에서 랜선 여행을 즐기세요.")
    }


}