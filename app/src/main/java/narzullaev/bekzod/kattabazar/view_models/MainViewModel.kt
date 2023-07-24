package narzullaev.bekzod.kattabazar.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import narzullaev.bekzod.kattabazar.models.GetOffersResDto
import narzullaev.bekzod.kattabazar.repository.NetworkRepository
import narzullaev.bekzod.kattabazar.util.ResApis
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: NetworkRepository
) : ViewModel() {

    val offerListRes = MutableLiveData<ResApis<GetOffersResDto>>(ResApis.Loading())

    fun getOffers() {
        viewModelScope.launch {
            try {
                repository.getOffers().also {
                    if (it.isSuccessful) {
                        offerListRes.postValue(ResApis.Success(it.body()!!))
                    } else {
                        offerListRes.postValue(ResApis.Error(it.errorBody()?.string() ?: ""))
                    }
                }
            } catch (e: Exception) {
                offerListRes.postValue(ResApis.Error(e.message ?: "unknown error"))
            }
        }
    }
}