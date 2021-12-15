package com.pod.airbnb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pod.airbnb.navigation.model.HostingDTO


// repo에 있는 데이터를 관찰하고 있다가 변경이 되면 mutableData의 값을변경 시켜주는 역할
class ListViewModel: ViewModel() {
    private val repo = Repo()
    fun fetchData(): LiveData<MutableList<HostingDTO>> {
        val mutableData = MutableLiveData<MutableList<HostingDTO>>()
        repo.getData().observeForever {
            mutableData.value = it
        }
        return mutableData
    }
}