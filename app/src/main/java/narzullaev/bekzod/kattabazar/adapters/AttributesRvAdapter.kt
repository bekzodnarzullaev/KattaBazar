package narzullaev.bekzod.kattabazar.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import narzullaev.bekzod.kattabazar.databinding.AttributeItemBinding
import narzullaev.bekzod.kattabazar.models.GetOffersResDto


class AttributesRvAdapter(
    val list: List<GetOffersResDto.Attribute>
) : RecyclerView.Adapter<AttributesRvAdapter.Vh>() {

    inner class Vh(var itemBinding: AttributeItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(model: GetOffersResDto.Attribute, position: Int) {
            itemBinding.nameTv.text = model.name + ": "
            itemBinding.attTv.text = model.value
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(
            AttributeItemBinding.inflate(
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