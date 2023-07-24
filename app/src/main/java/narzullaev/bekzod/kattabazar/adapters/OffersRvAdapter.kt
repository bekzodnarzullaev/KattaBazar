package narzullaev.bekzod.kattabazar.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import narzullaev.bekzod.kattabazar.databinding.OfferItemBinding
import narzullaev.bekzod.kattabazar.models.GetOffersResDto


class OffersRvAdapter(
    val context: Context,
    var list: List<GetOffersResDto.Offer>
) : RecyclerView.Adapter<OffersRvAdapter.Vh>() {

    inner class Vh(var itemBinding: OfferItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun onBind(model: GetOffersResDto.Offer, position: Int) {
            var name: String = ""
            val nameComp = model.name.split(" ")
            for (i in nameComp.indices) {
                if (i != 0) {
                    name += nameComp[i] + " "
                }
            }

            itemBinding.modelNameTv.text = name
            itemBinding.merchantTv.text = model.merchant
            Glide.with(context).load(model.image.url).into(itemBinding.iv)
            itemBinding.rv.adapter = AttributesRvAdapter(model.attributes)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(
            OfferItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size
}