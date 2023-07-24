package narzullaev.bekzod.kattabazar

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import narzullaev.bekzod.kattabazar.adapters.OffersRvAdapter
import narzullaev.bekzod.kattabazar.databinding.ActivityMainBinding
import narzullaev.bekzod.kattabazar.models.GetOffersResDto
import narzullaev.bekzod.kattabazar.util.ResApis
import narzullaev.bekzod.kattabazar.view_models.MainViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var list: ArrayList<GetOffersResDto.Offer>
    private lateinit var adapter: OffersRvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        setViewModelListener()
    }

    private fun init() {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]


        //initialize list and adapter
        list = ArrayList()
        adapter = OffersRvAdapter(this, list)
        binding.rv.adapter = adapter

        //initial request
        viewModel.getOffers()
    }

    private fun setViewModelListener() {
        viewModel.offerListRes.observe(this) {
            when(it){
                is ResApis.Success -> {
                    Log.d("TAG", "setViewModelListener: ${it.data.offers}")
                    list = ArrayList(it.data.offers)
                    adapter.list = list
                    adapter.notifyDataSetChanged()
                }
                is ResApis.Error -> {
                    Log.d("TAG", "setViewModelListener: ${it.message}")
                }
                else -> Unit
            }
        }
    }
}